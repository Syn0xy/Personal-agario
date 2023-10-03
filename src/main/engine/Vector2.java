package main.engine;

public class Vector2 {
    private double x;
    private double y;

    public Vector2(double x, double y){
        this.x = x;
        this.y = y;
    }

    public double getX(){ return x; }
    public double getY(){ return y; }

    public void plusX(double x){ this.x += x; }
    public void plusY(double y){ this.y += y; }

    public String toString(){
        return getClass().getSimpleName() + "[x:" + x + ", y:" + y + "]";
    }
}
