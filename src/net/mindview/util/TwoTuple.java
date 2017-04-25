//: net/mindview/util/TwoTuple.java
package net.mindview.util;

public class TwoTuple<A,B> implements Comparable {
    public final A first;
    public final B second;

    public TwoTuple(A a, B b) { first = a; second = b; }

    @Override
    public int hashCode() {
        int result = 17;
        if (first!=null)
            result = 37 * result + first.hashCode();
        if (second!=null)
            result = 37 * result + second.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if ((obj instanceof TwoTuple) &&
                (first.equals(((TwoTuple) obj).first)) &&
                (second.equals(((TwoTuple) obj).second)))
            result = true;
        return result;
    }

    @Override
    public int compareTo(Object o) {
        String thisClass = getClass().getSimpleName();
        String oClass = o.getClass().getSimpleName();
        int firstCompare = thisClass.compareTo(oClass);
        if (firstCompare != 0)
            return firstCompare;

        int result = 0;
        if (hashCode() < o.hashCode()) result = -1;
        if (hashCode() > o.hashCode()) result = 1;
        return result;
    }

    public String toString() {
        return "(" + first + ", " + second + ")";
    }
} ///:~
