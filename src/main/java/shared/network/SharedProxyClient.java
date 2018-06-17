package shared.network;

import shared.Cell;
import shared.Position;
import shared.PositionR;
import shared.TransferObjects.GameManagerT;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface SharedProxyClient extends Remote {
    String startGame(String nick) throws RemoteException;
    void updateView(GameManagerT gameManager) throws RemoteException;
    Boolean chooseWindow(ArrayList<Integer> windows, ArrayList<Cell[][]> matrices) throws RemoteException;
    Boolean ping() throws RemoteException;
    void aPrioriWin() throws RemoteException;
    void enable() throws RemoteException;
    void shut() throws RemoteException;
    void printScore(ArrayList<String> nicks, ArrayList<Integer> scores, ArrayList<Boolean> winner) throws RemoteException;
    Boolean chooseWindowBack(Integer window) throws RemoteException;
    Boolean startGameViewForced() throws RemoteException;
    Boolean placeDice(Integer index, Position p) throws RemoteException;
    Boolean useToolC(Integer i1, Position p1, Position p2, Position p3, Position p4, PositionR pr, Integer i2, Integer i3) throws RemoteException;
    void exitGame2() throws RemoteException;
    void endTurn() throws RemoteException;
    void updateViewFromC() throws RemoteException;
    void exitGame1() throws RemoteException;
}