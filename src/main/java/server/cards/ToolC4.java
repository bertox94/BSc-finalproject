package server.cards;

import server.Dice;
import server.Player;
import shared.Position;
import server.abstracts.PrivateOC;
import server.threads.GameManager;

public class ToolC4 extends PrivateOC {

    private String name = "1";
    private String description = null;


    public boolean use(Player player, GameManager game, Position position11, Position position21, Position position12,Position position22){
        //move any one die in your window regardless only of shade restriction.
        Dice dice = player.frame.getDicePositions()[position11.row][position11.column];
        //check if position is not empty, else return
        if(dice == null)
            return false;
        //move the die
        player.frame.checkDicePositions(dice, position12, player);
        player.frame.getDicePositions()[position12.row][position12.column] = null;

        //same for second dice


        return true;
    }
}
