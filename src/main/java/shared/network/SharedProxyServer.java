package shared.network;

import shared.Cell;
import shared.Position;
import shared.PositionR;
import shared.TransferObjects.GameManagerT;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface SharedProxyServer extends Remote {
    String startGame(String uuid, String nick, String ip, Integer port, Boolean isSocket, Object stub) throws RemoteException;
    void updateView(String uuid, GameManagerT gameManager) throws RemoteException;
    Boolean chooseWindow(String uuid, ArrayList<Integer> windows, ArrayList<Cell[][]> matrices) throws RemoteException;
    Boolean ping(String uuid) throws RemoteException;
    void tavoloWin(String uuid) throws RemoteException;
    void enable(String uuid) throws RemoteException;
    void shut(String uuid) throws RemoteException;
    void printScore(String uuid, ArrayList<String> nicks, ArrayList<Integer> score, ArrayList<Boolean> winner) throws RemoteException;
    Boolean chooseWindowBack(String uuid, Integer window) throws RemoteException;
    Boolean startGameViewForced(String uuid) throws RemoteException;
    Boolean placeDice(String uuid, Integer index, Position p) throws RemoteException;
    Boolean useToolC(String uuid, Integer i1, Position p1, Position p2, Position p3, Position p4, PositionR pr, Integer i2, Integer i3) throws RemoteException;
    void exitGame2(String uuid) throws RemoteException;
    void endTurn(String uuid) throws RemoteException;
    void updateViewFromC(String uuid) throws RemoteException;
    Boolean exitGame1(String uuid) throws RemoteException;
}