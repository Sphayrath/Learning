package Concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by VFilin on 21.08.2016.
 */
class SObject {
    private boolean flag = false;

    public synchronized void tFlag() {
        flag = true;
        notify();
    }

    public synchronized void fFlag() {
        flag = false;
        notify();
    }

    public synchronized void waitForFlag () throws InterruptedException {
        while (flag) wait();
    }

    public synchronized boolean getFlag() {
        return flag;
    }
}

class PassiveWaitObject implements Runnable {
    private SObject sObject;

    public PassiveWaitObject (SObject sObject) { this.sObject = sObject; }

    public void run (){
        try {
            while (!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(500);
                sObject.tFlag();
                System.out.println("Flag = true by PassiveWaitObject");
                sObject.waitForFlag();
            }
        } catch (InterruptedException e) {
            System.out.println("Exiting via interrupt"); }
        System.out.println("Ending PassiveWaitObject task");
    }
}

class ActiveWaitObject implements Runnable {
    private SObject sObject;
    long start, end;

    public ActiveWaitObject (SObject sObject) { this.sObject = sObject; }

    public void run (){
        System.out.println("ActiveWaitObject");
        while (!Thread.interrupted()) {
            start = System.nanoTime();
            if (sObject.getFlag()) {
                sObject.fFlag();
                System.out.println("Flag = false by ActiveWaitObject");
                end = System.nanoTime();
                System.out.println("Active waiting " + (end - start) + " nanoseconds");
            }
        }
        System.out.println("Ending ActiveWaitObject task");
    }
}
public class Task21_22 {
    public static void main(String[] args) throws Exception {
        SObject sObject = new SObject();
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new PassiveWaitObject(sObject));
        exec.execute(new ActiveWaitObject(sObject));
        TimeUnit.SECONDS.sleep(5);
        exec.shutdownNow();
    }
}
