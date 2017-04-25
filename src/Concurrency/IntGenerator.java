package Concurrency;

/**
 * Created by Vlad on 05.06.2016.
 */
public abstract class IntGenerator {
    private volatile boolean cancelled = false;
    public abstract int next();
    public void cancel() { cancelled = true; }
    public boolean isCancelled() { return cancelled; }
}
