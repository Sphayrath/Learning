package Main;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(new RandomChar(20));
        while (s.hasNext()) System.out.println(s.next());
    }
}
