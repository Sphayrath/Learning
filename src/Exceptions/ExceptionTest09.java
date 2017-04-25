package Exceptions;

/**
 * Created by VFilin on 25.03.2016.
 */
class MyException1 extends Exception {}
class MyException2 extends Exception {}
class MyException3 extends Exception {}

public class ExceptionTest09 {
    public static void Exceptions (int i) throws MyException1, MyException2, MyException3 {
        switch (i){
            case 0: throw new MyException1();
            case 1: throw new MyException2();
            case 2: throw new MyException3();
        }
    }
    public static void main (String[] args) {
        try {
            Exceptions(0);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        } finally {
            System.out.println("Finally");
        }
    }
}

