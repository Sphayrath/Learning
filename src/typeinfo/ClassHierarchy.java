package typeinfo;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by VFilin on 04.04.2016.
 */
public class ClassHierarchy {
    static void showClassHierarchy (Class cc) {
        System.out.println(cc.getSimpleName());
        for (Field field : cc.getDeclaredFields())
            System.out.println(field);
        System.out.println();
        try {
            showClassHierarchy(cc.getSuperclass());
        } catch (NullPointerException e) {
            return;
        }
    }

    static void showClassHierarchy (Object obj) {
        showClassHierarchy(obj.getClass());
    }

    public static void main (String[] args) {
        Class c = null;
        try {
            c = Class.forName("java.lang.Class");
        } catch (ClassNotFoundException e) {
            System.out.println("Class Not Found");
        }
        showClassHierarchy(c);
    }
}
