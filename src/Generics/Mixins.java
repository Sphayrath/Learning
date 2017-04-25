package Generics;

import java.util.Date;

/**
 * Created by Vlad on 16.04.2016.
 */
interface TimeStamped {long getStamp();}
interface SerialNumbered {long getSerialNumber();}
interface Colored {
    void setColor(String color);
    String getColor();
}
interface Basic {
    void set(String val);
    String get();
}

class TimeStampedImpl implements TimeStamped {
    private final long timeStamp;
    public TimeStampedImpl() {
        timeStamp = new Date().getTime();
    }
    public long getStamp() {return timeStamp;}
}

class SerialNumberedImpl implements SerialNumbered {
    private static long counter = 1;
    private final long serialNumber = counter++;
    public long getSerialNumber() {return serialNumber;}
}

class ColoredImpl implements Colored {
    private String color = "No color";
    public void setColor (String color) {this.color = color;}
    public String getColor() {return color;}
}

class BasicImpl implements Basic {
    private String value;
    public void set (String val) {value=val;}
    public String get() {return value;}
}

class Mixin extends BasicImpl
        implements TimeStamped, SerialNumbered, Colored {
    private TimeStamped timeStamp = new TimeStampedImpl();
    private SerialNumbered serialNumber = new SerialNumberedImpl();
    private Colored color = new ColoredImpl();

    public void setColor (String color) {
        this.color.setColor(color);
    }
    public String getColor() {return color.getColor();}
    public long getStamp () {return timeStamp.getStamp();}
    public long getSerialNumber() {return serialNumber.getSerialNumber();}
}


public class Mixins {
    public static void main (String[] args) {
        Mixin mixin1 = new Mixin(), mixin2 = new Mixin();
        mixin1.set("Test1");
        mixin2.set("Test2");
        mixin2.setColor("Blue");

        System.out.println(mixin1.getColor() + " " + mixin1.get() +
                " " + mixin1.getStamp() + " " + mixin1.getSerialNumber());
        System.out.println(mixin2.getColor() + " " + mixin2.get() +
                " " + mixin2.getStamp() + " " + mixin2.getSerialNumber());
    }
}
