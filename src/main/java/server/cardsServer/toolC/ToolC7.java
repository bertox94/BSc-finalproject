package server.cardsServer.toolC;

import shared.Dice;
import server.Player;
import shared.Position;
import shared.PositionR;
import server.abstractsServer.ToolC;
import server.threads.GameManager;

import java.util.Random;

public class ToolC7 extends ToolC {
    public ToolC7() {
        this.name = "toolC7";
    }

    @Override
    public boolean ableAndSettle(Player player) {
        Integer tokens = player.tokens;
        if (tokens < tokensRequired)
            return false;
        player.tokens = tokens - tokensRequired;
        tokensRequired = 2;
        player.hasUsedTc = true;
        return true;
    }



    public boolean use(GameManager game, Player player, Position p1, Position p2, Position p3, Position p4, PositionR pr, Integer i2, Integer i3) {

        if (!ableAndSettle(player))
            return false;

        if (player.privateTurn != 2)
            return false;
        Random rand = new Random();
        for (Dice d :
                game.pool) {
            d.value = rand.nextInt(6);
        }
        return true;
    }
}