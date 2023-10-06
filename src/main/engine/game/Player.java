package engine.game;

import java.awt.Color;

import engine.geometric.Vector2;
import engine.input.Input;
import engine.input.KeyCode;
import engine.util.Mathf;
import engine.util.Time;

public class Player extends Sphere{
    public final static double SIZE = 10;

    public Player(Vector2 position, Color color){
        super(position, SIZE, color);
    }

    public SphereType getType(){
        return SphereType.PLAYER;
    }

    public void move(){
        double dt = Time.getDeltaTime();
        double move = 30 * dt;
        if(Input.getKey(KeyCode.RIGHT_ARROW)) position.plusX(move);
        if(Input.getKey(KeyCode.LEFT_ARROW)) position.plusX(-move);
        if(Input.getKey(KeyCode.UP_ARROW)) position.plusY(move);
        if(Input.getKey(KeyCode.DOWN_ARROW)) position.plusY(-move);
        bounce();
    }

    public void bounce(){
        if(position.getX() < 0) position.plusX(Mathf.distance(position.getX(), 0));
        if(position.getX() > gameScene.getWidth()) position.plusX(- Mathf.distance(position.getX(), gameScene.getWidth()));
        if(position.getY() < 0) position.plusY(Mathf.distance(position.getY(), 0));
        if(position.getY() > gameScene.getHeight()) position.plusY(- Mathf.distance(position.getY(), gameScene.getHeight()));
    }
}
