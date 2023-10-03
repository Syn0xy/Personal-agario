package main.engine;

import java.awt.Color;
import java.awt.Graphics;

import main.application.Launcher;
import main.application.Main;

public class PaintScene {
    public final static Color BACKGROUND = Launcher.BACKGROUND;
    public final static Color BACKGROUND_TERRAIN = Launcher.BACKGROUND_TERRAIN;
    
    private Graphics graphics;

    private int windowWidth;
    private int windowHeight;
    private GameScene gameScene;

    private int halfWindowWidth;
    private int halfWindowHeight;
    private int startWidth;
    private int startHeight;
    private int width;
    private int height;

    public PaintScene(int windowWidth, int windowHeight, GameScene gameScene){
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        this.gameScene = gameScene;
        reloadSize();
    }

    public int getWindowWidth(){ return windowWidth; }
    public int getWindowHeight(){ return windowHeight; }

    public void reloadSize(){
        width = gameScene.getWidth();
        height = gameScene.getHeight();
        halfWindowWidth = windowWidth / 2;
        halfWindowHeight = windowHeight / 2;
        startWidth = halfWindowWidth - width / 2;
        startHeight = halfWindowHeight - height / 2;
    }

    public void setGraphics(Graphics graphics){
        this.graphics = graphics;
    }
    
    public void drawScene(){
        if(graphics == null) return;
        clearScreen();
        drawTerrain();
        drawSpheres();
    }

    public void clearScreen(){
        graphics.setColor(BACKGROUND);
        graphics.fillRect(0, 0, windowWidth, windowHeight);
    }

    public void drawTerrain(){
        graphics.setColor(BACKGROUND_TERRAIN);
        graphics.fillRect(startWidth, startHeight, width, height);
    }

    public void drawSpheres(){
        for(Sphere s : gameScene.getSpheres()){
            drawSphere(s);
        }
    }

    public void drawSphere(Sphere s){
        drawSphere(s.getColor(), s.getPosition(), s.getRadius());
    }

    public void drawSphere(Color color, Vector2 p, double r){
        drawSphere(color, (int)p.getX() + startWidth, startHeight + Main.HEIGHT - (int)p.getY(), (int)r);
    }

    public void drawSphere(Color color, int x, int y, int r){
        int d = r * 2;
        graphics.setColor(color);
        graphics.fillOval(x - r, y - r, d, d);
    }
}
