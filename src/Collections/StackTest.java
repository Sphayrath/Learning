package Collections;

/**
 * Created by Vlad on 15.03.2016.
 */
public class StackTest {
    public static void main (String[] args) {
        Stack stack = new Stack();
        String expression = "+U+n+c---+e+r+t---+a-+i-+n+t+y---+ -+r+u--+l+e+s---";
        char[] c = expression.toCharArray();
        for (int i=0; i<c.length; i++) {
            if (c[i] == '+') {
                i++;
                stack.push(c[i]);
            } else
                if (c[i] == '-')
                    System.out.print(stack.pop());
                else {
                    System.out.println("Неверное выражение");
                    break;
                }
        }
    }
}
