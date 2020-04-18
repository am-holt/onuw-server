import java.util.Collections;
import java.util.List;
import java.util.Map;
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

    public GameClientEventVisitor(String gameId, String playerId, GameStore gameStore) {
        this.gameId = gameId;
        this.playerId = playerId;
        this.gameStore = gameStore;
    }

    @Override
    public Void visitUpdateName(String value) {
        gameStore.updatePlayerName(gameId, playerId, value);
        return null;
    }

    @Override
    public Void visitClickPlayer(String value) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Void visitStartGame(com.aluminati.onuw.Void value) {
        // TODO Auto-generated method stub
        List<Role> roles = gameStore.getAvailableRoles(gameId);
        Collections.shuffle(roles);
        List<Player> players = gameStore.getGamePlayers(gameId);
        Map<String, Role> playerRoles = IntStream.range(0, players.size()).boxed()
                .collect(Collectors.toMap(index -> players.get(index).getId(), roles::get));
        gameStore.updatePlayerRoles(gameId, playerRoles);
        gameStore.updateGamePhase(gameId, Phase.DAY);
        gameStore.setTimeLeftInCurrentRound(gameId, 10);
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
