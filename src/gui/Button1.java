package gui;

import net.mindview.util.SwingConsole;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Vlad on 25.03.2017.
 */
public class Button1 extends JFrame {
    private JButton
        b1 = new JButton("Button 1"),
        b2 = new JButton("Button 2");

    public Button1() {
        setLayout(new FlowLayout());
        add(b1);
        add(b2);
    }

    public static void main(String[] args) {
        SwingConsole.run(new Button1(), 800, 600);
    }
}
