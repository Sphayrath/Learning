//: net/mindview/util/Tuple.java
// Tuple library using type argument inference.
package net.mindview.util;

public class Tuple {
    public static <A,B> TwoTuple<A,B> tuple(A a, B b) {
        return new TwoTuple<A,B>(a, b);
    }

    public static <A,B,C> ThreeTuple<A,B,C> tuple(A a, B b, C c) {
        return new ThreeTuple<A,B,C>(a, b, c);
    }

    public static <A,B,C,D> FourTuple<A,B,C,D> tuple(A a, B b, C c, D d) {
        return new FourTuple<A,B,C,D>(a, b, c, d);
    }

    public static <A,B,C,D,E> FiveTuple<A,B,C,D,E> tuple(A a, B b, C c, D d, E e) {
        return new FiveTuple<A,B,C,D,E>(a, b, c, d, e);
    }

    public static void main (String[] args) {
        //TwoTupleTest
        TwoTuple<Integer,String> tuple1 = tuple(1, "tuple1");
        TwoTuple<Integer,String> tuple2 = tuple(1, "tuple1");
        TwoTuple<Integer,String> tuple3 = tuple(0, "tuple3");
        System.out.println(tuple1.hashCode() + ", " + tuple2.hashCode() +
                            ", " + tuple3.hashCode());
        System.out.println(tuple1.equals(tuple2) + ", " + tuple1.equals(tuple3));
        System.out.println(tuple1.compareTo(tuple2) + ", " + tuple1.compareTo(tuple3));
        System.out.println();

        //ThreeTupleTest
        ThreeTuple<Integer,String,Character> tuple4 = tuple(1, "tuple1", 'a');
        ThreeTuple<Integer,String,Character> tuple5 = tuple(1, "tuple1", 'a');
        ThreeTuple<Integer,String,Character> tuple6 = tuple(0, "tuple3", 'b');
        System.out.println(tuple4.hashCode() + ", " + tuple5.hashCode() +
                ", " + tuple6.hashCode());
        System.out.println(tuple4.equals(tuple5) + ", " + tuple4.equals(tuple6));
        System.out.println(tuple4.compareTo(tuple5) + ", " + tuple5.compareTo(tuple6));
        System.out.println();
    }
} ///:~
