package engine.game;

import engine.geometric.Vector2;

public class Bot extends AdvancedCell{
    private BotAI ai;

    public Bot(Vector2 position){
        super(position);
        this.ai = new BotAI(position);
    }
    
    public CellType getType(){
        return CellType.BOT;
    }

    public BotAI getAI(){
        return ai;
    }

    public void move(){
        ai.update(this);
        ai.move(this);
    }
}
