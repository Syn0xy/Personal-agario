package main.engine;

import java.awt.Color;

import main.application.Main;
import main.engine.input.Input;
import main.engine.input.KeyCode;
import main.engine.util.Mathf;

public class Player extends Sphere{
    public final static double RADIUS = 20;

    public Player(Vector2 position, Color color){
        super(position, RADIUS, color);
    }

    public SphereType getType(){
        return SphereType.PLAYER;
    }

    public void move(){
        if(Input.getKey(KeyCode.RIGHT_ARROW)) position.plusX(0.002);
        if(Input.getKey(KeyCode.LEFT_ARROW)) position.plusX(-0.002);
        if(Input.getKey(KeyCode.UP_ARROW)) position.plusY(0.002);
        if(Input.getKey(KeyCode.DOWN_ARROW)) position.plusY(-0.002);
        bounce();
    }

    public void bounce(){
        if(position.getX() < 0) position.plusX(Mathf.distance(position.getX(), 0));
        if(position.getX() > Main.WIDTH) position.plusX(- Mathf.distance(position.getX(), Main.WIDTH));
        if(position.getY() < 0) position.plusY(Mathf.distance(position.getY(), 0));
        if(position.getY() > Main.HEIGHT) position.plusY(- Mathf.distance(position.getY(), Main.HEIGHT));
    }

    public String toString(){
        return super.toString();
    }
}
