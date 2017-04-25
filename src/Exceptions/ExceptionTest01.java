package Exceptions;

/**
 * Created by VFilin on 22.03.2016.
 */
public class ExceptionTest01 {
    public static void main (String[] args) {
        try {
            throw new Exception("Ololo!11!1");
        } catch (Exception e) {
            e.printStackTrace(System.out);
        } finally {
            System.out.println("Exception!!");
        }
    }
}
