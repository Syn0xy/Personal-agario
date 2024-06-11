package server.model.gameObject;

import utils.Mathf;
import utils.Vector2;

public class Food extends Cell {

    private static final double MIN_FOOD_SIZE = 4;

    private static final double MAX_FOOD_SIZE = 8;
    
    public Food(Vector2 position) {
        super(
            position,
            Mathf.random(MIN_FOOD_SIZE, MAX_FOOD_SIZE)
        );
    }

    @Override
    protected boolean canBeEatBy(Cell cell) {
        return true;
    }

}
