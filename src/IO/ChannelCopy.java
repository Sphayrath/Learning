package IO;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by Vlad on 15.05.2016.
 */
public class ChannelCopy {
    private static final int BSIZE = 1024;
    public static void main (String[] args) throws Exception {
        FileChannel
                in = new FileInputStream("D:\\SkyDrive\\Документы\\Learning\\src\\IO\\Task18_17.java").getChannel(),
                out = new FileOutputStream("Copy.txt").getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(BSIZE);
        while (in.read(buffer) != -1) {
            buffer.flip();
            out.write(buffer);
            buffer.clear();
        }
    }
}
