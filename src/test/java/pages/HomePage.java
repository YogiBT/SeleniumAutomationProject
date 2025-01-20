package pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class HomePage extends BasePage{


    public HomePage(WebDriver driver) {
        super(driver, 10);
    }

    public void clickContactUs() {
        click(By.cssSelector(".nav > li:nth-child(8) > a:nth-child(1)"));
    }

    public boolean validateHome() {
        int find = validateElementExists(By.cssSelector(".logo > a:nth-child(1) > img:nth-child(1)"));

        return find > 0;
    }
    public boolean verifyHomePageURL(){
        String currentURL = driver.getCurrentUrl();
        Logger logger = tests.ContactUsFormPageTest.logger;
        try{
            Assert.assertEquals(currentURL, "https://automationexercise.com/");
            logger.info("Home Page URL is correct - " + currentURL);
            return true;
        }catch (Exception e) {
            logger.error("Home Page URL is incorrect - " + currentURL);
            return false;
        }

    }

    public void testPageStatus() throws Exception {
        int statusCode = getStatusCode(driver.getCurrentUrl());
        testPageLoadStatus();
        Assert.assertEquals(statusCode, 200);
        }





}
