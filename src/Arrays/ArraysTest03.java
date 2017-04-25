package Arrays;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by VFilin on 26.04.2016.
 */
class Random2DArray {
    private double[][] array;

    public Random2DArray (int i, int j, double start, double end) throws ArrayIndexOutOfBoundsException {
        this.fillArray(i, j, start, end);
    }

    public void fillArray (int i, int j, double start, double end) throws ArrayIndexOutOfBoundsException {
        if ((i<=0)||(j<=0)) throw new ArrayIndexOutOfBoundsException();
        array = new double[i][j];
        for (int ii=0; ii<i; ii++)
            for (int jj=0; jj<j; jj++)
                array[ii][jj] = ThreadLocalRandom.current().nextDouble(start,end);
    }

    public void showArray() {
        for (int i=0; i<array.length; i++) {
            for (int j=0; j<array[i].length; j++ )
                System.out.format("%5.2f", array[i][j]);
            System.out.println();
        }
    }
}

public class ArraysTest03 {
    public static void main (String[] args) {
        Random2DArray array = null;

        try {
            array = new Random2DArray(2, 3, 2.47, 10.8);
        } catch (ArrayIndexOutOfBoundsException e) {e.printStackTrace(System.out);}

        try {
            array.showArray();
        } catch (NullPointerException e) {e.printStackTrace(System.out);};

    }

}
