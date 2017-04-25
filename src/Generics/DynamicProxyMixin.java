package Generics;

import net.mindview.util.TwoTuple;
import java.lang.reflect.*;
import java.util.*;
import static net.mindview.util.Tuple.*;

/**
 * Created by Vlad on 16.04.2016.
 */

class MixinProxy implements InvocationHandler {
    Map<String,Object> delegatesByMethod;
    public MixinProxy (TwoTuple<Object,Class<?>>... pairs) {
        delegatesByMethod = new HashMap<>();
        for (TwoTuple<Object,Class<?>> pair : pairs) {
            for (Method method : pair.second.getMethods()) {
                String methodName = method.getName();
                if (!delegatesByMethod.containsKey(methodName))
                    delegatesByMethod.put(methodName, pair.first);
            }
        }
    }

    public Object invoke (Object proxy, Method method,
                          Object[] args) throws Throwable {
        String methodName = method.getName();
        Object delegate = delegatesByMethod.get(methodName);
        return method.invoke(delegate, args);
    }

    public static Object newInstance (TwoTuple... pairs) {
        Class[] interfaces = new  Class[pairs.length];
        for (int i=0; i<pairs.length; i++) {
            interfaces[i] = (Class)pairs[i].second;
        }
        ClassLoader cl = pairs[0].first.getClass().getClassLoader();
        return Proxy.newProxyInstance(cl, interfaces, new MixinProxy(pairs));
    }
}
public class DynamicProxyMixin {
    public static void main (String[] args) {
        Object mixin = MixinProxy.newInstance (
                tuple(new BasicImpl(), Basic.class),
                tuple(new TimeStampedImpl(), TimeStamped.class),
                tuple(new SerialNumberedImpl(), SerialNumbered.class),
                tuple(new ColoredImpl(), Colored.class));

        Basic b = (Basic)mixin;
        TimeStamped t = (TimeStamped)mixin;
        SerialNumbered s = (SerialNumbered)mixin;
        Colored c = (Colored)mixin;
        b.set("Mixin");
        c.setColor("Green");

        System.out.println(c.getColor());
        System.out.println(b.get());
        System.out.println(s.getSerialNumber());
        System.out.println(t.getStamp());
    }
}
