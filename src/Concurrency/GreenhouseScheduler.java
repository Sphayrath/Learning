package Concurrency;

import java.util.*;
import java.util.concurrent.*;

/**
 * Created by VFilin on 02.02.2017.
 */
public class GreenhouseScheduler implements Runnable {
    private volatile boolean light = false;
    private volatile boolean water = false;
    private String thermostat = "Day";
    DelayQueue<DelayedTask> queue = new DelayQueue<>();

    public synchronized String getThermostat () {
        return thermostat;
    }

    public synchronized void setThermostat (String value) {
        thermostat = value;
    }

    /*ScheduledThreadPoolExecutor scheduler = new ScheduledThreadPoolExecutor(10);
    public void schedule (Runnable event, long delay) {
        scheduler.schedule (event, delay, TimeUnit.MILLISECONDS);
    }

    public void repeat (Runnable event, long initialDelay, long period) {
        scheduler.scheduleAtFixedRate (event, initialDelay, period, TimeUnit.MILLISECONDS);
    }
*/
    public void schedule (DelayedTask event) {
        queue.put(event);
    }


    class DelayedTask implements Delayed, Runnable {
        private final int delta;
        private final long trigger;
        public void run () {};
        public DelayedTask (int delayInMilliseconds) {
            delta = delayInMilliseconds;
            trigger = System.nanoTime() + TimeUnit.NANOSECONDS.convert(delta, TimeUnit.MILLISECONDS);
        }

        public long getDelay (TimeUnit unit) {
            return unit.convert(trigger - System.nanoTime(), TimeUnit.NANOSECONDS);
        }

        public int compareTo (Delayed arg) {
            DelayedTask that = (DelayedTask)arg;
            if (trigger < that.trigger) return -1;
            if (trigger > that.trigger) return 1;
            return 0;
        }
    }

    class LightOn extends DelayedTask {
        public LightOn (int delayInMilliseconds) { super(delayInMilliseconds); }
        public void run() {
            System.out.println("Turning on lights");
            light = true;
        }
    }

    class LightOff extends DelayedTask {
        public LightOff (int delayInMilliseconds) { super(delayInMilliseconds); }
        public void run() {
            System.out.println("Turning off lights");
            light = false;
        }
    }

    class WaterOn extends DelayedTask {
        public WaterOn (int delayInMilliseconds) { super(delayInMilliseconds); }
        public void run() {
            System.out.println("Turning water on");
            water = true;
        }
    }

    class WaterOff extends DelayedTask {
        public WaterOff (int delayInMilliseconds) { super(delayInMilliseconds); }
        public void run() {
            System.out.println("Turning water off");
            water = false;
        }
    }

    class ThermostatNight extends DelayedTask {
        public ThermostatNight (int delayInMilliseconds) { super(delayInMilliseconds); }
        public void run() {
            System.out.println("Thermostat to night setting");
            setThermostat("Night");
        }
    }

    class ThermostatDay extends DelayedTask {
        public ThermostatDay (int delayInMilliseconds) { super(delayInMilliseconds); }
        public void run() {
            System.out.println("Thermostat to day setting");
            setThermostat("Day");
        }
    }

    class Bell extends DelayedTask {
        public Bell (int delayInMilliseconds) { super(delayInMilliseconds); }
        public void run() { System.out.println("Bing!"); }
    }

    class Terminate extends DelayedTask {
        private ExecutorService exec;
        public Terminate (int delayInMilliseconds, ExecutorService exec) {
            super(delayInMilliseconds);
            this.exec = exec;
        }
        public void run() {
            System.out.println("Terminating");
            exec.shutdownNow();
            new Thread() {
                public void run() {
                    for (DataPoint d : data)
                        System.out.println(d);
                }
            }.start();
        }
    }

    static class DataPoint {
        final Calendar time;
        final float temperature;
        final float humidity;
        public DataPoint (Calendar d, float temp, float hum) {
            time = d;
            temperature = temp;
            humidity = hum;
        }

        public String toString() {
            return time.getTime() + java.lang.String.format(" temperature: %1$.1f humidity: %2$.2f",
                    temperature, humidity);
        }
    }

    private Calendar lastTime = Calendar.getInstance();
    {
        lastTime.set(Calendar.MINUTE, 30);
        lastTime.set(Calendar.SECOND, 0);
    }

    private float lastTemp = 65.0f;
    private int tempDirection = +1;
    private float lastHumidity = 50.0f;
    private int humidityDirection = +1;
    private Random random = new Random(47);
    List<DataPoint> data = Collections.synchronizedList(new ArrayList<DataPoint>());

    class CollectData extends DelayedTask implements Runnable {
        public CollectData (int delayInMilliseconds) { super(delayInMilliseconds); }
        public void run() {
            System.out.println("Collecting data");
            synchronized (GreenhouseScheduler.this) {
                //Имитировать более длинный интервал
                lastTime.set(Calendar.MINUTE, lastTime.get(Calendar.MINUTE)+30);
                //Направление меняется в одном из пяти случаев
                if (random.nextInt(5) == 4) tempDirection = -tempDirection;
                //Сохранение предыдущего значения
                lastTemp = lastTemp + tempDirection * (1.0f + random.nextFloat());
                if (random.nextInt(5) == 4) humidityDirection = -humidityDirection;
                lastHumidity = lastHumidity + humidityDirection * random.nextFloat();
                //Calendar необходимо копировать, в противном случае все объекты DataPoint
                //будут содержать ссылки на то же значение lastTime. Для простейших бъектов,
                //таких как Calendar - достаточно вызова Clone().
                data.add(new DataPoint((Calendar)lastTime.clone(), lastTemp, lastHumidity));
            }
        }
    }

    public void run () {
        try {
            while (!Thread.interrupted()) {
                queue.take().run(); //Запуск задач из текущего потока
            }
        } catch (InterruptedException e) {}
        System.out.println("Finished scheduled tasks");
    }

    public static void main (String[] args) {
        GreenhouseScheduler gh = new GreenhouseScheduler();
        ExecutorService exec = Executors.newCachedThreadPool();
        int endTime = 5000;
        gh.schedule(gh.new Terminate(endTime, exec));
        for (int i=0; i<endTime; i=i+1000) gh.schedule(gh.new Bell(i));
        for (int i=0; i<endTime; i=i+2000) gh.schedule(gh.new ThermostatNight(i));
        for (int i=0; i<endTime; i=i+200) gh.schedule(gh.new LightOn(i));
        for (int i=0; i<endTime; i=i+400) gh.schedule(gh.new LightOff(i));
        for (int i=0; i<endTime; i=i+600) gh.schedule(gh.new WaterOn(i));
        for (int i=0; i<endTime; i=i+800) gh.schedule(gh.new WaterOff(i));
        for (int i=0; i<endTime; i=i+1400) gh.schedule(gh.new ThermostatDay(i));
        for (int i=500; i<endTime; i=i+500) gh.schedule(gh.new CollectData(i));
        /*gh.repeat(gh.new Bell(), 0, 1000);
        gh.repeat(gh.new ThermostatNight(), 0, 2000);
        gh.repeat(gh.new LightOn(), 0, 200);
        gh.repeat(gh.new LightOff(), 0, 400);
        gh.repeat(gh.new WaterOn(), 0, 600);
        gh.repeat(gh.new WaterOff(), 0, 800);
        gh.repeat(gh.new ThermostatDay(), 0, 1400);
        gh.repeat(gh.new CollectData(), 500, 500);*/
        exec.execute (gh);
    }
}
