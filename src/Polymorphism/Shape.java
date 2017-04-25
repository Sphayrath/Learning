package Polymorphism;

/**
 * Created by Vlad on 20.03.2016.
 */
public abstract class Shape {
    protected boolean isMarked = false;
    public int vertexes = 0;
    public void mark () {
        isMarked = true;
    }

    public abstract void draw();
    public abstract void erase();
    public abstract String toString();
}
