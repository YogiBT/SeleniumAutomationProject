package tests;

import actions.Action;
import all.utils.GenerateDriverAll;
import all.utils.JsonUtils;
import io.qameta.allure.*;
import io.qameta.allure.testng.Tag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import utils.GenerateDriverAllSingleton;

import static utils.utilsMethods.sleep;

@Epic("Verify scroll up using arrow and scroll down - test scenario #25")
public class ScrollUpAndDownTest {

    public static final Logger logger = LogManager.getLogger(ScrollUpAndDownTest.class);
    WebDriver driver;
    Action actions;

    /**
     * Sets up the test environment by initializing the WebDriver and Actions.
     * Reads the URL and browser type from the JSON configuration file.
     */


    @BeforeSuite(alwaysRun = true)
    public void setUp() {
        String URL = JsonUtils.readJsonFromFile("url");
        String BROWSER = JsonUtils.readJsonFromFile("browser");
        //driver = GenerateDriverAll.initDriver(BROWSER, URL);
        driver = GenerateDriverAllSingleton.getDriver(BROWSER, URL);
        //driver.get(URL);
        actions = new Action(driver);
        logger.info("WebDriver setup complete: {}, {}", BROWSER, URL);
    }

    /**
     * Tests the home page arrival.


     */
    @Feature("Scroll up and down")
    @Story("Enter home page")
    @Description("Test to verify the home page Page URL")
    @Tag("scrollUpAndDown")
    @Owner("Yogev Orenshtein")
    @Step("Enter contact us page")
    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 1,description = "testing the home page arrival", groups = {"regression","testScenario25"})
    public void homePage() throws Exception {
        logger.info("Opening the HomePage");
        String url = actions.ariveAtHomePage();
        Assert.assertEquals(url,"https://automationexercise.com/");
        //Assert.assertEquals(actions.getStatusCode_homePage(),200);
        logger.info("HomePage test passed - arrived at HomePage with status code {}", actions.getStatusCode_homePage());
    }
    /**
     * Testing Verify that home page is visible successfully.
     */
    @Story("verify home page")
    @Owner("Yogev Orenshtein")
    @Step("Verify home page")
    @Description("Test to verify the home page is visible")
    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 2, description = "testing the Home page", groups = {"regression","testScenario25"},dependsOnMethods = "homePage")
    public void verifyHomePage() {
        boolean result = actions.verifyHomePage();
        if (result) {
            logger.info("Home page is visible, test passed.");
        } else {
            logger.error("Home page is not visible, test failed.");
        }
        Assert.assertTrue(result, "The Home page is not visible.");

        Assert.assertTrue(actions.checkScrollLocation_HomePage("top"));

    }

    /**
     * Tests the scroll down and verify subsription is visible.
     */
    @Feature("Scroll up and down")
    @Story("Scroll down and verify subsription is visible")
    @Description("Test to verify the subsription is visible")
    @Tag("scrollUpAndDown")
    @Owner("Yogev Orenshtein")
    @Step("scrol down and check visiblity")
    @Severity(SeverityLevel.CRITICAL)
    @Test(dependsOnMethods = "verifyHomePage",priority = 3,description = "testing the scroll down and verify subsription is visible", groups = {"regression","testScenario25"})
    public void verifySubsriptionVisible() {
        actions.scrollDown();

        //Assert.assertTrue(actions.checkScrollLocation_HomePage("bottom"));
        boolean result = actions.verifySubscriptionVisible();
        if (result) {
            logger.info("Subscription is visible, test passed.");
        } else {
            logger.error("Subscription is not visible, test failed.");
        }

        Assert.assertTrue(result, "The Subscription is not visible.");

    }

    /**
     * Tests the scroll up and verify back up.
     */
    @Feature("Scroll up and down")
    @Story("Scroll up and verify back up")
    @Step("scrol up and check visiblity")
    @Description("Test to verify the back up")
    @Owner("Yogev Orenshtein")
    @Tag("scrollUpAndDown")
    @Severity(SeverityLevel.CRITICAL)
    @Test(dependsOnMethods = "verifySubsriptionVisible",priority = 4,description = "testing the scroll up and verify back up", groups = {"regression","testScenario13"})
    public void verifyBackUp(){
        sleep(3);
        actions.clickUpArrow();
        logger.info("top arrow is clicked.");
        Assert.assertTrue(actions.topImageIsVisible());
        logger.info("top image is visible.11");
        Assert.assertTrue(actions.checkScrollLocation_HomePage("top"));
        logger.info("Back to top scroll test, test passed.");
        Assert.assertEquals(actions.getMainText(),"Full-Fledged practice website for Automation Engineers");
        logger.info("Main text is visible, test passed.");
        sleep(4);
    }
    /**
     * Tests the cart details after removing.
     */
    @AfterClass(alwaysRun = true)
    public void goBackToHomePage() {
        driver.get("https://automationexercise.com/");

    }
    /**
     * Cleans up the test environment by quitting the WebDriver.
     */
    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        //driver.get("https://automationexercise.com/");
        //GenerateDriverAll.cleanDriver(driver);
        GenerateDriverAllSingleton.quitDriver();
    }

}
