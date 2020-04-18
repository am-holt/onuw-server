import java.util.List;
import java.util.Map;

import com.aluminati.onuw.Game;
import com.aluminati.onuw.Phase;
import com.aluminati.onuw.Player;
import com.aluminati.onuw.Role;

interface GameStore {
    int getTimeLeftInCurrentRound(String gameId);

    void setTimeLeftInCurrentRound(String gameId, int timeLeft);

    List<Player> getGamePlayers(String gameId);

    void addPlayer(String gameId, String playerId, Player player);

    Player getPlayer(String gameId, String playerId);

    void updatePlayerName(String gameId, String playerId, String name);

    String createNewGame(String gameId);

    Game getGameStateForPlayer(String gameId, String playerId);

    Phase getGamePhase(String gameId);

    void updateGamePhase(String gameId, Phase phase);

    List<Role> getAvailableRoles(String gameId);

    void updatePlayerRoles(String gameId, Map<String, Role> roles);
}