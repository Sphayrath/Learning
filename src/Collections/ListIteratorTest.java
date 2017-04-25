package Collections;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by VFilin on 11.03.2016.
 */
public class ListIteratorTest {
    public static void main (String[] args){
        List<Integer> sourceList = new ArrayList<>();
        List<Integer> targetList = new ArrayList<>();
        ListIterator sourceListIterator = sourceList.listIterator();
        ListIterator targetListIterator = targetList.listIterator();

        for (int i=0; i<20; i++) {sourceListIterator.add(i);}

        while (sourceListIterator.hasPrevious())
            targetListIterator.add(sourceListIterator.previous());

        System.out.println(sourceList.toString());
        System.out.println(targetList.toString());
    }
}
