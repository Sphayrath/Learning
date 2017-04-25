package Concurrency;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.*;

/**
 * Created by VFilin on 16.03.2017.
 */
class Car {
    private final int id;
    private boolean engine = false, driveTrain = false, wheels = false,
                exhaust =false, carcase = false, flaps = false;
    public Car (int id) { this.id = id; }
    public Car() { id = -1; }

    public synchronized int getId() { return id; }
    public synchronized void addEngine() { engine = true; }
    public synchronized void addDriveTrain() { driveTrain = true; }
    public synchronized void addWheels() { wheels = true; }
    public synchronized void addExhaust() { exhaust = true; }
    public synchronized void addCarcase() { carcase = true; }
    public synchronized void addFlaps() { flaps = true; }
    public synchronized String toString() {
        return "Car " + id +" [" + " engine: " + engine + " driveTrain: " + driveTrain
                + " wheels: " + wheels + " exhaust: " + exhaust + " carcase: " + carcase
                +" flaps: " + flaps +" ]";
    }
}

class CarQueue extends LinkedBlockingQueue<Car> {}

class RobotPool {
    //Незаметно предотвращает использование идентичных элементов
    private Set<Robot> pool = new HashSet<>();

    public synchronized void add (Robot robot) {
        pool.add(robot);
        notifyAll();
    }

    public synchronized void hire (Class<? extends Robot> robotType, Assembler d) throws InterruptedException {
        for (Robot r : pool)
            if (r.getClass().equals(robotType)) {
                pool.remove(r);
                r.assignAssembler(d);
                r.engage(); //Включение для выполнения задания
                return;
            }
        wait(); //Нет доступных кандидатов
        hire(robotType, d); //Повторная попытка с рекурсией
    }

    public synchronized void release(Robot r) { add(r); }
}

class ChassisBuilder implements Runnable {
    private CarQueue carQueue;
    private int counter = 0;
    public ChassisBuilder (CarQueue carQueue) { this.carQueue = carQueue; }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(500);
                //Создание рамы
                Car car = new Car(counter++);
                System.out.println("ChassisBuilder created " + car);
                //Помещение в очередь
                carQueue.put(car);
            }
        } catch (InterruptedException e) {
            System.out.println("Interrupted: ChassisBuilder");
        }
        System.out.println("ChassisBuilder off");
    }
}

class Assembler implements Runnable {
    private CarQueue chassisQueue, finishingQueue;
    private Car car;
    private CyclicBarrier barrier = new CyclicBarrier(7);
    private RobotPool robotPool;
    public Assembler (CarQueue cq, CarQueue fq, RobotPool rp) {
        chassisQueue = cq;
        finishingQueue = fq;
        robotPool = rp;
    }

    public Car car() { return car; }
    public CyclicBarrier barrier() { return barrier; }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                //Блокируется пока рама не будет доступна:
                car = chassisQueue.take();
                //Привлечение роботов для выполнения работы:
                robotPool.hire(EngineRobot.class, this);
                robotPool.hire(DriveTrainRobot.class, this);
                robotPool.hire(WheelRobot.class, this);
                robotPool.hire(ExhaustRobot.class, this);
                robotPool.hire(CarcaseRobot.class, this);
                robotPool.hire(FlapRobot.class, this);
                barrier.await(); //Пока роботы не закончат работу
                //Машина помещается в очередь finishingQueue для дальнейшей работы
                finishingQueue.put(car);
            }
        } catch (InterruptedException e) {
            System.out.println("Exiting Assembler via interrupt");
        } catch (BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Assembler off");
    }
}

class Reporter implements Runnable {
    private CarQueue carQueue;
    public Reporter (CarQueue carQueue) { this.carQueue = carQueue; }
    public void run() {
        try {
            while (!Thread.interrupted())
                System.out.println(carQueue.take());
        } catch (InterruptedException e) {
            System.out.println("Exiting Reporter via interrupt");
        }
        System.out.println("Reporter off");
    }
}

abstract class Robot implements Runnable {
    private RobotPool pool;
    protected Assembler assembler;
    private boolean engage = false;
    public Robot (RobotPool pool) { this.pool = pool; }

    public Robot assignAssembler (Assembler assembler) {
        this.assembler = assembler;
        return this;
    }

    public synchronized void engage() {
        engage = true;
        notifyAll();
    }

    private synchronized void powerDown() throws InterruptedException {
        engage = false;
        assembler = null;   //Отключение от Assembler
        pool.release(this); //Возвращение в пул
        while (engage == false) wait(); //Выключение питания
    }

    //Часть run(), отличная для каждого робота:
    abstract protected void performService();

    public void run() {
        try {
            powerDown(); //Ожидать пока не понадобится
            while (!Thread.interrupted()) {
                performService();
                assembler.barrier().await();    //Синхронизация
                powerDown();    //Задание выполнено
            }
        } catch (InterruptedException e) {
            System.out.println("Exiting " + this + " via interrupt");
        } catch (BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
        System.out.println(this + " off");
    }

    public String toString() { return getClass().getName(); }
}

class EngineRobot extends Robot {
    public EngineRobot(RobotPool pool) { super(pool); }
    protected void performService() {
        System.out.println(this + " installing engine");
        assembler.car().addEngine();
    }
}

class DriveTrainRobot extends Robot {
    public DriveTrainRobot(RobotPool pool) { super(pool); }
    protected void performService() {
        System.out.println(this + " installing DriveTrain");
        assembler.car().addDriveTrain();
    }
}

class WheelRobot extends Robot {
    public WheelRobot(RobotPool pool) { super(pool); }
    protected void performService() {
        System.out.println(this + " installing Wheels");
        assembler.car().addWheels();
    }
}

class ExhaustRobot extends Robot {
    public ExhaustRobot(RobotPool pool) { super(pool); }
    protected void performService() {
        System.out.println(this + " installing Exhaust System");
        assembler.car().addExhaust();
    }
}

class CarcaseRobot extends Robot {
    public CarcaseRobot(RobotPool pool) { super(pool); }
    protected void performService() {
        System.out.println(this + " installing Carcase");
        assembler.car().addCarcase();
    }
}

class FlapRobot extends Robot {
    public FlapRobot(RobotPool pool) { super(pool); }
    protected void performService() {
        System.out.println(this + " installing Flaps");
        assembler.car().addFlaps();
    }
}

public class CarBuilder {
    public static void main(String[] args) throws Exception {
        CarQueue chassisQueue = new CarQueue(),
                 finishingQueue = new CarQueue();
        ExecutorService exec = Executors.newCachedThreadPool();
        RobotPool robotPool = new RobotPool();
        exec.execute(new EngineRobot(robotPool));
        exec.execute(new DriveTrainRobot(robotPool));
        exec.execute(new WheelRobot(robotPool));
        exec.execute(new ExhaustRobot(robotPool));
        exec.execute(new CarcaseRobot(robotPool));
        exec.execute(new FlapRobot(robotPool));
        exec.execute(new Assembler(chassisQueue, finishingQueue, robotPool));
        exec.execute(new Reporter(finishingQueue));
        //Создание рам приводит конвейер в движение:
        exec.execute(new ChassisBuilder(chassisQueue));
        TimeUnit.SECONDS.sleep(7);
        exec.shutdownNow();
    }
}
