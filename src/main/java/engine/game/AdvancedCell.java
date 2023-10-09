package engine.game;

import java.awt.Color;

import engine.geometric.Vector2;
import engine.util.Mathf;
import engine.util.Colorf;
import engine.util.Time;

import static application.Main.SPEED;

public abstract class AdvancedCell extends Cell{
    private final static int SIZE = 2;

    protected Rigidbody rigidbody;
    protected double speed;

    public AdvancedCell(Vector2 position, Color color){
        super(position, color, SIZE);
        this.rigidbody = new Rigidbody(position);
        this.speed = 0;
    }

    public AdvancedCell(Vector2 position){
        this(position, Colorf.random());
    }

    public abstract CellType getType();
    
    public Rigidbody getRigidbody(){
        return rigidbody;
    }
    
    public double getSpeed(){
        return speed;
    }
    
    public void addForce(Vector2 force){
        rigidbody.add(force);
    }

    public void update(){
        refreshSpeed();
        rigidbody.update();
        move();
        bounce();
        collisions();
    }

    public void collisions(){
        for(Cell s : cells){
            if(this == s) continue;
            if(!this.collision(s)) continue;
            if(!this.superior(s) && !s.isFood()) continue;
            eat(s);
        }
    }

    public void eat(Cell s){

        // Aire d'un cercle : pi * r * r
        // Soit : pi * size * size
        // Nouveau rayon : sqrt((airA + airB) / pi)
        size = Mathf.hypotenuse(size, s.getSize());
        s.delete();
    }

    public void refreshSpeed(){
        speed = SPEED * Time.getDeltaTime();
    }

    public abstract void move();

    public void bounce(){
        if(bounce(position.getX())) position.plusX(Mathf.distance(position.getX(), 0));
        if(bounce(position.getX(), gameScene.getWidth()))  position.plusX(- Mathf.distance(position.getX(), gameScene.getWidth()));
        if(bounce(position.getY()))  position.plusY(Mathf.distance(position.getY(), 0));
        if(bounce(position.getY(), gameScene.getHeight()))  position.plusY(- Mathf.distance(position.getY(), gameScene.getHeight()));
    }

    private boolean bounce(double a){
        return a < 0;
    }

    private boolean bounce(double a, double max){
        return a > max;
    }
}