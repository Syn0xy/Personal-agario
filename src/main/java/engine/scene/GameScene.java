package engine.scene;

import java.awt.Color;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import engine.game.Bot;
import engine.game.Cell;
import engine.game.Food;
import engine.game.Player;
import engine.geometric.Vector2;
import engine.input.Input;
import engine.input.KeyCode;

import static application.Main.BOT_COUNT;
import static application.Main.FOOD_COUNT;

public class GameScene {
    private List<Cell> cells;
    private List<Food> foods;

    private Cell player;
    
    private int width;
    private int height;

    public GameScene(int width, int height){
        this.cells = new CopyOnWriteArrayList<>();
        this.foods = new CopyOnWriteArrayList<>();
        this.width = width;
        this.height = height;
        Cell.gameScene = this;
        Cell.cells = cells;
    }

    public List<Cell> getCells(){ return cells; }
    public List<Food> getFoods(){ return foods; }
    public Cell getPlayer(){ return player; }
    public int getWidth(){ return width; }
    public int getHeight(){ return height; }

    public void add(Cell sphere){
        cells.add(sphere);
    }

    public void delete(Cell cell){
        cells.remove(cell);
        if(foods.contains(cell)) foods.remove(cell);
    }

    public void update(){
        refreshFood();
        for(Cell s : cells){
            s.update();
        }
        if(Input.getKeyDown(KeyCode.A)) spawnBot();
        if(Input.getKeyDown(KeyCode.Z)) newPlayerInstance();
    }

    public void spawnBot(){
        for(int i = 0; i < BOT_COUNT; i++) newBotInstance();
    }

    public void refreshFood(){
        for(int i = 0; i < FOOD_COUNT - foods.size(); i++) newFoodInstance();
    }

    public void newPlayerInstance(){
        player = new Player(new Vector2(width / 2, height / 2), Color.RED);
        add(player);
    }

    public void newBotInstance(){
        add(new Bot(Vector2.randomRange(width, height)));
    }

    public void newFoodInstance(){
        Food f = new Food(Vector2.randomRange(width, height));
        foods.add(f);
        cells.add(f);
    }

    public String toString(){
        return getClass().getSimpleName() + "[cells:" + cells + ", width:" + width + ", height:" + height + "]";
    }
}
