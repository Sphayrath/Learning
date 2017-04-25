package Strings;

import java.util.Arrays;

/**
 * Created by Vlad on 28.03.2016.
 */
public class Splitting {
    public static String knights =
            "Then, when you have found the shrubbery, you must " +
            "cut down the mightiest tree in the forest... " +
            "with... a herring.";
    public static void main (String[] args) {
        String vowels = "[AEIOUaeiou]";
        //System.out.println (Arrays.toString(knights.split(" ")));
        //System.out.println (Arrays.toString(knights.split("\\W+")));
        //System.out.println (Arrays.toString(knights.split("n\\W+")));

        //Упражнение 8
        //System.out.println (Arrays.toString(knights.split("the|you")));

        //Упражнение 9
        System.out.println (knights.replaceAll(vowels,"_"));

        //Упражнение 7
        System.out.println (knights.matches("(\\p{Upper})(.+)(\\.)"));
    }
}
