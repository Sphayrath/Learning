package enumerated;

import java.util.Random;

/**
 * Created by Vlad on 22.05.2016.
 */
public enum  Input {
    NICKEL(5), DIME(10), QUARTER(25), DOLLAR(100),
    TOOTHPASTE(200), CHIPS(75), SODA(100), SOAP(50),
    ABORT_TRANSACTION {
        public int amount() {
            throw new RuntimeException("ABORT.amount");
        }
    },
    STOP {
        public int amount() {
            throw new RuntimeException("SHUTDOWN.amount");
        }
    };

    int value;
    static Random random = new Random(47);

    Input(){}
    Input(int value) { this.value = value; }

    int amount() { return value; }
    public static Input randomSelection() {
        return values()[random.nextInt(values().length - 1)];
    }
}
