package application;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import engine.scene.GameScene;

public class Main {
    public final static Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
    public final static int SCREEN_WIDTH = (int)SCREEN_SIZE.getWidth();
    public final static int SCREEN_HEIGHT = (int)SCREEN_SIZE.getHeight();
    public final static int WINDOW_WIDTH = 1536;
    public final static int WINDOW_HEIGHT = 864;

    public final static Color BACKGROUND = Color.LIGHT_GRAY;
    public final static Color BACKGROUND_TERRAIN = Color.BLACK;

    public final static String FONT_NAME = "Comic sans MS";
    public final static int FONT_TYPE = Font.BOLD;
    public static int width = WINDOW_WIDTH;
    public static int height = WINDOW_HEIGHT;
    public final static int SPEED = 5;
    public final static int BOT_COUNT = 20;
    public final static int FOOD_COUNT = 1000;

    public static void main(String[] args) {
        System.out.println(SCREEN_SIZE);
        GameScene gameScene = new GameScene(width, height);
        new GameView(WINDOW_WIDTH, WINDOW_HEIGHT, gameScene);
    }
}