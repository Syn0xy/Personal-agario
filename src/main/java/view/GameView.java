package view;

import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseWheelEvent;

import utils.Subject;
import utils.Updatable;
import utils.input.Input;

public abstract class GameView extends View implements Updatable {

    private static final int WIDTH = (int)(SCREEN_WIDTH * (2.0 / 3.0));

    private static final int HEIGHT = (int)(SCREEN_HEIGHT * (2.0 / 3.0));
    
    private static final String TITLE = "Personal Agario";

    protected Canvas canvas;
    
    public GameView(Subject subject, Canvas canvas) {
        this.canvas = canvas;
        subject.attach(this);
        super.init(WIDTH, HEIGHT);
    }

    @Override
    public String getTitle() {
        return TITLE;
    }

    @Override
    public Component getContent() {
        return this.canvas;
    }
    
    @Override
    public KeyAdapter getKeyAdapter() {
        return Input.getInstance();
    }

    @Override
    public MouseAdapter getMouseAdapter() {
        return new MouseAdapter() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                canvas.plusZoom(e.getWheelRotation());
            }
        };
    }

    @Override
    public void update() {
        repaint();
        setTitle(TITLE);
    }
    
}
