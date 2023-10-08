package engine.game;

import engine.util.Colorf;
import engine.geometric.Vector2;

public class Food extends Cell{
    public final static double SIZE = 2;
    
    public Food(Vector2 position){
        super(position, Colorf.random(), SIZE);
    }

    public CellType getType(){
        return CellType.FOOD;
    }

    public void update(){}
}
