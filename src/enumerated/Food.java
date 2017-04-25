package enumerated;

/**
 * Created by Vlad on 21.05.2016.
 */
public interface Food {
    enum Appetizer implements Food {
        SALAD, SOUP, SPRING_ROLLS
    }
    enum MainCourse implements Food {
        LASAGNE, BURRITO, PAD_THAI, LENTILS, HUMMOUS, VINDALOO
    }
    enum Dessert implements Food {
        TIRAMISU, GELATO, BLACK_FOREST_CAKE, FRUIT, CREME_CARAMEL
    }
    enum Coffee implements Food {
        BLACK_COFFEE, DECAF_COFFE, ESPRESSO, LATTE, CAPPUCCINO, TEA, HERB_TEA
    }
}
