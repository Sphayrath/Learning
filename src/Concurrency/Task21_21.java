package Concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by Vlad on 13.06.2016.
 */
class Object1 implements Runnable {
    private volatile int id = 0;
    public synchronized void test() throws InterruptedException {
        System.out.println("Message " + id);
        wait();
        id++;
    }

    public synchronized void tested() {
        notifyAll();
    }

    public void run() {
        try {
            while (!Thread.interrupted()) test();
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }
    }
}

class Object2 implements Runnable {
    private Object1 obj;

    public Object2(Object1 obj) { this.obj = obj; }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                TimeUnit.SECONDS.sleep(1);
                obj.tested();
            }
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }
    }
}

public class Task21_21 {
    public static void main (String[] args) throws Exception {
        Object1 obj1 = new Object1();
        Object2 obj2 = new Object2(obj1);
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(obj1);
        exec.execute(obj2);
        TimeUnit.SECONDS.sleep(5);
        exec.shutdownNow();
    }
}
