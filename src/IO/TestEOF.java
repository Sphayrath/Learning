package IO;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by Vlad on 14.05.2016.
 */
public class TestEOF {
    public static void main (String[] args) throws IOException {
        DataInputStream in = new DataInputStream(new BufferedInputStream(
                new FileInputStream("D:\\SkyDrive\\Документы\\Learning\\src\\IO\\TestEOF.java")));
        while (in.available() != 0)
            System.out.print((char)in.readByte());
    }
}
