package client;

import client.view.ClientView;
import client.view.network.BroadcastServer;

public class Main {

    public static void main(String[] args) throws Exception {
        try {
            BroadcastServer broadcast = new BroadcastServer("localhost", 2000);
            new ClientView(broadcast);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

}   