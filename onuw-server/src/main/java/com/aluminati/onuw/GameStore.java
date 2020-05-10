package com.aluminati.onuw;

import java.util.List;
import java.util.Map;
import java.util.Optional;

interface GameStore {

    RoleType getPlayerStartRole(String gameId, String playerId);

    int getTimeLeftInCurrentRound(String gameId);

    Optional<String> getVote(String gameId, String voterId);

    void setTimeLeftInCurrentRound(String gameId, int timeLeft);

    List<Player> getGamePlayers(String gameId);

    void addPlayer(String gameId, String playerId, Player player);

    Player getPlayer(String gameId, String playerId);

    void updatePlayerName(String gameId, String playerId, String name);

    String createNewGame();

    Game getGameStateForPlayer(String gameId, String playerId);

    Phase getGamePhase(String gameId);

    void updateGamePhase(String gameId, Phase phase);

    List<RoleType> getAvailableRoles(String gameId);

    void setPlayerStartRoles(String gameId, Map<String, RoleType> roles);

    void updatePlayerRoles(String gameId, Map<String, RoleType> roles);

    String addNeutralPlayer(String gameId, RoleType role);
    
    Optional<Player> getNeutralPlayer(String gameId, String playerId);

    void setRoleActionAsUsed(String gameId, String playerId);
    
    boolean isRoleActionUsed(String gameId, String playerId);

    void setVote(String gameId, String voterId, Optional<String> votedId);
}