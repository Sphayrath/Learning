//: net/mindview/util/FourTuple.java
package net.mindview.util;

public class FourTuple<A,B,C,D> extends ThreeTuple<A,B,C> {
    public final D fourth;

    public FourTuple(A a, B b, C c, D d) {
      super(a, b, c);
      fourth = d;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        if (fourth != null)
            result = 37 * result + fourth.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if ((obj instanceof FourTuple) &&
                (first.equals(((FourTuple) obj).first)) &&
                (second.equals(((FourTuple) obj).second)) &&
                (third.equals(((FourTuple) obj).third)) &&
                (fourth.equals(((FourTuple) obj).fourth)))
            result = true;
        return result;
    }

    public String toString() {
      return "(" + first + ", " + second + ", " +
        third + ", " + fourth + ")";
    }
} ///:~
