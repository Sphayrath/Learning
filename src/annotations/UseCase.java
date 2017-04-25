package annotations;

import java.lang.annotation.*;

/**
 * Created by VFilin on 31.05.2016.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UseCase {
    public int id();
    public String description() default "no description";
}
