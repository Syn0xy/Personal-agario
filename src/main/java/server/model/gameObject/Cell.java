package server.model.gameObject;

import java.awt.Color;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import server.model.GameObject;
import server.model.component.CellCollider;
import utils.Colorf;
import utils.Vector2;

public abstract class Cell extends GameObject {

    public static final List<Cell> CELLS = new CopyOnWriteArrayList<>();
    
    private static final double PERCENTAGE_EAT = 0.925;

    protected double size;

    protected double radius;
    
    private Color color;

    protected transient CellCollider collider;
    
    public Cell(Vector2 position, double size, Color color) {
        super(position);
        this.size = size;
        this.color = color;
        this.refreshRadius();
        this.collider = new CellCollider(this.radius);
        this.addComponent(this.collider);
        CELLS.add(this);
    }

    public Cell(Vector2 position, double size) {
        this(
            position,
            size,
            Colorf.random()
        );
    }

    public double getSize() {
        return size;
    }

    public double getRadius() {
        return radius;
    }

    public Color getColor() {
        return color;
    }

    public CellCollider getCollider() {
        return collider;
    }

    protected void refreshRadius() {
        this.radius = Math.sqrt(this.size / Math.PI) * 2;
    }

    protected abstract boolean canBeEatBy(Cell cell);

    public boolean superior(Cell cell) {
        return this.size * PERCENTAGE_EAT >= cell.size;
    }

    @Override
    public void destroy() {
        CELLS.remove(this);
        super.destroy();
    }

    @Override
    public String toString() {
        return "Cell [size=" + size + ", radius=" + radius + ", color=" + color + "]";
    }
    
}
