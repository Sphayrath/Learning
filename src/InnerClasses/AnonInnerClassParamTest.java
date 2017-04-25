package InnerClasses;

class ClassParam {
    String name;
    ClassParam (String name) {this.name = name;}
    public String toString () {return name;}
}

class AnonInnerClassParam {
    public ClassParam innerClass (final String str) {
        return new ClassParam (str) {};
    }
}

public class AnonInnerClassParamTest {
    public static void main (String[] args){
        AnonInnerClassParam aicp = new AnonInnerClassParam();
        ClassParam test = aicp.innerClass("--TEST--");
        System.out.println(test);
    }
}
