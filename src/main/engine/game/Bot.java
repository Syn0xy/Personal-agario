package engine.game;

import java.awt.Color;

import engine.geometric.Vector2;
import engine.util.Mathf;
import engine.util.Time;

public class Bot extends Sphere{
    public final static double RADIUS = 20;

    public Bot(Vector2 position, Color color){
        super(position, RADIUS, color);
    }

    public SphereType getType(){
        return SphereType.BOT;
    }

    public void move(){
        double dt = Time.getDeltaTime();
        double move = 10 * dt;
        position.plus(Mathf.random(move) - 0.001 / 2, Mathf.random(move) - 0.001 / 2);
    }
}
