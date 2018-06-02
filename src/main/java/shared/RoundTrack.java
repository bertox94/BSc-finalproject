package shared;


import shared.Dice;

import java.io.Serializable;
import java.util.ArrayList;

public class RoundTrack implements Serializable {
    private Integer turn = 0;
    private ArrayList<ArrayList<Dice>> dices = new ArrayList<>(10);

    public RoundTrack() {
        for (int i = 1; i <= 10; i++) {
            dices.add(new ArrayList<>());
        }
    }

    public ArrayList<ArrayList<Dice>> getDices() {
        return dices;
    }

    public boolean busy(PositionR positionR) {
        Dice dice = this.dices.get(positionR.column).get(positionR.column);
        if (dice == null)
            return false;
        return true;
    }

    public void setDice(Dice dice, PositionR positionR) {
        this.dices.get(positionR.column).set(positionR.column, dice);
    }

    public void setDice(Dice dice, Integer column) {
        this.dices.get(column - 1).add(dice);
    }

    public Dice getDice(PositionR positionR) {
        return this.dices.get(positionR.column).get(positionR.column);
    }

    public Integer sumDices() {
        int sum = 0;
        for (ArrayList<Dice> arr :
                dices) {
            if (arr != null)
                for (Dice dice :
                        arr) {
                    if (dice != null)
                        sum++;
                }
        }
        return sum;
    }
}