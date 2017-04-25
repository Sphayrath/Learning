package Main;
import Interfaces.Processor;

/**
 * Created by Vlad on 28.02.2016.
 */
public class Switcher implements Processor {
    public String name (){
        return getClass().getSimpleName();
    }

    public String process (Object input){
        char[] result = ((String)input).toCharArray();
        char c1, c2;
        for (int i=0; i<=result.length-4; i=i+4){
            c1=result[i];
            c2=result[i+1];
            result[i]=result[i+2];
            result[i+1]=result[i+3];
            result[i+2]=c1;
            result[i+3]=c2;
        }
        return new String(result);
    }
}
