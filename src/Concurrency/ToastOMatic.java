package Concurrency;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by VFilin on 15.01.2017.
 */

class Toast {
    public enum Status { DRY, BUTTERED, JAMMED }
    private Status status = Status.DRY;
    private final int id;
    public Toast (int id) {this.id = id;}

    public void butter() {status = Status.BUTTERED;}
    public void jam() {status = Status.JAMMED;}
    public Status getStatus() {return status;}
    public int getId() {return id;}
    public String toString() {
        return "Toast " + id + ": " + status;
    }
}

class Sandwich {
    private Toast top, bottom;
    private final int id;
    public Sandwich (Toast bottom, Toast top, int id) {
        this.bottom = bottom;
        this.top = top;
        this.id = id;
    }

    public int getId() { return id; }
    public Toast getTop() { return top; }
    public Toast getBottom() { return bottom; }
    public String toString() {
        return "Sandwich " + id + ": top: " + top + " and bottom: " + bottom;
    }
}

class ToastQueue extends LinkedBlockingQueue<Toast> {}

class SandwichQueue extends LinkedBlockingQueue<Sandwich> {}

class Toaster implements Runnable {
    private ToastQueue toastQueue;
    private int count = 0;
    private Random random = new Random(47);
    public Toaster (ToastQueue tq) {toastQueue = tq;}

    public void run() {
        try {
            while (!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(100 + random.nextInt(500));

                //Приготовление тоста
                Toast t = new Toast(count++);
                System.out.println(t);
                //Вставка в очередь
                toastQueue.put(t);
            }
        } catch (InterruptedException e) {System.out.println("Toaster interrupted");}
        System.out.println("Toaster off");
    }
}

//Нанесение масла:
class Butterer implements Runnable {
    private ToastQueue dryQueue, butteredQueue;
    public Butterer (ToastQueue dry, ToastQueue buttered) {
        dryQueue = dry;
        butteredQueue = buttered;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                //Блокирует до готовности следующего тоста:
                Toast t = dryQueue.take();
                t.butter();
                System.out.println(t);
                butteredQueue.put(t);
            }
        } catch (InterruptedException e) {System.out.println("Butterer interrupted");}
        System.out.println("Butterer off");
    }
}

//Нанесение джема
/*class Jammer implements Runnable {
    private ToastQueue butteredQueue, finishedQueue;
    public Jammer (ToastQueue buttered, ToastQueue finished) {
        butteredQueue = buttered;
        finishedQueue = finished;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                //Блокирует до готовности следующего тоста
                Toast t = butteredQueue.take();
                t.jam();
                System.out.println(t);
                finishedQueue.put(t);
            }
        } catch (InterruptedException e) {System.out.println("Jammer interrupted");}
        System.out.println("Jammer off");
    }
}*/

//Нанесение джема
class Jammer implements Runnable {
    private ToastQueue dryQueue, jammedQueue;
    public Jammer (ToastQueue dry, ToastQueue jammed) {
        dryQueue = dry;
        jammedQueue = jammed;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                //Блокирует до готовности следующего тоста
                Toast t = dryQueue.take();
                t.jam();
                System.out.println(t);
                jammedQueue.put(t);
            }
        } catch (InterruptedException e) {System.out.println("Jammer interrupted");}
        System.out.println("Jammer off");
    }
}

//
class SandwichMaker implements Runnable {
    private ToastQueue butteredQueue, jammedQueue;
    private SandwichQueue finishedQueue;
    private int count = 0;
    public SandwichMaker (ToastQueue buttered, ToastQueue jammed, SandwichQueue finished) {
        butteredQueue = buttered;
        jammedQueue = jammed;
        finishedQueue = finished;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                //Блокирует до готовности следующего тоста
                Toast bottom = butteredQueue.take();
                Toast top = jammedQueue.take();
                Sandwich sandwich = new Sandwich(bottom, top, count++);
                System.out.println(sandwich);
                finishedQueue.put(sandwich);
            }
        } catch (InterruptedException e) {System.out.println("SandwichMaker interrupted");}
        System.out.println("SandwichMaker off");
    }
}

//Потребление тоста
class Eater implements Runnable {
    private SandwichQueue finishedQueue;
    private int counter = 0;
    public Eater(SandwichQueue finished) {finishedQueue = finished;}

    public void run() {
        try {
            while (!Thread.interrupted()) {
                //Блокирует до готовности следующего тоста
                Sandwich sandwich = finishedQueue.take();

                //Проверка, что все тосты следуют по порядку и все тосты намазаны джемом
                if (sandwich.getId() != counter++ || sandwich.getTop().getStatus() != Toast.Status.JAMMED ||
                        sandwich.getBottom().getStatus() != Toast.Status.BUTTERED) {
                    System.out.println(">>>> Error: " + sandwich);
                    System.exit(1);
                } else
                    System.out.println("Chomp! " + sandwich);
            }
        } catch (InterruptedException e) {System.out.println("Eater interrupted");}
        System.out.println("Eater off");
    }
}

public class ToastOMatic {
    public static void main (String[] args) throws Exception {
        ToastQueue  dryQueue = new ToastQueue(),
                    butteredQueue = new ToastQueue(),
                    jammedQueue = new ToastQueue();
        SandwichQueue finishedQueue = new SandwichQueue();
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new Toaster(dryQueue));
        exec.execute(new Butterer(dryQueue, butteredQueue));
        exec.execute(new Jammer(dryQueue, jammedQueue));
        exec.execute(new SandwichMaker(butteredQueue, jammedQueue, finishedQueue));
        exec.execute(new Eater(finishedQueue));
        TimeUnit.SECONDS.sleep(5);
        exec.shutdownNow();
    }
}
