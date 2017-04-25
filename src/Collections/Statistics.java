package Collections;

import java.util.*;

/**
 * Created by Vlad on 20.03.2016.
 */

public class Statistics {
    public static Map<Integer,Integer>  fillRandomMap (int rand, int maxValue) {
        Random random = new Random(rand);
        Map<Integer,Integer> map = new HashMap();
        for (int i=0; i<10000; i++){
            int r = random.nextInt(maxValue);
            if (map.containsKey(r))
                map.put(r, map.get(r)+1);
            else
                map.put(r, 1);
        }
        return map;
    }

    public static void main (String[] args) {
        Map<Integer,Integer> map;
        SortedMap<Integer,Integer> resultMap = new TreeMap<>();
        int maxValue = 25, key=0, max=0;

        for (int i=0; i<1000; i++) {
            map = fillRandomMap(i, maxValue);

            for (int j=0; j<maxValue; j++) {
                if (map.get(j) >= max) {
                    max = map.get(j);
                    key = j;
                }
            }

            if (resultMap.containsKey(key))
                resultMap.put(key, resultMap.get(key)+1);
            else
                resultMap.put(key, 1);
        }

        System.out.println(resultMap);
    }
}
