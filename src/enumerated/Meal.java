package enumerated;

/**
 * Created by Vlad on 21.05.2016.
 */
public class Meal {
    public static void main (String[] args) {
        for (int i=0; i<5; i++) {
            for (Course course : Course.values()) {
                Food food = course.randomSelection();
                System.out.println(food);
            }
            System.out.println("---");
        }
    }
}
