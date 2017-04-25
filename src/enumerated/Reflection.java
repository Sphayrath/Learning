package enumerated;

import net.mindview.util.OSExecute;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Vlad on 21.05.2016.
 */
enum Explore { HERE, THERE }

public class Reflection {
    public static Set<String> analize(Class<?> enumClass) {
        System.out.println("----- Analyzing " + enumClass + " -----");
        System.out.println("Interfaces:");
        for (Type t : enumClass.getGenericInterfaces())
            System.out.println(t);
        System.out.println("Base: " + enumClass.getSuperclass());
        System.out.println("Methods: ");
        Set<String> methods = new TreeSet<>();
        for (Method m : enumClass.getMethods())
            methods.add(m.getName());
        System.out.println(methods);
        return methods;
    }

    public static void main (String[] args) {
        Set<String> exploreMethods = analize(Explore.class);
        Set<String> enumMethods = analize(Enum.class);
        System.out.println("Explore.containsAll(Enum)? " +
                    exploreMethods.containsAll(enumMethods));
        System.out.println("Explore.removeAll(Enum): ");
        exploreMethods.removeAll(enumMethods);
        System.out.println(exploreMethods);
        OSExecute.command("javap Explore");
    }
}
