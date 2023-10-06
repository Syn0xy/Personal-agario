package engine.graphic;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import application.Launcher;
import engine.game.Sphere;
import engine.game.SphereType;
import engine.geometric.Vector2;
import engine.scene.GameScene;

public class PaintScene {
    public final static List<Sphere> SPHERES = Sphere.SPHERES;
    public final static Color BACKGROUND = Launcher.BACKGROUND;
    public final static Color BACKGROUND_TERRAIN = Launcher.BACKGROUND_TERRAIN;
    
    private Graphics graphics;

    private int windowWidth;
    private int windowHeight;
    private GameScene gameScene;

    private int halfWindowWidth;
    private int halfWindowHeight;
    private int width;
    private int height;

    private Sphere target;

    public PaintScene(int windowWidth, int windowHeight, GameScene gameScene){
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        this.gameScene = gameScene;
        reloadSize();
    }

    public int getWindowWidth(){ return windowWidth; }
    public int getWindowHeight(){ return windowHeight; }

    public void setTarget(Sphere target){
        this.target = target;
    }

    public void reloadSize(){
        width = gameScene.getWidth();
        height = gameScene.getHeight();
        halfWindowWidth = windowWidth / 2;
        halfWindowHeight = windowHeight / 2;
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
        setColor(BACKGROUND);
        fillRect(windowWidth, windowHeight);
    }

    public void drawTerrain(){
        setColor(BACKGROUND_TERRAIN);
        fillRect(width, height);
    }
    public void drawSpheres(){
        for(Sphere s : gameScene.getSpheres()) drawSphere(s);
    }

    public void drawSphere(Sphere s){
        setColor(s.getColor());
        drawSphere(s.getPosition(), s.getSize());
    }

    public void drawSphere(Vector2 p, double r){
        fillOval((int)p.getX(), (int)p.getY(), (int)r);
    }

    ////////// Graphics

    public void setColor(Color color){
        graphics.setColor(color);
    }

    public void fillRect(int width, int height){
        fillRect(0, 0, width, height);
    }

    public void fillRect(int x, int y, int width, int height){
        graphics.fillRect(x, windowHeight - height, width, height);
    }
    
    public void fillOval(int x, int y, int r){
        int d = r * 2;
        y = windowHeight - y;
        graphics.fillOval(x - r, y - r, d, d);
    }
}
