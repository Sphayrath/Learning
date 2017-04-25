package Exceptions;

/**
 * Created by Vlad on 27.03.2016.
 */
class Exceptions {
    public void f () throws MyException1 {
        throw new MyException1();
    }

    public void g () throws RuntimeException {
        try {
            f();
        } catch (MyException1 e) {
            throw new RuntimeException(e);
        }
    }
}

public class ExceptionTest10 {
    public static void main (String[] args) {
        Exceptions exceptions = new Exceptions();

        try {
            exceptions.g();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }

    }
}
