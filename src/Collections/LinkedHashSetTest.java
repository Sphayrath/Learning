package Collections;

import java.util.*;

/**
 * Created by VFilin on 16.03.2016.
 */

public class LinkedHashSetTest {
    public static void main (String[] args) {
        HashSet<Integer> hashSet = new HashSet<>();
        LinkedHashSet<Integer> linkedHashSet = new LinkedHashSet<>();
        Random random = new Random(47);
        List<Integer> list = new LinkedList<>();
        Iterator<Integer> iterator;

        for (int i=0; i<1000; i++)
            hashSet.add(random.nextInt(25));

        list.addAll(hashSet);
        Collections.sort(list);
        iterator = list.iterator();

        while (iterator.hasNext())
            linkedHashSet.add(iterator.next());

        System.out.println(hashSet);
        System.out.println(linkedHashSet);
    }
}
