package application;

import java.util.ConcurrentModificationException;

import javax.swing.SwingUtilities;

import engine.input.Input;
import engine.scene.GameScene;
import engine.util.Time;

public class GameView {
    private GameScene gameScene;
    private Launcher launcher;

    public GameView(int windowWidth, int windowHeight, GameScene gameScene){
        this.gameScene = gameScene;
        this.launcher = new Launcher(windowWidth, windowHeight, gameScene);

        thread();
        launcher.start();
    }

    public void thread(){
        SwingUtilities.invokeLater(() -> {
            Thread engineThread = new Thread("Engine"){
                public void run(){
                    while(isAlive() && !isInterrupted()){
                        try{
                            Time.update();
                            Input.update();
                            gameScene.update();
                        }catch(NullPointerException | ConcurrentModificationException e){
                            System.err.println("Error : " + e.getMessage());
                        }
                    }
                }
            };
            Thread graphicsThread = new Thread("Graphics"){
                public void run(){
                    while(isAlive() && !isInterrupted()){
                        try{
                            launcher.repaint();
                        }catch(NullPointerException | ConcurrentModificationException e){
                            System.err.println("Error : " + e.getMessage());
                        }
                    }
                }
            };
            engineThread.start();
            graphicsThread.start();
        });
    }
}