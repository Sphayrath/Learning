package typeinfo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by Vlad on 10.04.2016.
 */
class DynamicProxyHandler implements InvocationHandler {
    private Object proxied;

    public DynamicProxyHandler (Object proxied) {
        this.proxied=proxied;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        System.out.println("*** proxy: " + proxy.getClass() +
        ", method: " + method + ",args: " + args);
        /*if (args!=null)
            for (Object arg : args)
                System.out.println(" " + arg);*/
        long time = System.currentTimeMillis();
        Object object = method.invoke(proxied, args);
        time = System.currentTimeMillis() - time;
        System.out.println("Run time: " + time);
        return object;
    }
}

public class SimpleDynamicProxy {
    public static void consumer (Interface iface) {
        iface.doSomething();
        System.out.println();
        iface.somethingElse("Dynamic ololo");
    }

    public static void main (String[] args) {
        RealObject realObject = new RealObject();
        //consumer(realObject);

        Interface proxy = (Interface) Proxy.newProxyInstance (
                Interface.class.getClassLoader(),
                new Class[] {Interface.class},
                new DynamicProxyHandler(realObject));
        consumer(proxy);
    }
}
