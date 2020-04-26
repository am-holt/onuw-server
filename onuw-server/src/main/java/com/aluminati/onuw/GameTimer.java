package com.aluminati.onuw;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class GameTimer {
    private GameStore gameStore;
    private String gameId;
    private ScheduledExecutorService executor;
    private Consumer<Integer> onUpdate;
    private Runnable onTimeReachesZero;

    public GameTimer(
            GameStore gameStore,
            String gameId,
            ScheduledExecutorService executor,
            Consumer<Integer> onUpdate,
            Runnable onTimeReachesZero) {
        this.executor = executor;
        this.gameId = gameId;
        this.gameStore = gameStore;
        this.onUpdate = onUpdate;
        this.onTimeReachesZero = onTimeReachesZero;
    }

	public void start() {
        executor.scheduleAtFixedRate(
            () -> tick(),
            0,
            1,
            TimeUnit.SECONDS);
    }
    
    public void shutdown() {
        this.executor.shutdown();
    }

    private void tick() {
        try {
            int currentTime = gameStore.getTimeLeftInCurrentRound(gameId);
            int newTime = Math.max(currentTime - 1, 0);
            gameStore.setTimeLeftInCurrentRound(gameId, newTime);
            if (currentTime == 0) {
                this.onTimeReachesZero.run();
            } else {
                this.onUpdate.accept(newTime);
            }
        } catch (Exception e) { 
            System.out.println(e);
        }
    }
}