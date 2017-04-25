//: net/mindview/util/FiveTuple.java
package net.mindview.util;

public class FiveTuple<A,B,C,D,E>
extends FourTuple<A,B,C,D> {
    public final E fifth;

    public FiveTuple(A a, B b, C c, D d, E e) {
      super(a, b, c, d);
      fifth = e;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        if (fifth != null)
            result = 37 * result + fifth.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if ((obj instanceof FiveTuple) &&
                (first.equals(((FiveTuple) obj).first)) &&
                (second.equals(((FiveTuple) obj).second)) &&
                (third.equals(((FiveTuple) obj).third)) &&
                (fourth.equals(((FiveTuple) obj).fourth)) &&
                (fifth.equals(((FiveTuple) obj).fifth)))
            result = true;
        return result;
    }

    public String toString() {
        return "(" + first + ", " + second + ", " +
                third + ", " + fourth + ", " + fifth + ")";
    }
} ///:~
