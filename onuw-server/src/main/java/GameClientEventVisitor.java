import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.aluminati.onuw.ClientEvent;
import com.aluminati.onuw.Phase;
import com.aluminati.onuw.Player;
import com.aluminati.onuw.Role;

public class GameClientEventVisitor implements ClientEvent.Visitor<Void> {

    private GameStore gameStore;
    private String gameId;
    private String playerId;
    private Runnable broadcastGameState;
    private BiFunction<String, Player, Void> peek;
    private Consumer<String> vote;

    public GameClientEventVisitor(String gameId, String playerId, GameStore gameStore, Runnable broadcastGameState, BiFunction<String, Player, Void> peek, Consumer<String> vote) {
        this.gameId = gameId;
        this.playerId = playerId;
        this.gameStore = gameStore;
        this.broadcastGameState = broadcastGameState;
        this.peek = peek;
        this.vote = vote;
    }

    @Override
    public Void visitUpdateName(String value) {
        gameStore.updatePlayerName(gameId, playerId, value);
        broadcastGameState.run();
        return null;
    }

    @Override
    public Void visitClickPlayer(String selectedPlayerId) {
        if (gameStore.getGamePhase(gameId).equals(Phase.WEREWOLF)
                && gameStore.getPlayer(gameId, playerId).getRole().equals(Role.WEREWOLF)
                && gameStore.getGamePlayers(gameId)
                    .stream()
                    .filter(x -> !x.getId().equals(playerId))
                    .noneMatch(player -> player.getRole().equals(Role.WEREWOLF))
                && !gameStore.isRoleActionUsed(gameId, playerId)) {

            gameStore.getNeutralPlayer(gameId, selectedPlayerId)
                .ifPresent(neutral -> {
                    gameStore.setRoleActionAsUsed(gameId, playerId);
                    peek.apply(playerId, neutral);});    
        } else if (gameStore.getGamePhase(gameId).equals(Phase.SEER)
                && gameStore.getPlayer(gameId, playerId).getRole().equals(Role.SEER)
                && !gameStore.isRoleActionUsed(gameId, playerId)) {

            Player peekee = gameStore.getPlayer(gameId, selectedPlayerId);
            if (peekee != null ) {
                gameStore.setRoleActionAsUsed(gameId, playerId);
                peek.apply(playerId, peekee);
            }
        } else if (gameStore.getGamePhase(gameId).equals(Phase.VOTE)
                && gameStore.getGamePlayers(gameId).stream().anyMatch(p -> p.getId().equals(selectedPlayerId))) {
            gameStore.setVote(gameId, playerId, selectedPlayerId);
            vote.accept(playerId);
        }
        return null;
    }

    @Override
    public Void visitStartGame(com.aluminati.onuw.Void value) {
        List<Role> roles = gameStore.getAvailableRoles(gameId);
        Collections.shuffle(roles);
        List<Player> players = gameStore.getGamePlayers(gameId);
        Map<String, Role> playerRoles = IntStream.range(0, players.size()).boxed()
                .collect(Collectors.toMap(index -> players.get(index).getId(), roles::get));
        gameStore.updatePlayerRoles(gameId, playerRoles);
        IntStream.range(players.size(), roles.size()).boxed()
            .forEach(index -> gameStore.addNeutralPlayer(gameId, roles.get(index)));
        gameStore.updateGamePhase(gameId, Phase.DAY);
        gameStore.setTimeLeftInCurrentRound(gameId, 10);
        broadcastGameState.run();
        return null;
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
}
