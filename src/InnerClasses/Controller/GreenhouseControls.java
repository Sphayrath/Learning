package InnerClasses.Controller;

/**
 * Created by Vlad on 08.03.2016.
 */
public class GreenhouseControls extends Controller {
    private boolean light = false;
    private boolean water = false;
    private String thermostat = "Day";

    public class LightOn extends Event {
        public LightOn (long delayTime) {super(delayTime);}
        public void action() {light = true;}
        public String toString() {return "Light is on";}
    }

    public class LightOff extends Event {
        public LightOff (long delayTime) {super(delayTime);}
        public void action() {light = false;}
        public String toString() {return "Light is off";}
    }

    public class WaterOn extends Event {
        public WaterOn (long delayTime) {super(delayTime);}
        public void action() {water = true;}
        public String toString() {return "Water is on";}
    }

    public class WaterOff extends Event {
        public WaterOff (long delayTime) {super(delayTime);}
        public void action() {water = false;}
        public String toString() {return "Water is off";}
    }

    public class ThermostatNight extends Event {
        public ThermostatNight (long delayTime) {super(delayTime);}
        public void action() {thermostat = "Night";}
        public String toString() {return "Thermostat is set on 'Night'";}
    }

    public class ThermostatDay extends Event {
        public ThermostatDay (long delayTime) {super(delayTime);}
        public void action() {thermostat = "Day";}
        public String toString() {return "Thermostat is set on 'Day'";}
    }

    public class Bell extends Event {
        public Bell (long delayTime) {super(delayTime);}
        public void action() {
            addEvent (new Bell(delayTime));
        }
        public String toString() {return "Bam!";}
    }

    public class Restart extends Event {
        private Event[] eventList;

        public Restart (long delayTime, Event[] eventList) {
            super(delayTime);
            this.eventList = eventList;
            for (Event e : eventList)
                addEvent(e);
        }

        public void action() {
            for (Event e : eventList) {
                e.start();
                addEvent(e);
            }
            start();
            addEvent(this);
        }

        public String toString() {return "Restarting system";}
    }

    public class Terminate extends Event {
        public Terminate (long delayTime) {super(delayTime);}
        public void action() {System.exit(0);}
        public String toString () {return "Shutting down";}
    }
}
