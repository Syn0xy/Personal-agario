package engine.scene;

import java.awt.Color;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.SwingUtilities;

import engine.game.Bot;
import engine.game.Cell;
import engine.game.Food;
import engine.game.Player;
import engine.geometric.Vector2;
import engine.input.Input;
import engine.input.KeyCode;
import engine.util.Time;
import view.util.Subject;

public class GameScene extends Subject {
    public final static int CELL_SPEED = 5;
    public final static int BOT_COUNT = 20;
    public final static int FOOD_COUNT = 1000;

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
        Cell.cells = cells;
        Cell.gameScene = this;
        thread();
    }

    public List<Cell> getCells(){ return cells; }
    public List<Food> getFoods(){ return foods; }
    public Cell getPlayer(){ return player; }
    public int getWidth(){ return width; }
    public int getHeight(){ return height; }
    
    public void thread(){
        SwingUtilities.invokeLater(() -> {
            Thread engineThread = new Thread("Engine"){
                public void run(){
                    while(isAlive() && !isInterrupted()){
                        try{
                            Time.update();
                            Input.update();
                            update();
                        }catch(NullPointerException | ConcurrentModificationException e){
                            System.err.println("Error : " + e.getMessage());
                        }
                    }
                }
            };
            Thread graphicsThread = new Thread("Graphics"){
                public void run(){
                    while(isAlive() && !isInterrupted()){
                        try{
                            notifyObservers();
                        }catch(NullPointerException | ConcurrentModificationException e){
                            System.err.println("Error : " + e.getMessage());
                        }
                    }
                }
            };
            engineThread.start();
            graphicsThread.start();
        });
    }

    public void add(Cell sphere){
        cells.add(sphere);
    }

    public void delete(Cell cell){
        cells.remove(cell);
        if(foods.contains(cell)) foods.remove(cell);
        if(player == cell) player = null;
    }

    public void update(){
        refreshFood();
        cells.stream().forEach((c) -> { c.update(); });
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
        System.out.println("add bot");
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
