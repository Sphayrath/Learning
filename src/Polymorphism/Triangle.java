package Polymorphism;

/**
 * Created by Vlad on 20.03.2016.
 */
public class Triangle extends Shape {
    public Triangle () {super.vertexes = 3;}
    @Override
    public void draw() {
        System.out.println("Drawing Triangle");
    }

    @Override
    public void erase() {
        System.out.println("Erasing Triangle");
    }

    @Override
    public String toString() {
        if (isMarked) return "Triangle is marked";
        else return "Triangle is unmarked";
    }
}
