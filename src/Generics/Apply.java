package Generics;

import typeinfo.pets.*;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 17.04.2016.
 */
public class Apply {
    public static <T, S extends Iterable<? extends T>>
    void apply (S seq, Method method, Object... args) {
        try {
            for (T s : seq)
                method.invoke(s, args);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main (String[] args) {
        List<Pet> pets = Pets.arrayList(10);
        System.out.println(pets);
        try {
            apply(pets, Pet.class.getMethod("speak"));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
