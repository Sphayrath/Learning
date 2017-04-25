package Generics;

/**
 * Created by Vlad on 16.04.2016.
 */
abstract class AbstractGenericBound<T extends AbstractGenericBound<T>> {
    abstract T AbstractTest (T arg);
    void Test (T arg) {
        System.out.println(AbstractTest(arg));
    }
}

class MyGenericBound extends AbstractGenericBound<MyGenericBound> {

    MyGenericBound AbstractTest(MyGenericBound arg) {
        return null;
    }
}

public class GenericsTest34 {
    public static void main (String[] args) {
        MyGenericBound test = new MyGenericBound();
        test.Test(new MyGenericBound());
    }
}
