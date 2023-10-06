package engine.scene;

import java.util.ArrayList;
import java.util.List;

import engine.game.Sphere;

public class GameScene {
    private List<Sphere> spheres;
    
    private int width;
    private int height;

    public GameScene(int width, int height){
        this.spheres = new ArrayList<>();
        this.width = width;
        this.height = height;
        Sphere.gameScene = this;
    }

    public List<Sphere> getSpheres(){ return spheres; }
    public int getWidth(){ return width; }
    public int getHeight(){ return height; }

    public void add(Sphere sphere){
        spheres.add(sphere);
    }

    public void addAll(Sphere... spheres){
        for(Sphere s : spheres){ add(s); }
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
