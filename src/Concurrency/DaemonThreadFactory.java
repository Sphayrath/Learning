package Concurrency;

import java.util.concurrent.ThreadFactory;

/**
 * Created by Vlad on 04.06.2016.
 */
public class DaemonThreadFactory implements ThreadFactory {
    public Thread newThread(Runnable r) {
        Thread t = newThread(r);
        t.setDaemon(true);
        return t;
    }
}
