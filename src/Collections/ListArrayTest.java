package Collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by VFilin on 09.03.2016.
 */
class SomeClass {
    private int i=0;

    public SomeClass (int i) {this.i = i;}

    public String toString () {return Integer.toString(i);}
}

public class ListArrayTest {
    public static void main (String[] args) {
        SomeClass[] someClassArray = new SomeClass[6];
        List<SomeClass> someClassList = new ArrayList<SomeClass>();
        List<SomeClass> someClassSubList;

        for (int i=0; i<6; i++) someClassArray[i] = new SomeClass(i);
        Collections.addAll(someClassList, someClassArray);

        someClassSubList = someClassList.subList(2,5);

        someClassList.removeAll(someClassSubList);

        for (int i=0; i<someClassList.size(); i++) System.out.println(someClassList.get(i));
    }
}
