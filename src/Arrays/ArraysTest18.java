package Arrays;

import Polymorphism.*;

/**
 * Created by Vlad on 01.05.2016.
 */
public class ArraysTest18 {
    public static void main (String[] args) {
        Shape[] shapes1 = {new Triangle(), new Circle(), new Square(), new Rhomboid()};
        Shape[] shapes2 = new Shape[5];
        System.arraycopy(shapes1, 0, shapes2, 0, 4);
        shapes1[3] = new Circle();
        for (Shape shape : shapes2)
            System.out.println(shape);
        shapes2[3] = new Circle();
        for (Shape shape : shapes1)
            System.out.println(shape);
    }
}
