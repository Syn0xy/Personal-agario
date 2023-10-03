package engine.game;

import engine.util.RandomColor;
import engine.geometric.Vector2;

public class Food extends Sphere{
    public final static double SIZE = 5;
    
    public Food(Vector2 position){
        super(position, SIZE, RandomColor.random());
    }

    public SphereType getType(){
        return SphereType.FOOD;
    }
}
