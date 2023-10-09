package application;

import java.awt.Component;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JFrame;

import engine.graphic.PaintScene;
import engine.input.Input;
import engine.scene.GameScene;

import static application.Main.SCREEN_WIDTH;
import static application.Main.SCREEN_HEIGHT;

public class Launcher implements ComponentListener{
    public final static String TITLE = "Personal Agario";
    
    private int windowWidth;
    private int windowHeight;
    private GameScene gameScene;
    private PaintScene paintScene;

    public Launcher(int windowWidth, int windowHeight, GameScene gameScene){
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        this.gameScene = gameScene;
        this.paintScene = new PaintScene(this, gameScene);
    }

    public int getWindowWidth(){ return windowWidth; }
    public int getWindowHeight(){ return windowHeight; }
    public GameScene getGameScene(){ return gameScene; }
    public PaintScene getPaintScene(){ return paintScene; }

    public void setWindowSize(int windowWidth, int windowHeight){
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        paintScene.reloadWindowSize();
    }

    public void start(){
        JFrame.setDefaultLookAndFeelDecorated(false);
        JFrame frame = new JFrame(TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        // frame.setSize(windowWidth, windowHeight);
        // frame.setLocation((SCREEN_WIDTH - windowWidth) / 2, (SCREEN_HEIGHT - windowHeight) / 2);
        frame.addComponentListener(this);
        frame.addKeyListener(Input.INSTANCE);
        // frame.addMouseListener(Input.INSTANCE);
        
        // paintScene.setPreferredSize(frame.getPreferredSize());

        System.out.println("Screen : " + SCREEN_WIDTH + " / " + SCREEN_HEIGHT);
        System.out.println("Window : " + windowWidth + " / " + windowHeight);
        
        frame.add(paintScene);
        frame.setVisible(true);
    }

    public void repaint(){
        paintScene.repaint();
    }
    
    public void componentHidden(ComponentEvent e) {}
    public void componentShown(ComponentEvent e) {}
    public void componentMoved(ComponentEvent e) {}
    public void componentResized(ComponentEvent e) {
        Component c = e.getComponent();
        setWindowSize(c.getWidth(), c.getHeight());
    }
}