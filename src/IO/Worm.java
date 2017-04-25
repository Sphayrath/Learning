package IO;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Random;

/**
 * Created by VFilin on 19.05.2016.
 */
class Data implements Serializable {
    private int n;
    public  Data (int n) { this.n = n; }
    public String toString() {return Integer.toString(n);}
}

public class Worm implements Serializable {
    private static Random random = new Random(47);
    private Data[] data = {new Data(random.nextInt(10)), new Data(random.nextInt(10)), new Data(random.nextInt(10))};
    private Worm next;
    private char c;

    public Worm (int i, char x) {
        System.out.println("Worm constructor: " + i);
        c = x;
        if (--i > 0)
            next = new Worm(i, (char)(x+1));
    }

    public Worm () {System.out.println("Default constructor");}

    public String toString() {
        StringBuilder result = new StringBuilder(":");
        result.append(c);
        result.append("(");
        for (Data dat : data) result.append(dat);
        result.append(")");
        if (next != null)
            result.append(next);
        return result.toString();
    }

    public static void main (String[] args) throws ClassNotFoundException, IOException{
        Worm w = new Worm(6, 'a');
        System.out.println("w= " +w);

        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("worm.out"));
        out.writeObject("Worm storage\n");
        out.writeObject(w);
        out.close();
    }
}
