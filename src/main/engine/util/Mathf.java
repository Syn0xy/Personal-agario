package engine.util;

public class Mathf {
    public static double abs(double a){ return Math.abs(a); }
    public static int abs(int a){ return Math.abs(a); }

    public static double random(){
        return Math.random();
    }

    public static double random(double max){
        return random() * max;
    }

    public static double random(double min, double max){
        return random() * (max - min) + min;
    }

    public static int random(int max){
        return (int)(random() * (max + 1));
    }

    public static int random(int min, int max){
        return (int)(random() * (max - min + 1) + min);
    }

    public static double distance(double a, double b){
        return abs(a - b);
    }
    
    public static double hypotenuse(double a, double b){ return Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2)); }
}
