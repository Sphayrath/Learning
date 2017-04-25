package gui;

import net.mindview.util.SwingConsole;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Vlad on 26.03.2017.
 */
public class BorderLayout1 extends JFrame {
    public BorderLayout1() {
        add(BorderLayout.NORTH, new JButton("North"));
        add(BorderLayout.SOUTH, new JButton("South"));
        add(BorderLayout.EAST, new JButton("East"));
        add(BorderLayout.WEST, new JButton("West"));
        add(BorderLayout.CENTER, new JButton("Center"));
    }

    public static void main(String[] args) {
        SwingConsole.run(new BorderLayout1(), 300, 250);
    }
}
