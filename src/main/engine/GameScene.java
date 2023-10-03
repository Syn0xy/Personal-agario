package main.engine;

import java.util.LinkedList;
import java.util.List;

public class GameScene {
    private LinkedList<Sphere> spheres;
    
    private int width;
    private int height;

    public GameScene(int width, int height){
        this.spheres = new LinkedList<>();
        this.width = width;
        this.height = height;
        Sphere.gameScene = this;
    }

    public List<Sphere> getSpheres(){ return spheres; }
    public int getWidth(){ return width; }
    public int getHeight(){ return height; }

    public void add(Sphere sphere){
        spheres.addFirst(sphere);
    }

    public void delete(Sphere sphere){
        Sphere.SPHERES.remove(sphere);
        spheres.remove(sphere);
    }

    public void update(){
        for(Sphere s : spheres){
            s.update();
        }
    }

    public String toString(){
        return getClass().getSimpleName() + "[spheres:" + spheres + ", width:" + width + ", height:" + height + "]";
    }
}
