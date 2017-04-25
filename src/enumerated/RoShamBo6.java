package enumerated;

import static enumerated.Outcome.*;

/**
 * Created by Vlad on 29.05.2016.
 */
public enum RoShamBo6 implements Competitor<RoShamBo6> {
    PAPER, SCISSORS, ROCK;

    private static Outcome[][] table = {
            { DRAW, LOSE, WIN }, //Paper
            { WIN, DRAW, LOSE }, //Scissors
            { LOSE, WIN, DRAW }, //Rock
    };

    public Outcome compete(RoShamBo6 other) {
        return table[this.ordinal()][other.ordinal()];
    }

    public static void main(String[] args) {
        RoShamBo.play(RoShamBo6.class, 20);
    }
}
