public class SplitterConveyor {

    static {
        System.load("C:\\code\\str\\SplitterConveyor\\x64\\Debug\\SplitterConveyor.dll");
    }

    static synchronized native public void initializeHardwarePorts();
    static synchronized native public void closeChannels();

    static synchronized native public int cylinder1_getPosition();
    static synchronized native public void cylinder1_moveForward();
    static synchronized native public void cylinder1_moveBackward();
    static synchronized native public void cylinder1_stop();

    static synchronized native public int cylinder2_getPosition();
    static synchronized native public void cylinder2_moveForward();
    static synchronized native public void cylinder2_moveBackward();
    static synchronized native public void cylinder2_stop();

    static synchronized native public int cylinderStart_getPosition();
    static synchronized native public void cylinderStart_moveForward();
    static synchronized native public void cylinderStart_moveBackward();
    static synchronized native public void cylinderStart_stop();

    static synchronized native public void conveyor_move();
    static synchronized native public void conveyor_stop();
    static synchronized native public boolean conveyor_isMoving();

    static synchronized native public int getIdentificationSensors();
    static synchronized native public boolean getDock1Sensor();
    static synchronized native public boolean getDock2Sensor();

    static synchronized native public int getDockActuators();

    static synchronized native public void led_change(boolean value);

}
