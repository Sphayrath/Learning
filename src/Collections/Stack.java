package Collections;

import java.util.LinkedList;

/**
 * Created by Vlad on 15.03.2016.
 */
public class Stack<T> {
    private LinkedList<T> storage = new LinkedList<>();
    public void push (T v) {storage.addFirst(v);}
    public T peek() {return  storage.getFirst();}
    public T pop() {return storage.removeFirst();}
    public boolean empty() {return storage.isEmpty();}
    public String toString() {return storage.toString();}
}
