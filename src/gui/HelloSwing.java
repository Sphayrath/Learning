package gui;

import javax.swing.*;

/**
 * Created by Vlad on 25.03.2017.
 */
public class HelloSwing {
    public static void main (String[] args) {
        JFrame frame = new JFrame("Hello Swing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 100);
        frame.setVisible(true);
    }
}
