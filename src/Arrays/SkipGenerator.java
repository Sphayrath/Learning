package Arrays;

import net.mindview.util.Generator;

/**
 * Created by VFilin on 28.04.2016.
 */
public class SkipGenerator {
    public static class Boolean implements Generator<java.lang.Boolean> {
        private int dif = 1;
        private boolean value = false;

        public Boolean (int dif) {if (dif > 1) this.dif = dif;}
        public java.lang.Boolean next() {
            for (int i=0; i<dif; i++)
                value = !value; // Just flips back and forth
            return value;
        }
    }

    public static class Byte implements Generator<java.lang.Byte> {
        private byte value = 0;
        private int dif = 1;

        public Byte (int dif) {if (dif > 1) this.dif = dif;}
        public java.lang.Byte next() {
            for (int i=0; i<dif; i++)
                value++;
            return value;
        }
    }
    static char[] chars = ("abcdefghijklmnopqrstuvwxyz" +
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();
    public static class Character implements Generator<java.lang.Character> {
        int index = -1;
        private int dif = 1;

        public Character (int dif) {
            if (dif > 1) this.dif = dif;
            index = -dif;
        }

        public java.lang.Character next() {
            for (int i=0; i<dif; i++)
                index = (index + 1) % chars.length;
            return chars[index];
        }
    }
    /*
    public static class String implements Generator<java.lang.String> {
        private int length = 7;
        Generator<java.lang.Character> cg = new Character();
        public String() {}
        public String(int length) { this.length = length; }
        public java.lang.String next() {
            char[] buf = new char[length];
            for(int i = 0; i < length; i++)
                buf[i] = cg.next();
            return new java.lang.String(buf);
        }
    }*/

    public static class Short implements Generator<java.lang.Short> {
        private short value = -1;
        private short dif = 1;

        public Short (short dif) {
            if (dif > 1) this.dif = dif;
            value = (short)(-dif);
        }

        public java.lang.Short next() {
            value = (short)(dif+value); //Говнокод детектед
            return value;
        }
    }

    public static class Integer implements Generator<java.lang.Integer> {
        private int value = -1;
        private int dif = 1;

        public Integer (int dif) {
            if (dif > 1) this.dif = dif;
            value = -dif;
        }

        public java.lang.Integer next() {
            value = value+dif;
            return value;
        }
    }

    public static class Long implements Generator<java.lang.Long> {
        private long value = -1;
        private long dif = 1;

        public Long (long dif) {
            if (dif > 1) this.dif = dif;
            value = -dif;
        }

        public java.lang.Long next() {
            value = value+dif;
            return value;
        }
    }

    public static class Float implements Generator<java.lang.Float> {
        private float value = -1;
        private float dif = 1;

        public Float (float dif) {
            if (dif > 1) this.dif = dif;
            value = -dif;
        }

        public java.lang.Float next() {
            value = value+dif;
            return value;
        }
    }
    public static class Double implements Generator<java.lang.Double> {
        private double value = -1.0;
        private double dif = 1.0;

        public Double (double dif) {
            if (dif > 1.0) this.dif = dif;
            value = -dif;
        }

        public java.lang.Double next() {
            value = value+dif;
            return value;
        }
    }
} ///:~

