package engine.graphic;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.Collections;
import java.util.List;

import javax.swing.JPanel;

import application.Launcher;
import engine.game.Cell;
import engine.game.CellType;
import engine.game.VisionCellComparator;
import engine.geometric.Vector2;
import engine.scene.GameScene;
import engine.util.Colorf;

import static application.Main.BACKGROUND;
import static application.Main.BACKGROUND_TERRAIN;
import static application.Main.FONT_NAME;
import static application.Main.FONT_TYPE;

public class PaintScene extends JPanel{
    private Graphics graphics;

    private int windowWidth;
    private int windowHeight;
    private Launcher launcher;
    private GameScene gameScene;

    private List<Cell> cells;

    // private int halfWindowWidth;
    // private int halfWindowHeight;
    private int width;
    private int height;

    private static Cell target;

    public PaintScene(Launcher launcher, GameScene gameScene){
        this.launcher = launcher;
        this.gameScene = gameScene;
        this.cells = gameScene.getCells();
        reloadWindowSize();
        reloadSize();
    }

    public int getWindowWidth(){ return windowWidth; }
    public int getWindowHeight(){ return windowHeight; }
    public Cell getTarget(){ return target; }

    public static void setTarget(Cell target){ PaintScene.target = target; }

    public void setGraphics(Graphics graphics){
        this.graphics = graphics;
    }
    
    public void reloadWindowSize(){
        windowWidth = launcher.getWindowWidth();
        windowHeight = launcher.getWindowHeight();
        // halfWindowWidth = windowWidth / 2;
        // halfWindowHeight = windowHeight / 2;
    }
    
    public void reloadSize(){
        width = gameScene.getWidth();
        height = gameScene.getHeight();
    }

    @Override
    protected void paintComponent(Graphics g){
        this.graphics = g;
        drawScene();
    }
    
    public void drawScene(){
        if(graphics == null) return;
        clearScreen();
        sortCells();
        drawTerrain();
        drawCells();
        drawDebugs();
    }

    public void clearScreen(){
        setColor(BACKGROUND);
        fillRect(0, 0, windowWidth, windowHeight);
    }

    public void drawDebugs(){
        String s1 = "Nb de cells : " + gameScene.getCells().size();
        String s2 = "Nb de foods : " + gameScene.getFoods().size();
        String s3 = "Nb de cells avancées : " + (gameScene.getCells().size() - gameScene.getFoods().size());
        graphics.setColor(Colorf.inverse(BACKGROUND));
        graphics.setFont(new Font(FONT_NAME, FONT_TYPE, 12));
        graphics.drawString(s1, 30, 30);
        graphics.drawString(s2, 30, 60);
        graphics.drawString(s3, 30, 90);
    }

    public void sortCells(){
        Collections.sort(cells, new VisionCellComparator());
    }

    public void drawTerrain(){
        setColor(BACKGROUND_TERRAIN);
        fillRect(width, height);
    }
    
    public void drawCells(){
        for(Cell s : cells) drawCell(s);
    }

    public void drawCell(Cell s){
        Vector2 p = s.getPosition();
        Color c = s.getColor();
        int x = getWindowWidth(p.getX());
        int y = getWindowHeight(p.getY());
        int r = (int)s.getSize();
        fillOval(c, x, y, r);
        if(s.getType() != CellType.FOOD) drawPlate(Colorf.inverse(c), x, y, r);
    }

    ////////// Graphics

    public void setColor(Color color){
        graphics.setColor(color);
    }

    public void fillRect(int width, int height){
        fillRect(getWindowWidth(0), getWindowHeight(height), width, height);
    }

    public void fillRect(int x, int y, int width, int height){
        graphics.fillRect(x, y, width, height);
    }
    
    public void fillOval(Color color, int x, int y, int r){
        int d = r * 2;
        graphics.setColor(color);
        graphics.fillOval(x - r, y - r, d, d);
        graphics.setColor(color.brighter());
        graphics.drawOval(x - r, y - r, d, d);
    }

    public void drawPlate(Color color, int x, int y, int r){
        graphics.setColor(color);
        drawCenteredString(String.valueOf(r), x, y, r);
    }
    
    public void drawCenteredString(String plate, int x, int y, int r) {
        Font font = new Font(FONT_NAME, FONT_TYPE, r);
        FontMetrics metrics = graphics.getFontMetrics(font);
        x = x + (r - metrics.stringWidth(plate)) / 2;
        y = y + ((r - metrics.getHeight()) / 2) + metrics.getAscent();
        graphics.setFont(font);
        graphics.drawString(plate, x - r / 2, y - r / 2);
    }
    
    public int getWindowWidth(double x){
        if(target == null) return (int)((windowWidth - width) / 2 + x);
        // Screen : 200 ; 200
        // Target : 50 ; 50
        
        x = ;
        return (int)((windowWidth - width) / 2 + x);
    }
    
    public int getWindowHeight(double y){
        // (windowHeight - height) / 2 - y + height
        if(target == null) return (int)((windowHeight - height) / 2 - y + height);
        return (int)((windowHeight - height) / 2 - y + height);
    }
}
