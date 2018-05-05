package server.threads;

import server.MatchManager;
import shared.Logger;
import shared.logic.ConcurrencyManager;
import shared.logic.GeneralTask;
import shared.logic.Locker;

import java.util.ArrayList;

@SuppressWarnings("InfiniteLoopStatement")
public class NewGameManager extends GeneralTask {
    private final Locker safe = Locker.getSafe();
    private Integer sleepTime = 1000000;
    public boolean start = false;

    @Override
    public void run() {
        super.run();

        //start below when two clients connects, and handle client deletion
        TimerNewGame timerNewGame = new TimerNewGame(sleepTime, this);
        ConcurrencyManager.submit(timerNewGame);

        while (true) {
            synchronized (safe.sLock2) {
                while (MatchManager.q.size() != 4) {
                    try {
                        safe.sLock2.wait();
                        if(start) {
                            if(MatchManager.q.size()>1)
                                break;
                            start = false;
                            timerNewGame = new TimerNewGame(sleepTime, this);
                            ConcurrencyManager.submit(timerNewGame);
                        }
                    } catch (Exception e) {
                        Logger.log("Error waiting on lock!");
                        Logger.log(e.toString());
                    }
                }
                ArrayList<String> clients = new ArrayList<>(MatchManager.q.size());
                clients.addAll(MatchManager.q);
                MatchManager.q.clear();
                ConcurrencyManager.submit(new GameManager(clients));
            }
        }
    }
}