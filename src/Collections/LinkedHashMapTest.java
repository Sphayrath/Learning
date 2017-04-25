package Collections;

import java.util.*;

/**
 * Created by VFilin on 16.03.2016.
 */
public class LinkedHashMapTest {
    public static void main (String[] args) {
        HashMap<Integer,Integer> hashMap = new HashMap<>();
        LinkedHashMap<Integer,Integer> linkedHashMap = new LinkedHashMap<>();
        TreeSet<Integer> treeSet = new TreeSet<>();
        Iterator<Integer> iterator;
        Integer key;
        Random random = new Random(47);

        for (int i=0; i<2000; i++){
            int r = random.nextInt(25);
            if (hashMap.containsKey(r))
                hashMap.put(r, hashMap.get(r)+1);
            else
                hashMap.put(r, 1);
        }
        System.out.println(hashMap);

        treeSet.addAll(hashMap.keySet());
        iterator = treeSet.iterator();

        while (iterator.hasNext()) {
            key = iterator.next();
            linkedHashMap.put(key, hashMap.get(key));
        }

        System.out.println(linkedHashMap);

    }
}
