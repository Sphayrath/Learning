package Collections;

import java.util.PriorityQueue;
import java.util.Random;

/**
 * Created by Vlad on 20.03.2016.
 */
public class PriorityQueueTest {
    public static void main (String[] args) {
        Random random = new Random(47);
        PriorityQueue<Double> queue = new PriorityQueue<>();

        for (int i=0; i<20; i++)
            queue.offer(random.nextDouble());

        while (queue.peek()!=null)
            System.out.println (queue.poll());
    }
}
