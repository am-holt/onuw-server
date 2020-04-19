import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import com.aluminati.onuw.Game;
import com.aluminati.onuw.Phase;
import com.aluminati.onuw.Player;
import com.aluminati.onuw.Role;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

public class InMemGameStore implements GameStore{

    //Need to be concurrent safe
    private Map<String, Map<String,Player>> gamePlayers = new ConcurrentHashMap();
    private Map<String, Phase> phaseForGames = new ConcurrentHashMap<>();
    private Map<String, Integer> gamePhaseTimers = new ConcurrentHashMap<>();
    private Map<String, List<Role>> availableRoles = new ConcurrentHashMap<>();
    private Map<String, Map<String,Player>> neutralCards = new ConcurrentHashMap<>();

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
    public String createNewGame(String gameId) {
        //UUID gameId = UUID.randomUUID();
        gamePlayers.put(gameId.toString(), new ConcurrentHashMap());
        neutralCards.put(gameId.toString(), new ConcurrentHashMap());
        availableRoles.put(gameId.toString(), defaultRoles());
        phaseForGames.put(gameId.toString(), Phase.LOBBY);
        gamePhaseTimers.put(gameId, 0);
        return gameId.toString();
    }

    // TODO should move this logic out into another class and just have the store store universal state
    @Override
    public Game getGameStateForPlayer(String gameId, String playerId) {
        Map<String, Player> players = this.gamePlayers.get(gameId);
        List<Role> availableRolesForGame = availableRoles.get(gameId);
        return Game.builder()
            .currentPlayer(players.get(playerId))
            .otherPlayers(players.values().stream()
                .filter(player -> !player.getId().equals(playerId))
                .map(player -> Player.builder().from(player).role(Role.HIDDEN).build())
                .collect(Collectors.toList()))
            .currentPhase(phaseForGames.get(gameId))
            .neutralCards(neutralCards.get(gameId).values().stream()
                .map(player -> Player.builder().from(player).role(Role.HIDDEN).build())
                .collect(Collectors.toList()))
            .availableRoles(availableRolesForGame)
            .timeLeft(getTimeLeftInCurrentRound(gameId))
            .currentVote("")
            .gameId(gameId)
            .build();
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
    public List<Role> getAvailableRoles(String gameId) {
        return availableRoles.get(gameId);
    }

    @Override
    public void updatePlayerRoles(String gameId, Map<String, Role> roles) {
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

    private List<Role> defaultRoles() {
        List<Role> def = new ArrayList();
        def.add(Role.VILLAGER);
        def.add(Role.VILLAGER);
        def.add(Role.VILLAGER);
        def.add(Role.WEREWOLF);
        def.add(Role.WEREWOLF);
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
    public String addNeutralPlayer(String gameId, Role role){
        Map<String, Player> neutrals = neutralCards.get(gameId);
        String newId = "n" + neutrals.size();
        neutrals.put(newId, Player.of("neutral", newId, role));
        System.out.println(neutrals);
        System.out.println(neutralCards.get(gameId));
        return newId;
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