package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.Collections;
import java.util.List;

import javax.swing.JPanel;

import engine.game.Cell;
import engine.game.CellType;
import engine.game.VisionCellComparator;
import engine.geometric.Vector2;
import engine.scene.GameScene;
import engine.util.Colorf;

public class GameCanvas extends JPanel {
    public final static Color BACKGROUND = Color.LIGHT_GRAY;
    public final static Color BACKGROUND_TERRAIN = Color.BLACK;
    public final static String FONT_NAME = "Comic sans MS";
    public final static int FONT_TYPE = Font.BOLD;

    private GameScene gameScene;
    private List<Cell> cells;

    private int width;
    private int height;

    public GameCanvas(GameScene gameScene){
        this.gameScene = gameScene;
        this.cells = gameScene.getCells();
        this.width = gameScene.getWidth();
        this.height = gameScene.getHeight();
    }

    public Cell getTarget(){ return gameScene.getPlayer(); }

    @Override
    public void paint(Graphics g){
        draw(g);
    }
    
    public void draw(Graphics g){
        clearScreen(g);
        sortCells();
        drawTerrain(g);
        drawCells(g);
        drawDebugs(g);
    }

    public void clearScreen(Graphics g){
        g.setColor(BACKGROUND);
        g.fillRect(0, 0, getWidth(), getHeight());
    }

    public void drawTerrain(Graphics g){
        g.setColor(BACKGROUND_TERRAIN);
        g.fillRect(getWindowWidth(0), getWindowHeight(height), width, height);
    }

    public void sortCells(){
        Collections.sort(cells, new VisionCellComparator());
    }
    
    public void drawCells(Graphics g){
        cells.stream().forEach((c) -> { drawCell(g, c); });
    }

    public void drawCell(Graphics g, Cell s){
        Vector2 p = s.getPosition();
        Color c = s.getColor();
        int x = getWindowWidth(p.getX());
        int y = getWindowHeight(p.getY());
        int r = (int)s.getSize();
        fillOval(g, c, x, y, r);
        if(s.getType() != CellType.FOOD) drawPlate(g, Colorf.inverse(c), x, y, r);
    }
    
    public void drawDebugs(Graphics g){
        String s1 = "Nb de cellules : " + gameScene.getCells().size();
        String s2 = "Nb de nourritures : " + gameScene.getFoods().size();
        String s3 = "Nb de cellules avancées : " + (gameScene.getCells().size() - gameScene.getFoods().size());
        g.setColor(Colorf.inverse(BACKGROUND));
        g.setFont(new Font(FONT_NAME, FONT_TYPE, 12));
        g.drawString(s1, 30, 30);
        g.drawString(s2, 30, 60);
        g.drawString(s3, 30, 90);
    }

    ////////// Graphics
    
    public void fillOval(Graphics g, Color color, int x, int y, int r){
        int d = r * 2;
        g.setColor(color);
        g.fillOval(x - r, y - r, d, d);
        g.setColor(color.brighter());
        g.drawOval(x - r, y - r, d, d);
    }

    public void drawPlate(Graphics g, Color color, int x, int y, int r){
        g.setColor(color);
        drawCenteredString(g, String.valueOf(r), x, y, r);
    }
    
    public void drawCenteredString(Graphics g, String plate, int x, int y, int r) {
        Font font = new Font(FONT_NAME, FONT_TYPE, r);
        FontMetrics metrics = g.getFontMetrics(font);
        x = x + (r - metrics.stringWidth(plate)) / 2;
        y = y + ((r - metrics.getHeight()) / 2) + metrics.getAscent();
        g.setFont(font);
        g.drawString(plate, x - r / 2, y - r / 2);
    }
    
    public int getWindowWidth(double x){
        Cell c = getTarget();
        if(c == null) return (int)((getWidth() - width) / 2 + x);
        // Screen : 150 ; 150
        // Size :   100 ; 100
        // Target : 25 ; 25
        return (int)(getWidth() / 2 + x - c.getPosition().getX());
    }
    
    public int getWindowHeight(double y){
        Cell c = getTarget();
        // (windowHeight - height) / 2 - y + height
        if(c == null) return (int)((getHeight() - height) / 2 - y + height);
        return (int)(getHeight() / 2 - y + c.getPosition().getY());
    }
}
