package server.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import server.model.component.ColliderListener;
import server.model.component.Component;
import utils.Destroyable;
import utils.Updatable;
import utils.Vector2;

public class GameObject implements Serializable, Updatable, Destroyable, ColliderListener {
    
    public static GameScene gameScene;

    protected Vector2 position;

    private transient List<Component> components;

    public GameObject(Vector2 position) {
        this.position = position;
        this.components = new ArrayList<>();
    }

    public GameObject() {
        this(new Vector2());
    }

    public Vector2 getPosition() {
        return position;
    }

    public void addComponent(Component component) {
        component.gameObject = this;
        component.position = this.position;
        component.start();
        this.components.add(component);
    }

    @SuppressWarnings("unchecked")
    public <T extends Component> T getComponent(Class<? extends T> componentType) {
        int componentsSize = this.components.size();
        
        for (int i = 0; i < componentsSize; i++) {
            Component component = this.components.get(i);
            
            if (component.getClass().isAssignableFrom(componentType)) {
                return (T) component;
            }
        }

        return null;
    }

    @Override
    public void update() {
        this.components.forEach(Updatable::update);
    }

    @Override
    public void destroy() {
        this.components.forEach(Destroyable::destroy);
        gameScene.destroy(this);
    }
    
    @Override
    public void onCollision(GameObject gameObject) {}
    
}
