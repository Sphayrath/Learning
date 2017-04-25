package Generics;

import java.util.*;

/**
 * Created by VFilin on 12.04.2016.
 */
public class Generators {
    public static <T> Collection<T>
    fill(Collection<T> coll, Generator<T> gen, int n) {
        for (int i = 0; i < n; i++)
            coll.add(gen.next());
        return coll;
    }
}
