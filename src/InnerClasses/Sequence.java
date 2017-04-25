package InnerClasses;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Vlad on 06.03.2016.
 */

class Str {
    String str;
    Str (String str) {this.str = str;}
    public String toString () {return str;}
}

public class Sequence<T> {
    private ArrayList<T> items;

    public Sequence () {items = new ArrayList<T>();}

    public void add (T x) throws ArrayIndexOutOfBoundsException {
        if (items.size()>9) throw new ArrayIndexOutOfBoundsException();
        items.add(x);
    }

    private class ForwardIterator implements Iterator {
        private int i = 0;

        public void remove () {}

        public boolean hasNext () {return i!=items.size();}

        public T next () {
            if (hasNext()) i++;
            return items.get(i-1);
        }
    }

    private class ReverseIterator implements Iterator {
        private int i = items.size()-1;

        public void remove () {}

        public boolean hasNext () {return i>=0;}

        public T next () {
            if (hasNext()) i--;
            return items.get(i+1);
        }
    }

    public Iterator forwardIterator () {return new ForwardIterator();}
    public Iterator reverseIterator () {return new ReverseIterator();}

    public static void main (String[] args) {
        Sequence<Str> sequence = new Sequence();

        try {
            for (int i = 0; i < 10; i++)
                sequence.add(new Str("lol" + i));
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Выход за пределы массива");
            e.printStackTrace(System.out);
            return;
        }

        Iterator fIterator = sequence.forwardIterator();
        Iterator rIterator = sequence.reverseIterator();

        while (fIterator.hasNext())
            System.out.print(fIterator.next() + " ");

        System.out.println();
        while (rIterator.hasNext())
            System.out.print(rIterator.next() + " ");
    }
}


