import java.util.concurrent.Callable;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GameTimer {
    private GameStore gameStore;
    private String gameId;
    private ScheduledExecutorService executor;
    private Runnable onUpdate;
    private Runnable onTimeReachesZero;

    public GameTimer(
            GameStore gameStore,
            String gameId,
            ScheduledExecutorService executor,
            Runnable onUpdate,
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
            gameStore.setTimeLeftInCurrentRound(gameId, Math.max(currentTime - 1, 0));
            if (currentTime == 0) {
                this.onTimeReachesZero.run();
            } else {
                this.onUpdate.run();
            }
        } catch (Exception e) { 
            System.out.println(e);
        }
    }
}