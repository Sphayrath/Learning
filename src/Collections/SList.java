package Collections;

/**
 * Created by Vlad on 04.05.2016.
 */
public class SList<T> {
    private T link = null;
    private SList<T> next = null;

    public class SIterator {
        private SList<T> head = SList.this;
        private SList<T> current = head.next;
        private SList<T> previous = head;
        private SIterator () {};

        public void add (T element) {
            if (current == null) {
                current = new SList<>();
                current.link = element;
                head.next = current;
                current.next = null;
            } else {
                if (current.next == null) {
                    current.next = new SList<>();
                    current.next.link = element;
                    current.next.next = null;
                } else {
                    SList<T> c = new SList<>();
                    c.link = element;
                    c.next = current.next;
                    current.next = c;
                }
            }
        }

        public void remove() {
            if (current != null) {
                previous.next = current.next;
                if (current.next == null)
                    current = previous;
                else
                    current = current.next;
            }
        }

        public void set (T element) {
            if (current!=null)
                current.link = element;
        }

        public boolean hasNext () {
            boolean result = true;
            if (current.next == null) result = false;
            return result;
        }

        public T next () {
            T element = null;
            if (current!=null) {
                element = current.link;
                if (current.next != null) {
                    previous = current;
                    current = current.next;
                }
            }
            return element;
        }
    }

    public SIterator iterator () {
        return new SIterator ();
    }

    public String toString () {
        String result = "";
        SList<T> current = this.next;
        if (current != null) result = current.link.toString();
        current = current.next;
        while (current!=null) {
            result = result + ", " + current.link.toString();
            current = current.next;
        }
        return result;
    }
}
