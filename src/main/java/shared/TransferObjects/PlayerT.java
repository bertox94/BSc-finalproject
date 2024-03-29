package shared.TransferObjects;

import shared.Dice;
import shared.Position;

import java.io.Serializable;

public class PlayerT implements Serializable {
    public final String nickName;
    public final Character privateO;
    public final WindowT window;
    public final Dice[][] overlay;
    public final Integer tokens;
    public final Integer turno;
    public final Integer score;
    public final Integer privateTurn;
    public final Position lastPlaced;

    public PlayerT(String nickName, Character privateOC,
                   WindowT window, Dice[][] overlay,
                   Integer tokens, Integer turno,
                   Integer score, Integer privateTurn,
                   Position lastPlaced) {

        this.nickName = nickName;
        this.privateO = privateOC;
        this.window = window;
        this.overlay = overlay;
        this.tokens = tokens;
        this.turno = turno;
        this.score = score;
        this.privateTurn = privateTurn;
        this.lastPlaced = lastPlaced;

    }
}