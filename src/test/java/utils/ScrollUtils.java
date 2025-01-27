package utils;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class ScrollUtils {
    WebDriver driver;

    public ScrollUtils(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Scrolls to the bottom of the page.
     */
    public void scrollToBottom() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    /**
     * Scrolls to the top of the page.
     */
    public void scrollToTop() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, 0);");
    }

    /**
     * Scrolls to the given pixel.
     *
     */
    public boolean isAtTop() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Long scrollPosition = (Long) js.executeScript("return window.pageYOffset;");
        assert scrollPosition != null;

        return scrollPosition <= 1; // Allow a small margin for error
    }

    /**
     * Checks if the page is at the bottom.
     *
     * @return true if the page is at the bottom, false otherwise
     */
    public boolean isAtBottom() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Long scrollPosition = (Long) js.executeScript("return window.pageYOffset + window.innerHeight;");
        Long pageHeight = (Long) js.executeScript("return document.body.scrollHeight;");
        assert scrollPosition != null;
        return scrollPosition.equals(pageHeight);
    }

    public void logScrollPositionAndTopElements() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Long scrollPosition = (Long) js.executeScript("return window.pageYOffset;");
        System.out.println("Scroll Position: " + scrollPosition);

        // Log the outer HTML of the first few elements at the top of the page
        String topElementsHtml = (String) js.executeScript(
                "return document.documentElement.outerHTML.substring(0, 1000);"
        );
        System.out.println("Top Elements HTML: " + topElementsHtml);
    }
}