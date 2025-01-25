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
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import utils.readFromExcel;

@Epic("Test Cases Page")
public class TestCasesPageTest {

    public static final Logger logger = LogManager.getLogger(TestCasesPageTest.class);
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
        driver = GenerateDriverAll.initDriver(BROWSER, URL);
        actions = new Action(driver);
        logger.info("WebDriver setup complete: {}, {}", BROWSER, URL);
    }

    /**
     * Tests the home page arrival.
     */

    @Test(priority = 1,description = "testing the home page arrival", groups = {"regression"})
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
    @Test(priority = 2, description = "testing the Home page", groups = {"smoke"},dependsOnMethods = "homePage")
    public void verifyHomePage() {
        boolean result = actions.verifyHomePage();
        if (result) {
            logger.info("Home page is visible, test passed.");
        } else {
            logger.error("Home page is not visible, test failed.");
        }
        Assert.assertTrue(result, "The Home page is not visible.");
    }



    /**
     * Tests the arrival to test case page.
     */
    @Feature("Test Cases Page flow")
    @Story("Test Cases Page")
    @Description("Test to verify the Test cases Page")
    @Link("https://automationexercise.com/test_cases")
    @Tag("contactUs")
    @Owner("Yogev Orenshtein")
    @Step("Enter contact us page")
    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "testing the arrival to test case page", groups = {"testCase7","regression"},dependsOnMethods = "verifyHomePage")
    public void testCasePage() {
        logger.info("Starting TestCasePage test");
        assert actions.ariveAtTestCasePage();
        //Assert.assertEquals(actions.getStatusCode_testCasePage(),200);
        logger.info("TestCasePage test passed - arrived at TestCasePage");
    }
    /**
     * Cleans up the test environment by quitting the WebDriver.
     */
    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        GenerateDriverAll.cleanDriver(driver);
    }


}
