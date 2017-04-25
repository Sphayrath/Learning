package Concurrency.activewaxomatic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by VFilin on 23.03.2017.
 */

class ActiveCar {
    private ExecutorService ex =
            Executors.newSingleThreadExecutor();
    private enum Action { WAX, BUFF }
    private Action lastAction = Action.BUFF;
    private boolean waxOn;

    public void wax() {
        try {
            ex.execute(new Runnable() {
                @Override
                public void run() {
                    if(lastAction != Action.WAX) {
                        System.out.print("Wax On! ");
                        pause(200);
                        waxOn = true;
                        lastAction = Action.WAX;
                    }
                }
            });
        } catch(RejectedExecutionException e) {}
    }

    public void buff() {
        try {
            ex.execute(new Runnable() {
                @Override
                public void run() {
                    if(lastAction != Action.BUFF) {
                        System.out.print("Wax Off! ");
                        pause(200);
                        waxOn = false;
                        lastAction = Action.BUFF;
                    }
                }
            });
        } catch(RejectedExecutionException e) {}
    }

    public void shutdown() { ex.shutdown(); }

    private static void pause(int sleep_time) {
        try {
            TimeUnit.MILLISECONDS.sleep(sleep_time);
        } catch(InterruptedException e) {
            System.out.println("sleep() interrupted");
        }
    }
}

public class ActiveWaxOMatic {
    public static void main(String[] args) throws Exception {
        ActiveCar car = new ActiveCar();
        for (int i=0; i<10; i++) {
            System.out.println("Start " + i);
            car.wax();
            car.buff();
        }
        car.shutdown();
    }
}
