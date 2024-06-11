package utils;

import java.awt.Color;

public class Colorf {
    
    public static Color random() {
        return new Color((float)Math.random(), (float)Math.random(), (float)Math.random());
    }
    
    public static Color inverse(Color color) {
        return new Color(255 - color.getRed(), 255 - color.getGreen(), 255 - color.getBlue());
    }
    
}
