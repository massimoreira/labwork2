public class CylinderStart {

    public void moveForward() {
        SplitterConveyor.cylinderStart_moveForward();
    }

    public void moveBackward() {
        SplitterConveyor.cylinderStart_moveBackward();
    }

    public void stop() {
        SplitterConveyor.cylinderStart_stop();
    }

    public int getPosition() {
        return SplitterConveyor.cylinderStart_getPosition();
    }

    public void gotoPosition(int position) throws IllegalArgumentException {
        if (position != 0 && position != 1)
            throw new IllegalArgumentException("Position must be 0 or 1");
        if (position == getPosition())
            return;
        if (position == 0)
            moveBackward();
        else
            moveForward();

        while(position != getPosition()) {Thread.yield();};
        if(position == 1) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        stop();
    }
}
