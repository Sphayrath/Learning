package typeinfo;

import java.lang.reflect.*;

/**
 * Created by VFilin on 04.04.2016.
 */

interface HasBatteries {}
interface Waterproof {}
interface Shoots {}
interface Moves {}

class Toy {
    private int i;
    public Toy() {}
    public Toy (int i) {this.i = i;}
    public String toString () {return Integer.toString(i);}
}

class FancyToy extends Toy
implements HasBatteries, Waterproof, Shoots, Moves {
    public FancyToy() {super(1);}
}

public class ToyTest {
    static void printInfo (Class cc) {
        System.out.println("Имя класса: " + cc.getName() + " являктся интерфейсом? [" + cc.isInterface() + "]");
        System.out.println("Простое имя: " + cc.getSimpleName());
        System.out.println("Каноническое имя: " + cc.getCanonicalName());
    }

    public static void main (String[] args) {
        Class c = null;
        Object obj = null;

       /*
        try {
            c = Class.forName("typeinfo.FancyToy");
        } catch (ClassNotFoundException e) {
            System.out.println("Не удается найти FancyToy");
            System.exit(1);
        }
        printInfo(c);

        for (Class face : c.getInterfaces())
            printInfo(face);

        Class up = c.getSuperclass();

        try {
            obj = up.newInstance();
        } catch (InstantiationException e) {
            System.out.println("Не удалось создать экземпляр");
            System.exit(1);
        } catch (IllegalAccessException e) {
            System.out.println("Отказано в доступе");
            System.exit(1);
        }

        printInfo(obj.getClass());

        //Создание объекта Toy с использованием отражения
        try {
            c = Class.forName("typeinfo.Toy");
        } catch (ClassNotFoundException e) {
            System.out.println("Не удается найти FancyToy");
            System.exit(1);
        }
*/
        try {
            c = Class.forName("typeinfo.Toy");
        } catch (ClassNotFoundException e) {
            System.out.println("Не удается найти Toy");
            System.exit(1);
        }

        System.out.println("Простое имя: " + c.getSimpleName());

        Constructor[] ctors = c.getConstructors();

        for (Constructor ctor : ctors)

                try {
                    obj = ctor.newInstance(5);
                    break;
                } catch (InstantiationException e) {
                    System.out.println("Не удалось создать экземпляр");
                    System.exit(1);
                } catch (IllegalAccessException e) {
                    System.out.println("Отказано в доступе");
                    System.exit(1);
                } catch (InvocationTargetException e) {
                    System.out.println("InvocationTargetException: " + e.getCause());
                    System.exit(1);
                } catch (IllegalArgumentException e) {
                    continue;
                }

        System.out.println(obj);
    }
}
