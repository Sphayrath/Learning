package Collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Vlad on 04.05.2016.
 */
public class Task17_8 {
    public static void main (String[] args) {
        SList<Integer> sList = new SList<>();
        SList.SIterator iter = sList.iterator();
        for (int i=0; i<10; i++)
            iter.add(i);
        System.out.println(sList);

        while (iter.hasNext()) {
            iter.set((Integer)iter.next() + 5);
        }
        System.out.println(sList);

        iter =sList.iterator();

        iter.next();
        iter.next();
        iter.add(7);
        iter.next();
        iter.next();
        iter.next();
        iter.remove();
        iter =sList.iterator();
        iter.remove();
        System.out.println(sList);
        while (iter.hasNext())
            System.out.print(iter.next() + " ");
        System.out.println(iter.next());
    }
}
