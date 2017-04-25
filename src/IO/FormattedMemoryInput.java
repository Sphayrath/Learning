package IO;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;

/**
 * Created by Vlad on 14.05.2016.
 */
public class FormattedMemoryInput {
    public static void main (String[] args) throws IOException {
        try {
            DataInputStream in = new DataInputStream(
                    new ByteArrayInputStream(BufferedInputFile.read(
                    "D:\\SkyDrive\\Документы\\Learning\\src\\IO\\FormattedMemoryInput.java").getBytes()));
            while (true)
                System.out.print((char)in.readByte());
        } catch (EOFException e) {
            System.err.println("End of stream");
        }
    }
}
