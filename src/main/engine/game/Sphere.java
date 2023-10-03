package engine.game;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import engine.geometric.Vector2;
import engine.scene.GameScene;
import engine.util.Mathf;

public abstract class Sphere {
    public final static List<Sphere> SPHERES = new ArrayList<>();
    public final static double RANGE = 2;
    public static GameScene gameScene;

    protected Vector2 position;
    protected double size;
    protected Color color;

    public Sphere(Vector2 position, double size, Color color){
        this.position = position;
        this.size = size;
        this.color = color;
        SPHERES.add(this);
    }

    public Vector2 getPosition(){ return position; }
    public double getSize(){ return size; }
    public Color getColor(){ return color; }

    public abstract SphereType getType();

    public void eat(Sphere s){
        size += s.getSize();
        s.delete();
    }

    public void collision(){
        for(Sphere s : SPHERES){
            if(this != s && this.collision(s)) eat(s);
        }
    }
    
    public final void update(){
        if(getType() != SphereType.FOOD){
            move();
            collision();
        }
    }

    public void move(){}

    public void delete(){
        gameScene.delete(this);
    }

    public double distance(Sphere s){
        return Mathf.distance(position, s.getPosition());
    }

    public boolean collision(Sphere s){
        return distance(s) < (size + s.getSize()) * 2 + RANGE;
    }
    
    public String toString(){
        return getClass().getSimpleName() + "[position:" + position + ", size:" + size + ", color:" + color + "]";
    }
}
