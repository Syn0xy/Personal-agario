package engine.graphic;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Collections;

import engine.game.Cell;
import engine.game.DistanceCellComparator;
import engine.geometric.Vector2;
import engine.scene.GameScene;

import static application.Launcher.BACKGROUND;
import static application.Launcher.BACKGROUND_TERRAIN;

public class PaintScene {
    private Graphics graphics;

    private int windowWidth;
    private int windowHeight;
    private GameScene gameScene;

    // private int halfWindowWidth;
    // private int halfWindowHeight;
    private int width;
    private int height;

    private Cell target;

    public PaintScene(int windowWidth, int windowHeight, GameScene gameScene){
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        this.gameScene = gameScene;
        reloadSize();
    }

    public int getWindowWidth(){ return windowWidth; }
    public int getWindowHeight(){ return windowHeight; }
    public Cell getTarget(){ return target; }

    public void setTarget(Cell target){ this.target = target; }
    
    public void reloadSize(){
        width = gameScene.getWidth();
        height = gameScene.getHeight();
        // halfWindowWidth = windowWidth / 2;
        // halfWindowHeight = windowHeight / 2;
    }

    public void setGraphics(Graphics graphics){
        this.graphics = graphics;
    }
    
    public void drawScene(){
        if(graphics == null) return;
        clearScreen();
        sortCells();
        drawTerrain();
        drawCells();
    }

    public void clearScreen(){
        setColor(BACKGROUND);
        fillRect(windowWidth, windowHeight);
    }

    public void sortCells(){
        Collections.sort(gameScene.getCells(), new DistanceCellComparator());
    }

    public void drawTerrain(){
        setColor(BACKGROUND_TERRAIN);
        fillRect(width, height);
    }
    public void drawCells(){
        for(Cell s : gameScene.getCells()) drawCell(s);
    }

    public void drawCell(Cell s){
        drawCell(s.getPosition(), s.getColor(), s.getSize());
    }

    public void drawCell(Vector2 p, Color color, double r){
        fillOval(color, (int)p.getX(), (int)p.getY(), (int)r);
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
    
    public void fillOval(Color color, int x, int y, int r){
        int d = r * 2;
        y = windowHeight - y;
        graphics.setColor(color);
        graphics.fillOval(x - r, y - r, d, d);
        graphics.setColor(color.brighter());
        graphics.drawOval(x - r, y - r, d, d);
    }
}
