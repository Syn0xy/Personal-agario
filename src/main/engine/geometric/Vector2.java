package engine.geometric;

public class Vector2 {
    private double x;
    private double y;

    public Vector2(double x, double y){
        this.x = x;
        this.y = y;
    }

    public double getX(){ return x; }
    public double getY(){ return y; }

    public void plus(Vector2 v){ plus(v.getX(), v.getY()); }
    public void plus(double x, double y){ plusX(x); plusY(y); }
    public void plusX(double x){ this.x += x; }
    public void plusY(double y){ this.y += y; }

    public void minus(Vector2 v){ minus(v.getX(), v.getY()); }
    public void minus(double x, double y){ minusX(x); minusY(y); }
    public void minusX(double x){ this.x -= x; }
    public void minusY(double y){ this.y -= y; }

    public Vector2 copy(){ return new Vector2(x, y); }

    public String toString(){
        return getClass().getSimpleName() + "[x:" + x + ", y:" + y + "]";
    }
}