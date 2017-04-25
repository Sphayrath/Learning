package Concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Vlad on 05.06.2016.
 */
class Coordinates {
    private volatile boolean cancelled = false;
    private int x=0, y=0;
    public synchronized void addX() { x++; x++; }
    public synchronized void addY() { y++; y++; }
    public synchronized int getX() { return x; }
    public synchronized int getY() { return y; }
    public void cancel() { cancelled = true; }
    public boolean isCancelled() { return cancelled; }
}

class CoordinatesChecker implements Runnable {
    private final int id;
    private static int errors = 0;
    private Coordinates coordinates;
    public CoordinatesChecker(Coordinates coordinates, int id) {
        this.id = id;
        this.coordinates = coordinates;
    }

    public void run() {
        while (!coordinates.isCancelled()) {
            coordinates.addX();
            coordinates.addY();

            int x = coordinates.getX();
            if (x % 2 != 0) {
                System.out.println("Process #" + id + ": x=" + x + " isn't even");
                errors++;
            }

            int y = coordinates.getY();
            if (y % 2 != 0) {
                System.out.println("Process #" + id + ": y=" + y + " isn't even");
                errors++;
            }

            if (errors > 10) coordinates.cancel();
        }
    }
}

public class Task21_11 {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        Coordinates coordinates = new Coordinates();
        for (int i=0; i<5; i++)
            exec.execute(new CoordinatesChecker(coordinates, i));
        exec.shutdown();
    }
}
