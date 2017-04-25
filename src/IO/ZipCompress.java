package IO;

import java.io.*;
import java.util.zip.*;

/**
 * Created by VFilin on 19.05.2016.
 */
public class ZipCompress {
    public static void main (String[] args) throws IOException{
        String[] files = new String[] {"test.dat", "test.txt"};
        FileOutputStream fileOutputStream = new FileOutputStream("test.zip");
        CheckedOutputStream csum = new CheckedOutputStream(fileOutputStream, new Adler32());
        ZipOutputStream zipOutputStream = new ZipOutputStream(csum);
        BufferedOutputStream out = new BufferedOutputStream(zipOutputStream);

        for (String str : files) {
            System.out.println("Writing file: " + str);
            BufferedReader in = new BufferedReader(new FileReader(str));
            zipOutputStream.putNextEntry(new ZipEntry(str));
            int c;
            while ((c = in.read()) != -1)
                out.write(c);
            in.close();
            out.flush();
        }
        out.close();
        System.out.println("Checksum: " + csum.getChecksum().getValue());

        System.out.println("Reading file");
        FileInputStream fileInputStream = new FileInputStream("test.zip");
        CheckedInputStream csumi = new CheckedInputStream(fileInputStream, new Adler32());
        ZipInputStream in2 = new ZipInputStream(csumi);
        BufferedInputStream bis = new BufferedInputStream(in2);
        ZipEntry ze;
        while ((ze = in2.getNextEntry()) != null) {
            System.out.println("Reading file: " + ze);
            int x;
            while ((x = bis.read()) != -1)
                System.out.write(x);
        }
        bis.close();
    }
}
