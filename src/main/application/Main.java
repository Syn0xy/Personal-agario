package application;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ConcurrentModificationException;

import engine.game.Bot;
import engine.game.Food;
import engine.game.Player;
import engine.geometric.Vector2;
import engine.input.Input;
import engine.scene.GameScene;
import engine.util.Mathf;
import engine.util.Time;

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
        Bot bot = new Bot(new Vector2(WIDTH / 2, HEIGHT / 2), Color.BLUE);

        for (int i = 0; i < 50; i++) {
            Vector2 p = new Vector2(Mathf.random(WIDTH), Mathf.random(HEIGHT));
            gameScene.add(new Food(p));
        }

        launcher.getPaintScene().setTarget(player);
        gameScene.add(player);
        gameScene.add(bot);
    }

    public void thread(){
        Thread engineThread = new Thread("Engine"){
            public void run(){
                while(isAlive() && !isInterrupted()){
                    try{
                        Time.update();
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
