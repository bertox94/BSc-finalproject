package client.threads;

import client.network.NetworkClient;
import shared.SharedClientGame;
import shared.SharedServerGameManager;
import shared.SharedServerMatchManager;

import java.net.InetAddress;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.locks.ReentrantLock;

public class Game extends NetworkClient implements SharedClientGame {
    private static Game instance = null;

    private Game game = this;
    private SharedServerMatchManager netMatchManager;
    private SharedServerGameManager netGameManager;
    private Integer nMates;
    private ReentrantLock Lock1 = new ReentrantLock();

    public Game() {
        try {
            // Look for the RMI registry on specific server port
            this.rmiRegistry = LocateRegistry.getRegistry(SERVER_IP, RMI_PORT);
            // get local IP
            this.clientIp = InetAddress.getLocalHost().getHostAddress();
            // Get a reference to the remote instance of client, through shared interface
            this.netMatchManager = (SharedServerMatchManager) rmiRegistry.lookup(RMI_IFACE_NAME);

            // Inform the registry about symbolic server name
            System.setProperty("java.rmi.server.hostname", this.clientIp);
            // Setup permissive security policy
            System.setProperty("java.rmi.server.useCodebaseOnly", "false");
            // Export the object listener on specific server port
            UnicastRemoteObject.exportObject(this, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        super.run();

        synchronized (Lock1){
            while (nMates == null)
                try {
                    Lock1.wait();
                }catch (InterruptedException e){
                e.printStackTrace();
                }
        }
        try {
            String string = netMatchManager.startGame(this, nMates);
        } catch (Exception e ){
            e.printStackTrace();
        }
    }

    //TODO handshake()

    public String getClientIp() {
        return this.clientIp;
    }
}