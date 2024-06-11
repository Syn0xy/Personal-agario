package view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;

import javax.swing.JFrame;

public abstract class View extends JFrame {

    protected static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();

    protected static final int SCREEN_WIDTH = SCREEN_SIZE.width;

    protected static final int SCREEN_HEIGHT = SCREEN_SIZE.height;

    protected void init(int width, int height) {
        KeyAdapter keyAdapter = getKeyAdapter();
        MouseAdapter mouseAdapter = getMouseAdapter();
        
        addKeyListener(keyAdapter);
        addMouseListener(mouseAdapter);
        addMouseMotionListener(mouseAdapter);
        addMouseWheelListener(mouseAdapter);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(width, height);
        setLocation(
            (SCREEN_WIDTH - width) / 2,
            (SCREEN_HEIGHT - height) / 2
        );
        setTitle(getTitle());
        add(getContent());
        setVisible(true);
    }
    
    public abstract String getTitle();

    public abstract Component getContent();

    public KeyAdapter getKeyAdapter() {
        return null;
    }

    public MouseAdapter getMouseAdapter() {
        return null;
    }

}
