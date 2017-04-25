package Concurrency.restaurant2;

import enumerated.Course;
import enumerated.Food;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by Vlad on 25.02.2017.
 */

//Заказ передается официанту, который передает его повару
class Order {
    private static int counter = 0;
    private final int id = counter++;
    private final Customer customer;
    private final WaitPerson waitPerson;
    private final Food food;
    public Order(Customer customer, WaitPerson waitPerson, Food food) {
        this.customer = customer;
        this.waitPerson = waitPerson;
        this.food = food;
    }

    public Food item() {return food;}
    public Customer getCustomer() { return customer; }
    public WaitPerson getWaitPerson() { return waitPerson; }
    public String toString() {
        return "Order: " + id + " item: " + food + " for: " + customer + " served by: " + waitPerson;
    }
}

//Повар возвращает готовое блюдо
class Plate {
    private final Order order;
    private final Food food;
    public Plate (Order order, Food food) {
        this.order = order;
        this.food = food;
    }

    public Order getOrder() {return order;}
    public Food getFood() {return food;}
    public String toString() { return food.toString(); }
}

class Customer implements Runnable {
    private static int counter = 0;
    private final int id = counter++;
    private final WaitPerson waitPerson;
    //Посетитель может получить только одно блюдо за раз
    private SynchronousQueue<Plate> placeSetting = new SynchronousQueue<>();
    public Customer(WaitPerson waitPerson) { this.waitPerson = waitPerson; }

    public void deliver (Plate plate) throws InterruptedException {
        //Блокируется только в том случае, если посетитель не закончил с предыдущим блюдом
        placeSetting.put(plate);
    }

    public void run() {
        for (Course course : Course.values()) {
            Food food = course.randomSelection();
            try {
                waitPerson.placeOrder(this, food);
                //Блокируется до получения блюда
                System.out.println(this + "eating " + placeSetting.take());
            } catch (InterruptedException e) {
                System.out.println(this + "waiting for " + course + " interrupted");
                break;
            }
        }
        System.out.println(this + "finished meal, leaving");
    }

    public String toString() {
        return "Customer " + id + " ";
    }
}

class WaitPerson implements Runnable {
    private static int counter = 0;
    private final int id = counter++;
    private final Restaurant restaurant;
    BlockingQueue<Plate> filledOrders = new LinkedBlockingQueue<>();
    public WaitPerson (Restaurant restaurant) { this.restaurant = restaurant; }

    public void placeOrder (Customer customer, Food food) {
        try {
            restaurant.orders.put (new Order(customer, this, food));
        } catch (InterruptedException e) {
            System.out.println(this + " placeOrder interrupted");
        }
    }

    public void run () {
        try {
            while (!Thread.interrupted()) {
                //Блокируется пока блюдо не будет готово
                Plate plate = filledOrders.take();
                System.out.println(this + "received " + plate + " delivering to "
                + plate.getOrder().getCustomer());
                plate.getOrder().getCustomer().deliver(plate);
            }
        } catch (InterruptedException e) {
            System.out.println(this + " interrupted");
        }
        System.out.println(this + " off duty");
    }

    public String toString() { return "WaitPerson "+ id + " "; }
}

class Chef implements Runnable {
    private static int counter = 0;
    private final int id  = counter++;
    private final Restaurant restaurant;
    private static Random random = new Random(47);
    public Chef (Restaurant restaurant) { this.restaurant = restaurant; }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                //Блокируется до появления заказа
                Order order = restaurant.orders.take();
                Food requestedItem = order.item();
                //Время на подготовку заказа
                TimeUnit.MILLISECONDS.sleep(random.nextInt(500));
                Plate plate = new Plate(order, requestedItem);
                order.getWaitPerson().filledOrders.put(plate);
            }
        } catch (InterruptedException e) {
            System.out.println(this + " interrupted");
        }
        System.out.println(this + " off duty");
    }

    public String toString() { return "Chef " + id + " "; }
}

class Restaurant implements Runnable {
    private List<WaitPerson> waitPersons = new ArrayList<>();
    private List<Chef> chefs = new ArrayList<>();
    private ExecutorService exec;
    private static Random random = new Random(47);
    BlockingQueue<Order> orders = new LinkedBlockingQueue<>();
    public Restaurant (ExecutorService exec, int nWaitPersons, int nChefs) {
        this.exec = exec;
        for (int i=0; i<nWaitPersons; i++) {
            WaitPerson waitPerson = new WaitPerson(this);
            waitPersons.add(waitPerson);
            exec.execute(waitPerson);
        }
        for (int i=0; i<nChefs; i++) {
            Chef chef = new Chef(this);
            chefs.add(chef);
            exec.execute(chef);
        }
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                //ПРиходит новый посетитель, ему назначается официант
                WaitPerson waitPerson = waitPersons.get(random.nextInt(waitPersons.size()));
                Customer customer = new Customer(waitPerson);
                exec.execute(customer);
                TimeUnit.MILLISECONDS.sleep(100);
            }
        } catch (InterruptedException e) {
            System.out.println("Restaurant interrupted");
        }
        System.out.println("Restaurant closing");
    }
}

public class RestaurantWithQueues {
    public static void main (String[] args) throws Exception {
        ExecutorService exec = Executors.newCachedThreadPool();
        Restaurant restaurant = new Restaurant(exec, 5, 2);
        exec.execute(restaurant);
        TimeUnit.SECONDS.sleep(3);
        exec.shutdownNow();
    }
}
