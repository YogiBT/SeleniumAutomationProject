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
import utils.readFromExcel;

import static utils.utilsMethods.sleep;

public class ContactUsFormPageTest {

    public static final Logger logger = LogManager.getLogger(ContactUsFormPageTest.class);
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
        logger.info("Opening the HomePage");
        assert actions.ariveAtHomePage();
        //Assert.assertEquals(actions.getStatusCode_homePage(),200);
        logger.info("HomePage test passed - arrived at HomePage with status code {}", actions.getStatusCode_homePage());


    }


    @Test(dataProvider = "excelData",dataProviderClass = readFromExcel.class,description = "testing the contact us page arrival",dependsOnMethods = "homePage", groups = {"regression"})
    public void ContactUsPage(String name,String email,String subject,String message,String file) throws InterruptedException {

        logger.info("Starting the ContactUsPage test");
        Assert.assertTrue(actions.ariveAtContactUsPage(),"ContactUsPage test failed - did not arrive at ContactUsPage");
        logger.info("ContactUsPage test passed - arrived at ContactUsPage");
        actions.enterDetailsInContactUsPage(name,email,subject,message,file);
        logger.info("All the following details have being entered & submitted");

    }

    /**
     * Cleans up the test environment by quitting the WebDriver.
     */
    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        GenerateDriverAll.cleanDriver(driver);
    }


}
