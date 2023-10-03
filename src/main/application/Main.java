package main.application;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Time;
import java.util.ConcurrentModificationException;

import main.engine.Bot;
import main.engine.Food;
import main.engine.GameScene;
import main.engine.PaintScene;
import main.engine.Player;
import main.engine.Vector2;
import main.engine.input.Input;
import main.engine.util.Mathf;

public class Main {
    public final static Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
    public final static int SCREEN_WIDTH = (int)SCREEN_SIZE.getWidth();
    public final static int SCREEN_HEIGHT = (int)SCREEN_SIZE.getHeight();
    public final static Dimension WINDOW_SIZE = new Dimension(900, 600);
    public final static int WINDOW_WIDTH = (int)WINDOW_SIZE.getWidth();
    public final static int WINDOW_HEIGHT = (int)WINDOW_SIZE.getHeight();

    public final static int WIDTH = 800;
    public final static int HEIGHT = 500;

    private GameScene gameScene;
    private Launcher launcher;

    public static void main(String[] args) {
        new Main();
    }

    public Main(){
        this.gameScene = new GameScene(WIDTH, HEIGHT);
        this.launcher = new Launcher(WINDOW_WIDTH, WINDOW_HEIGHT, gameScene);
        init();
        thread();

        launcher.start();
    }

    public void init(){
        Player player = new Player(new Vector2(WIDTH / 2, HEIGHT / 2), Color.RED);
        //Bot bot = new Bot(new Vector2(WIDTH / 2, HEIGHT / 2), Color.BLUE);

        for (int i = 0; i < 1000; i++) {
            Vector2 p = new Vector2(Mathf.random(WIDTH), Mathf.random(HEIGHT));
            gameScene.add(new Food(p));
        }

        gameScene.add(player);
        //gameScene.add(bot);
    }

    public void thread(){
        Thread engineThread = new Thread("Engine"){
            public void run(){
                while(isAlive() && !isInterrupted()){
                    try{
                        Input.update();
                        launcher.repaint();
                        gameScene.update();
                    }catch(NullPointerException e){
                        System.err.println("Error : " + e.getLocalizedMessage());
                    }catch(ConcurrentModificationException e){
                        System.err.println("Error : " + e.getMessage());
                    }
                }
            }
        };
        engineThread.start();
    }
}
