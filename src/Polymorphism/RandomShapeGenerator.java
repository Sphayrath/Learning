package Polymorphism;

import java.util.*;

/**
 * Created by Vlad on 20.03.2016.
 */
public class RandomShapeGenerator implements Iterable<Shape> {
    private Random random = new Random(47);
    private int length;
    private Shape[] shapes;

    public RandomShapeGenerator (int length) {
        this.length = length;
        shapes = new Shape[length];
        for (int i=0; i<length; i++)
            shapes[i] = this.next();
    }

    public Shape next() {
        switch (random.nextInt(4)) {
            default:
            case 0: return new Circle();
            case 1: return new Square();
            case 2: return new Triangle();
            case 3: return new Rhomboid();
        }
    }

    @Override
    public Iterator<Shape> iterator() {
        return new Iterator<Shape>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < length;
            }

            @Override
            public Shape next() {
                return shapes[index++];
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    public Iterable<Shape> reversed() {
        return new Iterable<Shape>() {
            @Override
            public Iterator<Shape> iterator() {
                return new Iterator<Shape>() {
                    private int index = length-1;

                    @Override
                    public boolean hasNext() {
                        return index>-1;
                    }

                    @Override
                    public Shape next() {
                        return shapes[index--];
                    }

                    @Override
                    public void remove() {
                        throw new UnsupportedOperationException();
                    }
                };
            }
        };
    }

    public Iterable<Shape> randomized() {
        return new Iterable<Shape>() {
            @Override
            public Iterator<Shape> iterator() {
                List<Shape> shuffled =
                        new ArrayList<>(Arrays.asList(shapes));
                Collections.shuffle(shuffled, new Random(47));
                return shuffled.iterator();
            }
        };
    }
}
