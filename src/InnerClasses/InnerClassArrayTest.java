package InnerClasses;

/**
 * Created by Vlad on 06.03.2016.
 */
interface U {
    public void method0 ();
    public void method1 ();
    public void method2 ();
}

class A {
    public U getU () {
        return new U () {
            public void method0 () {System.out.print("Method0 ");}
            public void method1 () {System.out.print("Method1 ");}
            public void method2 () {System.out.print("Method2 ");}
        };
    }
}

class B {
    U[] uArray;
    int length;

    public B (int length) {
        uArray = new U[length];
        this.length = length;
    }

    public void put (U u) {
        for (int i=0; i<length; i++)
            if (uArray[i] == null) {
                uArray[i] = u;
                break;
            }
    }

    public void clear (U u) {
        for (int i=0; i<length; i++)
            if (uArray[i] == u) {
                uArray[i] = null;
                break;
            }
    }

    public void show () {
        for (int i=0; i<length; i++)
            if (uArray[i]!=null) {
                uArray[i].method0();
                uArray[i].method1();
                uArray[i].method2();
                System.out.println();
            } else System.out.println("null");
    }
}

public class InnerClassArrayTest {
    public static void main (String[] args) {
        A a1 = new A();
        A a2 = new A();
        A a3 = new A();
        B b = new B(4);

        U u1 = a2.getU();

        b.put(a1.getU());
        b.put(u1);
        b.put(a3.getU());

        b.show();

        b.clear(u1);

        b.show();

    }
}
