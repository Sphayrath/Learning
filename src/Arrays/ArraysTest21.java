package Arrays;

import Polymorphism.*;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by Vlad on 01.05.2016.
 */
class ComparableShape extends Shape implements Comparable<Shape> {
    private Shape shape;
    public ComparableShape (Shape shape) {this.shape = shape; }

    @Override
    public int compareTo(Shape o) {
        if (o.vertexes < shape.vertexes) return -1;
        if (o.vertexes > shape.vertexes) return 1;
        return 0;
    }

    @Override
    public void mark() {shape.mark();}

    @Override
    public void draw() {shape.draw();}

    @Override
    public void erase() {shape.erase();}

    @Override
    public String toString() {return shape.toString();}
}

class ShapeReverseComparator implements Comparator<Shape> {

    @Override
    public int compare(Shape o1, Shape o2) {
        if (o2.vertexes < o1.vertexes) return -1;
        if (o2.vertexes > o1.vertexes) return 1;
        return 0;
    }
}

public class ArraysTest21 {
    public static void main (String[] args) {
        Shape[] shapes = {new ComparableShape(new Triangle()),
                new ComparableShape(new Circle()),
                new ComparableShape(new Square()),
                new ComparableShape(new Rhomboid())};

        Arrays.sort(shapes);
        System.out.println(Arrays.toString(shapes));
        Arrays.sort(shapes, new ShapeReverseComparator());
        System.out.println(Arrays.toString(shapes));
    }
}
