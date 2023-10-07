package engine.scene;

import java.util.ArrayList;
import java.util.List;

import engine.game.Cell;

public class GameScene {
    private List<Cell> cells;
    
    private int width;
    private int height;

    public GameScene(int width, int height){
        this.cells = new ArrayList<>();
        this.width = width;
        this.height = height;
        Cell.gameScene = this;
    }

    public List<Cell> getCells(){ return cells; }
    public int getWidth(){ return width; }
    public int getHeight(){ return height; }

    public void add(Cell sphere){
        cells.add(sphere);
    }

    public void addAll(Cell... spheres){
        for(Cell s : spheres){ add(s); }
    }

    public void delete(Cell cell){
        cells.remove(cell);
    }

    public void update(){
        for(Cell s : cells){
            s.update();
        }
    }

    public String toString(){
        return getClass().getSimpleName() + "[cells:" + cells + ", width:" + width + ", height:" + height + "]";
    }
}
