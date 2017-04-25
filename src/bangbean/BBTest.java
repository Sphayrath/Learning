package bangbean;

import javax.swing.*;

/**
 * Created by VFilin on 06.04.2017.
 * BranchTest2
 */
public class BBTest {
    private BangBean bangBean;
    private JPanel panel;

    public static void main(String[] args) {
        JFrame frame = new JFrame("BBTest");
        frame.setContentPane(new BBTest().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
