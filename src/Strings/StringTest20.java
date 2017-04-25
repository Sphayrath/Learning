package Strings;

import java.util.Scanner;

/**
 * Created by Vlad on 03.04.2016.
 */
class TestClass {
    private int i;
    private long l;
    private float f;
    private double d;
    private String s;
    public TestClass (String str) {
        Scanner scanner = new Scanner(str);
        i = scanner.nextInt();
        l = scanner.nextLong();
        f = scanner.nextFloat();
        d = scanner.nextDouble();
        s = scanner.next();
    }

    public String toString () {
        return i + "\n" + l + "\n" + f +"\n" + d +"\n" + s;
    }
}

public class StringTest20 {
    public static void main (String[] args) {
        TestClass test = new TestClass("1 2 3,4 5,6 test");
        System.out.println(test);

    }
}
