package pages;

import actions.Action;
//import org.apache.log4j.LogManager;
//import org.apache.log4j.Logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.List;
//import java.util.logging.Logger;


public class BasePage {

    private static final Logger logger = LogManager.getLogger(BasePage.class);
    WebDriver driver;
    WebDriverWait wait;
    Action actions;
    //static final Logger logger = LogManager.getLogger(all.pages.BasePage.class);

    /**
     * Constructor to initialize the WebDriver and WebDriverWait.
     *
     * @param driver  the WebDriver instance
     * @param timeout the timeout duration in seconds
     */
    public BasePage(WebDriver driver, int timeout) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));

        // this casuse infinite loop
        //actions = new Action(driver);
        //this.logger = Logger.getLogger(BasePage.class.getName());
    }
    /**
     * Clicks on the element located by the given locator.
     *
     * @param locator the By locator of the element to be clicked
     */
    public void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        WebElement element = driver.findElement(locator);
        element.click();
    }
    /**
     * Types the given text into the element located by the given locator.
     *
     * @param locator the By locator of the element
     * @param text    the text to be typed
     */
    public void typeText(By locator, String text) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        WebElement element = driver.findElement(locator);
        element.sendKeys(text);
    }

    /**
     * Validates if elements exist for the given locator.
     *
     * @param locator the By locator of the elements
     * @return the according elements size
     */
    public boolean validateElementExist(By locator) {
        // Wait for the page to load completely
        wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));

        // Find all elements matching the locator
        List<WebElement> elements = driver.findElements(locator);
        if (elements.isEmpty()) {
            logger.error("No elements found for locator: " + locator);
        } else {
            logger.debug("Elements found for locator: "  + locator);
        }
        return !elements.isEmpty();
    }


    public int validateElementExists(By locator) {
        // Wait for the page to load completely
        wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));

        // Find all elements matching the locator
        List<WebElement> elements = driver.findElements(locator);
        return elements.size();
    }

    public List<WebElement> getAllAvailableElements(By locator) {
        // Wait for the page to load completely
        wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));

        // Find all elements matching the locator
        return driver.findElements(locator);
    }

    public int getStatusCode(String urlString) throws Exception {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        return connection.getResponseCode();
    }
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
    public void testPageLoadStatus() {
        try {

            // Use an API request to verify the status code
            int statusCode = getStatusCode(driver.getCurrentUrl());
            //logger.info("Status code for URL " + driver.getCurrentUrl() + ": " + statusCode);


            // Assert that the status code is 200
            Assert.assertEquals(statusCode, 200, "Page did not load successfully!");
        } catch (Exception e) {
            //logger.info("An error occurred while testing the page load status: " + e.getMessage());

            Assert.fail("An error occurred while testing the page load status: " + e.getMessage());
        }
    }
}
