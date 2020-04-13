import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.common.collect.ImmutableList;

import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableGameState.class)
@JsonDeserialize(as = ImmutableGameState.class)
public interface GameState {
    public List<Player> otherPlayers();
    public List<Neutral> neutralCards();
    public Player currentPlayer();
    public List<String> availableRoles();
    public String gamePhase();
    public int timeLeft();
    public int currentVote();
    public int gameId();

    public static GameState exampleState() {
        
        Player currentPlayer = Player.of("1", "jeff", "Werewolf");
        GameState gameState = ImmutableGameState.builder()
            .currentPlayer(currentPlayer)
            .otherPlayers(ImmutableList.of())
            .neutralCards(ImmutableList.of())
            .gamePhase("Day")
            .availableRoles(ImmutableList.of())
            .timeLeft(90)
            .currentVote(0)
            .gameId(2)
            .build();
        return gameState;
    }
}