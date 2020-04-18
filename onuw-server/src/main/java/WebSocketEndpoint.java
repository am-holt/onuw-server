import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.websocket.EncodeException;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.aluminati.onuw.ClientEvent;
import com.aluminati.onuw.Game;
import com.aluminati.onuw.Phase;
import com.aluminati.onuw.Role;
import com.aluminati.onuw.ServerEvent;
import com.aluminati.onuw.Player;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;

@ServerEndpoint("/onuw/{gameId}")
public class WebSocketEndpoint {
    private static final ObjectMapper mapper = new ObjectMapper();
    private static GameStore gameStore = new InMemGameStore();
    private Session session;
    private String gameId;
    private static Map<String, Set<Session>> playersInGames = new ConcurrentHashMap<>();
    private static Map<String, ScheduledExecutorService> scheduledExecutors = new ConcurrentHashMap();

    @OnOpen
    public void onOpen(final Session session, @PathParam("gameId") final String gameId) throws IOException {
        this.session = session;
        this.gameId = gameId;
        if (!playersInGames.containsKey(gameId)) {
            gameStore.createNewGame(gameId);
            System.out.println("create");
            ScheduledExecutorService gameTimeExecutor = Executors.newSingleThreadScheduledExecutor();
            scheduledExecutors.put(gameId, gameTimeExecutor);
            gameTimeExecutor.scheduleAtFixedRate(
                () -> {
                    try {
                        System.out.println("time");
                        gameStore.setTimeLeftInCurrentRound(gameId, gameStore.getTimeLeftInCurrentRound(gameId) - 1);
                        broadcastFullGameSate(gameId);}
                    catch (Exception e) { 
                        System.out.println(e);
                    }},
                0,
                1,
                TimeUnit.SECONDS);
        }
        System.out.println("HERE");
        System.out.println(gameId);
        Set<Session> existingPlayers = playersInGames.getOrDefault(gameId, new HashSet());
        System.out.println(existingPlayers.size());
        System.out.println(session.getId());
        existingPlayers.add(session);
        System.out.println(existingPlayers.size());
        playersInGames.put(gameId, existingPlayers);
        gameStore.addPlayer(gameId, session.getId(),
                Player.builder().id(session.getId()).name("Player " + session.getId()).role(Role.HIDDEN).build());
        System.out.println("Size");
        System.out.println(playersInGames.get(gameId).size());
        System.out.println("New player joined");
        System.out.println(playersInGames.size());
        sendFullGameState(gameId, session);
        broadcastFullGameSate(gameId);
    }

    @OnMessage
    public void handleTextMessage(final String message) throws JsonMappingException, JsonProcessingException {
        ClientEvent clientEvent = mapper.readValue(message, ClientEvent.class);
        clientEvent.accept(new GameClientEventVisitor(gameId, session.getId(), gameStore));
        System.out.println("GameStart");
        broadcastFullGameSate(gameId);
    }

    private static void sendFullGameState(final String gameId, final Session session) {
        try {
            Game gameState = gameStore.getGameStateForPlayer(gameId, session.getId());
            String msg = mapper.writeValueAsString(ServerEvent.fullUpdate(gameState));
            synchronized (session) {
                session.getBasicRemote().sendText(msg);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static void broadcastFullGameSate(final String gameId) {
        playersInGames.get(gameId).stream().forEach(session -> {
            sendFullGameState(gameId, session);
        });
    }
}