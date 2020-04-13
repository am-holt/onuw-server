import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import javax.websocket.EncodeException;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.fasterxml.jackson.databind.ObjectMapper;

@ServerEndpoint("/onuw/{gameId}")
public class WebSocketEndpoint {
    private static final ObjectMapper mapper = new ObjectMapper();
    private Session session;
    private String gameId;
    private static Map<String, Set<WebSocketEndpoint>> playersInGames = new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(final Session session, @PathParam("gameId") final String gameId) throws IOException {
        this.session = session;
        this.gameId = gameId;
        System.out.println("HERE");
        Set<WebSocketEndpoint> existingPlayers = playersInGames.getOrDefault(gameId, new HashSet());
        existingPlayers.add(this);
        playersInGames.put(gameId, existingPlayers);
        System.out.println("New player joined");
        System.out.println(playersInGames.size());
        //broadcastToAllInGame(gameId, session.getId() + " connected");
        broadcastPlayerJoin(gameId);
    }

    @OnMessage
    public void handleTextMessage(final String message) {
        System.out.println("New Text Message Received");
        System.out.println("New Text Message Received");
        broadcastToAllInGame(gameId, message);
    }

    private static void broadcastToAllInGame(final String gameId, final String message) {
        playersInGames.get(gameId).stream().forEach(socket -> {
            try {
                socket.session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
    }

    private static void broadcastPlayerJoin(final String gameId) {
        playersInGames.get(gameId).stream().forEach(socket -> {
            try {
                GameState gameState = ImmutableGameState.builder().from(GameState.exampleState())
                    .otherPlayers(playersInGames.get(gameId)
                                        .stream()
                                        .filter(x -> !x.equals(socket))
                                        .map(x -> Player.of(x.session.getId(), x.session.getId(), "Werewolf"))
                                        .collect(Collectors.toList()))
                    .currentPlayer(Player.of(socket.session.getId(), socket.session.getId(), "Werewolf"))
                    .build();
                socket.session.getBasicRemote().sendText(mapper.writeValueAsString(gameState));
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
    }
}