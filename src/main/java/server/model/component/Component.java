package server.model.component;

import server.model.GameObject;
import utils.Destroyable;
import utils.Startable;
import utils.Updatable;
import utils.Vector2;

public abstract class Component implements Startable, Updatable, Destroyable {

    private static int countID = 0;

    protected final int ID;

    public GameObject gameObject;

    public Vector2 position;

    public Component() {
        this.ID = countID++;
    }

    public int getID() {
        return ID;
    }
    
    @Override
    public void start() {}

    @Override
    public void update() {}

    @Override
    public void destroy() {}
    
}
