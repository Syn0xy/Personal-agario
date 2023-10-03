package engine.graphic;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import application.Launcher;
import application.Main;
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
    private int startWidth;
    private int startHeight;
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
        startWidth = halfWindowWidth - width / 2;
        startHeight = halfWindowHeight - height / 2;
    }

    public void setGraphics(Graphics graphics){
        this.graphics = graphics;
    }
    
    public void drawScene(){
        if(graphics == null) return;
        clearScreen();
        if(target != null){
            drawTarget();
            drawTerrainWithTarget();
            drawFoodWithTarget();
        }else{
            drawTerrain();
            drawSpheres();
        }
    }

    public void clearScreen(){
        graphics.setColor(BACKGROUND);
        graphics.fillRect(0, 0, windowWidth, windowHeight);
    }

    public void drawTarget(){
        Vector2 middle = new Vector2(windowWidth / 2, windowHeight / 2);
        drawSphere(target.getColor(), middle, windowWidth * 0.05);
    }

    public void drawFoodWithTarget(){
        Vector2 pt = target.getPosition();
        double prop = (windowWidth * 0.05) / target.getSize();
        for(Sphere s : SPHERES){
            if(s.getType() != SphereType.FOOD) continue;
            Vector2 p = s.getPosition().copy();
            p.minus(pt);
            double size = s.getSize() * prop;
            drawSphere(s.getColor(), p, size);
        }
    }

    public void drawTerrainWithTarget(){
        // drawTerrainWithTarget(target.getPosition(), BACKGROUND_TERRAIN, );
    }

    public void drawTerrainWithTarget(Vector2 position, Color color, double proportion){

    }

    public void drawTerrain(){
        graphics.setColor(BACKGROUND_TERRAIN);
        graphics.fillRect(startWidth, startHeight, width, height);
    }

    public void drawSpheres(){
        for(Sphere s : gameScene.getSpheres()) drawSphere(s);
    }

    public void drawSphere(Sphere s){
        drawSphere(s.getColor(), s.getPosition(), s.getSize());
    }

    public void drawSphere(Color color, Vector2 p, double r){
        drawSphere(color, (int)p.getX(), (int)p.getY(), (int)r);
    }

    public void drawSphere(Color color, int x, int y, int r){
        int d = r * 2;
        y = windowHeight - y;
        graphics.setColor(color);
        graphics.fillOval(x - r, y - r, d, d);
    }
}
