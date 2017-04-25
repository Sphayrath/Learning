package gui;

import net.mindview.util.SwingConsole;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Vlad on 25.03.2017.
 */
public class Task22_6 extends JFrame {
    JButton button = new JButton("Apply");
    JTextArea textArea1 = new JTextArea(5, 20),
        textArea2 = new JTextArea(5, 20);
    JTextField field = new JTextField(20);
    String Input, Regular;

    public Task22_6() {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Input = textArea1.getText();
                Regular = field.getText();
                Pattern p = Pattern.compile(Regular);
                Matcher m = p.matcher(Input);
                textArea2.setText("");
                while (m.find())
                    textArea2.append("Match \"" + m.group() + "\" at positions " +
                            m.start() + "-" + (m.end() - 1) +"\n");
            }
        });

        setLayout(new FlowLayout());
        add(new JScrollPane(textArea1));
        add(field);
        add(new JScrollPane(textArea2));
        add(button);
    }

    public static void main(String[] args) {
        SwingConsole.run(new Task22_6(), 275, 300);
    }
}
