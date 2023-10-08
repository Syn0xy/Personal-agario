package engine.game;

import java.util.Comparator;

public class TargetCellComparator implements Comparator<Cell>{
    private Cell cell;

    public TargetCellComparator(Cell cell){
        this.cell = cell;
    }

    public int compare(Cell a, Cell b){
        // if(bot == c) continue;
        // else if(closest == null) closest = c;
        // else{
        //     double d = bot.distance(c);
        //     if(d < dist && bot.superior(c)){
        //         dist = d;
        //         closest = c;
        //     }
        // }
        double d1 = cell.distance(a);
        double d2 = cell.distance(b);
        return Double.compare(d1, d2);
    }
}