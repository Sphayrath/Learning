package Exceptions;

/**
 * Created by Vlad on 27.03.2016.
 */
class ExceptionA extends Exception {
    public String toString () {
        return "ExceptionA";
    }
}

class ExceptionB extends ExceptionA {
    public String toString () {
        return "ExceptionB";
    }
}

class ExceptionC extends ExceptionB {
    public String toString () {
        return "ExceptionC";
    }
}

class A {
    public void f () throws ExceptionA {
        throw new ExceptionA();
    }
}

class B extends A {
    public void f () throws ExceptionB {
        throw new ExceptionB();
    }
}

class C extends B {
    public void f () throws ExceptionC {
        throw new ExceptionC();
    }
}
public class ExceptionTest25 {
    public static void main(String[] args) {
        A a = new C();
        try {
            a.f();
        } catch (ExceptionA e) {
            System.out.println(e);
        }
    }
}
