package Interfaces;

/**
 * Created by Vlad on 28.02.2016.
 */
public class Apply {
    public static void process (Processor p, Object s){
        System.out.println("Using Interfaces.Processor" + p.name());
        System.out.println(p.process(s));
    }
}

