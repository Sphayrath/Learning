package Strings;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vlad on 28.03.2016.
 */
public class InfiniteRecursion {
    public String toString () {
        return "InfiniteRecursion address: " + super.toString() + "\n";
    }
    public static void main (String[] args) {
        List<InfiniteRecursion> v =
                new ArrayList<>();
        for (int i=0; i<10; i++)
            v.add(new InfiniteRecursion());
        System.out.println(v);
    }
}
