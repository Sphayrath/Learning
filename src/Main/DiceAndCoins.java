package Main;

import java.util.Random;

/**
 * Created by Vlad on 28.02.2016.
 */
interface Throw {
    public void throwing ();
}

interface Factory {
    public Throw get();
}

class Coin implements Throw {
    private Coin () {}

    public static Factory factory () {
        return new Factory() {
            public Throw get() {return new Coin();}
        };
    }

    public void throwing () {
        Random rand = new Random ();
        if (rand.nextBoolean())
            System.out.println("Heads");
        else
            System.out.println("Tails");
    }
}

class Dice implements Throw {
    private Dice () {}

    public static Factory factory () {
        return new Factory() {
            public Throw get() {return new Dice();}
        };
    }

    public void throwing () {
        Random rand = new Random ();
        int result = rand.nextInt(6)+1;
        System.out.println(result);
    }
}

public class DiceAndCoins {
    public static void play (Factory factory, int count) {
        Throw t = factory.get();
        for (int i=0; i<count; i++)
            t.throwing();
    }

    public static void main(String[] args){
        play(Coin.factory(), 3);
        play(Dice.factory(), 5);
    }
}
