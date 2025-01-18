package example2.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import all.utils.GenerateDriverAll;
import all.utils.JsonUtils;
import example2.actions.Actions;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class SubscriptionTest {

    private static final Logger logger = LogManager.getLogger(SubscriptionTest.class);
    WebDriver driver;
    Actions actions;

    /**
     * Sets up the test environment by initializing the WebDriver and Actions.
     * Reads the URL and browser type from the JSON configuration file.
     */
    @BeforeSuite(description = "setting up the test environment", alwaysRun = true)
    public void setUp() {
        String URL_FOR_SUBSCRIPTION = JsonUtils.readJsonFromFile("url_for_subscription");
        String BROWSER = JsonUtils.readJsonFromFile("browser");
        logger.info("WebDriver setup complete: {}, {}", BROWSER, URL_FOR_SUBSCRIPTION);
        driver = GenerateDriverAll.initDriver(BROWSER, URL_FOR_SUBSCRIPTION);
        actions = new Actions(driver);
    }

    /**
     * Testing the registration form positive scenario.
     */
    @Test(description = "testing the registration form positive", groups = {"smoke", "regression", "registration"})
    public void registrationFormPositiveTest() {
        boolean result = actions.doSubscription("test@test.com");
        if (result) {
            logger.info("Registration form positive test passed.");
        } else {
            logger.error("Registration form positive test failed.");
        }
        Assert.assertTrue(result, "The registration form positive test did not pass.");
    }

    /**
     * Cleans up the test environment by quitting the WebDriver.
     */
    @AfterSuite(description = "cleaning up the test environment", alwaysRun = true)
    public void tearDown() {
        GenerateDriverAll.cleanDriver(driver);
        logger.info("Registration form positive test ended.");
    }
}