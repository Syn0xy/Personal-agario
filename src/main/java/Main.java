import javax.swing.SwingUtilities;

import engine.scene.GameScene;
import view.GameView;

public class Main {
    public static int WIDTH = 1200;
    public static int HEIGHT = 600;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GameView(new GameScene(WIDTH, HEIGHT));
            }
        });
    }
}