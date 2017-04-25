package IO;

import java.io.*;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Created by Vlad on 14.05.2016.
 */
public class Task18_2 {
    public static void main (String[] args) {
        File file = new File("D:\\SkyDrive\\Документы\\Learning\\src\\IO\\Task18_2.java");
        File outFile = new File("Task18_2.txt");
        BufferedReader reader;
        String str;
        LinkedList<String> list = new LinkedList<>();
        ListIterator<String> listIterator = list.listIterator();

        try {
            reader = new BufferedReader(new FileReader(file));
            try {
                while ((str = reader.readLine()) != null)
                    listIterator.add(str);
                reader.close();
            } catch (IOException e) {
                System.out.println("IO exception");
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        int lineCount = 1;
        try {
            PrintWriter out = new PrintWriter(outFile);
            while (listIterator.hasPrevious())
                out.println(lineCount++ + ": " + listIterator.previous());
            out.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
}
