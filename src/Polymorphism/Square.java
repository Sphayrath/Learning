package Polymorphism;

/**
 * Created by Vlad on 20.03.2016.
 */
public class Square extends Shape {
    public Square () {super.vertexes = 4;}

    @Override
    public void draw() {
        System.out.println("Drawing Square");
    }

    @Override
    public void erase() {
        System.out.println("Erasing Square");
    }

    @Override
    public String toString() {
        if (isMarked) return "Square is marked";
        else return "Square is unmarked";
    }
}
