package utils;

public class Mathf {

    public static int random(int max) {
        return (int)(Math.random() * (max + 1));
    }

    public static double random(double max) {
        return Math.random() * max;
    }

    public static double random(double min, double max) {
        return Math.random() * (max - min) + min;
    }

    public static <E> E random(E[] array) {
        int randomIndex = (int)(Math.random() * array.length);
        return array[randomIndex];
    }

    public static double clamp(double value, double min, double max) {
        if (value > max) {
            return max;
        } else if (value < min) {
            return min;
        } else {
            return value;
        }
    }
    
}
