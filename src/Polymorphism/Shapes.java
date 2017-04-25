package Polymorphism;

/**
 * Created by Vlad on 20.03.2016.
 */
public class Shapes {
    private static RandomShapeGenerator gen =
            new RandomShapeGenerator(10);

    static void rotate () {
        for (Shape shape : gen)
            if (shape instanceof Circle)
                System.out.println("This is Circle");
            else
                System.out.println("Rotating " + shape);

    }

    static void markSquares () {
        for (Shape shape : gen)
            if (shape instanceof Square)
                shape.mark();
    }

    public static void main (String[] args) {
        markSquares();

        for (Shape shape : gen)
            System.out.println(shape);
/*
        for (Shape shape : gen.reversed())
            System.out.print(shape + " ");
        System.out.println();

        for (Shape shape : gen.randomized())
            System.out.print(shape + " ");
        System.out.println();
        */
    }
}
