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

@Epic("Product Quantity in Cart - Test Scenario #13")
public class ProductQuantityinCartTest {

    public static final Logger logger = LogManager.getLogger(ProductQuantityinCartTest.class);
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
    @Feature("Product quantity in cart")
    @Story("Product quantity in cart")
    @Description("Test to verify the home page Page URL")
    @Tag("productQuantity")
    @Owner("Yogev Orenshtein")
    @Step("Enter contact us page")
    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 1,description = "testing the home page arrival", groups = {"regression","testScenario13"})
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
    @Story("Product quantity in cart")
    @Owner("Yogev Orenshtein")
    @Step("Verify home page")
    @Description("Test to verify the home page is visible")
    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 2, description = "testing the Home page", groups = {"testScenario13"},dependsOnMethods = "homePage")
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
     * Tests the product details URL.
     */
    @Story("Product quantity in cart")
    @Description("Test to verify the product details URL")
    @Step("Verify product details URL")
    @Severity(SeverityLevel.CRITICAL)
    @Link("https://automationexercise.com/product_details/1")
    @Test(dependsOnMethods = "verifyHomePage",groups = {"testScenario13"},description = "testing the product details URL")
    public void verifyProductDetailsURL() {
        actions.clickViewProduct();
        Assert.assertEquals(actions.verifyProductDetailsURL(),"https://automationexercise.com/product_details/1");
    }

    /**
     * Tests the product details.
     */
    @Story("Product quantity in cart")
    @Description("Test to verify the product details")
    @Step("Verify product details")
    @Severity(SeverityLevel.NORMAL)
    @Link("https://automationexercise.com/product_details/1")
    @Test(dependsOnMethods = "verifyProductDetailsURL",groups = {"testScenario13"},description = "testing the product details")
    public void verifyProductDetails() {
        Assert.assertTrue(actions.verifyProductDetails());
    }

    /**
     * Tests the initial quantity.
     */
    @Story("Product quantity in cart")
    @Description("Test to verify the initial quantity")
    @Step("Verify initial quantity")
    @Severity(SeverityLevel.NORMAL)
    @Test(dependsOnMethods = "verifyProductDetails",groups = {"testScenario13"},description = "testing the initial quantity")
    public void verifyInitialQuantity() {
        actions.increaseProductQuantity(4);
        Assert.assertEquals(actions.getQuantity(),4);
    }


    /**
     * Tests the view cart.
     */
    @Story("Product quantity in cart")
    @Description("Test to verify the view cart")
    @Step("Verify view cart")
    @Severity(SeverityLevel.NORMAL)
    @Link("https://automationexercise.com/view_cart")
    @Test(dependsOnMethods = "verifyInitialQuantity",groups = {"testScenario13"},description = "testing the view cart")
    public void verifyViewCart() {
        actions.addToCart();
        Assert.assertTrue(actions.verifyViewCart());
    }

    /**
     * Tests the cart details after adding.
     */
    @Story("Product quantity in cart")
    @Description("Test to verify the cart details after adding")
    @Step("Verify cart details after adding")
    @Severity(SeverityLevel.NORMAL)
    @Link("https://automationexercise.com/view_cart")
    @Tag("cartDetails")
    @Test(dependsOnMethods = "verifyViewCart",groups = {"testScenario13"},description = "testing the cart details after adding")
    public void verifyCartDetailsAfterAdding() {

        Assert.assertEquals(actions.getItemName(),"Blue Top");
        Assert.assertEquals(actions.getItemDescription(),"Women > Tops");
        Assert.assertEquals(actions.getCartQuantity(),4);
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
