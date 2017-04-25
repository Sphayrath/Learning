package IO;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Created by VFilin on 19.05.2016.
 */
public class WormTest {
    public static void main (String[] args) throws IOException, ClassNotFoundException {
        Worm w;
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("worm.out"));
        System.out.println((String)in.readObject());
        w = (Worm)in.readObject();
        System.out.println(w);
    }
}
