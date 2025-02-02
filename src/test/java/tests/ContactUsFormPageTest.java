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
import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.GenerateDriverAllSingleton;
import utils.ScreenshotUtil;
import utils.readFromExcel;


@Epic("Contact Us Form Page - Test Scenario #6")
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
        //driver = GenerateDriverAll.initDriver(BROWSER, URL);
        driver = GenerateDriverAllSingleton.getDriver(BROWSER, URL);
        //driver.get(URL);
        actions = new Action(driver);
        logger.info("WebDriver setup complete: {}, {}", BROWSER, URL);
    }

    /**
     * Reset to home page.
     */
    @BeforeClass(alwaysRun = true)
    public void goBackToHomePage() {
        driver.get("https://automationexercise.com/");

    }
    /**
     * Tests the home page arrival.
     */
    @Feature("Contact Us Form Page flow")
    @Story("Contact Us Form Page")
    @Description("Test to verify the home page Page URL")
    @Link("https://automationexercise.com/")
    @Tag("contactUs")
    @Owner("Yogev Orenshtein")
    @Step("Enter contact us page")
    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 1,description = "testing the home page arrival", groups = {"regression","testCase6"})
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
    @Feature("Contact Us Form Page flow")
    @Story("Contact Us Form Page")
    @Description("Test to verify the home page Page")
    @Link("https://automationexercise.com/")
    @Tag("contactUs")
    @Owner("Yogev Orenshtein")
    @Step("Enter contact us page")
    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 2, description = "testing the Home page", groups = {"testCase6"},dependsOnMethods = "homePage")
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
    @Feature("Contact Us Form Page flow")
    @Story("Contact Us Form Page")
    @Description("Test to verify arrival at Contact Us Form Page")
    @Link("https://automationexercise.com/contact_us")
    @Tag("contactUs")
    @Owner("Yogev Orenshtein")
    @Step("Enter contact us page")
    @Severity(SeverityLevel.CRITICAL)
    @Test(dependsOnMethods = "verifyHomePage", groups = {"testCase6","regression"},description = "testing the arrival to test case page",priority = 3)
    public void ArriveAtContactUsPage() {
        logger.info("Starting the ContactUsPage test");
        Assert.assertTrue(actions.ariveAtContactUsPage(),"ContactUsPage test failed - did not arrive at ContactUsPage");

        Assert.assertEquals(actions.verifyContactPageURL(),"https://automationexercise.com/contact_us");

        logger.info("ContactUsPage test passed - arrived at ContactUsPage");
    }


    /**
     * Tests the arrival to test case page.
     */
    @Feature("Contact Us Form Page flow")
    @Story("Contact Us Form Page")
    @Description("Test to verify arrival at Contact Us Form Page")
    @Link("https://automationexercise.com/contact_us")
    @Tag("contactUs")
    @Owner("Yogev Orenshtein")
    @Step("Verify Get In Touch Visible")
    @Severity(SeverityLevel.NORMAL)
    @Test(dependsOnMethods = "ArriveAtContactUsPage", groups = {"testCase6","regression"},description = "testing the GET IN TOUCH visible",priority = 4)
    public void verifyGetInTouchVisible() {
        logger.info("Starting the GET IN TOUCH visible test");
        Assert.assertEquals(actions.getInTouchVisible(),"GET IN TOUCH");
        logger.info("Get in touch is visible");
    }



    /**
     * Tests the contact us page arrival.
     * @param name the name to enter in the contact us form
     * @param email the email to enter in the contact us form
     * @param subject the subject to enter in the contact us form
     * @param message the message to enter in the contact us form
     * @param file the file to upload in the contact us form
     */
    @Feature("Contact Us Form Page flow")
    @Story("Contact Us Form Page")
    @Description("Test enter details in the contact us page and verify success message")
    @Link("https://automationexercise.com/contact_us")
    @Tag("contactUs")
    @Owner("Yogev Orenshtein")
    @Step("Enter contact us page and verify success message")
    @Severity(SeverityLevel.NORMAL)
    @Test(dependsOnMethods = "verifyGetInTouchVisible",dataProvider = "excelData",dataProviderClass = readFromExcel.class,description = "testing the contact us page arrival", groups = {"testCase6"},priority = 5)
    public void verifySuccessMessage(String name,String email,String subject,String message,String file,String userName,String password) throws InterruptedException {
        logger.info("Stating to fill details in the contact us page");
        actions.enterDetailsInContactUsPage(name,email,subject,message,file);
        logger.info("Starting the verify success message test");
        Assert.assertEquals(actions.verifySuccessMessage(),"Success! Your details have been submitted successfully.");

        logger.info("Success message is visible");
    }


    /**
     * Tests the contact us page arrival.
     */
    @Feature("Contact Us Form Page flow")
    @Story("Contact Us Form Page")
    @Description("Test going back home after success")
    @Link("https://automationexercise.com/contact_us")
    @Tag("contactUs")
    @Owner("Yogev Orenshtein")
    @Step("Go back home after success")
    @Severity(SeverityLevel.NORMAL)
    @Test(dependsOnMethods = "verifySuccessMessage",description = "testing the go back home after success", groups = {"testCase6"},priority = 6)
    public void goBackHome() {
        logger.info("Starting the go back home test");
        actions.goBackHome();
        Assert.assertTrue(actions.verifyHomePage());
        logger.info("Arrived back at the home page");
    }

    /**
     * Take a screenshot of the test.
     */
    @AfterMethod(alwaysRun = true)
    public void takeScreenshot(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        String testPriority = result.getMethod().getPriority() + "";
        String currentDate = java.time.LocalDate.now().toString();
        String currentTime = java.time.LocalTime.now().toString();
        String BROWSER = JsonUtils.readJsonFromFile("browser");

        ScreenshotUtil.takeScreenshot(driver, "screenshots/test_case_6/"+BROWSER +"/"+currentDate+"/" + testPriority + "_" + testName +"_" + currentTime + ".png");

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
