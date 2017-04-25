package Concurrency;

/**
 * Created by Vlad on 04.06.2016.
 */
public class MoreBasicThreads {
    public static void main(String[] args) {
        for (int i=0; i<5; i++)
            new Thread(new LiftOff()).start();
        System.out.println("Waiting for LiftOff");
    }
}
