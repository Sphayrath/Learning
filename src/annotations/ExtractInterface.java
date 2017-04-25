package annotations;

import java.lang.annotation.*;

/**
 * Created by VFilin on 31.05.2016.
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface ExtractInterface {
    public String value();
}
