package engine.game;

import java.awt.Color;

import engine.geometric.Vector2;
import engine.util.Mathf;
import engine.util.RandomColor;
import engine.util.Time;

public class Bot extends Sphere{
    public final static double RADIUS = 20;

    public Bot(Vector2 position, Color color){
        super(position, RADIUS, color);
    }

    public Bot(Vector2 position){
        this(position, RandomColor.random());
    }

    public Bot(double x, double y){
        this(new Vector2(x, y));
    }

    public SphereType getType(){
        return SphereType.BOT;
    }

    public void move(){
        double d = 100 * Time.getDeltaTime();
        position.plus(Vector2.random(-d, d));
        bounce();
    }

    public void bounce(){
        if(position.getX() < 0) position.plusX(Mathf.distance(position.getX(), 0));
        if(position.getX() > gameScene.getWidth()) position.plusX(- Mathf.distance(position.getX(), gameScene.getWidth()));
        if(position.getY() < 0) position.plusY(Mathf.distance(position.getY(), 0));
        if(position.getY() > gameScene.getHeight()) position.plusY(- Mathf.distance(position.getY(), gameScene.getHeight()));
    }
}
