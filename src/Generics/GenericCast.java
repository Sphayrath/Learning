package Generics;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vlad on 16.04.2016.
 */


class FixedSizeStack<T> {
    private int index = 0;
    private List<T> storage;

    public FixedSizeStack (int size) {
        storage = new ArrayList<>(size);
    }

    public  void push (T item) {
        storage.add(item);
        index++;
    }

    public T pop() {return storage.remove(--index);}
}

public class GenericCast {
    public static final int SIZE = 10;
    public static void main (String[] args) {
        FixedSizeStack<String> strings = new FixedSizeStack<>(SIZE);
        for (String s : "A B C D E F G H I J".split(" "))
            strings.push(s);
        for (int i=0; i<SIZE; i++) {
            String s=strings.pop();
            System.out.print(s + " ");
        }
    }
}
