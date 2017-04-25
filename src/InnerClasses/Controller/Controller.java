package InnerClasses.Controller;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Vlad on 08.03.2016.
 */
public class Controller {
    private List<Event> eventList = new LinkedList<Event>();

    public void addEvent (Event c) {eventList.add(c);}

    public void run () {
        while (eventList.size() > 0) {
            Iterator<Event> iterator = new LinkedList<>(eventList).iterator();
            Event e;
            while (iterator.hasNext()) {
                e=iterator.next();
                if (e.ready()) {
                    System.out.println(e);
                    e.action();
                    eventList.remove(e);
                }
            }
        }
    }

    /*
    public void run () {
        while (eventList.size() > 0)
            for (Event e : new ArrayList<Event>(eventList))
                if (e.ready()) {
                    System.out.println(e);
                    e.action();
                    eventList.remove(e);
                }
    }*/

}
