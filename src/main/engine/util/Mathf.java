package main.engine.util;

import main.engine.Vector2;

public class Mathf {
    public static double random(){
        return Math.random();
    }

    public static double random(double max){
        return random() * (max + 1);
    }

    public static int random(int max){
        return (int)(random() * (max + 1));
    }

    public static double distance(double a, double b){
        return Math.abs(a - b);
    }

    public static double distance(Vector2 a, Vector2 b){
        return Math.sqrt(Math.pow(distance(a.getX(), b.getX()), 2) + Math.pow(distance(a.getY(), b.getY()), 2));
    }
}
