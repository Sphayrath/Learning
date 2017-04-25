package Collections;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Vlad on 20.03.2016.
 */
class Command {
    private String command;
    public Command (String command) {this.command = command;}
    public void operation () {
        System.out.print(command);
    }
}

class CommandQueue {
    public static Queue<Command> fillCommandQueue (String[] commands) {
        Queue<Command> commandQueue = new LinkedList<>();
        for (String str : commands)
            commandQueue.offer(new Command(str));
        return  commandQueue;
    }
}

public class CommandQueueTest {
    public static void main (String[] args) {
        Queue<Command> commandQueue;
        String[] commands = "Push,Poll,Add,Delete,Clear,Fill".split(",");

        commandQueue = CommandQueue.fillCommandQueue(commands);

        while (commandQueue.peek()!=null)
            commandQueue.remove().operation();
    }
}
