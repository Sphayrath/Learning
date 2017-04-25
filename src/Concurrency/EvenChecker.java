package Concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Vlad on 05.06.2016.
 */
public class EvenChecker implements Runnable {
    private IntGenerator generator;
    private final int id;

    public EvenChecker(IntGenerator g, int id) {
        generator = g;
        this.id = id;
    }

    public void run() {
        while (!generator.isCancelled()) {
            int val = generator.next();
            if(val % 2 != 0) {
                System.out.println("Process " + id +": " + val + " not even!");
                generator.cancel();
            }
        }
    }

    public static void test(IntGenerator gp, int count) {
        System.out.println("Press Ctrl+C to exit");

        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i=0; i<count; i++)
            exec.execute(new EvenChecker(gp, i));
        exec.shutdown();
    }

    public static void test(IntGenerator gp) {
        test(gp, 10);
    }
}
