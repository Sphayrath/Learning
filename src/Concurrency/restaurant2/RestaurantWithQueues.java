package Concurrency.restaurant2;

import enumerated.Course;
import enumerated.Food;

import java.util.*;
import java.util.concurrent.*;

/**
 * Created by Vlad on 25.02.2017.
 */

//Заказ передается официанту, который передает его повару

class Order {
    private static int counter = 0;
    private final int id = counter++;
    private final Customer customer;
    private final Food food;
    public Order(Customer customer, Food food) {
        this.customer = customer;
        this.food = food;
    }

    public Food item() {return food;}
    public Customer getCustomer() { return customer; }
    public String toString() {
        return "Order: " + id + " item: " + food + " for: " + customer;
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

    public Order getOrder() { return order; }
    public Food getFood() { return food; }
    public String toString() { return food.toString(); }
}

class OrderTicket {
    private static int counter = 0;
    private final int id = counter++;
    private final Table table;
    private List<Order> orders = new ArrayList<>();
    public OrderTicket (Table table) { this.table = table; }

    public void placeOrder (Order order) { this.orders.add(order); }

    public Table getTable() { return table; }

    public List<Order> getOrders () { return orders; }

    public String toString() {
        StringBuilder result = new StringBuilder("OrderTicket " +id + " from "+ table + ":\n");
        for (Order order : orders) {
            result.append(order + "\n");
        }
        return result.toString();
    }
}

class Table {
    private static int counter = 0;
    private final int id = counter++;
    private static final int SIZE = 4;
    BlockingQueue<Customer> customers = new ArrayBlockingQueue<>(SIZE);
    /*private WaitPerson waitPerson = null;

    public synchronized void readyForServicing(WaitPerson waitPerson) throws InterruptedException{
        if (this.waitPerson == null) {
            this.waitPerson = waitPerson;
            notifyAll();
        }
    }

    public synchronized void waitForWaiter() throws InterruptedException{
        while (waitPerson == null) wait();
    }

    public synchronized void served() throws InterruptedException{
        waitPerson = null;
    }*/

    public String toString () { return "Table " + id; }
}

class Customer implements Runnable {
    private static int counter = 0;
    private final int id = counter++;
    private Table table = null;
    private int number = 0;

    //Посетитель может получить только одно блюдо за раз
    private SynchronousQueue<Plate> placeSetting = new SynchronousQueue<>();

    public void setTable (Table table) {
        this.table = table;
    }

    public Table getTable() {
        return table;
    }

    public List<Food> takeOrder() {
        if (number > 0) return null;
        else {
            List<Food> foodList = new LinkedList<>();
            for (Course course : Course.values()) {
                foodList.add(course.randomSelection());
                number++;
            }
            return foodList;
        }
    }

    public void deliver (Plate plate) throws InterruptedException {
        placeSetting.put(plate);
    }

    public void run() {
        try {
            do {
                System.out.println(this + "eating " + placeSetting.take());
                TimeUnit.MILLISECONDS.sleep(100);
                number--;
            }
            while (number>0);
        } catch (InterruptedException e) { System.out.println(this + "interrupted"); }
        System.out.println(this + "finished meal, leaving");
        table.customers.remove(this);
    }

    public String toString() {
        return "Customer " + id + " ";
    }
}

class WaitPerson implements Runnable {
    private static int counter = 0;
    private final int id = counter++;
    private final Restaurant restaurant;
    private OrderTicket orderTicket = null;

    public WaitPerson (Restaurant restaurant) { this.restaurant = restaurant; }

    public void run () {
        try {
            while (!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(200);
                //Прием заказов
                Table table = restaurant.tables.take();
                for (Customer customer : table.customers) {
                    List<Food> foodList = customer.takeOrder();
                    if (foodList != null) {
                        if (orderTicket == null)
                            orderTicket = new OrderTicket(table);
                        for (Food food : foodList)
                            orderTicket.placeOrder(new Order(customer, food));
                    }
                }
                restaurant.tables.put(table);
                if (orderTicket!=null) {
                    System.out.println("WaitPerson " + id + " received " + orderTicket);
                    TimeUnit.MILLISECONDS.sleep(50);
                    restaurant.orderTickets.put(orderTicket);
                    orderTicket=null;
                }

                //Доставка заказа
                if (!restaurant.plates.isEmpty()) {
                    Plate plate = restaurant.plates.take();
                    System.out.println(this + "received " + plate + " delivering to "
                            + plate.getOrder().getCustomer());
                    TimeUnit.MILLISECONDS.sleep(50);
                    plate.getOrder().getCustomer().deliver(plate);
                }
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
                OrderTicket orderTicket = restaurant.orderTickets.take();
                List<Order> orders = orderTicket.getOrders();
                //Время на подготовку заказа
                for (Order order : orders) {
                    TimeUnit.MILLISECONDS.sleep(random.nextInt(500));
                    Plate plate = new Plate(order, order.item());
                    restaurant.plates.put(plate);
                }
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
    BlockingQueue<Table> tables = new LinkedBlockingQueue<>();
    BlockingQueue<OrderTicket> orderTickets = new LinkedBlockingQueue<>();
    BlockingQueue<Plate> plates = new LinkedBlockingQueue<>();
    public Restaurant (ExecutorService exec, int nWaitPersons,
                       int nChefs, int nTables) {
        this.exec = exec;
        for (int i=0; i<nTables; i++)
            tables.add(new Table());

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
                //Приходит новый посетитель, садится за свободный стол
                Customer customer = new Customer();
                for (Table table : tables)
                    if (table.customers.offer(customer)) {
                        customer.setTable(table);
                        break;
                    }
                if (customer.getTable() == null)
                    System.out.println("Restaurant is full. No place for " + customer);
                else {
                    exec.execute(customer);
                    System.out.println(customer + " set down at " + customer.getTable());
                }
                TimeUnit.MILLISECONDS.sleep(90);
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
        Restaurant restaurant = new Restaurant(exec, 4, 2, 3);
        exec.execute(restaurant);
        TimeUnit.SECONDS.sleep(3);
        exec.shutdownNow();
    }
}
