package Main;

/**
 * Created by Vlad on 28.02.2016.
 */
interface Cycle {
    public String toString ();
    public void wheels();
}

class Unicycle implements Cycle {
    public String toString (){
        return "Unicycle";
    }

    public void wheels (){
        System.out.println("one wheel");
    }
}

class Bicycle implements Cycle {
    public String toString (){
        return "Bicycle";
    }

    public void wheels (){
        System.out.println("two wheels");
    }
}

class Tricycle implements Cycle {
    public String toString (){
        return "Tricycle";
    }

    public void wheels (){
        System.out.println("three wheels");
    }
}

interface CyclesFactory {
    Cycle getCycle();
}

class UnicyclesFactory implements CyclesFactory {
    public Cycle getCycle(){
        return new Unicycle();
    }
}

class BicyclesFactory implements CyclesFactory {
    public Cycle getCycle(){
        return new Bicycle();
    }
}

class TricyclesFactory implements CyclesFactory {
    public Cycle getCycle(){
        return new Tricycle();
    }
}

public class Cycles {
    public static void rideCycle (CyclesFactory factory) {
        Cycle cycle = factory.getCycle();
        System.out.print("Riding " + cycle.toString() + " on ");
        cycle.wheels();
    }

    public static void main(String[] args){
        rideCycle(new UnicyclesFactory());
        rideCycle(new BicyclesFactory());
        rideCycle(new TricyclesFactory());
    }
}
