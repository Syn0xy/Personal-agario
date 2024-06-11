package server.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.util.List;

import server.model.GameScene;
import server.model.Grid;
import server.model.gameObject.AdvancedCell;
import server.model.gameObject.Cell;
import server.model.gameObject.Player;
import utils.Vector2;
import utils.input.Input;
import utils.input.KeyCode;
import view.Canvas;

public class ServerCanvas extends Canvas {
    
    private static final Color GRID_COLOR = Color.GRAY;
    
    private GameScene gameScene;

    private Grid grid;

    private int gridSize;

    public ServerCanvas(GameScene gameScene) {
        this.gameScene = gameScene;
        this.gameWidth = gameScene.getWidth();
        this.gameHeight = gameScene.getHeight();
        this.grid = gameScene.getGrid();

        Input.onKeyDown(KeyCode.O, () -> CanvasInfos.switchDrawGrid());
        Input.onKeyDown(KeyCode.P, () -> CanvasInfos.switchDrawGridPositions());
    }

    protected void refreshDirection() {
        if (focusCell == null || !(focusCell instanceof Player)) {
            return;
        }
        
        Point mousePoint = this.getMousePosition();

        if (mousePoint == null) {
            return;
        }

        Player player = (Player)focusCell;
        Vector2 mousePosition = new Vector2(mousePoint.x, mousePoint.y);
        Vector2 direction = mousePosition.minusCopy(this.width / 2, this.height / 2).normalize();
        player.setDirection(new Vector2(direction.x, -direction.y));
    }
    
    @Override
    public void paint(Graphics g) {
        this.gridSize = grid.getSize();
        this.focusCell = this.gameScene.getFocusCell();

        this.refreshDirection();
        
        super.paint(g);
        this.drawScores(g);
        this.drawDebugs(g);
    }

    @Override
    protected void draw(Graphics g) {
        if (CanvasInfos.drawGrid) {
            this.drawGrid(g);
        }
        
        this.drawCells(g);

        if (CanvasInfos.drawGridPositions) {
            this.drawGridPositions(g);
        }
    }

    private void drawGrid(Graphics g) {
        g.setColor(GRID_COLOR);
        
        int sx = this.getReelX(0);
        int sy = this.getReelY(0);
        int ex = this.getReelX(this.gameWidth);
        int ey = this.getReelY(this.gameHeight);

        for (int i = 0; i < this.gameHeight / this.gridSize + 1; i++) {
            int py = this.getReelY(this.gridSize * i);
            
            g.drawLine(sx, py, ex, py);
        }
        
        for (int i = 0; i < this.gameWidth / this.gridSize + 1; i++) {
            int px = this.getReelX(this.gridSize * i);
            
            g.drawLine(px, sy, px, ey);
        }
    }
    
    @Override
    protected void drawCells(Graphics g) {
        Cell.CELLS.forEach(cell -> this.drawCell(g, cell));
    }
    
    private void drawRightString(Graphics g, String plate, int x, int y, int r) {
        Font font = new Font(FONT_NAME, FONT_TYPE, r);
        FontMetrics metrics = g.getFontMetrics(font);
        x = x + (r - metrics.stringWidth(plate));
        g.setFont(font);
        g.drawString(plate, x - r / 2, y);
    }

    private void drawGridPositions(Graphics g) {
        g.setColor(Color.GREEN);
        this.grid.getPositions().forEach((k, v) -> {
            Vector2 vec2 = k.getPosition();

            int gx = getReelX(vec2.x);
            int gy = getReelY(vec2.y);

            v.forEach(position -> {
                int px = getReelX((position[0] + 0.5) * this.gridSize);
                int py = getReelY((position[1] + 0.5) * this.gridSize);

                g.drawLine(gx, gy, px, py);
            });
        });
    }

    private void drawDebugs(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font(FONT_NAME, FONT_TYPE, 12));

        String[] debugs = new String[] {
            "Nombre de cells: " + Cell.CELLS.size(),
            "Grid size: " + this.gridSize,
            "Food count: " + GameScene.foodCount,
            "Bot count: " + GameScene.botCount,
        };

        for (int i = 0; i < debugs.length; i++) {
            g.drawString(debugs[i], 30, (i + 1) * 30);
        }
    }

    private void drawScores(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font(FONT_NAME, FONT_TYPE, 15));

        List<Cell> list = Cell.CELLS;
        int cellsSize = list.size();
        for (int i = 0; i < 10 && i < cellsSize; i++) {
            Cell cell = list.get(cellsSize - i - 1);
            
            if (cell instanceof AdvancedCell) {
                int rank = i + 1;
                AdvancedCell advCell = (AdvancedCell) cell;
                this.drawRightString(
                    g,
                    rank + ". " + advCell.getPseudo() + " - " + (int) advCell.getSize(),
                    this.width - 30,
                    (i + 1) * 30,
                    15
                );
            }
        }
    }

}
