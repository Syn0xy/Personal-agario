package engine.game;

import engine.geometric.Vector2;
import engine.util.Mathf;

public class BotAI {
    private Vector2 position;
    private Cell destination;

    public BotAI(Vector2 position){
        this.position = position;
    }

    public Vector2 getPosition(){
        return position;
    }

    public Cell getDestination(){
        return destination;
    }

    public void update(Bot bot){
        Cell closest = null;
        double dist = Double.MAX_VALUE;
        for(Cell c : Bot.gameScene.getCells()){
            if(bot == c) continue;
            else if(closest == null) closest = c;
            else{
                double d = bot.distance(c);
                if(d < dist && bot.superior(c)){
                    dist = d;
                    closest = c;
                }
            }
        }
        destination = closest;
    }
    
    public void move(Bot bot){
        if(destination == null) return;
        Vector2 v = look();
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
