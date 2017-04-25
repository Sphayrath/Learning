package Collections;

import java.util.Collection;
import java.util.Iterator;

/**
 * Created by Vlad on 20.03.2016.
 */

public class CollectionSequence
implements Collection<Command> {
    private Command[] commands;

    CollectionSequence (String[] commands) {
        this.commands = new Command[commands.length];
        for (int i=0; i<commands.length; i++)
            this.commands[i] = new Command(commands[i]);
    }

    public int size() {return commands.length;}

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    public Iterator<Command> iterator() {
        return new Iterator<Command>() {
            private int index = 0;

            @Override
            public boolean hasNext() {return index<commands.length;}

            @Override
            public Command next() {return commands[index++];}

            @Override
            public void remove() {throw new UnsupportedOperationException();}
        };
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(Command command) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends Command> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    public void clear() {throw new UnsupportedOperationException();}

    public static void display (Iterator<Command> it) {
        while (it.hasNext()) {
            it.next().operation();
            System.out.print(" ");
        }
        System.out.println();
    }

    public static void display (Collection<Command> commands) {
        for (Command c : commands) {
            c.operation();
            System.out.print(" ");
        }
        System.out.println();
    }

    public static void main (String[] args) {
        String[] commands = "Push,Poll,Add,Delete,Clear,Fill".split(",");
        CollectionSequence c = new CollectionSequence (commands);

        CollectionSequence.display(c);
        CollectionSequence.display(c.iterator());
    }
}
