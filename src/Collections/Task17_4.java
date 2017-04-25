package Collections;

import Files.TextFile;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vlad on 02.05.2016.
 */
class FileCollectionData extends ArrayList<String> {
    public FileCollectionData (String filename) {
        super(new TextFile(filename,"\\W+"));
    }

    public static FileCollectionData list (String filename) {
        return new FileCollectionData(filename);
    }
}

public class Task17_4 {
    public static void main (String[] args) {
        String filename = "D:\\SkyDrive\\Документы\\Learning\\src\\Files\\TextFile.java";
        List<String> list = new ArrayList<>(FileCollectionData.list(filename));
        System.out.println(list);
    }
}
