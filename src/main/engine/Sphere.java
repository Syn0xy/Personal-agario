package main.engine;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import main.engine.util.Mathf;

public abstract class Sphere {
    public final static List<Sphere> SPHERES = new ArrayList<>();
    public static GameScene gameScene;

    protected Vector2 position;
    protected double radius;
    protected Color color;

    public Sphere(Vector2 position, double radius, Color color){
        this.position = position;
        this.radius = radius;
        this.color = color;
        SPHERES.add(this);
    }

    public Vector2 getPosition(){ return position; }
    public double getRadius(){ return radius; }
    public Color getColor(){ return color; }

    public abstract SphereType getType();

    public void eat(Sphere s){
        radius += s.getRadius();
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
        return distance(s) < (radius + s.getRadius());
    }

    public String toString(){
        return getClass().getSimpleName() + "[position:" + position + ", radius:" + radius + ", color:" + color + "]";
    }
}
