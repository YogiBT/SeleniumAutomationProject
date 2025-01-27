package pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utils.ScrollUtils;

import java.time.Duration;

public class HomePage extends BasePage{

    Logger logger = tests.ContactUsFormPageTest.logger;
    ScrollUtils scrollUtils = new ScrollUtils(driver);

    public HomePage(WebDriver driver) {

        super(driver, 10);
    }

    public void clickContactUs() {
        click(By.cssSelector("a[href='/contact_us']"));
    }

    public boolean verifyHomePage() {
        return validateElementExist(By.xpath("//a[@href='/'][contains(text(),'Home')][@style='color: orange;']"));
    }
    public void verifyHomePageURL(){


        String currentURL = driver.getCurrentUrl();
        try{
            Assert.assertEquals(currentURL, "https://automationexercise.com/");
            logger.info("Home Page URL is correct - " + currentURL);
            // Verify that the home page is visible successfully
            String expectedTitle = "Automation Exercise";
            String actualTitle = driver.getTitle();
            Assert.assertEquals(actualTitle, expectedTitle, "Home page is not visible successfully");
        }catch (Exception e) {
            logger.error("Home Page URL is incorrect - " + currentURL);
        }

    }

    public void testPageStatus() throws Exception {
        int statusCode = getStatusCode(driver.getCurrentUrl());
        testPageLoadStatus();
        Assert.assertEquals(statusCode, 200);
        }


    public void clickTestCase() {
        click(By.cssSelector("a[href='/test_cases']"));
    }

    public void clickViewProduct() {
        click(By.cssSelector("a[href='/product_details/1'][style='color: brown;']"));
    }

    public void scrollDown() {
        scrollUtils= new ScrollUtils(driver);
        scrollUtils.scrollToBottom();
        // Add assertions or further actions here
    }

    public void scrollUp() {
        scrollUtils= new ScrollUtils(driver);
        scrollUtils.scrollToTop();
        // Add assertions or further actions here
    }

    public boolean isSubscriptionVisible() {
        return driver.findElement(By.xpath("//h2[text()='Subscription']")).isDisplayed();

    }

    public void clickUpArrow() {
        click(By.cssSelector("i.fa.fa-angle-up"));
        //scrollUtils.logScrollPositionAndTopElements();

    }

    public boolean checkScrollLocation(String location) {
        return switch (location) {
            case "top" -> scrollUtils.isAtTop();
            case "bottom" -> scrollUtils.isAtBottom();
            default -> false;
        };
    }

    public boolean isMainTextVisible() {
        return driver.findElement(By.xpath("//h2[text()='Full-Fledged practice website for Automation Engineers']")).isDisplayed();
    }

    public String getFirstH2Text() {

        return driver.findElement(By.xpath("//h2")).getText();
    }

    public boolean isTopImageVisible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("img[src='/static/images/home/logo.png']")));
        //logger.info("image is visible");

        return driver.findElement(By.cssSelector("img[src='/static/images/home/logo.png']")).isDisplayed();
    }

    public void waitForCopyrightTextToBeVisible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("p.pull-left")));
    }
}
