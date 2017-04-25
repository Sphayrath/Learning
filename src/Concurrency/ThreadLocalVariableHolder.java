package Concurrency;

import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by VFilin on 07.06.2016.
 */
class Accessor implements Runnable {
    private final int id;
    public Accessor (int id) { this.id = id; }
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            ThreadLocalVariableHolder.increment();
            System.out.println(this);
            Thread.yield();
        }
    }

    public String toString() {
        return "#" + id + ": " + ThreadLocalVariableHolder.get();
    }
}

public class ThreadLocalVariableHolder {
    private static ThreadLocal<Integer> value = new ThreadLocal<Integer>() {
        private Random random = new Random(47);

        protected synchronized Integer initialValue() {
            return random.nextInt(10000);
        }
    };

    public static void increment() {
        value.set(value.get() + 1);
    }

    public static int get() { return value.get(); }

    public static void main(String[] args) throws Exception {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i=0; i<5; i++)
            exec.execute(new Accessor(i));
        TimeUnit.MILLISECONDS.sleep(2);
        exec.shutdownNow();
    }
}
