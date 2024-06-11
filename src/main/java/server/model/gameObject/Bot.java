package server.model.gameObject;

import utils.Vector2;

public class Bot extends AdvancedCell {

    private transient Cell target;

    private transient double lastDistance;

    public Bot(Vector2 position) {
        super(position);
        this.resetTarget();
    }

    @Override
    public void update() {
        this.resetTarget();
        CELLS.forEach(this::check);
        this.move();
        super.update();
    }

    private void resetTarget() {
        this.target = null;
        this.lastDistance = Double.MAX_VALUE;
    }

    private void check(Cell cell) {
        if (this == cell) {
            return;
        }
        
        double distance = this.position.distance(cell.getPosition());
        
        if (distance < this.lastDistance) {
            this.target = cell;
            this.lastDistance = distance;
        }
    }
    
    private void move() {
        if (this.target == null) {
            return;
        }
        
        Vector2 relativeDirection = this.target.getPosition().minusCopy(this.position);
        Vector2 normalizedDirection = relativeDirection.normalize();

        if (!this.superior(this.target)) {
            normalizedDirection = normalizedDirection.inverse();
        }
        
        this.addForce(normalizedDirection);
    }

}
