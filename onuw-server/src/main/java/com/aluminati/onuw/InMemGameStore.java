package com.aluminati.onuw;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

public class InMemGameStore implements GameStore{

    public final static InMemGameStore INSTANCE = new InMemGameStore();

    //Need to be concurrent safe
    private Map<String, Map<String,Player>> gamePlayers = new ConcurrentHashMap();
    private Map<String, Phase> phaseForGames = new ConcurrentHashMap<>();
    private Map<String, Integer> gamePhaseTimers = new ConcurrentHashMap<>();
    private Map<String, List<RoleType>> availableRoles = new ConcurrentHashMap<>();
    private Map<String, Map<String,Player>> neutralCards = new ConcurrentHashMap<>();
    private Map<String, Map<String, Boolean>> actionsUsed = new ConcurrentHashMap<>();
    private Map<String, Map<String, String>> votes = new ConcurrentHashMap();

    @Override
    public List<Player> getGamePlayers(String gameId) {
        return ImmutableList.copyOf(
            this.gamePlayers.getOrDefault(gameId, ImmutableMap.of())
            .values());
    }

    @Override
    public void addPlayer(String gameId, String playerId, Player player) {
        gamePlayers.get(gameId).put(playerId, player);
    }

    @Override
    public String createNewGame() {
        String gameId = Integer.toString(gamePlayers.size());
        gamePlayers.put(gameId.toString(), new ConcurrentHashMap());
        neutralCards.put(gameId.toString(), new ConcurrentHashMap());
        actionsUsed.put(gameId.toString(), new ConcurrentHashMap());
        votes.put(gameId.toString(), new ConcurrentHashMap());
        availableRoles.put(gameId.toString(), defaultRoles());
        phaseForGames.put(gameId.toString(), Phase.LOBBY);
        gamePhaseTimers.put(gameId, 0);
        return gameId.toString();
    }

    // TODO should move this logic out into another class and just have the store store universal state
    @Override
    public Game getGameStateForPlayer(String gameId, String playerId) {
        Map<String, Player> players = this.gamePlayers.get(gameId);
        List<RoleType> availableRolesForGame = availableRoles.get(gameId);
        Phase currentPhase = phaseForGames.get(gameId);
        Optional<Team> winningTeam = getWinningTeam(gameId);
        return Game.builder()
            .currentPlayer(players.get(playerId))
            .otherPlayers(players.values().stream()
                .filter(player -> !player.getId().equals(playerId))
                .map(player -> 
                    currentPhase.equals(Phase.END) ? player 
                        : Player.builder().from(player).role(RoleType.HIDDEN).votingFor(Optional.empty()).build())
                .collect(Collectors.toList()))
            .currentPhase(currentPhase)
            .neutralCards(neutralCards.get(gameId).values().stream()
                .map(player -> Player.builder().from(player).role(RoleType.HIDDEN).build())
                .collect(Collectors.toList()))
            .availableRoles(availableRolesForGame)
            .timeLeft(getTimeLeftInCurrentRound(gameId))
            .gameId(gameId)
            .build();
    }

    private Optional<Team> getWinningTeam(String gameId) {
        if (getGamePhase(gameId).equals(Phase.END)) {
            return Optional.empty();
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void updateGamePhase(String gameId, Phase phase) {
        this.phaseForGames.put(gameId, phase);
    }

    @Override
    public Player getPlayer(String gameId, String playerId) {
        return gamePlayers.get(gameId).get(playerId);
    }

    @Override
    public void updatePlayerName(String gameId, String playerId, String name) {
        Player oldPlayer = getPlayer(gameId, playerId);
        Player newPlayer = Player.builder().from(oldPlayer).name(name).build();
        updatePlayer(gameId, playerId, newPlayer);
    }

    @Override
    public List<RoleType> getAvailableRoles(String gameId) {
        return availableRoles.get(gameId);
    }

    @Override
    public void updatePlayerRoles(String gameId, Map<String, RoleType> roles) {
        roles.entrySet().stream()
        .forEach(entry -> this.updatePlayer(
            gameId,
            entry.getKey(),
            Player.builder()
                .from(this.getPlayer(gameId, entry.getKey()))
                .role(entry.getValue())
                .build()));
        }

    @Override
    public int getTimeLeftInCurrentRound(String gameId) {
        return gamePhaseTimers.get(gameId);
    }
        
    private void updatePlayer(String gameId, String playerId, Player player) {
        this.gamePlayers.get(gameId).put(playerId, player);
    }

    private List<RoleType> defaultRoles() {
        List<RoleType> def = new ArrayList();
        def.add(RoleType.VILLAGER);
        def.add(RoleType.VILLAGER);
        def.add(RoleType.SEER);
        def.add(RoleType.WEREWOLF);
        def.add(RoleType.WEREWOLF);
        return def;
    }

    @Override
    public void setTimeLeftInCurrentRound(String gameId, int timeLeft) {
        System.out.println("time " + timeLeft);
        gamePhaseTimers.put(gameId, timeLeft);
    }

    @Override
    public Phase getGamePhase(String gameId) {
        return phaseForGames.get(gameId);
    }

    @Override
    public String addNeutralPlayer(String gameId, RoleType role){
        Map<String, Player> neutrals = neutralCards.get(gameId);
        String newId = "n" + neutrals.size();
        neutrals.put(newId, Player.builder().name("neutral").id(newId).role(role).team(Team.HIDDEN).build());
        System.out.println(neutrals);
        System.out.println(neutralCards.get(gameId));
        return newId;
    }

    @Override
    public Optional<Player> getNeutralPlayer(String gameId, String playerId) {
        return Optional.ofNullable(neutralCards.get(gameId).get(playerId));
    }

    @Override
    public void setRoleActionAsUsed(String gameId, String playerId) {
        this.actionsUsed.get(gameId).put(playerId, true);
    }

    @Override
    public boolean isRoleActionUsed(String gameId, String playerId) {
        return this.actionsUsed.get(gameId).getOrDefault(playerId, false);
    }

    @Override
    public void setVote(String gameId, String voterId, String votedId) {
        Map<String, Player> playersInGame = this.gamePlayers.get(gameId);
        Player voter = playersInGame.get(voterId);
        playersInGame.put(voterId, Player.builder().from(voter).votingFor(votedId).build());
    }
}

//         Game gameState = Game.builder()
// .currentPlayer(currentPlayer)
// .otherPlayers(ImmutableList.of())
// .neutralCards(ImmutableList.of())
// .currentPhase(Phase.LOBBY)
// .availableRoles(ImmutableList.of())
// .timeLeft(90)
// .currentVote("")
// .gameId("2")
// .build();