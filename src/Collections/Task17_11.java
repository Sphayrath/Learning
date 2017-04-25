package Collections;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

/**
 * Created by Vlad on 05.05.2016.
 */
class MyClass implements Comparable<MyClass> {
    private int field;
    private static Random random = new Random(47);

    public MyClass () {
        field = random.nextInt(101);
    }

    @Override
    public int compareTo(MyClass o) {
        int result = 0;
        if (field < o.field) result = -1;
        if (field > o.field) result = 1;
        return result;
    }

    public String toString () {
        return Integer.toString(field);
    }
}

public class Task17_11 {
    public static void main(String[] args) {
        Queue<MyClass> queue = new PriorityQueue<>();

        for (int i=0; i<20; i++)
            queue.add(new MyClass());

        while (!queue.isEmpty())
            System.out.print(queue.poll() + ", ");
    }
}
