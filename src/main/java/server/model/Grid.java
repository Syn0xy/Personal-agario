package server.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import server.model.gameObject.Cell;
import utils.Updatable;
import utils.Vector2;

public class Grid implements Updatable {

    public static final int CELL_SIZE = 10;

    private List<Cell> cells;

    private Map<Cell, List<int[]>> positions;

    private Map<Integer, Map<Integer, List<Cell>>> grid;

    private int size;

    public Grid() {
        this.size = CELL_SIZE;
        this.cells = Cell.CELLS;
        this.positions = new ConcurrentHashMap<>();
        this.grid = new ConcurrentHashMap<>();
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = Math.max(size, 1);
    }
    
    public Map<Cell, List<int[]>> getPositions() {
        return positions;
    }

    private int[] toGridPosition(Vector2 vec2) {
        return vec2.divideCopy(this.size).toInt();
    }

    public List<Cell> getCells(GameObject gameObject) {
        List<Cell> cells = new ArrayList<>();
        
        this.getPositions(gameObject).forEach(position -> {
            cells.addAll(this.getCells(position));
        });

        return cells;
    }

    private List<int[]> getPositions(GameObject gameObject) {
        return this.positions
            .getOrDefault(gameObject, new ArrayList<>());
    }
    
    public List<Cell> getCells(Vector2 position) {
        return this.getCells(this.toGridPosition(position));
    }

    public List<Cell> getCells(int[] cellPosition) {
        return this.grid.
            getOrDefault(cellPosition[0], new ConcurrentHashMap<>()).
            getOrDefault(cellPosition[1], new ArrayList<>());
    }

    @Override
    public void update() {
        positions.clear();
        grid.clear();
        cells.forEach(this::handleCell);
    }

    private void handleCell(Cell cell) {
        int[] rPos = this.toGridPosition(cell.position);
        
        this.grid
            .computeIfAbsent(rPos[0], k -> new HashMap<>())
            .computeIfAbsent(rPos[1], k -> new ArrayList<>())
            .add(cell);
            
        this.positions
                .computeIfAbsent(cell, k -> new ArrayList<>())
                .add(rPos);
    }
    
}
