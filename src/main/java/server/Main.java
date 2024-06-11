package server;

import network.server.Server;
import server.model.GameScene;
import server.network.BroadcastClient;
import server.view.ServerView;

public class Main {

    private static final int PORT = 2000;

    public static void main(String[] args) {
        GameScene gameScene = new GameScene(2000, 1000);
        gameScene.start(100);
        
        Server io = new Server(PORT);
        new BroadcastClient(io, gameScene);
        new ServerView(gameScene);
    }
    
}
