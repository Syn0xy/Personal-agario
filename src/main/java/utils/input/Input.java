package utils.input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public class Input extends KeyAdapter {

    private static Input singleton;

    public static Input getInstance() {
        if (Input.singleton == null) {
            Input.singleton = new Input();
        }
        return Input.singleton;
    }

    private Map<Integer, List<Runnable>> inputDownActions;

    private Map<Integer, List<Runnable>> inputUpActions;

    private Input() {
        this.inputDownActions = new HashMap<>();
        this.inputUpActions = new HashMap<>();
    }
    
    private void addKeyDown(KeyCode keyCode, Runnable action) {
        Input.addKey(inputDownActions, keyCode.getCode(), action);
    }

    private void addKeyUp(KeyCode keyCode, Runnable action) {
        Input.addKey(inputUpActions, keyCode.getCode(), action);
    }

    private static void addKey(Map<Integer, List<Runnable>> inputActions, Integer keyCode, Runnable action) {
        if (!inputActions.containsKey(keyCode)) {
            inputActions.put(keyCode, new CopyOnWriteArrayList<>());
        }
        inputActions.get(keyCode).add(action);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        
        if (this.inputDownActions.containsKey(keyCode)) {
            this.inputDownActions.get(keyCode).forEach(Runnable::run);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        
        if (this.inputUpActions.containsKey(keyCode)) {
            this.inputUpActions.get(keyCode).forEach(Runnable::run);
        }
    }

    public static void onKeyDown(KeyCode keyCode, Runnable action) {
        Input.getInstance().addKeyDown(keyCode, action);
    }

    public static void onKeyUp(KeyCode keyCode, Runnable action) {
        Input.getInstance().addKeyUp(keyCode, action);
    }
    
}
