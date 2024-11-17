public interface Cylinder {

    public int getType();

    public void moveForward();
    public void moveBackward();
    public void stop();
    public int getPosition();
    public void gotoPosition(int position) throws IllegalArgumentException;

    public boolean boxDetected();

    public boolean isClosed();
    public void setStatusClosed(boolean status);

    public void increaseBoxCount();
    public int getBoxCount();

    public void increaseRejectedBoxCount();
    public int getRejectedBoxCount();
}
