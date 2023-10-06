package application;

import java.awt.Color;
import java.util.ConcurrentModificationException;

import engine.game.Bot;
import engine.game.Food;
import engine.game.Player;
import engine.geometric.Vector2;
import engine.input.Input;
import engine.scene.GameScene;
import engine.util.Mathf;
import engine.util.RandomColor;
import engine.util.Time;

public class GameView {
    private int width;
    private int height;

    private GameScene gameScene;
    private Launcher launcher;

    public GameView(int windowWidth, int windowHeight, GameScene gameScene){
        this.width = gameScene.getWidth();
        this.height = gameScene.getHeight();
        this.gameScene = gameScene;
        this.launcher = new Launcher(windowWidth, windowHeight, gameScene);
        init();
        thread();

        launcher.start();
    }

    public void init(){
        // Player player = new Player(new Vector2(width / 2, height / 2), Color.RED);
        for(int i = 0; i < 10; i++){
            Bot b = new Bot(Vector2.random(0, width));
            gameScene.add(b);
        }

        for (int i = 0; i < 50; i++) {
            Food f = new Food(Vector2.random(0, width));
            gameScene.add(f);
        }
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
