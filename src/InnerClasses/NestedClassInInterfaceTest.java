package InnerClasses;

/**
 * Created by Vlad on 06.03.2016.
 */
interface InterfaceForNestedClass {
    public void method0 ();
    public void method1 ();
    public static class NestedClassInInterface {
        public static void nestedClassMethod (InterfaceForNestedClass example) {
            example.method0();
            example.method1();
        }
    }
}

class NestedClassInInterface1 implements InterfaceForNestedClass {
    public void method0 () {System.out.println("Method --0--");}
    public void method1 () {System.out.println("Method --1--");}
}

class NestedClassInInterface2 implements InterfaceForNestedClass {
    public void method0 () {System.out.println("Method --2--");}
    public void method1 () {System.out.println("Method --3--");}
}

public class NestedClassInInterfaceTest {
    public static void main (String[] args) {
        InterfaceForNestedClass.NestedClassInInterface.nestedClassMethod(new NestedClassInInterface1());
        InterfaceForNestedClass.NestedClassInInterface.nestedClassMethod(new NestedClassInInterface2());
    }
}
