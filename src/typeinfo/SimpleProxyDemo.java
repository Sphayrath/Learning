package typeinfo;

/**
 * Created by Vlad on 10.04.2016.
 */
interface Interface {
    void doSomething();
    void somethingElse(String arg);
}

class RealObject implements Interface {

    @Override
    public void doSomething() {
        for (int i=0; i<Integer.MAX_VALUE; i++) {}
        System.out.println("doSomething");
    }

    @Override
    public void somethingElse(String arg) {
        for (int i=0; i<3; i++)
            for (int j=0; j<Integer.MAX_VALUE; j++)
                for (int z=0; z<Integer.MAX_VALUE; z++) {}
        System.out.println("somethingElse " + arg);
    }
}

class SimpleProxy implements Interface {
    private Interface proxied;

    public SimpleProxy (Interface proxied) {this.proxied=proxied;}

    @Override
    public void doSomething() {
        long time = System.currentTimeMillis();
        proxied.doSomething();
        time = System.currentTimeMillis() - time;
        System.out.println("takes time: " + time);
    }

    @Override
    public void somethingElse(String arg) {
        long time = System.currentTimeMillis();
        proxied.somethingElse(arg);
        time = System.currentTimeMillis() - time;
        System.out.println("takes time: " + time);
    }
}

public class SimpleProxyDemo {
    public static void consumer (Interface iface) {
        iface.doSomething();
        iface.somethingElse("olololo");
    }

    public static void main (String[] args) {
        consumer(new RealObject());
        System.out.println();
        consumer(new SimpleProxy(new RealObject()));
    }
}
