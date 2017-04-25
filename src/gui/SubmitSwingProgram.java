package gui;

import net.mindview.util.SwingConsole;

import javax.swing.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by Vlad on 25.03.2017.
 */
public class SubmitSwingProgram extends JFrame {
    JLabel label;

    public SubmitSwingProgram() {
        label = new JLabel("A Label");
        add(label);
    }

    static SubmitSwingProgram ssp;
    public static void main (String[] args) throws Exception {
        ssp = new SubmitSwingProgram();
        SwingConsole.run(ssp, 800, 600);
        TimeUnit.SECONDS.sleep(1);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ssp.label.setText("Hey! This is Different!");
            }
        });
    }
}
