package Exceptions;

import net.mindview.util.CountingGenerator;
import net.mindview.util.Generated;

import java.util.Arrays;

/**
 * Created by Vlad on 01.05.2016.
 */
public class ArraysTest20 {
    public static void main(String[] args) {
        Integer[][] array1 = new Integer[10][];
        Integer[][] array2 = new Integer[10][];

        for (int i=0; i<10; i++) {
            array1[i] = Generated.array(new Integer[10], new CountingGenerator.Integer(), 10);
            array2[i] = Generated.array(new Integer[10], new CountingGenerator.Integer(), 10);
        }

        System.out.println(Arrays.deepToString(array1));
        System.out.println(Arrays.deepEquals(array1, array2));
        array2[2][2] = 10;
        System.out.println(Arrays.deepEquals(array1, array2));
    }
}
