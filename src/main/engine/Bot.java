package main.engine;

import java.awt.Color;
import main.engine.input.Input;
import main.engine.input.KeyCode;
import main.engine.util.Mathf;

public class Bot extends Sphere{
    public final static double RADIUS = 20;

    public Bot(Vector2 position, Color color){
        super(position, RADIUS, color);
    }

    public SphereType getType(){
        return SphereType.BOT;
    }

    public void move(){
        position.plusX(Mathf.random(0.002) - 0.001);
        position.plusY(Mathf.random(0.002) - 0.001);
    }
}
