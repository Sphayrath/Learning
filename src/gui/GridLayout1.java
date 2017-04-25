package gui;

import net.mindview.util.SwingConsole;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Vlad on 26.03.2017.
 */
public class GridLayout1 extends JFrame {
    public GridLayout1() {
        setLayout(new GridLayout(7,3));
        for (int i=0; i<20; i++)
            add(new JButton("Button " + i));
    }

    public static void main(String[] args) {
        SwingConsole.run(new GridLayout1(), 300, 300);
    }
}
