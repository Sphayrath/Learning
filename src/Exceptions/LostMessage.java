package Exceptions;

/**
 * Created by Vlad on 27.03.2016.
 */
class VeryImportantException extends Exception {
    public String toString () {
        return "Очень важное исключение!";
    }
}

class HoHumException extends Exception {
    public String toString () {
        return "Второстепенное исключение!";
    }
}

public class LostMessage {
    void f() throws  VeryImportantException {
        throw new VeryImportantException();
    }

    void dispose() throws HoHumException {
        throw new HoHumException();
    }

    public static void main (String[] args) {
        try {
            LostMessage lm = new LostMessage();
            try {
                lm.f();
            } finally {
                try {
                    lm.dispose();
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
