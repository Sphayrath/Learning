package Concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by VFilin on 04.10.2016.
 */
class Meal {
    private final int orderNum;
    public Meal(int orderNum) {this.orderNum = orderNum;}
    public String toString() {return "Meal " + orderNum;}
}

class WaitPerson implements Runnable {
    private Restaurant restaurant;
    public WaitPerson (Restaurant restaurant) {this.restaurant = restaurant;}

    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    while (restaurant.meal == null)
                        wait(); //Ожидание приготовления блюда
                }
                System.out.println ("WaitPerson got " + restaurant.meal);
                synchronized (restaurant.chef) {
                    restaurant.meal = null;
                    restaurant.chef.notifyAll();
                }
                //TimeUnit.MILLISECONDS.sleep(100);
                synchronized (restaurant.busBoy) {
                    restaurant.busBoy.notifyAll();
                }
            }
        } catch (InterruptedException e) {System.out.println("WaitPerson interrupted");}
    }
}

class Chef implements Runnable {
    private Restaurant restaurant;
    private int count = 0;
    public Chef (Restaurant restaurant) {this.restaurant = restaurant;}

    public void run(){
        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    while (restaurant.meal!=null)
                        wait(); //Ожидание официанта
                }
                if(++count == 10) {
                    System.out.println("Out of food, closing");
                    restaurant.exec.shutdownNow();
                }
                System.out.print("Order up! ");
                synchronized (restaurant.waitPerson) {
                    restaurant.meal = new Meal(count);
                    restaurant.waitPerson.notifyAll();
                }
                TimeUnit.MILLISECONDS.sleep(300);
            }
        } catch (InterruptedException e) {System.out.println("Chef interrupted");}
    }
}

class BusBoy implements Runnable {
    private Restaurant restaurant;
    public BusBoy (Restaurant restaurant) {this.restaurant = restaurant;}

    public void run(){
        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    //while (restaurant.meal!=null)
                        wait(); //Ожидание официанта
                }
                TimeUnit.MILLISECONDS.sleep(200);
                System.out.println("Table cleaned");
            }
        } catch (InterruptedException e) {System.out.println("BusBoy interrupted");}
    }
}

public class Restaurant {
    Meal meal;
    ExecutorService exec = Executors.newCachedThreadPool();
    WaitPerson waitPerson = new WaitPerson(this);
    Chef chef = new Chef(this);
    BusBoy busBoy = new BusBoy(this);

    public Restaurant() {
        exec.execute(chef);
        exec.execute(waitPerson);
        exec.execute(busBoy);
    }

    public static void main(String[] args) {
        new Restaurant();
    }
}
