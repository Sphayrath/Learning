package InnerClasses;

/**
 * Created by Vlad on 06.03.2016.
 */
public class Outer {
    private String str;

    Outer (String str) {this.str = str;}

    class Inner {
        public int test = 0;
        public String toString() {return str;}
    }

    Inner getInner (){
        return new Inner();
    }

    public static void main(String[] args) {
        Outer outer = new Outer("Test1");
        Outer.Inner inner = outer.new Inner();
        //Outer.Inner inner = outer.getInner();
        System.out.print(inner);
    }
}