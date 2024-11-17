import java.util.Scanner;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static Integer dock3Counter = 0;

    public static Semaphore boxesInputSemaphore = new Semaphore(20);
    public static Semaphore boxesOutputSemaphore = new Semaphore(1);
    public static CyclicBarrier inputBarrier = new CyclicBarrier(2);
    public static LinkedBlockingQueue<Integer> dock1Queue = new LinkedBlockingQueue<>(1);
    public static LinkedBlockingQueue<Integer> dock2Queue = new LinkedBlockingQueue<>(5);

    private static final CylinderStart cylinderStart = new CylinderStart();
    private static final Cylinder1 cylinder1 = new Cylinder1();
    private static final Cylinder2 cylinder2 = new Cylinder2();

    private static final CylinderStartThread cylinderStartThread = new CylinderStartThread(cylinderStart);
    private static final CylinderThread cylinder1Thread = new CylinderThread(cylinder1);
    private static final CylinderThread cylinder2Thread = new CylinderThread(cylinder2);
    private static final BoxIDThread boxIDThread = new BoxIDThread();


    public static void main(String[] args) {

        boolean exit = false;
        Scanner sc = new Scanner(System.in);

        initializeMain();

        Mechanism.conveyorMove();
        while (!exit) {
            System.out.print("Enter a character: ");
            char c = sc.next().charAt(0);
            switch (c) {
                case 'p': boxesInputSemaphore.release(); break;
                case 's': System.out.println("Dock 3 counter: " + dock3Counter); break;
                case 'f': exit = true; break;
            }
        }
        Mechanism.conveyorStop();
        SplitterConveyor.closeChannels();
    }

    private static void initializeMain() {
        SplitterConveyor.initializeHardwarePorts();

        boxesInputSemaphore.drainPermits();
        boxesOutputSemaphore.drainPermits();

        cylinderStartThread.setDaemon(true);
        cylinderStartThread.start();
        cylinder1Thread.setDaemon(true);
        cylinder1Thread.start();
        cylinder2Thread.setDaemon(true);
        cylinder2Thread.start();
        boxIDThread.setDaemon(true);
        boxIDThread.start();
    }
}