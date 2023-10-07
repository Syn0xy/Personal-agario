package engine.game;

import java.awt.Color;

import engine.geometric.Vector2;
import engine.scene.GameScene;
import engine.util.Mathf;

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

    public void eat(Cell s){
        // Aire d'un cercle : pi * r * r
        // Soit : pi * size * size
        // Nouveau rayon : sqrt((airA + airB) / pi)
        size = Mathf.hypotenuse(size, s.getSize());
        s.delete();
    }

    public void collisions(){
        for(Cell s : gameScene.getCells()){
            if(this != s && this.collision(s) && this.superior(s)) eat(s);
        }
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
