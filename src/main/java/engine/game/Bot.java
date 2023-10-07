package engine.game;

import engine.geometric.Vector2;

public class Bot extends AdvancedCell{
    public Bot(Vector2 position){
        super(position);
    }
    
    public CellType getType(){
        return CellType.BOT;
    }

    public void move(){
        addForce(Vector2.random(-speed, speed));
    }
}
