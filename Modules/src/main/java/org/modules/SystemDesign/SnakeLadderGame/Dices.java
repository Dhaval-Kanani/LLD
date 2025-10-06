package org.modules.SystemDesign.SnakeLadderGame;

import java.util.concurrent.ThreadLocalRandom;

public class Dices {
    private Integer diceCount;
    private Integer min;
    private Integer max;

    public Dices(Integer diceCount, Integer min, Integer max) {
        this.diceCount = diceCount;
        this.min = min;
        this.max = max;
    }

    public int rollDice(){
        int count = 0;
        int sum = 0;

        while (count<diceCount){
            int number = ThreadLocalRandom.current().nextInt(min, max+1);
            sum += number;
            count++;
        }
        return sum;
    }
}
