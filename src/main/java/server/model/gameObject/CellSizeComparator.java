package server.model.gameObject;

import java.util.Comparator;

public class CellSizeComparator implements Comparator<Cell> {

    @Override
    public int compare(Cell a, Cell b) {
        return Double.compare(
            a.size,
            b.size
        );
    }
    
}
