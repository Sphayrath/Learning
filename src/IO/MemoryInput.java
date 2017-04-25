package IO;

import java.io.IOException;
import java.io.StringReader;

/**
 * Created by Vlad on 14.05.2016.
 */
public class MemoryInput {
    public static void main (String[] args) throws IOException {
        StringReader reader = new StringReader(
                BufferedInputFile.read("D:\\SkyDrive\\Документы\\Learning\\src\\IO\\MemoryInput.java"));
        int c;
        while ((c = reader.read()) != -1)
            System.out.print((char)c);
    }
}
