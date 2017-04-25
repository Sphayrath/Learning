package InnerClasses;

/**
 * Created by Vlad on 08.03.2016.
 */

import InnerClasses.Controller.Event;
import InnerClasses.Controller.GreenhouseControls;

class MyGreenhouseControls extends GreenhouseControls {
    private boolean ventilation = false;

    public class VentilationOn extends Event {
        public VentilationOn (long delayTime) {super(delayTime);}
        public void action() {ventilation = true;}
        public String toString() {return "Ventilation is on";}
    }

    public class VentilationOff extends Event {
        public VentilationOff (long delayTime) {super(delayTime);}
        public void action() {ventilation = false;}
        public String toString() {return "Ventilation is off";}
    }
}

public class GreenhouseController {
    public static void main (String[] args) {
        MyGreenhouseControls gc = new MyGreenhouseControls();
        //gc.addEvent(gc.new Bell(900));
        Event[] eventList = {
                gc.new ThermostatNight(0),
                gc.new LightOn(200),
                gc.new LightOff(400),
                gc.new WaterOn(600),
                gc.new WaterOff(800),
                gc.new VentilationOn(1000),
                gc.new VentilationOff(1200),
                gc.new ThermostatDay(1400)
        };
        gc.addEvent(gc.new Restart(2000, eventList));

        if (args.length == 1)
            gc.addEvent(gc.new Terminate(new Integer(args[0])));

        gc.run();
    }
}
