package Collections;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by Vlad on 05.05.2016.
 */
public class MySortedSet<T> extends LinkedList<T> {
    public MySortedSet(Collection<? extends T> c) {
        super();
        addAll(c);
    }

    @Override
    public void addFirst(T t) {
        add(t);
    }

    @Override
    public void addLast(T t) {
        add(t);
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        Iterator<? extends T> iterator = c.iterator();
        boolean result = true;
        try {
            while (iterator.hasNext()) add(iterator.next());
        } catch (Exception e) {result = false;}
        return result;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return addAll(c);
    }

    int compare(T e1, T e2) {
        int c = e1.hashCode() - e2.hashCode();
        return (c < 0) ? -1 : ((c == 0) ? 0 : 1);
    }

    public boolean add(T e) {
        if(!this.contains(e)) {
            Iterator<T> it = this.iterator();
            int index = 0;
            while(it.hasNext()) {
                if(compare(it.next(), e) < 1)
                    index++;
            }
            add(index, e);
            return true;
        }
        return false;
    }
}
