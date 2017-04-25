package Collections;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Created by VFilin on 11.03.2016.
 */
public class LinkedListTest {
   public static void main (String[] args) {
       LinkedList<Integer> linkedList = new LinkedList<>();
       ListIterator listIterator = linkedList.listIterator();
       int i;
       for (i=1, listIterator.add(0); i<20; i++) {
           listIterator = linkedList.listIterator(i/2);
           listIterator.add(i);
           System.out.println(linkedList.toString());
       }
    }
}
