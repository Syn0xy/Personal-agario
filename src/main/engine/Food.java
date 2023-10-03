package main.engine;

import main.engine.util.RandomColor;

public class Food extends Sphere{
    public final static double RADIUS = 2;
    
    public Food(Vector2 position){
        super(position, RADIUS, RandomColor.random());
    }

    public SphereType getType(){
        return SphereType.FOOD;
    }

    public String toString(){
        return super.toString();
    }
}
