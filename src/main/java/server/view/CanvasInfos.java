package server.view;

public class CanvasInfos {
    
    public static boolean drawGrid = true;

    public static boolean drawGridPositions = false;

    public static void switchDrawGrid() {
        drawGrid = !drawGrid;
    }

    public static void switchDrawGridPositions() {
        drawGridPositions = !drawGridPositions;
    }
    
}
