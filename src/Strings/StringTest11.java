package Strings;

import java.util.regex.*;

/**
 * Created by VFilin on 31.03.2016.
 */
public class StringTest11 {
    public static void main (String[] args) {
        String str = "Airline ate eight apples and one orange while Anita hadn't any";
        Pattern pattern = Pattern.compile("(?i)((^[aeiou])|(\\s+[aeiou]))\\w+?[aeiou]\\b");
        Matcher matcher = pattern.matcher(str);
        System.out.println(matcher.find());
    }
}
