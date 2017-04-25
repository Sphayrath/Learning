package Polymorphism;

/**
 * Created by VFilin on 04.04.2016.
 */
public class Rhomboid extends Shape {
    public Rhomboid () {super.vertexes = 4;}

    @Override
    public void draw() {
        System.out.println("Drawing Rhomboid");
    }

    @Override
    public void erase() {
        System.out.println("Erasing Rhomboid");
    }

    @Override
    public String toString() {
        if (isMarked) return "Rhomboid is marked";
        else return "Rhomboid is unmarked";
    }
}
