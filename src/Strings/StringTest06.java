package Strings;

/**
 * Created by Vlad on 28.03.2016.
 */
class StringFormat {
    private int i;
    private long l;
    private float f;
    private double d;

    public StringFormat (int i, long l, float f, double d) {
        this.i=i;
        this.l=l;
        this.f=f;
        this.d=d;
    }

    public String toString() {
        return String.format("%-5d %-5d %-5.2f %-5.2f", i, l, f, d);
    }
}

public class StringTest06 {
    public static void main (String[] args) {
        StringFormat sf = new StringFormat(1, 2, 3, 4.56);
        System.out.println(sf);
    }
}
