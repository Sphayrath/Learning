package Concurrency;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by Vlad on 29.01.2017.
 */

//Выполнение части общей задачи
class TaskPortion implements Runnable {
    private static int counter = 0;
    private final int id = counter++;
    private static Random random = new Random(47);
    private final CountDownLatch latch;
    TaskPortion (CountDownLatch latch) {
        this.latch = latch;
    }

    public void run() {
        try {
            doWork();
            latch.countDown();
        } catch (InterruptedException e) {System.out.println("Task interrupted");}
    }

    public void doWork() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(random.nextInt(2000));
    }

    public String toString() {
        return String.format("%1$-3d ", id);
    }
}

//Ожидание по CountdownLatch
class WaitingTask implements Runnable {
    private static int counter = 0;
    private final int id = counter++;
    private final CountDownLatch latch;
    WaitingTask (CountDownLatch latch) {
        this.latch = latch;
    }

    public void run() {
        try {
            latch.await();
            System.out.println("Latch barrier passed for " + this);
        } catch (InterruptedException e) { System.out.println(this + " interrupted"); }
    }

    public String toString() {
        return String.format("Waiting task %1$-3d", id);
    }
}
public class CountDownLatchDemo {
    static final int SIZE = 100;
    public static void main (String[] args) throws Exception {
        ExecutorService exec = Executors.newCachedThreadPool();
        CountDownLatch latch = new CountDownLatch(SIZE);
        for (int i=0; i<10; i++)
            exec.execute(new WaitingTask(latch));
        for (int i=0; i<SIZE; i++)
            exec.execute(new TaskPortion(latch));
        System.out.println("Launched all tasks");
        exec.shutdown();
    }
}
