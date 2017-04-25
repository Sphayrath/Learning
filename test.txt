package Files;

import java.io.*;
import java.util.*;

/**
 * Created by Vlad on 03.04.2016.
 */
public class TextFile extends ArrayList<String> {
    //Чтение всего файла как одной строки
    public static String read (String fileName) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader in = new BufferedReader(new FileReader(
                    new File(fileName).getAbsoluteFile()));
            try {
                String s;
                while ((s=in.readLine()) != null) {
                    sb.append(s);
                    sb.append("\n");
                }
            } finally {
                in.close();;
            }
        } catch (IOException e) {
            throw  new RuntimeException(e);
        }
        return sb.toString();
    }

    //Запись файла одним вызовом метода
    public static void write (String fileName, String text) {
        try {
            PrintWriter out = new PrintWriter(
                    new File(fileName).getAbsoluteFile());
            try {
                out.print(text);
            } finally {
                out.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //Чтение файла с разбивкой по регулярному выражению
    public TextFile (String fileName, String splitter) {
        super(Arrays.asList(read(fileName).split(splitter)));
        //Регулярное выражение split() часто оставляет
        //пустую строку в первой позиции
        if (get(0).equals("")) remove(0);
    }

    //Обычное чтение по строкам
    public TextFile (String fileName) {
        this (fileName, "\n");
    }

    public void write (String fileName) {
        try {
            PrintWriter out = new PrintWriter(
                    new File(fileName).getAbsoluteFile());
            try {
                for (String item : this)
                    out.println(item);
            } finally {
                out.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main (String[] args) {
        String file = read("D:\\SkyDrive\\Документы\\Learning\\src\\Files\\TextFile.java");
        write("D:\\SkyDrive\\Документы\\Learning\\test.txt", file);
        TextFile text = new TextFile("D:\\SkyDrive\\Документы\\Learning\\test.txt");
        text.write("D:\\SkyDrive\\Документы\\Learning\\test2.txt");
        TreeSet<String> words = new TreeSet<>(
                new TextFile("D:\\SkyDrive\\Документы\\Learning\\src\\Files\\TextFile.java","\\W+"));
        System.out.println(words.headSet("a"));
    }
}
