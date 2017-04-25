package Strings;

import java.util.regex.*;

/**
 * Created by VFilin on 31.03.2016.
 */
public class StringTest10 {

    //Коментарий 1
    public static void main (String[] args) {
        //Коментарий 2
        String str = "Java now has regular expressions";
        Matcher[] matches = new Matcher[9];
        matches[0] = Pattern.compile("^Java").matcher(str);
        matches[1] = Pattern.compile("\\Breg.*").matcher(str);
        matches[2] = Pattern.compile("n.w\\s+h(a|i)s").matcher(str);
        matches[3] = Pattern.compile("s?").matcher(str);
        matches[4] = Pattern.compile("s*").matcher(str);
        /*
        matches[5] = Pattern.compile("s+").matcher(str);
        matches[6] = Pattern.compile("s{4}").matcher(str);
        */
        matches[7] = Pattern.compile("s{1,}").matcher(str);
        matches[8] = Pattern.compile("s{0,3}").matcher(str);

        //Коментарий 3
        for (Matcher m : matches )
            while (matches[8].find())
                System.out.println("Match \"" + matches[8].group() + "\" at positions " + matches[8].start() + "-" + (matches[8].end()-1));
    }
}
