package engine.game;

import java.awt.Color;

import engine.geometric.Vector2;
import engine.scene.GameScene;

public abstract class Cell {
    public final static double RANGE = 0;
    public static GameScene gameScene;

    protected Vector2 position;
    protected Color color;
    protected double size;

    public Cell(Vector2 position, Color color, double size){
        this.position = position;
        this.color = color;
        this.size = size;
    }

    public Vector2 getPosition(){ return position; }
    public Color getColor(){ return color; }
    public double getSize(){ return size; }

    public abstract CellType getType();
    public abstract void update();

    public boolean superior(Cell s){
        return size > s.getSize();
    }

    public void addSize(double size){
        this.size += size;
    }

    public void delete(){
        gameScene.delete(this);
    }

    public double distance(Cell s){
        return position.distance(s.getPosition());
    }

    public boolean collision(Cell s){
        return distance(s) < size + RANGE;
    }
    
    public String toString(){
        return getClass().getSimpleName() + "[position:" + position + ", size:" + size + ", color:" + color + "]";
    }
}
