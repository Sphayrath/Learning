package enumerated;

import net.mindview.util.Generator;
import net.mindview.util.RandomGenerator;
import net.mindview.util.TextFile;

import java.util.EnumMap;
import java.util.Iterator;

import static enumerated.Input.*;
/**
 * Created by Vlad on 22.05.2016.
 */
interface VendingHandler {
    void handle (Input input);
}

enum Category {
    MONEY(NICKEL, DIME, QUARTER, DOLLAR),
    ITEM_SELECTION(TOOTHPASTE, CHIPS, SODA, SOAP),
    QUIT_TRANSACTION(ABORT_TRANSACTION),
    SHUT_DOWN(STOP);

    private Input[] values;
    private static EnumMap<Input,Category> categories = new EnumMap<>(Input.class);

    Category (Input... types) { values = types; }

    static {
        for (Category c : Category.class.getEnumConstants())
            for (Input type : c.values)
                categories.put(type, c);
    }

    public static Category categorize(Input input) {
        return categories.get(input);
    }
}

public class VendingMachine {
    enum StateDuration { TRANSIENT }
    enum State {
        RESTING, ADDING_MONEY, DISPENSING (StateDuration.TRANSIENT), GIVING_CHANGE (StateDuration.TRANSIENT), TERMINAL;
        private boolean isTransient = false;
        State() {}
        State(StateDuration trans) { isTransient = true; }
    }
    private EnumMap<State,VendingHandler> states = new EnumMap<>(State.class);

    private State state = State.RESTING;
    private int amount = 0;
    private Input selection = null;

    public VendingMachine() { fill(); }

    public void fill () {
        states.put(State.RESTING, new VendingHandler() {
            @Override
            public void handle(Input input) {
                switch (Category.categorize(input)) {
                    case MONEY:
                        amount += input.amount();
                        state = State.ADDING_MONEY;
                        break;
                    case SHUT_DOWN: state = State.TERMINAL;
                    default:
                }
            }
        });

        states.put(State.ADDING_MONEY, new VendingHandler() {
            @Override
            public void handle(Input input) {
                switch (Category.categorize(input)) {
                    case MONEY:
                        amount += input.amount();
                        break;
                    case ITEM_SELECTION:
                        selection = input;
                        if (amount < selection.amount())
                            System.out.println("Insufficient money for " + selection);
                        else state = State.DISPENSING;
                        break;
                    case QUIT_TRANSACTION:
                        state = State.GIVING_CHANGE;
                        break;
                    case SHUT_DOWN:
                        state = State.TERMINAL;
                    default:
                }
            }
        });

        states.put(State.DISPENSING, new VendingHandler() {
            @Override
            public void handle(Input input) {
                System.out.println("Here is your " + selection);
                amount -= selection.amount();
                state = State.GIVING_CHANGE;
            }
        });

        states.put(State.GIVING_CHANGE, new VendingHandler() {
            @Override
            public void handle(Input input) {
                if (amount > 0) {
                    System.out.println("Your change: " + amount);
                    amount = 0;
                }
                state = State.RESTING;
            }
        });

        states.put(State.TERMINAL, new VendingHandler() {
            @Override
            public void handle(Input input) {
                System.out.println("Halted");
            }
        });
    }

    /*
    enum State {
        RESTING {
            void next(Input input) {
                switch (Category.categorize(input)) {
                    case MONEY:
                        amount += input.amount();
                        state = ADDING_MONEY;
                        break;
                    case SHUT_DOWN: state = TERMINAL;
                    default:
                }
            }
        },

        ADDING_MONEY {
            void next(Input input) {
                switch (Category.categorize(input)) {
                    case MONEY:
                        amount += input.amount();
                        break;
                    case ITEM_SELECTION:
                        selection = input;
                        if (amount < selection.amount())
                            System.out.println("Insufficient money for " + selection);
                        else state = DISPENSING;
                        break;
                    case QUIT_TRANSACTION:
                        state = GIVING_CHANGE;
                        break;
                    case SHUT_DOWN:
                        state = TERMINAL;
                    default:
                }
            }
        },

        DISPENSING(StateDuration.TRANSIENT) {
            void next() {
                System.out.println("Here is your " + selection);
                amount -= selection.amount();
                state = GIVING_CHANGE;
            }
        },

        GIVING_CHANGE(StateDuration.TRANSIENT) {
            void next() {
                if (amount > 0) {
                    System.out.println("Your change: " + amount);
                    amount = 0;
                }
                state = RESTING;
            }
        },

        TERMINAL { void  output() { System.out.println("Halted");}};

        private boolean isTransient = false;
        State() {}
        State(StateDuration trans) { isTransient = true; }

        void next(Input input) {
            throw new RuntimeException("Only call next(Input input) for " +
                    "non-transient states");
        }

        void next() {
            throw new RuntimeException("Only call next() for " +
                "StateDuration.TRANSIENT states");
        }

        void output() {System.out.println(amount);}
    }*/

    public void run(Generator<Input> gen) {
        while (state != State.TERMINAL) {
            states.get(state).handle(gen.next());
            while (state.isTransient)
                states.get(state).handle(null);
        }
    }

    public static void main(String[] args) {
        VendingMachine vendingMachine = new VendingMachine();
        //Generator<Input> generator = new RandomInputGenerator();
        Generator<Input> generator = new FileInputGenerator("VendingMachineInput.txt");
        vendingMachine.run(generator);
    }
}

class RandomInputGenerator implements Generator<Input> {
    public Input next() { return Input.randomSelection(); }
}

class FileInputGenerator implements Generator<Input> {
    private Iterator<String> input;

    public FileInputGenerator(String fileName) {
        input = new TextFile(fileName, ";").iterator();
    }

    public Input next() {
        if (!input.hasNext())
            return null;
        return Enum.valueOf(Input.class, input.next().trim());
    }
}
