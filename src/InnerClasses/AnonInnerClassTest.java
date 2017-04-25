package InnerClasses;

/**
 * Created by Vlad on 06.03.2016.
 */
interface InterfaceTest {
    public void MethodTest ();
}

class InnerClassTest {
    public InterfaceTest innerClass () {
        return new InterfaceTest () {
            public void MethodTest (){
                System.out.println("--MethodTest--");
            }
        };
    }
}

public class AnonInnerClassTest {
    public static void main (String[] args){
        InnerClassTest aict = new InnerClassTest();
        InterfaceTest test = aict.innerClass();
        test.MethodTest();
    }
}
