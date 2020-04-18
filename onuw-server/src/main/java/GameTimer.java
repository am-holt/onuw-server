import java.util.concurrent.Callable;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GameTimer {
    private GameStore gameStore;
    private ScheduledExecutorService executor;
    private Runnable onUpdate;
    private String gameId;

    public GameTimer(
            GameStore gameStore,
            String gameId,
            ScheduledExecutorService executor,
            Runnable onUpdate) {
        this.executor = executor;
        this.gameId = gameId;
        this.gameStore = gameStore;
        this.onUpdate = onUpdate;
    }

	public void start() {
		// TODO Auto-generated method stub
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
            this.onUpdate.run();
        } catch (Exception e) { 
            System.out.println(e);
        }
    }
}