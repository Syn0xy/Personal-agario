package server.model.gameObject;

import server.data.Pseudos;
import server.model.GameObject;
import server.model.component.Rigidbody;
import utils.Mathf;
import utils.Vector2;

public class AdvancedCell extends Cell {

    private static final double INITIAL_CELL_SIZE = 10;
    
    private static final double MAX_SPEED = 2;

    private static final double ALPHA_SPEED = 0.4;

    private String pseudo;

    private transient Rigidbody rigidbody;
    
    public AdvancedCell(Vector2 position) {
        super(
            position,
            INITIAL_CELL_SIZE
        );
        this.pseudo = Pseudos.getRandomPseudo();
        this.rigidbody = new Rigidbody();
        this.addComponent(this.rigidbody);
    }

    public String getPseudo() {
        return pseudo;
    }
    
    @Override
    protected boolean canBeEatBy(Cell cell) {
        return cell.superior(this);
    }

    @Override
    public void onCollision(GameObject gameObject) {
        if (gameObject instanceof Cell) {
            Cell cell = (Cell)gameObject;
            
            if (cell.canBeEatBy(this)) {
                this.eat(cell);
            }
        }
    }

    protected void addForce(Vector2 force) {
        this.rigidbody.addForce(force, this.calculateSpeed());
        this.handleInvisibleWalls();
    }

    private double calculateSpeed() {
        return MAX_SPEED * Math.pow(INITIAL_CELL_SIZE / this.size, ALPHA_SPEED);
    }
    
    private void handleInvisibleWalls() {
        this.position.x = Mathf.clamp(this.position.x, 0, gameScene.getWidth());
        this.position.y = Mathf.clamp(this.position.y, 0, gameScene.getHeight());
    }

    protected void eat(Cell cell) {
        // air d'un cercle: pi * r * r
        // air = pi * rayon * rayon;
        // sqrt(air / pi);
        
        this.size += cell.size;
        this.refreshRadius();
        this.collider.setRadius(this.radius);
        cell.destroy();
    }
    
}
