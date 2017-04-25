package IO;

import net.mindview.util.TextFile;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Vlad on 15.05.2016.
 */
public class Task18_17 {
    public static void main (String[] args) {
        List<String> list = new TextFile(
                "D:\\SkyDrive\\Документы\\Learning\\src\\IO\\Task18_17.java","");
        Map<String,Integer> map = new HashMap<>();
        Iterator<String> iterator = list.iterator();
        String temp;

        while (iterator.hasNext()){
            temp = iterator.next();
            if (map.containsKey(temp))
                map.put(temp, (map.get(temp))+1);
            else
                map.put(temp, 1);
        }

        System.out.println(map);

    }
}
