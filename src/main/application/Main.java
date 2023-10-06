package application;

import java.awt.Dimension;
import java.awt.Toolkit;

import engine.scene.GameScene;

public class Main {
    public final static Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
    public final static int SCREEN_WIDTH = (int)SCREEN_SIZE.getWidth();
    public final static int SCREEN_HEIGHT = (int)SCREEN_SIZE.getHeight();
    public final static Dimension WINDOW_SIZE = new Dimension(900, 600);
    public final static int WINDOW_WIDTH = (int)WINDOW_SIZE.getWidth();
    public final static int WINDOW_HEIGHT = (int)WINDOW_SIZE.getHeight();

    public static void main(String[] args) {
        int width = 500;
        int height = 500;
        GameScene gameScene = new GameScene(width, height);
        new GameView(WINDOW_WIDTH, WINDOW_HEIGHT, gameScene);
    }
}