package Concurrency;

import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by VFilin on 23.03.2017.
 */
public class ActiveObjectDemo {
    private ExecutorService exec = Executors.newSingleThreadExecutor();
    private Random random = new Random(47);

    private void pause(int factor) {
        try {
            TimeUnit.MILLISECONDS.sleep(100 + random.nextInt(factor));
        } catch (InterruptedException e) { System.out.println("sleep() interrupted");}
    }

    public Future<Integer> calculateInt (final int x, final int y) {
        return exec.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("starting "+ x + " + " + y);
                pause(500);
                return x+y;
            }
        });
    }

    public Future<Float> calculateFloat (final float x, final float y) {
        return exec.submit(new Callable<Float>() {
            @Override
            public Float call() throws Exception {
                System.out.println("starting "+ x + " + " + y);
                pause(2000);
                return x+y;
            }
        });
    }

    public void calculateVoid() {
        exec.submit(new Runnable() {
            @Override
            public void run() {
                pause(1000);
                System.out.println("calculateVoid()");
            }
        });
    }

    public void shutdown() { exec.shutdown(); }

    public static void main(String[] args) {
        ActiveObjectDemo d1 = new ActiveObjectDemo();
        List<Future<?>> results = new CopyOnWriteArrayList<>();

        for (float f = 0.0f; f < 1.0f; f+= 0.2f)
            results.add(d1.calculateFloat(f, f));
        d1.calculateVoid();
        for (int i=0; i<5; i++)
            results.add(d1.calculateInt(i, i));
        d1.calculateVoid();
        System.out.println("All async calls made");
        while (results.size() > 0) {
            for (Future<?> f : results)
                if (f.isDone()) {
                    try {
                        System.out.println(f.get());
                    } catch (Exception e) {
                        throw new RuntimeException();
                    }
                    results.remove(f);
                }
        }
        d1.shutdown();
    }
}
