package Concurrency;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Vlad on 05.06.2016.
 */
public class AtomicIntegerTest implements Runnable {
    private AtomicInteger i = new AtomicInteger(0);
    public int getValue() { return i.get(); }
    private void evenIncrement() { i.addAndGet(2); }

    public void run() {
        while (true)
            evenIncrement();
    }

    public static void main (String[] args) throws Exception {
        final ExecutorService exec = Executors.newCachedThreadPool();
        final AtomicIntegerTest ait = new AtomicIntegerTest();
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.err.println("Aborting");
                System.exit(0);
            }
        }, 10000);

        exec.execute(ait);
        while (true) {
            int val = ait.getValue();
            if (val%2 !=0 ) {
                System.out.println(val);
                System.exit(0);
            }
        }
    }
}
