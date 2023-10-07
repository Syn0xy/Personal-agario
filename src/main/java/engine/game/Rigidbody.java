package engine.game;

import engine.geometric.Vector2;
import engine.util.Time;

public class Rigidbody {
    private Vector2 position;
    private Vector2 inertie;
    private double velocity;

    public Rigidbody(Vector2 position){
        this.position = position;
        this.inertie = new Vector2();
        this.velocity = 0;
    }

    public Vector2 getPosition(){ return position; }
    public Vector2 getInertie(){ return inertie; }
    public double getVelocity(){ return velocity; }

    public void add(Vector2 force){
        inertie.plus(force);
    }

    public void update(){
        position.plus(inertie);
        Vector2 oldInertie = inertie;
        inertie.multiply(0.98);
        velocity = oldInertie.distance(inertie) / Time.getDeltaTime();

        if(velocity < 0.001) velocity = 0;
    }

    public String toString(){
        return getClass().getSimpleName() + "[position:" + position + ", inertie:" + inertie + ", velocity:" + velocity + "]";
    }
}
