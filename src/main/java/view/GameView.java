package view;

import java.awt.Point;

import engine.input.Input;
import engine.scene.GameScene;
import view.util.Observer;
import view.util.Subject;

public class GameView extends View implements Observer {
    private static final int WIDTH = (int)(SCREEN_WIDTH * (2.0/3.0));
    private static final int HEIGHT = (int)(SCREEN_HEIGHT * (2.0/3.0));
    private static final String TITLE = "Personal - Agario";

    private GameScene gameScene;

    public GameView(GameScene gameScene){
        this.gameScene = gameScene;
        gameScene.attach(this);
        init(WIDTH, HEIGHT);
    }

    @Override
    public String title() {
        return TITLE + " (" + gameScene.getWidth() + "x" + gameScene.getHeight() + ")";
    }

    @Override
    protected void view() {
        add(new GameCanvas(gameScene));
        addKeyListener(Input.INSTANCE);
    }

    @Override
    protected Point position() {
        return center();
    }

    @Override
    public void update(Subject subj) {
        repaint();
    }

    @Override
    public void update(Subject subj, Object data) {
        repaint();
    }    
}
