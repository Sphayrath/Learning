package Concurrency;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by Vlad on 04.06.2016.
 */
class TestSleep implements Runnable {
    long time = 0;
    private static int count = 0;
    private final int id = count++;

    public TestSleep(int time) { this.time = time * 1000;}
    public void run() {
        try {
            TimeUnit.MILLISECONDS.sleep(time);
        } catch (InterruptedException e) {
            System.err.println("Interrupted");
        }
        System.out.println("Task " +id + ": " + time);
    }
}
public class Task21_6 {
    public static void main(String[] args) {
        Random random = new Random(47);
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i=0; i<10; i++)
            exec.execute(new TestSleep(random.nextInt(11)));
        exec.shutdown();
    }
}
