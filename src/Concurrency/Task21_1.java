package Concurrency;

/**
 * Created by Vlad on 04.06.2016.
 */
class RunnableTask implements Runnable {
    public RunnableTask() {System.out.println("Start");}

    @Override
    public void run() {
        System.out.println("Message #1");
        Thread.yield();
        System.out.println("Message #2");
        Thread.yield();
        System.out.println("Message #3");
        Thread.yield();
        End();
    }

    public void End() {System.out.println("End");}
}
public class Task21_1 {
    public static void main(String[] args) {
        for (int i=0; i<3; i++)
            new Thread(new RunnableTask()).start();
    }
}
