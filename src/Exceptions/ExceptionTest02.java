package Exceptions;

/**
 * Created by VFilin on 22.03.2016.
 */
public class ExceptionTest02 {
    public static void main (String[] args) {
        String s = null;
        char c;
        try {
            c = s.charAt(0);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }
}
