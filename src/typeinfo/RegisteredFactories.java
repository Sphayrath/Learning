package typeinfo;

import java.lang.reflect.*;
import java.util.*;

/**
 * Created by Vlad on 10.04.2016.
 */

class Part {
    public String toString() {
        return getClass().getSimpleName();
    }

    static List<Factory<? extends Part>> partFactories =
            new ArrayList<>();

    static {
        // Collections.addAll() gives an "unchecked generic
        // array creation ... for varargs parameter" warning.
        partFactories.add(new FuelFilter.Factory());
        partFactories.add(new AirFilter.Factory());
        partFactories.add(new CabinAirFilter.Factory());
        partFactories.add(new OilFilter.Factory());
        partFactories.add(new FanBelt.Factory());
        partFactories.add(new PowerSteeringBelt.Factory());
        partFactories.add(new GeneratorBelt.Factory());
    }

    private static Random rand = new Random(47);

    public static Part createRandom() {
        int n = rand.nextInt(partFactories.size());
        return partFactories.get(n).create();
    }

    public static Part create (Class<? extends Part> type) {
        Part part=null;
        try {
            part=type.newInstance();
        } catch (InstantiationException e) {
            new RuntimeException(e);
        } catch (IllegalAccessException e) {
            new RuntimeException(e);
        }
        return part;
    }
}

class Filter extends Part {}

class FuelFilter extends Filter {
    // Create a Class Factory for each specific type:
    public static class Factory
            implements typeinfo.Factory<FuelFilter> {
        public FuelFilter create() { return new FuelFilter(); }
    }
}

class AirFilter extends Filter {
    public static class Factory
            implements typeinfo.Factory<AirFilter> {
        public AirFilter create() { return new AirFilter(); }
    }
}

class CabinAirFilter extends Filter {
    public static class Factory
            implements typeinfo.Factory<CabinAirFilter> {
        public CabinAirFilter create() {
            return new CabinAirFilter();
        }
    }
}

class OilFilter extends Filter {
    public static class Factory
            implements typeinfo.Factory<OilFilter> {
        public OilFilter create() { return new OilFilter(); }
    }
}

class Belt extends Part {}

class FanBelt extends Belt {
    public static class Factory
            implements typeinfo.Factory<FanBelt> {
        public FanBelt create() { return new FanBelt(); }
    }
}

class GeneratorBelt extends Belt {
    public static class Factory
            implements typeinfo.Factory<GeneratorBelt> {
        public GeneratorBelt create() {
            return new GeneratorBelt();
        }
    }
}

class PowerSteeringBelt extends Belt {
    public static class Factory
            implements typeinfo.Factory<PowerSteeringBelt> {
        public PowerSteeringBelt create() {
            return new PowerSteeringBelt();
        }
    }
}

class NullPartProxyHandler implements InvocationHandler {
    private String nullName;
    private Part proxied = new NPart();

    NullPartProxyHandler (Class<? extends Part> type) {
        nullName = type.getSimpleName() + " NullPart";
    }

    private class NPart extends Part implements Null {
        @Override
        public String toString() {
            return nullName;
        }
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(proxied, args);
    }

}

//NullОбъект сделан пиздец криво, но для обучения пойдет
class NullPart {
    public static Object newNullPart (Class<? extends Part> type) {
        return Proxy.newProxyInstance(
                NullPart.class.getClassLoader(),
                new Class[] {Null.class},
                new NullPartProxyHandler(type));
    }
}

public class RegisteredFactories {
    public static void main(String[] args) {
        Object[] parts = new Object[10];

        /*for(int i = 0; i < 10; i++)
            System.out.println(Part.createRandom());*/

        for(int i=0; i < 10; i++)
            parts[i] = NullPart.newNullPart(Part.createRandom().getClass());

        for (Object part : parts)
            if (part instanceof Null)
                System.out.println(part);
    }
}
