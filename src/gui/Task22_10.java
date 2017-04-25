package gui;

import net.mindview.util.SwingConsole;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by Vlad on 26.03.2017.
 */
public class Task22_10 extends JFrame {
    private JButton button = new JButton("Button");
    private JTextField field = new JTextField(40);

    public Task22_10() {
        button.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                if (button.hasFocus()) {
                    String text = field.getText();
                    text = text + e.getKeyChar();
                    field.setText(text);
                }
            }
        });

        button.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                field.setText("");
            }
        });

        field.setEditable(false);
        setLayout(new FlowLayout());
        add(field);
        add(button);
    }

    public static void main(String[] args) {
        SwingConsole.run(new Task22_10(), 600, 100);
    }
}
