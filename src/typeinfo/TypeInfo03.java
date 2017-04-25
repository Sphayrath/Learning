package typeinfo;

import Polymorphism.*;

/**
 * Created by VFilin on 04.04.2016.
 */
public class TypeInfo03 {
    public static void main (String[] args) {
        Shape shape = new Rhomboid();
        System.out.println(shape);
        if (shape instanceof Rhomboid) {
            Rhomboid rhomboid = (Rhomboid) shape;
            System.out.println(rhomboid);
        } else System.out.println("Shape is not a Rhomboid");
        Circle circle;
        if (shape instanceof Circle)
            circle = (Circle)shape;
        else System.out.println("Shape is not a Circle");
    }
}
