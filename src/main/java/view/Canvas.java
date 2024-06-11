package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import server.model.gameObject.AdvancedCell;
import server.model.gameObject.Cell;
import utils.Colorf;
import utils.Vector2;

public abstract class Canvas extends Component {

    private static final Color BACKGROUND_COLOR = Color.BLACK;

    protected static final String FONT_NAME = "Comic sans MS";
    
    protected static final int FONT_TYPE = Font.BOLD;

    protected int gameWidth;

    protected int gameHeight;

    protected int width;

    protected int height;

    protected double zoom;

    protected Cell focusCell;

    protected Vector2 focusPosition;

    public Canvas() {
        this.zoom = 1;
    }

    public void plusZoom(int zoom) {
        if (zoom > 0) {
            this.zoom /= 1.1;
        } else {
            this.zoom *= 1.1;
        }
        this.zoom = Math.max(0.25, this.zoom);
        this.zoom = Math.min(50, this.zoom);
    }

    protected int getReelSize(double a) {
        return (int)(a * zoom);
    }

    protected int getReelX(double x) {
        return this.getReelSize(x - this.focusPosition.x);
    }

    protected int getReelY(double y) {
        return this.getReelSize(this.focusPosition.y - y);
    }

    @Override
    public void paint(Graphics g) {
        this.width = this.getWidth();
        this.height = this.getHeight();
        
        if (this.focusCell != null) {
            this.focusPosition = this.focusCell.getPosition();
        } else {
            this.zoom = 0.5;
            this.focusPosition = new Vector2(
                this.gameWidth / 2,
                this.gameHeight / 2
            );
        }

        this.clearScreen(g);
        
        g.translate(
            this.width / 2,
            this.height / 2
        );
        
        this.draw(g);
        
        g.translate(
            -this.width / 2,
            -this.height / 2
        );
    }

    private void clearScreen(Graphics g) {
        g.setColor(BACKGROUND_COLOR);
        g.fillRect(0, 0, this.width, this.height);
    }

    protected abstract void draw(Graphics g);

    protected abstract void drawCells(Graphics g);

    protected void drawCell(Graphics g, Cell cell) {
        Vector2 position = cell.getPosition();
        Color color = cell.getColor();
        Color inverse = Colorf.inverse(color);
        double radius = cell.getRadius();
        int rr = getReelSize(radius);
        int rd = rr * 2;
        int px = getReelX(position.x);
        int py = getReelY(position.y);
        int rx = px - rr;
        int ry = py - rr;
        
        g.setColor(color);
        g.fillOval(rx, ry, rd, rd);
        g.setColor(inverse);
        g.drawOval(rx, ry, rd, rd);

        String sizeText = String.valueOf((int)cell.getSize());
        
        if (cell instanceof AdvancedCell) {
            int br = rr / 2;
            int rbr = (int) (rr / 3);
            String pseudoText = ((AdvancedCell) cell).getPseudo();
            this.drawPlate(g, inverse, pseudoText, px, py, br);
            this.drawPlate(g, inverse, sizeText, px, py + br, rbr);
        } else {
            this.drawPlate(g, inverse, sizeText, px, py, rr);
        }
    }
    
    protected void drawPlate(Graphics g, Color color, String text, int x, int y, int r) {
        g.setColor(color);
        this.drawCenteredString(g, text, x, y, r);
    }
    
    protected void drawCenteredString(Graphics g, String plate, int x, int y, int r) {
        Font font = new Font(FONT_NAME, FONT_TYPE, r);
        FontMetrics metrics = g.getFontMetrics(font);
        x = x + (r - metrics.stringWidth(plate)) / 2;
        y = y + ((r - metrics.getHeight()) / 2) + metrics.getAscent();
        g.setFont(font);
        g.drawString(plate, x - r / 2, y - r / 2);
    }
    
}
