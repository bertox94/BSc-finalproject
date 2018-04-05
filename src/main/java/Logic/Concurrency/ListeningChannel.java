package Logic.Concurrency;

import Logic.ConcurrencyManager;
import Logic.Locker;
import Logic.MatchManager;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ListeningChannel extends GeneralTask {

    private final ServerSocket mySocket;
    private final Locker Safe = Locker.getSafe();

    public ListeningChannel(int port) throws IOException {
        this.mySocket = new ServerSocket(port);
    }

    @SuppressWarnings("InfiniteLoopStatement")
    @Override
    public void run() {
        System.out.println("Listener started");
        synchronized (Safe.activePlayerRefs) {
            while (MatchManager.getInstance().getActivePlayerRefs() ==
                    MatchManager.getInstance().getMaxActivePlayerRefs()) {
                try {
                    Safe.activePlayerRefs.wait();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                while (true) {
                    try {
                        // Wait until someone connects
                        Socket clientSocket = mySocket.accept();
                        // Start client handling
                        ConcurrencyManager.submit(new SerializedSocket(clientSocket));
                    } catch (IOException e) {
                        e.printStackTrace();
                        break;
                    }
                }
            }
        }
    }

    public void close() throws IOException {
        mySocket.close();
    }
}