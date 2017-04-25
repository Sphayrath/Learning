package gui;

import net.mindview.util.Countries;
import net.mindview.util.SwingConsole;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Vlad on 25.03.2017.
 */
public class TextArea extends JFrame {
    private JButton
        b = new JButton("Add Data"),
        c = new JButton("Clear Data");
    private JTextArea t = new JTextArea(20, 40);
    private Map<String, String> m = new HashMap<>();
    public TextArea() {
        m.putAll(Countries.capitals());
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Map.Entry me : m.entrySet())
                    t.append(me.getKey() + ": " + me.getValue()+ "\n");
            }
        });

        c.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                t.setText("");
            }
        });

        setLayout(new FlowLayout());
        add(new JScrollPane(t));
        add(b);
        add(c);
        setCursor(new Cursor(1));
    }

    public static void main(String[] args) {
        SwingConsole.run(new TextArea(), 500, 425);
    }
}
