package client.view;

import java.awt.Graphics;
import java.awt.Point;

import client.view.network.BroadcastServer;
import server.model.gameObject.Cell;
import utils.Vector2;
import view.Canvas;

public class ClientCanvas extends Canvas {

    private BroadcastServer broadcast;

    public ClientCanvas(BroadcastServer broadcast) {
        this.broadcast = broadcast;
        this.focusCell = null;
        this.focusPosition = new Vector2();
    }

    protected void refreshDirection() {
        if (this.focusCell == null) {
            return;
        }
        
        Point mousePoint = this.getMousePosition();
        
        if (mousePoint == null) {
            return;
        }
        
        Vector2 mousePosition = new Vector2(mousePoint.x, mousePoint.y);
        Vector2 direction = mousePosition.minusCopy(this.width / 2, this.height / 2).normalize();
        this.broadcast.setLocalPlayerDirection(new Vector2(direction.x, -direction.y));
    }
    
    @Override
    public void paint(Graphics g) {
        this.gameWidth = this.broadcast.getGameWidth();
        this.gameHeight = this.broadcast.getGameHeight();
        this.focusCell = this.broadcast.getLocalPlayer();
        
        this.refreshDirection();
        
        super.paint(g);
    }

    @Override
    protected void draw(Graphics g) {
        this.drawCells(g);
    }
    
    @Override
    protected void drawCells(Graphics g) {
        Cell[] cells = this.broadcast.getServerCells();

        if (cells == null) {
            return;
        }
        
        for (int i = 0; i < cells.length; i++) {
            this.drawCell(g, cells[i]);
        }
    }

}
