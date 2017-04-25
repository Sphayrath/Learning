package InnerClasses;

/**
 * Created by Vlad on 06.03.2016.
 */
class ExampleClass {
    public static class NestedClass {
        public static void print () {
            System.out.println("Nested class");
        }

        public static class NestedNestedClass {
            public static void print () {
                System.out.println("Nested class in nested class");
            }
        }
    }
}
public class NestedNestedClassTest {
    public static void main (String[] args) {
        ExampleClass.NestedClass.print();
        ExampleClass.NestedClass.NestedNestedClass.print();
    }
}
