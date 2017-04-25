package Concurrency;

/**
 * Created by Vlad on 18.02.2017.
 */
public class Fat {
    private volatile double d;
    private static int counter = 0;
    private final int id = counter++;
    public Fat() {
        for (int i=1; i<1000; i++)
            d += (Math.PI + Math.E) / (double)i;
    }

    public void operation() {System.out.println(this);}
    public String toString() {return "Fat id: " + id; }
}
