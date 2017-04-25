package Collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static net.mindview.util.Countries.names;

/**
 * Created by Vlad on 02.05.2016.
 */
public class Task17_1 {
    public static void main (String[] args) {
        List<String> list = new ArrayList(names(10));
        Collections.sort(list);
        System.out.println(list);
        Collections.shuffle(list);
        System.out.println(list);
        Collections.shuffle(list);
        System.out.println(list);
    }
}
