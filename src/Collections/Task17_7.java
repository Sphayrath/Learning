package Collections;

import net.mindview.util.Countries;

import java.util.*;

/**
 * Created by Vlad on 02.05.2016.
 */
public class Task17_7 {
    public static void main (String[] args) {
        List<String> arrayList = new ArrayList<>(Countries.names(5));
        List<String> linkedList = new LinkedList<>(Countries.names(5));

        Iterator iterator = arrayList.iterator();
        while (iterator.hasNext())
            System.out.print(iterator.next() + " ");
        System.out.println();

        iterator = linkedList.iterator();
        while (iterator.hasNext())
            System.out.print(iterator.next() + " ");
        System.out.println();

        ListIterator listIterator = linkedList.listIterator();
        iterator = arrayList.iterator();

        while (listIterator.hasNext() && iterator.hasNext()) {
            listIterator.next();
            listIterator.add(iterator.next());
        }
        System.out.println(linkedList);

        iterator = arrayList.iterator();
        while (listIterator.hasPrevious() && iterator.hasNext()) {
            listIterator.previous();
            listIterator.add(iterator.next());
            listIterator.previous();
        }
        System.out.println(linkedList);

    }
}
