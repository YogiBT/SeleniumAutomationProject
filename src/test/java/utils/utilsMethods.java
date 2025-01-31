package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

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




    public static int countElementsContainingString(WebDriver driver, String searchString) {
        List<WebElement> elements = driver.findElements(By.tagName("a"));
        int count = 0;

        for (WebElement element : elements) {
            if (element.getText().toLowerCase().contains(searchString.toLowerCase())) {
                count++;
            }
        }

        return count;
    }
}


