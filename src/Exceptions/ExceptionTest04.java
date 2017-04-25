package Exceptions;

/**
 * Created by VFilin on 22.03.2016.
 */
class MyException extends Exception {
    String msg;
    public MyException (String msg) {this.msg = msg;}
    public void printMsg () {System.out.println(msg);}
}

public class ExceptionTest04 {
    public void f() throws MyException {
        throw new MyException("Exception in ExceptionTest04.f()");
    }
    public static void main (String[] args) {
        ExceptionTest04 obj = new ExceptionTest04();
        try {
            obj.f();
        } catch (MyException e) {
            e.printMsg();
        }
    }
}
