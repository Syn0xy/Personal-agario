package server.model.component;

import utils.Time;
import utils.Vector2;

public class Rigidbody extends Component {
    
    private Vector2 velocity;

    public Rigidbody() {
        this.velocity = new Vector2();
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    @Override
    public void update() {
        this.position.plus(this.velocity);
        this.velocity.multiply(1 - Time.getDeltaTime());
    }
    
    public void addForce(Vector2 force) {
        this.velocity.plus(force);
    }
    
    public void addForce(Vector2 direction, double force) {
        this.velocity.plus(direction.multiplyCopy(force * Time.getDeltaTime()));
    }

}
