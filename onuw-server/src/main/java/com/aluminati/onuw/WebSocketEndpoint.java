package com.aluminati.onuw;

import java.io.IOException;
import java.lang.Void;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.aluminati.onuw.Phase.Visitor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.palantir.conjure.java.serialization.ObjectMappers;

@ServerEndpoint("/onuw/game/{gameId}")
public class WebSocketEndpoint {
    private static final ObjectMapper mapper = ObjectMappers.newServerObjectMapper();
    private static GameStore gameStore = InMemGameStore.INSTANCE;
    private Session session;
    private String gameId;
    private static Map<String, Map<String, Session>> playersInGames = new ConcurrentHashMap<>();
    private static Map<String, GameTimer> gameTimers = new ConcurrentHashMap();

    @OnOpen
    public void onOpen(final Session session, @PathParam("gameId") final String gameId) throws IOException {
        this.session = session;
        this.gameId = gameId;
        if (!playersInGames.containsKey(gameId)) {
            GameTimer timer = new GameTimer(gameStore, gameId, Executors.newSingleThreadScheduledExecutor(),
                    (newTime) -> broadcastServerEvent(gameId, ServerEvent.updateTime(newTime)),
                    () -> maybeMoveToTheNextPhase(gameId));
            gameTimers.put(gameId, timer);
            timer.start();
        }
        System.out.println(gameId);
        Map<String, Session> existingPlayers = playersInGames.getOrDefault(gameId, new ConcurrentHashMap<>());
        existingPlayers.put(session.getId(), session);
        playersInGames.put(gameId, existingPlayers);
        gameStore.addPlayer(gameId, session.getId(),
                Player.builder().id(session.getId()).name("Player " + session.getId()).role(RoleType.HIDDEN).team(Team.HIDDEN).build());
        sendFullGameState(gameId, session);
        broadcastFullGameSate(gameId);
    }

    private void maybeMoveToTheNextPhase(String gameId) {
        Phase currentPhase = gameStore.getGamePhase(gameId);
        Visitor<Void> visitor = new Visitor<Void>() {
            public Void visitLobby() { return null; };

            public Void visitDay(){ 
                gameStore.updateGamePhase(gameId, Phase.WEREWOLF);
                gameStore.setTimeLeftInCurrentRound(gameId, 10);
                broadcastFullGameSate(gameId);
                Set<Player> werewolves = 
                    gameStore.getGamePlayers(gameId).stream()
                        .filter(x -> x.getRole().equals(RoleType.WEREWOLF))
                        .collect(Collectors.toSet());
                werewolves.stream()
                    .map(player -> player.getId())
                    .map(playerId -> playersInGames.get(gameId).get(playerId))
                    .forEach(session -> 
                        werewolves.stream().forEach(ww ->
                            sendServerEvent(ServerEvent.updatePlayer(ww), session)));
                return null;
            };

            public Void visitWerewolf(){ 
                gameStore.updateGamePhase(gameId, Phase.SEER);
                gameStore.setTimeLeftInCurrentRound(gameId, 10);
                broadcastFullGameSate(gameId);
                return null;
            };

            public Void visitSeer(){ 
                gameStore.updateGamePhase(gameId, Phase.ROBBER);
                gameStore.setTimeLeftInCurrentRound(gameId, 10);
                broadcastFullGameSate(gameId);
                return null;
            };

            public Void visitRobber(){
                gameStore.updateGamePhase(gameId, Phase.TROUBLEMAKER);
                gameStore.setTimeLeftInCurrentRound(gameId, 10);
                broadcastFullGameSate(gameId);
                return null;
            };

            public Void visitTroublemaker(){
                gameStore.updateGamePhase(gameId, Phase.VOTE);
                gameStore.setTimeLeftInCurrentRound(gameId, 180);
                broadcastFullGameSate(gameId);
                return null;
            };

            public Void visitVote(){
                gameStore.updateGamePhase(gameId, Phase.END);
                gameStore.setTimeLeftInCurrentRound(gameId, 30);
                broadcastFullGameSate(gameId);
                return null;
            };

            public Void visitEnd(){ return null; };

            public Void visitUnknown(String unknownValue){ return null;};
        };
        currentPhase.accept(visitor);
    }

    @OnError
    public void handleError(Throwable e) {
        e.printStackTrace();
        shutdownGame(gameId);
    }

    @OnClose
    public void handleClose() {
        playersInGames.get(gameId).remove(session.getId());
        if (playersInGames.get(gameId).size() == 0 ) {
            shutdownGame(gameId);
        }
    }

    private void shutdownGame(String gameId) {
        System.out.println("Shutdown");
        gameTimers.get(gameId).shutdown();
    }

    @OnMessage
    public void handleTextMessage(final String request) throws JsonMappingException, JsonProcessingException {
        ClientEvent clientEvent = mapper.readValue(request, ClientEvent.class);
        Map<String, Session> playerSessions = this.playersInGames.get(gameId);
        clientEvent.accept(new GameClientEventVisitor(
            gameId,
            session.getId(),
            gameStore,
            () -> broadcastFullGameSate(gameId),
            (playerId, message) -> {sendServerEvent(ServerEvent.sendMessage(message), playerSessions.get(playerId)); return null;},
            (peekerId, player) -> {sendServerEvent(ServerEvent.updatePlayer(player), playerSessions.get(peekerId)); return null;},
            voterId -> sendFullGameState(gameId, playerSessions.get(voterId))));
        System.out.println("GameStart");
    }

    private static void sendServerEvent(ServerEvent event, Session session) {
        try {
            synchronized (session) {
                String msg = mapper.writeValueAsString(event);
                session.getBasicRemote().sendText(msg);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static void sendFullGameState(final String gameId, final Session session) {
        Game gameState = gameStore.getGameStateForPlayer(gameId, session.getId());
        sendServerEvent(ServerEvent.fullUpdate(gameState), session);
    }


    private static void broadcastServerEvent(final String gameId, ServerEvent serverEvent) {
        playersInGames.get(gameId).values().stream().forEach(session -> {
            sendServerEvent(serverEvent, session);
        });
    }

    private static void broadcastFullGameSate(final String gameId) {
        playersInGames.get(gameId).values().stream().forEach(session -> {
            sendFullGameState(gameId, session);
        });
    }
}