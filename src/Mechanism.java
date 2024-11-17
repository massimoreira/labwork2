public class Mechanism {

    public static void conveyorMove (){
        SplitterConveyor.conveyor_move();
    }

    public static void conveyorStop (){
        SplitterConveyor.conveyor_stop();
    }

    public static boolean conveyorIsMoving (){
        return SplitterConveyor.conveyor_isMoving();
    }

    public static boolean switchDock1Pressed() {
        return SplitterConveyor.getDockActuators() == 1;
    }

    public static boolean switchDock2Pressed() {
        return SplitterConveyor.getDockActuators() == 2;
    }

    public static boolean switchDockEndPressed() {
        return SplitterConveyor.getDockActuators() == 3;
    }

    public static void ledSwitch (boolean on) {
        SplitterConveyor.led_change(on);
    }
}
