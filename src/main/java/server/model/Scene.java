package server.model;

import java.util.Timer;
import java.util.TimerTask;

import utils.Subject;
import utils.Time;
import utils.Updatable;

public abstract class Scene extends Subject implements Updatable {

    public void start(int framesPerSecond) {
        new Timer().schedule(getTask(), 0, 1000 / framesPerSecond);
    }

    private TimerTask getTask() {
        return new TimerTask() {
            @Override
            public void run() {
                Time.update();
                update();
                notifyObserver();
            }
        };
    }
    
}
