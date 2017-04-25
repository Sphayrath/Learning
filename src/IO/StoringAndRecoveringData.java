package IO;

import java.io.*;

/**
 * Created by Vlad on 14.05.2016.
 */
public class StoringAndRecoveringData {
    public static void main (String[] args) throws IOException {
        DataOutputStream out = new DataOutputStream(new BufferedOutputStream(
                new FileOutputStream("Data.txt")));
        out.writeDouble(3.14159);
        out.writeInt(16);
        out.writeUTF("Olololo!!11!");
        out.close();

        DataInputStream in = new DataInputStream(new BufferedInputStream(
                new FileInputStream("Data.txt")));
        System.out.println(in.readDouble());
        System.out.println(in.readInt());
        System.out.println(in.readUTF());
    }
}
