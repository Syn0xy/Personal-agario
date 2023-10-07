package engine.util;

import java.awt.Color;

public class RandomColor {
    private static Color color(double r, double g, double b){
        return new Color((float)r, (float)g, (float)b, 1);
    }

    public static Color random(){
        return color(Mathf.random(), Mathf.random(), Mathf.random());
    }
}
