import java.util.concurrent.BrokenBarrierException;

public class BoxIDThread extends Thread{

    private boolean interrupted;
    @Override
    public boolean isInterrupted() {
        return interrupted;
    }
    public void setInterrupted(boolean interrupted) {
        this.interrupted = interrupted;
    }

    @Override
    public void run() {
        interrupted = false;
        boolean dot1;
        boolean dot2;
        int brickType;
        while(!interrupted){
            try {
                dot1 = false;
                dot2 = false;

                Main.inputBarrier.await();

                while(!Main.boxesOutputSemaphore.tryAcquire()) {
                    if(SplitterConveyor.getIdentificationSensors() == 1)
                        dot1 = true;
                    else if (SplitterConveyor.getIdentificationSensors() == 2)
                        dot2 = true;
                    else Thread.yield();
                }

                if (dot1&&dot2)
                    brickType = 3;
                else if (dot1||dot2)
                    brickType = 2;
                else
                    brickType = 1;

                Main.dock1Queue.put(brickType);

            } catch (BrokenBarrierException | InterruptedException ignored) {
            }
        }
    }
}
