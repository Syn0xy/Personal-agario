package engine.game;

import java.awt.Color;

import engine.geometric.Vector2;
import engine.input.Input;
import engine.input.KeyCode;

public class Player extends AdvancedCell{
    public Player(Vector2 position, Color color){
        super(position, color);
    }
    
    public CellType getType(){
        return CellType.PLAYER;
    }

    public void move(){
        if(Input.getKey(KeyCode.RIGHT_ARROW)) addForce(new Vector2(speed, 0));
        if(Input.getKey(KeyCode.LEFT_ARROW)) addForce(new Vector2(-speed, 0));
        if(Input.getKey(KeyCode.UP_ARROW)) addForce(new Vector2(0, speed));
        if(Input.getKey(KeyCode.DOWN_ARROW)) addForce(new Vector2(0, -speed));
    }
}
