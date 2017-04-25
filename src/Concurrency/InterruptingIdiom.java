package Concurrency;

import java.util.concurrent.TimeUnit;

/**
 * Created by VFilin on 09.06.2016.
 */
class NeedsCleanup {
    private final int id;
    public NeedsCleanup(int ident) {
        id = ident;
        System.out.println("NeedsCleanup " +id);
    }
    public void cleanup() { System.out.println("Cleaning up " +id); }
}

class Blocked3 implements Runnable {
    private volatile double d = 0.0;
    public void run() {
        try {
            while (!Thread.interrupted()) {
                //точка1
                NeedsCleanup n1 = new NeedsCleanup(1);
                // try-finally начинается сразу же за определением n1, тобы гарантировать особождение n1
                try {
                    System.out.println("Sleeping");
                    TimeUnit.SECONDS.sleep(1);
                    //точка2
                    NeedsCleanup n2 = new NeedsCleanup(2);
                    //Гарантирует правильное освобождение n2
                    try {
                        System.out.println("Calculating");
                        //Продолжительная неблокирующая операция
                        for (int i=1; i<2500000; i++)
                            d = d + (Math.PI + Math.E) / d;
                        System.out.println("Finished time-consuming operation");
                    } finally { n2.cleanup(); }
                } finally { n1.cleanup(); }
            }
            System.out.println("Exiting via while() test");
        } catch (InterruptedException e) { System.out.println("Exiting via InterruptedException"); }
    }
}

public class InterruptingIdiom {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new Blocked3());
        t.start();
        TimeUnit.MILLISECONDS.sleep(10000);
        t.interrupt();
    }
}


