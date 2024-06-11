package server.view;

import server.model.GameScene;
import view.GameView;

public class ServerView extends GameView {
    
    private static final String TITLE = "Server";
    
    public ServerView(GameScene gameScene) {
        super(gameScene, new ServerCanvas(gameScene));
    }
    
    @Override
    public String getTitle() {
        return super.getTitle() + " - " + TITLE;
    }
    
}
