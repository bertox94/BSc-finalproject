package ServerTest;

import client.ProxyClient;
import server.Player;
import server.SReferences;
import server.connection.ProxyServer;
import server.threads.MainServer;
import server.threads.GameManager;

import java.util.ArrayList;
import java.util.HashSet;


import org.junit.jupiter.api.Test;
import shared.*;
import shared.network.SharedProxyClient;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

class Tests {

    public static ArrayList<GameManager> gameManagers = new ArrayList<>();
    public static ProxyServer proxyServer = ProxyServer.getInstance();
    public static Object obj;
    public static Integer timeout;
    public GameManager gameManager;
    public Player player;


    private void pause(Integer millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void startGame(String player) {
        SharedProxyClient p = ProxyClient.getInstance();
        proxyServer.startGame(player, player, "192.168.223.1", -1, false, p);
    }

    public void after() {
        while (gameManager.getPool().remove(null)) {
        }
        String s = "";
        for (Dice dice :
                gameManager.getPool().getDices()) {
            s = s + dice.toString() + "; ";
        }
        System.out.println(s + "\n");
        System.out.println(player.getOverlay());
        player.clearUsedTcAndPlacedDice();

    }

    @org.junit.Test
    public void testCards() {
        ArrayList<String> players = new ArrayList<>();
        players.add("player1");
        players.add("player2");
        players.add("player3");
        startGame(players.get(0));
        startGame(players.get(1));
        startGame(players.get(2));
        gameManager = new GameManager(players);
        player = new Player(gameManager, "player1");

        player.setGame(gameManager);

        SReferences.addUuidRefEnhanced("player1");
        SReferences.addGameRef(players.get(0), gameManager);
        SReferences.addPlayerRef(players.get(0), player);

        ArrayList<Integer> ais = new ArrayList<>();
        gameManager.setPublicOCs(ais);

        ais.add(0, 0);
        ais.add(1, 1);
        ais.add(2, 2);
        gameManager.setToolCards(ais);

        Dice[][] overlayTest = new Dice[4][5];
        player.setWindow(1);

        player.placeDice(0, new Position(0, 0));
        after();
        overlayTest[0][0] = new Dice('y',2);
        assertArrayEquals(overlayTest,player.getOverlay().getDicePositions());

        player.placeDice(0, new Position(0, 1));
        after();
        overlayTest[0][1] = new Dice('b',3);
        assertArrayEquals(overlayTest,player.getOverlay().getDicePositions());

        player.placeDice(0, new Position(0, 2));
        after();
        overlayTest[0][2] = new Dice('r',6);
        assertArrayEquals(overlayTest,player.getOverlay().getDicePositions());

        player.placeDice(0, new Position(0, 3));
        after();
        overlayTest[0][3] = new Dice('b',5);
        assertArrayEquals(overlayTest,player.getOverlay().getDicePositions());

        player.placeDice(0, new Position(0, 4));
        after();
        overlayTest[0][4] = new Dice('v',1);
        assertArrayEquals(overlayTest,player.getOverlay().getDicePositions());

        player.placeDice(0, new Position(1, 0));
        after();
        overlayTest[1][0] = new Dice('g',4);
        assertArrayEquals(overlayTest,player.getOverlay().getDicePositions());

        player.placeDice(0, new Position(1, 1));
        after();
        overlayTest[1][1] = new Dice('y',6);
        assertArrayEquals(overlayTest,player.getOverlay().getDicePositions());

        player.placeDice(0, new Position(1, 2));
        after();
        overlayTest[1][2] = new Dice('g',5);
        assertArrayEquals(overlayTest,player.getOverlay().getDicePositions());

        player.placeDice(0, new Position(1, 3));
        after();
        overlayTest[1][3] = new Dice('v',2);
        assertArrayEquals(overlayTest,player.getOverlay().getDicePositions());

        player.placeDice(0, new Position(1, 4));
        after();
        overlayTest[1][4] = new Dice('b',4);
        assertArrayEquals(overlayTest,player.getOverlay().getDicePositions());

        player.placeDice(0, new Position(2, 1));
        after();
        overlayTest[2][1] = new Dice('v',2);
        assertArrayEquals(overlayTest,player.getOverlay().getDicePositions());

        player.placeDice(0, new Position(2, 2));
        after();
        overlayTest[2][2] = new Dice('r',6);
        assertArrayEquals(overlayTest,player.getOverlay().getDicePositions());

        player.placeDice(0, new Position(2, 3));
        after();
        overlayTest[2][3] = new Dice('g',4);
        assertArrayEquals(overlayTest,player.getOverlay().getDicePositions());

        player.placeDice(0, new Position(3, 0));
        after();
        overlayTest[3][0] = new Dice('g',2);
        assertArrayEquals(overlayTest,player.getOverlay().getDicePositions());

        player.placeDice(0, new Position(3, 1));
        after();
        overlayTest[3][1] = new Dice('y',3);
        assertArrayEquals(overlayTest,player.getOverlay().getDicePositions());

        player.placeDice(0, new Position(3, 2));
        after();
        overlayTest[3][2] = new Dice('v',2);
        assertArrayEquals(overlayTest,player.getOverlay().getDicePositions());

        player.placeDice(0, new Position(3, 3));
        after();
        overlayTest[3][3] = new Dice('b',5);
        assertArrayEquals(overlayTest,player.getOverlay().getDicePositions());

        player.placeDice(0, new Position(3, 4));
        after();
        overlayTest[3][4] = new Dice('y',3);
        assertArrayEquals(overlayTest,player.getOverlay().getDicePositions());

        Dice test[][] = new Dice[4][5];
        test[0][0] = new Dice('y', 2);
        test[0][1] = new Dice('b', 3);
        test[0][2] = new Dice('r', 6);
        test[0][3] = new Dice('b', 5);
        test[0][4] = new Dice('v', 1);
        test[1][0] = new Dice('g', 4);
        test[1][1] = new Dice('y', 6);
        test[1][2] = new Dice('g', 5);
        test[1][3] = new Dice('v', 2);
        test[1][4] = new Dice('b', 4);
        test[2][0] = null;
        test[2][1] = new Dice('v', 2);
        test[2][2] = new Dice('r', 6);
        test[2][3] = new Dice('g', 4);
        test[2][4] = null;
        test[3][0] = new Dice('g', 2);
        test[3][1] = new Dice('y', 3);
        test[3][2] = new Dice('v', 2);
        test[3][3] = new Dice('b', 5);
        test[3][4] = new Dice('y', 3);

        overlayTest[0][0] = new Dice('y', 2);
        overlayTest[0][1] = new Dice('b', 3);
        overlayTest[0][2] = new Dice('r', 6);
        overlayTest[0][3] = new Dice('b', 5);
        overlayTest[0][4] = new Dice('v', 1);
        overlayTest[1][0] = new Dice('g', 4);
        overlayTest[1][1] = new Dice('y', 6);
        overlayTest[1][2] = new Dice('g', 5);
        overlayTest[1][3] = new Dice('v', 2);
        overlayTest[1][4] = new Dice('b', 4);
        overlayTest[2][0] = null;
        overlayTest[2][1] = new Dice('v', 2);
        overlayTest[2][2] = new Dice('r', 6);
        overlayTest[2][3] = new Dice('g', 4);
        overlayTest[2][4] = null;
        overlayTest[3][0] = new Dice('g', 2);
        overlayTest[3][1] = new Dice('y', 3);
        overlayTest[3][2] = new Dice('v', 2);
        overlayTest[3][3] = new Dice('b', 5);
        overlayTest[3][4] = new Dice('y', 3);

        assertArrayEquals(overlayTest,test);

        player.setTokens(800);
        ArrayList<Dice> pool = new ArrayList<>();
        pool.addAll(gameManager.getPool().getDices());


        //test tc1
        player.useTool(players.get(0), 0, null, null, null, null, null, null, null);
        after();
        player.useTool(players.get(0), 0, new Position(5, 0), null, null, null, null, 0, +1);
        after();
        pool.add(0,new Dice('r',6));
        player.useTool(players.get(0), 0, new Position(2, 5), null, null, null, null, 0, +1);
        after();
        pool.remove(0);
        player.useTool(players.get(0), 0, new Position(2, 0), null, null, null, null, 0, +1);
        after();
        overlayTest[2][0] = new Dice('b',3);
        assertArrayEquals(overlayTest,player.getOverlay().getDicePositions());

        //use tc2
        player.useTool(players.get(0), 1, null, null, null, null, null, null, null);
        after();
        player.useTool(players.get(0), 1, new Position(0, 4), new Position(3, 2), null
                , null, null, null, null);
        after();
        player.getOverlay().setDicePosition(new Dice('b',6), new Position(0, 3));
        player.getOverlay().setDicePosition(null, new Position(3, 3));
        player.useTool(players.get(0), 1, new Position(0, 3), new Position(3, 3), null
                , null, null, null, null);
        after();
        overlayTest[0][3] = null;
        overlayTest[3][3] = new Dice('b',6);
        assertArrayEquals(overlayTest,player.getOverlay().getDicePositions());

        //use tc3
        player.useTool(players.get(0), 2, null, null, null, null, null, null, null);
        after();
        player.useTool(players.get(0), 2, new Position(0, 0), new Position(2, -1), null, null, null, null, null);
        player.getOverlay().setDicePosition(null, new Position(2, 0));
        after();
        player.useTool(players.get(0), 2, new Position(0, 2), new Position(2, 0), null, null, null, null, null);
        after();
        overlayTest[2][0] = overlayTest[0][2];
        overlayTest[0][2] = null;
        assertArrayEquals(overlayTest,player.getOverlay().getDicePositions());

        ais.set(0, 3);
        ais.set(1, 4);
        ais.set(2, 5);
        gameManager.setToolCards(ais);

        //use tc4
        player.useTool(players.get(0), 0, null, null, null, null, null, null, null);
        after();
        player.useTool(players.get(0), 0, new Position(1, 1), new Position(0, 2), new Position(1, 4), new Position(0, 3), null, null, null);
        after();
        overlayTest[0][2] = overlayTest[1][1];
        overlayTest[0][3] = overlayTest[1][4];
        overlayTest[1][1] = null;
        overlayTest[1][4] = null;
        assertArrayEquals(overlayTest,player.getOverlay().getDicePositions());

        //use tc5
        player.useTool(players.get(0), 1, null, null, null, null, null, null, null);
        after();
        player.useTool(players.get(0), 1, new Position(1, null), null, null, null, new PositionR(4, 0), 0, null);
        after();
        gameManager.getRoundTrack().addDice(new Dice('r', 6), 4);
        player.useTool(players.get(0), 1, new Position(1, 1), null, null, null, new PositionR(4, 0), 0, null);
        after();

        //use tc6
        player.useTool(players.get(0), 2, null, null, null, null, null, null, null);
        after();
        player.getOverlay().setDicePosition(null,new Position(0,4));
        player.getOverlay().setDicePosition(null,new Position(1,3));
        player.getWindow().getMatrices()[1][4] = new Cell('b');
        player.useTool(players.get(0), 2, new Position(1, 4), null, null, null, null, 2, null);
        after();
        player.getOverlay().setDicePosition(null,new Position(1,4));

        ais.set(0, 6);
        ais.set(1, 7);
        ais.set(2, 8);
        gameManager.setToolCards(ais);

        //use tc7
        player.useTool(players.get(0), 0, null, null, null, null, null, null, null);
        after();
        player.useTool(players.get(0), 5, null, null, null, null, null, null, null);
        after();
        player.incrementTurn();
        player.incrementTurn();
        player.useTool(players.get(0), 0, null, null, null, null, null, null, null);
        after();

        //use tc8
        player.useTool(players.get(0), 1, null, null, null, null, null, null, null);
        after();
        player.useTool(players.get(0), 1, new Position(4, 4), null, null, null, null, 15, null);
        after();
        player.clearUsedTcAndPlacedDice();
        player.getOverlay().setDicePosition(null, new Position(1, 4));
        player.useTool(players.get(0), 1, new Position(1, 4), null, null, null, null, -1, null);
        after();
        pool.add(0, new Dice('b', 4));
        player.getOverlay().setDicePosition(null, new Position(1, 4));
        after();
        player.getWindow().getMatrices()[1][4] = new Cell('y');
        player.incrementTurn();
        player.useTool(players.get(0), 1, new Position(1, 4), null, null, null, null, 0, null);
        after();

        //use tc9
        player.useTool(players.get(0), 2, null, null, null, null, null, null, null);
        after();
        player.useTool(players.get(0), 2, new Position(1, -1), new Position(null, 4), null, null, null, 0, null);
        after();
        player.getOverlay().setDicePosition(null, new Position(1, 4));
        player.getOverlay().setDicePosition(null, new Position(1, 3));
        player.getOverlay().setDicePosition(null, new Position(2, 3));
        player.getOverlay().setDicePosition(null, new Position(3, 3));
        player.getOverlay().setDicePosition(null, new Position(3, 4));
        player.useTool(players.get(0), 2, new Position(1, 0), new Position(2, 4), null, null, null, 0, null);
        after();
        pool.add(0, new Dice('g', 5));
        player.getOverlay().setDicePosition(null, new Position(1, 0));
        after();
        player.useTool(players.get(0), 2, new Position(1, 0), new Position(2, 4), null, null, null, 0, null);
        after();

        ais.set(0, 9);
        ais.set(1, 10);
        ais.set(2, 11);
        gameManager.setToolCards(ais);

        //use tc10
        player.useTool(players.get(0), 5, null, null, null, null, null, null, null);
        after();
        player.useTool(players.get(0), 0, new Position(14, 4), null, null, null, null, 0, null);
        after();
        player.getOverlay().setDicePosition(new Dice('g', 3), new Position(2, 4));
        player.useTool(players.get(0), 0, new Position(1, 4), null, null, null, null, 0, null);
        after();
        gameManager.getPool().addDice(new Dice('r', 3));
        player.getWindow().getMatrices()[1][4] = new Cell('r');
        player.getOverlay().setDicePosition(new Dice('g', 3), new Position(2, 4));
        player.useTool(players.get(0), 0, new Position(1, 4), null, null, null, null, 0, null);
        after();

        //use tc11
        player.useTool(players.get(0), 1, null, null, null, null, null, null, null);
        after();
        player.getOverlay().setDicePosition(null, new Position(0, 3));
        player.getOverlay().setDicePosition(null, new Position(0, 4));
        player.useTool(players.get(0), 1, new Position(0, 3), null, null, null, null, 0, 4);
        after();
        gameManager.getPool().addDice(new Dice('v',6));
        player.getOverlay().setDicePosition(null, new Position(0, 2));
        player.getOverlay().setDicePosition(null, new Position(0, 3));
        player.getOverlay().setDicePosition(null, new Position(0, 4));
        player.useTool(players.get(0), 1, new Position(0, 3), null, null, null, null, 0, 4);
        after();

        //use tc12
        player.useTool(players.get(0), 2, null, null, null, null, null, null, null);
        after();
        player.getWindow().getMatrices()[3][3] = new Cell('g');
        player.useTool(players.get(0), 2, new Position(2, 0), new Position(0, 2), new Position(2, 2), new Position(3, 3), new PositionR(4, 0), 0, null);
        after();
        player.getOverlay().setDicePosition(new Dice('g', 6), new Position(2, 0));
        player.getOverlay().setDicePosition(new Dice('g', 6), new Position(2, 2));
        player.getOverlay().setDicePosition(new Dice('b', 5), new Position(1, 2));
        player.getWindow().getMatrices()[3][3] = new Cell('g');
        player.useTool(players.get(0), 2, new Position(2, 0), new Position(0, 2), new Position(2, 2), new Position(3, 3), new PositionR(4, 0), 0, null);
        after();


        //use public object
        Integer sum = 0;
        player.setPrivateOC('r');

        ais.clear();

        ais.add(0, 0);
        ais.add(1, 1);
        ais.add(2, 2);
        ais.add(3, 3);
        ais.add(4, 4);
        ais.add(5, 5);
        ais.add(6, 6);
        ais.add(7, 7);
        ais.add(8, 8);
        ais.add(9, 9);
        ais.add(10, 10);

        gameManager.setPublicOCs(ais);

        sum = player.getScore();

        assert (true);

    }

    @Test
    public boolean testHashCode() {
        HashSet<Integer> hashSet = new HashSet<>();
        Dice dice = new Dice('g', 1);
        int i = 1;
        while (i < 7) {
            dice = new Dice('g', i);
            System.out.println(dice.hashCode());
            if (!hashSet.add(dice.hashCode()))
                return false;
            i++;
        }

        i = 1;
        while (i < 7) {
            dice = new Dice('b', i);
            System.out.println(dice.hashCode());
            if (!hashSet.add(dice.hashCode()))
                return false;
            i++;
        }

        i = 1;
        while (i < 7) {
            dice = new Dice('y', i);
            System.out.println(dice.hashCode());
            if (!hashSet.add(dice.hashCode()))
                return false;
            i++;
        }

        i = 1;
        while (i < 7) {
            dice = new Dice('v', i);
            System.out.println(dice.hashCode());
            if (!hashSet.add(dice.hashCode()))
                return false;
            i++;
        }

        i = 1;
        while (i < 7) {
            dice = new Dice('r', i);
            System.out.println(dice.hashCode());
            if (!hashSet.add(dice.hashCode()))
                return false;
            i++;
        }

        hashSet.clear();
        i = 0;
        int j = 0;
        while (i < 4) {
            while (j < 5) {
                Position pos = new Position(i, j);
                System.out.println(pos.hashCode());
                if (!hashSet.add(pos.hashCode()))
                    return false;
                j++;
            }
            j = 0;
            i++;
        }

        return true;
    }

    @Test
    void main() {

        MainServer.simulation();
        obj = MainServer.obj;
        if (testHashCode()) {
            testCards();
        } else
            assert (false);
    }
}