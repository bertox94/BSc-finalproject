package server.cardsServer.windows;
import shared.Cell;
import server.abstractsServer.Window;

public class Window5 extends Window {

    public Window5(){
        setName("Window5");
        Cell[][] cells = new Cell[4][5];

        //assign of cells


        setCells(cells);
    }
}