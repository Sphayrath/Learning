package Arrays;

import net.mindview.util.ConvertTo;
import net.mindview.util.CountingGenerator;
import net.mindview.util.Generated;

import java.util.Arrays;


/**
 * Created by VFilin on 28.04.2016.
 */
public class ArraysTest14 {
    public static void main (String[] args) {
        Integer[] a = Generated.array(Integer.class, new SkipGenerator.Integer(3), 10);
        int[] ints = ConvertTo.primitive(a);

        Long[] l = Generated.array(Long.class, new SkipGenerator.Long(5), 10);
        long[] longs = ConvertTo.primitive(l);

        Short[] s = Generated.array(Short.class, new SkipGenerator.Short((short)2), 10);
        short[] shorts = ConvertTo.primitive(s);

        Float[] f = Generated.array(Float.class, new SkipGenerator.Float(1.7f), 10);
        float[] floats = ConvertTo.primitive(f);

        Double[] d = Generated.array(Double.class, new SkipGenerator.Double(3.95), 10);
        double[] doubles = ConvertTo.primitive(d);

        Boolean[] b = Generated.array(Boolean.class, new SkipGenerator.Boolean(2), 10);
        boolean[] booleans = ConvertTo.primitive(b);

        Character[] c = Generated.array(Character.class, new SkipGenerator.Character(2), 10);
        char[] chars = ConvertTo.primitive(c);

        System.out.println(Arrays.toString(ints));
        System.out.println(Arrays.toString(longs));
        System.out.println(Arrays.toString(shorts));
        System.out.println(Arrays.toString(floats));
        System.out.println(Arrays.toString(doubles));
        System.out.println(Arrays.toString(booleans));
        System.out.println(Arrays.toString(chars));
    }
}
