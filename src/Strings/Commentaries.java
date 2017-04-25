package Strings;

import Files.TextFile;

import java.util.regex.*;

/**
 * Created by Vlad on 03.04.2016.
 */
public class Commentaries {
    //Вывод всех комментариев в файле
    public static void main (String[] args) {
        String text = TextFile.read(
                "D:\\SkyDrive\\Документы\\Learning\\src\\Strings\\StringTest10.java");
        Matcher matcher1 = Pattern.compile("(//(.+)\\n)|(/\\*((.|\\n)+)\\*/)").matcher(text);
        while (matcher1.find())
            System.out.println(matcher1.group());
    }
}
