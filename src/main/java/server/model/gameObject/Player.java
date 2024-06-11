package server.model.gameObject;

import utils.Vector2;

public class Player extends AdvancedCell {

    private transient Vector2 direction;

    public Player(Vector2 position) {
        super(position);
        this.direction = new Vector2();
    }

    public void setDirection(Vector2 direction) {
        this.direction = direction;
    }

    @Override
    public void update() {
        this.addForce(this.direction);
        super.update();
    }

}
