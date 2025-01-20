package pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class TestCasePage extends BasePage {

    Logger logger = tests.ContactUsFormPageTest.logger;

    public TestCasePage(WebDriver driver) {
        super(driver, 10);
    }

    /**
     * Verifies the test case page URL.
     */
    public boolean verifyTestCasePageURL(){
        String currentURL = driver.getCurrentUrl();

        try{
            Assert.assertEquals(currentURL, "https://automationexercise.com/test_cases");
            logger.info("Test Case Page URL is correct - " + currentURL);
            testPageStatus();
            return true;
        }catch (Exception e) {
            logger.error("Test Case Page URL is incorrect - " + currentURL);
            return false;
        }
    }
    /**
     * Verifies the test case page status.
     */
    public void testPageStatus() throws Exception {
        testPageLoadStatus();
    }


}
