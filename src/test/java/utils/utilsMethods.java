package utils;
/**
    * This class contains utility methods that can be used across the project
    */
public class utilsMethods {
    public static void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
