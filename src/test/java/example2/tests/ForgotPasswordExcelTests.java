package example2.tests;

import all.utils.GenerateDriverAll;
import all.utils.JsonUtils;
import example2.actions.Actions;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class ForgotPasswordExcelTests {

    WebDriver driver;
    Actions actions;

    String URL = JsonUtils.readJsonFromFile("url");
    String BROWSER = JsonUtils.readJsonFromFile("browser");

    /**
     * Sets up the test environment by initializing the WebDriver and Actions.
     * Reads the URL and browser type from the JSON configuration file.
     */
    @BeforeSuite(alwaysRun = true)
    public void setUp() {
        driver = GenerateDriverAll.initDriver(BROWSER, URL);
        actions = new Actions(driver);
    }

    @BeforeMethod
    public void navigateToForgotPasswordPage() {
        driver.navigate().to(URL);
    }

    /**
     * Tests the forgot password functionality.
     */
    @Test(dataProvider = "excelData", dataProviderClass = TestDataProvider.class, description = "testing the forgot password positive functionality, using excel data", groups = {"smoke", "regression", "forgotPassword"})
    public void forgotPassword(String email) {
        assert actions.doForgotPasswordFromExcel(email);
    }

    /**
     * Cleans up the test environment by quitting the WebDriver.
     */
    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        GenerateDriverAll.cleanDriver(driver);
    }
}