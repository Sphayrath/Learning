package Polymorphism;

/**
 * Created by Vlad on 20.03.2016.
 */
public class Circle extends Shape {

    @Override
    public void draw() {
        System.out.println("Drawing Circle");
    }

    @Override
    public void erase() {
        System.out.println("Erasing Circle");
    }

    @Override
    public String toString() {
        if (isMarked) return "Circle is marked";
        else return "Circle is unmarked";
    }
}
