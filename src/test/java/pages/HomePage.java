package pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class HomePage extends BasePage{

    Logger logger = tests.ContactUsFormPageTest.logger;

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

}
