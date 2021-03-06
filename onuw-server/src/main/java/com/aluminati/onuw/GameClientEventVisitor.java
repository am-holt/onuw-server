package com.aluminati.onuw;

import com.aluminati.onuw.actions.ActionType;
import com.aluminati.onuw.actions.SelectPlayersAction;
import com.google.common.collect.ImmutableMap;

import java.lang.Void;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GameClientEventVisitor implements ClientEvent.Visitor<Void> {

    private GameStore gameStore;
    private String gameId;
    private String playerId;
    private Runnable broadcastGameState;
    private BiFunction<String, Message, Void> sendMessage;
    private BiFunction<String, Player, Void> peek;
    private Consumer<String> vote;

    public GameClientEventVisitor(String gameId, String playerId, GameStore gameStore, Runnable broadcastGameState, BiFunction<String, Message, Void> sendMessage, BiFunction<String, Player, Void> peek, Consumer<String> vote) {
        this.gameId = gameId;
        this.playerId = playerId;
        this.gameStore = gameStore;
        this.broadcastGameState = broadcastGameState;
        this.peek = peek;
        this.vote = vote;
        this.sendMessage = sendMessage;
    }

    @Override
    public Void visitUpdateName(String value) {
        gameStore.updatePlayerName(gameId, playerId, value);
        broadcastGameState.run();
        return null;
    }

    @Override
    public Void visitClickPlayer(String selectedPlayerId) {
        Optional<ActionType> playerAction = gameStore.getPlayerAction(gameId, playerId);
        if (gameStore.getGamePhase(gameId).equals(Phase.WEREWOLF)
                && gameStore.getPlayer(gameId, playerId).getRole().equals(RoleType.WEREWOLF)
                && gameStore.getGamePlayers(gameId)
                    .stream()
                    .filter(x -> !x.getId().equals(playerId))
                    .noneMatch(player -> player.getRole().equals(RoleType.WEREWOLF))
                && playerAction.isPresent()
                && !playerAction.get().isActionUsed()) {

            gameStore.getNeutralPlayer(gameId, selectedPlayerId)
                .ifPresent(neutral -> {
                    ((SelectPlayersAction)playerAction.get())
                            .choosePlayer(
                                    neutral.getId(),
                                    1,
                                    ids -> ids.forEach(id -> peek.apply(playerId, neutral)));
                    });
        } else if(gameStore.getGamePhase(gameId).equals(Phase.TROUBLEMAKER)
                && gameStore.getPlayer(gameId, playerId).getRole().equals(RoleType.TROUBLEMAKER)
                && playerAction.isPresent()
                && !playerAction.get().isActionUsed()) {

            Player selected = gameStore.getPlayer(gameId, selectedPlayerId);
            if (selected != null ) {
                ((SelectPlayersAction)playerAction.get())
                        .choosePlayer(
                                selectedPlayerId,
                                2,
                                ids -> swapRoles(playerId, ids));
            }
        } else if(gameStore.getGamePhase(gameId).equals(Phase.ROBBER)
                && gameStore.getPlayer(gameId, playerId).getRole().equals(RoleType.ROBBER)
                && playerAction.isPresent()
                && !playerAction.get().isActionUsed()) {

            Player selected = gameStore.getPlayer(gameId, selectedPlayerId);
            if (selected != null) {
                ((SelectPlayersAction)playerAction.get())
                        .choosePlayer(
                                selectedPlayerId,
                                1,
                                ids -> {ids.add(playerId); swapRoles(playerId, ids);});
            }
        } else if (gameStore.getGamePhase(gameId).equals(Phase.SEER)
                && gameStore.getPlayer(gameId, playerId).getRole().equals(RoleType.SEER)
                && playerAction.isPresent()
                && !playerAction.get().isActionUsed()) {

            Player peekee = gameStore.getPlayer(gameId, selectedPlayerId);
            if (peekee != null ) {
                ((SelectPlayersAction)playerAction.get())
                        .choosePlayer(
                                peekee.getId(),
                                1,
                                ids -> ids.forEach(id -> peek.apply(playerId, peekee)));
            }
        } else if (gameStore.getGamePhase(gameId).equals(Phase.VOTE)
                && gameStore.getGamePlayers(gameId).stream().anyMatch(p -> p.getId().equals(selectedPlayerId))) {
            if (gameStore.getVote(gameId, playerId).isPresent()
                    && gameStore.getVote(gameId, playerId).get().equals(selectedPlayerId)) {
                gameStore.setVote(gameId, playerId, Optional.empty());
            } else {
                gameStore.setVote(gameId, playerId, Optional.of(selectedPlayerId));
            }
            vote.accept(playerId);
        }
        return null;
    }

    private void swapRoles(String playerToNotify, Set<String> ids) {
        if (ids.size() != 2) {
            throw new RuntimeException("A swap must occur between two cards");
        }
        Iterator<String> idIterator = ids.iterator();
        Player player1 = gameStore.getPlayer(gameId, idIterator.next());
        Player player2 = gameStore.getPlayer(gameId, idIterator.next());
        gameStore.updatePlayerRoles(
                gameId,
                ImmutableMap.of(player1.getId(), player2.getRole(), player2.getId(), player1.getRole()));
        sendInfo(playerToNotify, String.format("Swapped players: %s & %s", player1.getName(), player2.getName()));
    }

    @Override
    public Void visitStartGame(com.aluminati.onuw.Void value) {
        List<Player> players = gameStore.getGamePlayers(gameId);
        List<RoleType> roles = getDefaultGameRoles(players.size());
        Collections.shuffle(roles);
        Map<String, RoleType> playerRoles = IntStream.range(0, players.size()).boxed()
                .collect(Collectors.toMap(index -> players.get(index).getId(), roles::get));
        gameStore.setPlayerStartRoles(gameId, playerRoles);
        IntStream.range(players.size(), roles.size()).boxed()
            .forEach(index -> gameStore.addNeutralPlayer(gameId, roles.get(index)));
        gameStore.updateGamePhase(gameId, Phase.DAY);
        gameStore.setTimeLeftInCurrentRound(gameId, 10);
        broadcastGameState.run();
        return null;
    }

    private List<RoleType> getDefaultGameRoles(int size) {
        List<RoleType> def = new ArrayList();
        def.add(RoleType.WEREWOLF);
        def.add(RoleType.VILLAGER);
        def.add(RoleType.VILLAGER);
        def.add(RoleType.SEER);
        def.add(RoleType.TROUBLEMAKER);
        if (size > 3) {
            def.add(RoleType.ROBBER);
        }
        if (size > 4) {
            def.add(RoleType.WEREWOLF);
        }
        if (size > 5) {
            def.add(RoleType.VILLAGER);
        }
        if (size > 6) {
            def.add(RoleType.WEREWOLF);
        }
        return def;
    }

    @Override
    public Void visitChangeVote(String value) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Void visitUnknown(String unknownType) {
        // TODO Auto-generated method stub
        return null;
    }

    private void sendInfo(String playerId, String message) {
        this.sendMessage.apply(playerId, Message.of(message, MessageLevel.INFO));
    }
}
