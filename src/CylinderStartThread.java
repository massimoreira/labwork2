import java.util.concurrent.BrokenBarrierException;

public class CylinderStartThread extends Thread {

    private boolean interrupted;
    @Override
    public boolean isInterrupted() {
        return interrupted;
    }
    public void setInterrupted(boolean interrupted) {
        this.interrupted = interrupted;
    }

    private final CylinderStart cylinder;
    public CylinderStartThread(CylinderStart cylinder) {
        this.cylinder = cylinder;
    }

    @Override
    public void run() {
        interrupted = false;
        while (!isInterrupted()) {
            try {
                Main.boxesInputSemaphore.acquire();
                Main.inputBarrier.await();
                cylinder.gotoPosition(1);
                cylinder.gotoPosition(0);
            } catch (InterruptedException | BrokenBarrierException ignored) {
            }
        }
    }
}
