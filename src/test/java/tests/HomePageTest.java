package tests;

import actions.Action;
import all.utils.GenerateDriverAll;
import all.utils.JsonUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class HomePageTest  {

    private static final Logger logger = LogManager.getLogger(HomePageTest.class);
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
    @Test(description = "testing the home page arrival", groups = {"regression"})
    public void homePage() throws Exception {
        logger.info("Starting the HomePage test");
        Assert.assertTrue(true,"simple string");
        assert actions.ariveAtHomePage();
        Assert.assertEquals(actions.getStatusCode_homePage(),200);
        logger.info("HomePage test passed - arrived at HomePage with status code {}", actions.getStatusCode_homePage());


    }


    @Test(description = "testing the contact us page arrival",dependsOnMethods = "homePage", groups = {"regression"})
    public void ContactUsPage()
    {
        logger.info("Starting the ContactUsPage test");
        Assert.assertTrue(actions.ariveAtContactUsPage(),"ContactUsPage test failed - did not arrive at ContactUsPage");
        logger.info("ContactUsPage test passed - arrived at ContactUsPage");
        actions.isGetInTouchVisble();
        logger.info("Get in touch is visible");

    }

    /**
     * Cleans up the test environment by quitting the WebDriver.
     */
    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        GenerateDriverAll.cleanDriver(driver);
    }

}
