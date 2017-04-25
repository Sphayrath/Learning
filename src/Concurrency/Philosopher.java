package Concurrency;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by VFilin on 24.01.2017.
 */
public class Philosopher implements Runnable {
    private Chopstick left;
    private Chopstick right;
    ChopstickQueue queue;
    private final int id;
    private final int ponderFactor;
    private Random random = new Random(47);

    private void pause() throws InterruptedException {
        if (ponderFactor == 0) return;
        TimeUnit.MILLISECONDS.sleep(random.nextInt(ponderFactor*250));
    }

    public Philosopher (ChopstickQueue queue, int ident, int ponder) {
        this.queue = queue;
        id = ident;
        ponderFactor = ponder;
    }

    public void run () {
        try {
            while (!Thread.interrupted()) {
                System.out.println(this + " thinking");
                pause();
                //Философ проголодался
                System.out.println(this + " grabbing right");
                right = queue.take();
                //right.take();
                System.out.println(this + " grabbing left");
                left = queue.take();
                //left.take();
                System.out.println(this + " eating");
                pause();
                queue.put(right);
                queue.put(left);
                //right.drop();
                //left.drop();
            }
        } catch (InterruptedException e) {System.out.println (this + " " + "exiting via interrupt"); }
    }

    public String toString() { return "Philosopher " + id; }
}
