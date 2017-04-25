package Concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Vlad on 29.01.2017.
 */
public class DeadlockingDiningPhilosophers {
    public static void main (String[] args) throws Exception {
        int ponder = 0;
        int size = 5;
        ChopstickQueue queue = new ChopstickQueue();
        ExecutorService exec = Executors.newCachedThreadPool();
        /*
        Chopstick[] sticks = new Chopstick[5];
        for (int i=0; i<size; i++)
            sticks[i] = new Chopstick();
        for (int i=0; i<size; i++)
            exec.execute(new Philosopher(sticks[i], sticks[(i+1) % size], i, ponder));*/
        for (int i=0; i<size; i++)
            queue.put(new Chopstick());
        for (int i=0; i<size; i++)
            exec.execute(new Philosopher(queue, i, ponder));
        System.out.println("Press Enter to quit");
        System.in.read();
        exec.shutdownNow();
    }
}
