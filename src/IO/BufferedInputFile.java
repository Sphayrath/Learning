package IO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Vlad on 14.05.2016.
 */
public class BufferedInputFile {
    public static String read (String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String s;
        StringBuilder sb = new StringBuilder();
        while ((s = reader.readLine()) != null)
            sb.append(s + "\n");
        reader.close();
        return sb.toString();
    }
}
