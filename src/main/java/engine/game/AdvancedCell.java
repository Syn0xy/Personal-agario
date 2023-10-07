package engine.game;

import java.awt.Color;

import engine.geometric.Vector2;
import engine.util.Mathf;
import engine.util.RandomColor;
import engine.util.Time;

public abstract class AdvancedCell extends Cell{
    private final static int SIZE = 10;

    protected Rigidbody rigidbody;
    protected double speed;

    public AdvancedCell(Vector2 position, Color color){
        super(position, color, SIZE);
        this.rigidbody = new Rigidbody(position);
        this.speed = 0;
    }

    public AdvancedCell(Vector2 position){
        this(position, RandomColor.random());
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
        speed = 5 * Time.getDeltaTime();
        rigidbody.update();
        move();
        bounce();
        collisions();
    }

    public abstract void move();

    public void bounce(){
        if(bounce(position.getX())) position.plusX(Mathf.distance(position.getX() - size, 0));
        if(bounce(position.getX(), gameScene.getWidth()))  position.plusX(- Mathf.distance(position.getX() + size, gameScene.getWidth()));
        if(bounce(position.getY()))  position.plusY(Mathf.distance(position.getY() - size, 0));
        if(bounce(position.getY(), gameScene.getHeight()))  position.plusY(- Mathf.distance(position.getY() + size, gameScene.getHeight()));
    }

    private boolean bounce(double a){
        return a - size < 0;
    }

    private boolean bounce(double a, double max){
        return a + size > max;
    }
}