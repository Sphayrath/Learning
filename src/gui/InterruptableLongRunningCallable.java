package gui;

import net.mindview.util.SwingConsole;
import net.mindview.util.TaskItem;
import net.mindview.util.TaskManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * Created by Vlad on 02.04.2017.
 */

class Task implements Runnable {
    private static int counter = 0;
    private final int id = counter++;
    public void run() {
        System.out.println(this + " started");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            System.out.println(this + " interrupted");
            return;
        }
        System.out.println(this + " completed");
    }

    public String toString() { return "Task " + id; }
    public long id() { return id; }
}

class CallableTask extends Task implements Callable<String> {
    public String call() {
        run();
        return "Return value of " + this;
    }
}

public class InterruptableLongRunningCallable extends JFrame {
    private JButton
        b1 = new JButton("Start long running task"),
        b2 = new JButton("End long running task"),
        b3 = new JButton("Get results");
    private TaskManager<String,CallableTask> manager =
            new TaskManager<>();
    public InterruptableLongRunningCallable() {
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CallableTask task = new CallableTask();
                manager.add(task);
                System.out.println(task + " added to the queue");
            }
        });

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (String result : manager.purge())
                    System.out.println(result);
            }
        });

        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (TaskItem<String,CallableTask> tt : manager)
                    tt.task.id();
                for (String result : manager.getResults())
                    System.out.println(result);
            }
        });

        setLayout(new FlowLayout());
        add(b1);
        add(b2);
        add(b3);
    }

    public static void main(String[] args) {
        SwingConsole.run(new InterruptableLongRunningCallable(), 200, 150);
    }
}
