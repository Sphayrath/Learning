package IO;

import net.mindview.util.RandomGenerator;

import java.io.*;

/**
 * Created by Vlad on 14.05.2016.
 */
public class BasicFileOutput {
    static String file = "BasicFileOutput.out";

    public static void  main (String[] args) throws IOException {
        BufferedReader in = new BufferedReader (new StringReader(
                BufferedInputFile.read("D:\\SkyDrive\\Документы\\Learning\\src\\IO\\BasicFileOutput.java")));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file)));
        int lineCount = 1;
        String s;
        while ((s = in.readLine()) != null)
            out.println(lineCount++ + ": " + s);
        out.close();
    }
}
