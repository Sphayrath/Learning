package IO;

import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Elements;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Vlad on 21.05.2016.
 */
public class People extends ArrayList<Person> {
    public People(File fileName) throws Exception {
        Document doc = new Builder().build(fileName);
        Elements elements = doc.getRootElement().getChildElements();
        for (int i=0; i<elements.size(); i++)
            add(new Person(elements.get(i)));
    }

    public static void main(String[] args) throws Exception {
        People p = new People(new File("D:\\SkyDrive\\Документы\\Learning\\People.xml"));
        System.out.println(p);
    }
}
