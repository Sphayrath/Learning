package enumerated;

/**
 * Created by Vlad on 29.05.2016.
 */
public interface Competitor<T extends Competitor<T>> {
    Outcome compete(T competitor);
}
