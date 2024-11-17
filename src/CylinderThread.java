import java.util.concurrent.BrokenBarrierException;

public class CylinderThread extends Thread {

    private boolean interrupted;
    @Override
    public boolean isInterrupted() {
        return interrupted;
    }
    public void setInterrupted(boolean interrupted) {
        this.interrupted = interrupted;
    }

    private final Cylinder cylinder;
    public CylinderThread(Cylinder cylinder) {
        this.cylinder = cylinder;
    }


    private void cylinderPassBox(int type, int box) throws InterruptedException {
        if (type == 1)
            Main.dock2Queue.put(box);
        else if (type == 2) {
            (new BlinkLed(350, 5)).start();
            Main.dock3Counter++;
        }
    }


    @Override
    public void run() {
        interrupted = false;
        int box;
        while (!isInterrupted()) {
            try {
                while (!cylinder.boxDetected()) {Thread.yield();}
                if (cylinder.getType() == 1) {
                    Main.boxesOutputSemaphore.release();
                }

                if (cylinder.getType() == 1)
                    box = Main.dock1Queue.take();
                else
                    box = Main.dock2Queue.take();

                boolean flag = Mechanism.conveyorIsMoving();
                Mechanism.conveyorStop();

                if (box != cylinder.getType()) {
                    cylinderPassBox(cylinder.getType(), box);
                }
                else if (cylinder.isClosed()) {
                    cylinderPassBox(cylinder.getType(), box);
                    cylinder.increaseRejectedBoxCount();
                }
                else {
                    cylinder.gotoPosition(1);
                    (new BlinkLed(box == 1 ? 1000 : 2000)).start();
                    cylinder.gotoPosition(0);
                    cylinder.increaseBoxCount();
                }

                if (flag)
                    Mechanism.conveyorMove();
                while(cylinder.boxDetected()){Thread.yield();};

            } catch (InterruptedException ignored) {
            }
        }
    }
}
