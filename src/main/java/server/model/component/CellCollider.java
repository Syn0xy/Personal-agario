package server.model.component;

import server.model.GameObject;

public class CellCollider extends Component {

    private double radius;

    public CellCollider(double radius) {
        this.radius = radius;
    }
    
    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public void update() {
        if (this.position == null) {
            return;
        }
        
        GameObject.gameScene.grid
            .getCells(this.gameObject)
            .forEach(cell ->  {
                if (this.gameObject != cell) {
                    this.handleCollision(cell.getCollider());
                }
            }
        );
    }
    
    private void handleCollision(CellCollider collider) {
        if (this.ID > collider.ID) {
            double distance = this.position.distance(collider.position);

            if (distance <= this.radius) {
                this.gameObject.onCollision(collider.gameObject);
            }

            if (distance <= collider.radius) {
                collider.gameObject.onCollision(this.gameObject);
            }
        }
    }
    
}
