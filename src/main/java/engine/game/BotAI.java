package engine.game;

import java.util.Collections;
import java.util.List;

import engine.geometric.Vector2;
import engine.util.Mathf;

public class BotAI {
    private Bot bot;
    private Vector2 position;
    private Cell destination;

    private TargetCellComparator comparator;

    public BotAI(Bot bot, Vector2 position){
        this.bot = bot;
        this.position = position;
        this.destination = null;
        this.comparator = new TargetCellComparator(bot);
    }

    public Bot getBot(){ return bot; }
    public Vector2 getPosition(){ return position; }
    public Cell getDestination(){ return destination; }

    public void update(){
        refreshDestination();
        move();
    }

    public void refreshDestination(){
        List<Cell> list = Bot.cells;
        if(list.size() > 1){
            Collections.sort(list, comparator);
            destination = list.get(1);
        }else destination = null;
    }
    
    public void move(){
        if(destination == null) return;
        Vector2 v = look();
        if(v == null) return;
        v.multiply(bot.getSpeed());
        bot.addForce(v);
    }

    public Vector2 look(){
        return look(position, destination.getPosition());
    }

    public Vector2 look(Vector2 start, Vector2 end){
        Vector2 m = end.copy();
        m.minus(start);
        double d = Mathf.hypotenuse(m.getX(), m.getY());
        if(d != 0){
            m.divide(d);
            return m;
        }
        return null;
    }

    public String toString(){
        return getClass().getSimpleName() + "[position:" + position + ", destination:" + destination + "]";
    }
}
