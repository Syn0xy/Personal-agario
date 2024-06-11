package server.model;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import server.model.gameObject.AdvancedCell;
import server.model.gameObject.Bot;
import server.model.gameObject.Cell;
import server.model.gameObject.CellSizeComparator;
import server.model.gameObject.Food;
import server.model.gameObject.Player;
import utils.Mathf;
import utils.Updatable;
import utils.Vector2;
import utils.input.Input;
import utils.input.KeyCode;

public class GameScene extends Scene {

    public static int foodCount = 1000;
    
    public static int botCount = 50;
    
    private int width;
    
    private int height;

    private List<GameObject> gameObjects;
    
    public Grid grid;
    
    private Cell focusCell;
    
    public GameScene(int width, int height) {
        this.width = width;
        this.height = height;
        this.gameObjects = new CopyOnWriteArrayList<>();
        this.grid = new Grid();
        GameObject.gameScene = this;
        this.attach(this.grid);
        this.handleInputEvent();
        this.init();
    }

    public int getWidth() {
        return width;
    }
    
    public int getHeight() {
        return height;
    }
    
    public List<GameObject> getGameObjects() {
        return gameObjects;
    }

    public Grid getGrid() {
        return grid;
    }

    public Cell getFocusCell() {
        return focusCell;
    }
    
    private void init() {
        this.generateFoods();
    }

    private Vector2 getRandomPosition() {
        return new Vector2(
            Mathf.random((double)this.width) - 1,
            Mathf.random((double)this.height) - 1
        );
    }

    private void handleInputEvent() {
        Input.onKeyDown(KeyCode.A, () -> foodCount += 500);
        Input.onKeyDown(KeyCode.E, () -> focusCell = generatePlayer());
        Input.onKeyDown(KeyCode.R, () -> botCount += 10);
        Input.onKeyDown(KeyCode.T, () -> {
            this.gameObjects.forEach(gameObject -> {
                if (gameObject instanceof Bot) {
                    gameObject.destroy();
                }
            });
        });
        Input.onKeyDown(KeyCode.UP, () -> this.grid.setSize(this.grid.getSize() + 5));
        Input.onKeyDown(KeyCode.DOWN, () -> this.grid.setSize(this.grid.getSize() - 5));
    }

    private int countFoods() {
        int nb = 0;
        for (GameObject gameObject : gameObjects) {
            if (gameObject instanceof Food) {
                nb++;
            }
        }
        return nb;
    }

    private int countBots() {
        int nb = 0;
        for (GameObject gameObject : gameObjects) {
            if (gameObject instanceof Bot) {
                nb++;
            }
        }
        return nb;
    }

    private void generateFoods() {
        for (int i = 0; i < foodCount - countFoods(); i++) {
            this.gameObjects.add(new Food(this.getRandomPosition()));
        }
    }

    private void generateBots() {
        for (int i = 0; i < botCount - countBots(); i++) {
            this.gameObjects.add(new Bot(this.getRandomPosition()));
        }
    }

    public Player generatePlayer() {
        Player player = new Player(this.getRandomPosition());
        this.gameObjects.add(player);
        return player;
    }

    public void instantiate(GameObject gameObject) {
        this.gameObjects.add(gameObject);
    }

    public void destroy(GameObject gameObject) {
        this.gameObjects.remove(gameObject);

        if (this.focusCell == gameObject) {
            this.focusCell = null;
        }
    }

    @Override
    public void update() {
        if (this.focusCell == null) {
            this.focusCell = this.getFirstAdvancedCell();
        }
        
        this.gameObjects.forEach(Updatable::update);
        this.generateFoods();
        this.generateBots();
        Cell.CELLS.sort(new CellSizeComparator());
    }

    private AdvancedCell getFirstAdvancedCell() {
        int gameObjectsSize = this.gameObjects.size();
        for (int i = 0; i < gameObjectsSize; i++) {
            GameObject gameObject = this.gameObjects.get(i);
            if (gameObject instanceof AdvancedCell) {
                return (AdvancedCell) gameObject;
            }
        }
        return null;
    }
    
}
