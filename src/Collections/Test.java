package Collections;


/**
 * Среда для проведения хронометражного тестирования контейнеров
 * Created by Vlad on 08.05.2016.
 */
public abstract class Test<C> {
    String name;

    public Test (String name) { this.name = name; }
    abstract int test (C container, TestParam tp);
}
