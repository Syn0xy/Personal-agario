package utils;

import java.util.ArrayList;
import java.util.List;

public class Subject {

    private List<Updatable> observers;

    public Subject() {
        this.observers = new ArrayList<>();
    }

    public void attach(Updatable observer) {
        if (!this.observers.contains(observer)) {
            this.observers.add(observer);
        }
    }

    public void detach(Updatable observer) {
        if (this.observers.contains(observer)) {
            this.observers.remove(observer);
        }
    }

    protected void notifyObserver() {
        this.observers.forEach(Updatable::update);
    }
    
}
