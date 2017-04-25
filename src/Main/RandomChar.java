package Main;

import java.nio.CharBuffer;
import java.util.Random;

/**
 * Created by Vlad on 28.02.2016.
 */
public class RandomChar implements Readable {
    private static Random rand = new Random(47);
    private int count;
    private static final char[] ch =
            "ABCDEFGHIKLMNOPQRSTVXYZabcdefghiklmnopqrstvxyz".toCharArray();

    public RandomChar (int count){
        this.count=count;
    }
    public int read (CharBuffer cb) {
        if (count-- == 0)
            return -1;
        cb.append(ch[rand.nextInt(46)]);
        return 1;
    }
}
