package pages;

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
import java.util.logging.Logger;

public class BasePage {
    WebDriver driver;
    WebDriverWait wait;
    Logger logger;

    public BasePage(WebDriver driver, int timeout) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        this.logger = Logger.getLogger(BasePage.class.getName());
    }

    public void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        WebElement element = driver.findElement(locator);
        element.click();
    }

    public void typeText(By locator, String text) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        WebElement element = driver.findElement(locator);
        element.sendKeys(text);
    }

    public int validateElementExists(By locator) {
        // Wait for the page to load completely
        wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));

        // Find all elements matching the locator
        List<WebElement> elements = driver.findElements(locator);
        return elements.size();
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
