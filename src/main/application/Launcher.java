package application;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import engine.graphic.PaintScene;
import engine.input.Input;
import engine.scene.GameScene;

public class Launcher extends JPanel{
    public final static String TITLE = "Personal Agario";
    public final static Color BACKGROUND = Color.GRAY;
    public final static Color BACKGROUND_TERRAIN = Color.BLACK;

    public final static Dimension SCREEN_SIZE = Main.SCREEN_SIZE;
    public final static int SCREEN_WIDTH = Main.SCREEN_WIDTH;
    public final static int SCREEN_HEIGHT = Main.SCREEN_HEIGHT;
    public final static Dimension WINDOW_SIZE = Main.WINDOW_SIZE;
    public final static int WINDOW_WIDTH = Main.WINDOW_WIDTH;
    public final static int WINDOW_HEIGHT = Main.WINDOW_HEIGHT;
    
    private int windowWidth;
    private int windowHeight;
    private GameScene gameScene;
    private PaintScene paintScene;

    public Launcher(int windowWidth, int windowHeight, GameScene gameScene){
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        this.gameScene = gameScene;
        this.paintScene = new PaintScene(windowWidth, windowHeight, gameScene);
    }

    public int getWindowWidth(){ return windowWidth; }
    public int getWindowHeight(){ return windowHeight; }
    public GameScene getGameScene(){ return gameScene; }
    public PaintScene getPaintScene(){ return paintScene; }

    public void start(){
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame(TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(BACKGROUND);
        frame.setSize(windowWidth, windowHeight);
        frame.setLocation((SCREEN_WIDTH - windowWidth) / 2, (SCREEN_HEIGHT - windowHeight) / 2);        
        frame.addKeyListener(Input.INSTANCE);
        // frame.addMouseListener(Input.INSTANCE);

        setPreferredSize(frame.getPreferredSize());
        
        frame.add(this);
        frame.setVisible(true);
    }

    public void paint(Graphics g){
        paintScene.setGraphics(g);
        paintScene.drawScene();
    }
}