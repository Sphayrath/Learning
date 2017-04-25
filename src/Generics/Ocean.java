package Generics;

import java.util.*;

/**
 * Created by VFilin on 12.04.2016.
 */
class BigFish {
    private static long counter = 1;
    private final long id = counter++;

    private BigFish() {}

    public String toString() {return "Bigfish " + id;}

    public static Generator<BigFish> generator() {
        return new Generator<BigFish>() {
            @Override
            public BigFish next() {
                return new BigFish();
            }
        };
    }
}

class LittleFish {
    private static long counter = 1;
    private final long id = counter++;

    private LittleFish() {}

    public String toString() {return "Litlefish " + id;}

    public static Generator<LittleFish> generator() {
        return new Generator<LittleFish>() {
            @Override
            public LittleFish next() {
                return new LittleFish();
            }
        };
    }
}
public class Ocean {
    public static void serve (BigFish bf, LittleFish lf) {
        System.out.println(bf + " eats " + lf);
    }

    public static void main (String[] args) {
        Random rand = new Random(47);
        Queue<LittleFish> littlefish = new LinkedList<>();
        Generators.fill(littlefish, LittleFish.generator(), 15);

        List<BigFish> bigfish = new ArrayList<>();
        Generators.fill(bigfish, BigFish.generator(), 4);

        for (LittleFish lf : littlefish)
            serve(bigfish.get(rand.nextInt(bigfish.size())), lf);
    }
}
