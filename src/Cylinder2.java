public class Cylinder2 implements Cylinder {
    private static final int type = 2;
    private int count = 0;
    private int rejectedCount = 0;
    private boolean closed = false;

    @Override
    public int getType() {
        return type;
    }

    @Override
    public void moveForward() {
        SplitterConveyor.cylinder2_moveForward();
    }

    @Override
    public void moveBackward() {
        SplitterConveyor.cylinder2_moveBackward();
    }

    @Override
    public void stop() {
        SplitterConveyor.cylinder2_stop();
    }

    @Override
    public int getPosition() {
        return SplitterConveyor.cylinder2_getPosition();
    }

    @Override
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
        stop();
    }

    @Override
    public boolean boxDetected() {
        return SplitterConveyor.getDock2Sensor();
    }

    public boolean isClosed() {
        return closed;
    }
    public void setStatusClosed(boolean status) {
        closed = status;
    }

    public void increaseBoxCount() {
        count++;
    }
    public int getBoxCount() {
        return count;
    }

    public void increaseRejectedBoxCount() {
        rejectedCount++;
    }
    public int getRejectedBoxCount() {
        return rejectedCount;
    }
}
