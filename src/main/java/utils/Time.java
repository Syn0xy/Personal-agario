package utils;

public class Time {

    private static double deltaTime;

    private static double lastDeltaTime = Time.currentTime();

    public static double getDeltaTime() {
        return deltaTime;
    }

    public static void update() {
        double crntTimeMillis = Time.currentTime();
        Time.deltaTime = crntTimeMillis - Time.lastDeltaTime;
        Time.lastDeltaTime = crntTimeMillis;
    }

    private static double currentTime() {
        return System.currentTimeMillis() / 1000.0;
    }
    
}
