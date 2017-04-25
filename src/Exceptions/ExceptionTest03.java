package Exceptions;

/**
 * Created by VFilin on 22.03.2016.
 */
public class ExceptionTest03 {
    public static void main (String[] args) {
        int[] array = new int[10];
        try {
            for (int i=0; i<11; i++) {
                array[i] = i;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Выход за пределы массива");
        }

    }
}
