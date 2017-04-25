package Collections;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Created by Vlad on 05.05.2016.
 */
public class Task17_10 {
    public static void main(String[] args) {
        Random random = new Random(47);
        List<Integer> list = new LinkedList<>();

        for (int i=0; i<15; i++)
            list.add(random.nextInt(20));
        System.out.println(list);

        MySortedSet<Integer> set = new MySortedSet<>(list);
        System.out.println(set);

        set.add(2);
        set.addFirst(1);
        set.addLast(9);
        set.add(25);
        set.add(10);
        set.remove(5);
        System.out.println(set);

    }
}
