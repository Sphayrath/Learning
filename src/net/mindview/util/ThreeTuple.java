//: net/mindview/util/ThreeTuple.java
package net.mindview.util;

public class ThreeTuple<A,B,C> extends TwoTuple<A,B> {
    public final C third;
    public ThreeTuple(A a, B b, C c) {
        super(a, b);
        third = c;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        if (third != null)
            result = 37 * result + third.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if ((obj instanceof ThreeTuple) &&
                (first.equals(((ThreeTuple) obj).first)) &&
                (second.equals(((ThreeTuple) obj).second)) &&
                (third.equals(((ThreeTuple) obj).third)))
            result = true;
        return result;
    }

    public String toString() {
        return "(" + first + ", " + second + ", " + third +")";
    }
} ///:~
