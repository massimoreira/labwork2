public class BlinkLed extends Thread{
    private final long pulse;
    private int repeatNumber = 1;

    public BlinkLed(long pulse){
        this.pulse = pulse;
    }
    public BlinkLed(long pulse, int repeatNumber){
        this.pulse = pulse;
        if (repeatNumber == 0)
            this.repeatNumber = Integer.MAX_VALUE;
        else
            this.repeatNumber = repeatNumber;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < repeatNumber && !this.isInterrupted(); i++) {
                Mechanism.ledSwitch(true);
                Thread.sleep(pulse);
                Mechanism.ledSwitch(false);
                Thread.sleep(pulse);
            }
        } catch (InterruptedException ignored) {}
    }
}
