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

@Epic("Search product and verify cart after login - test scenario #20")
public class SearchAndVerifyAfterLoginTest {

    public static final Logger logger = LogManager.getLogger(SearchAndVerifyAfterLoginTest.class);
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
    @Feature("Search product and verify cart after login")
    @Story("Enter home page")
    @Description("Test to verify the home page Page URL")
    @Tag("searchProduct")
    @Owner("Yogev Orenshtein")
    @Step("Enter contact us page")
    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 1,description = "testing the home page arrival", groups = {"regression","testScenario20"})
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
    @Test(priority = 2, description = "testing the Home page", groups = {"testScenario20"},dependsOnMethods = "homePage")
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
     * Tests the products page arrival.
     */
    @Feature("Search product and verify cart after login")
    @Story("Enter products page")
    @Description("Test to verify the products page")
    @Tag("searchProduct")
    @Owner("Yogev Orenshtein")
    @Severity(SeverityLevel.CRITICAL)
    @Step("Enter contact us page")
    @Test(dependsOnMethods = "verifyHomePage",priority = 3,description = "testing the products page arrival", groups = {"regression,testScenario20"})
    public void verifyProductsPage() {
        actions.navigateToProducts();
        Assert.assertTrue(actions.verifyProductsPage());
        logger.info("Products page is visible, test passed.");
        Assert.assertTrue(actions.isAllProductsVisible());
        logger.info("All products text are visible, test passed.");
    }
    /**
     * Tests the products page URL.
     */
    @Feature("Search product and verify cart after login")
    @Story("Enter products page")
    @Description("Test to verify the products page URL")
    @Tag("searchProduct")
    @Owner("Yogev Orenshtein")
    @Severity(SeverityLevel.NORMAL)
    @Step("Enter contact us page 2")
    @Test(dependsOnMethods = "verifyProductsPage",priority = 4,description = "testing the products page URL", groups = {"regression","testScenario20"})
    public void verifyProductsPageURL() {
        Assert.assertEquals(actions.verifyProductsPageURL(), "https://automationexercise.com/products");
        logger.info("Products page URL is correct, test passed.");
    }


    /**
     * Tests the searched products.
     */
    @Feature("Search product and verify cart after login")
    @Story("Search products")
    @Description("Test to verify the searched products")
    @Tag("searchProduct")
    @Owner("Yogev Orenshtein")
    @Severity(SeverityLevel.NORMAL)
    @Step("Enter contact us page URL")
    @Test(dependsOnMethods = "verifyProductsPageURL",priority = 5,description = "testing the searched products", groups = {"regression","testScenario20"})
    public void verifySearchedProducts(){
        actions.searchProduct("women");
        Assert.assertTrue(actions.isSearchedProductsVisible());
        Assert.assertEquals(actions.numOfSearchedProducts(),2);
        logger.info("Searched products are visible, test passed.");

    }

    /**
     * Tests the product in cart.
     */
    @Feature("Search product and verify cart after login")
    @Story("Add product to cart")
    @Description("Test to verify the product in cart")
    @Tag("searchProduct")
    @Owner("Yogev Orenshtein")
    @Severity(SeverityLevel.BLOCKER)
    @Step("verify in cart")
    @Test(dependsOnMethods = "verifySearchedProducts",priority = 6,description = "testing the product in cart", groups = {"regression","testScenario20"})
    public void verifyProductInCart(){
        actions.addProductToCart();

        Assert.assertEquals(actions.isProductInCart("women"),2);
        logger.info("Product is in cart, test passed.");
    }


    /**
     * Tests the product in cart after login.
     */
    @Feature("Search product and verify cart after login")
    @Story("Add product to cart")
    @Description("Test to verify the product in cart after login")
    @Tag("searchProduct")
    @Owner("Yogev Orenshtein")
    @Severity(SeverityLevel.BLOCKER)
    @Step("verify in cart after login")
    @Test(dependsOnMethods = "verifyProductInCart",priority = 7,description = "testing the product in cart after login", groups = {"regression","testScenario20"})
    public void verifyProductAfterLogin() {


        String email = (String) new utils.readFromExcel().getData()[0][1];
        String password =(String) new utils.readFromExcel().getData()[0][6];


        logger.info("Opening the LoginPage");
        Assert.assertEquals(actions.navigateToLoginPage(), "https://automationexercise.com/login");
        logger.info("LoginPage test passed - arrived at LoginPage");

        Assert.assertEquals(actions.isProductInCartAfterLogin("women",email,password),2);
        logger.info("Product is in cart after login, test passed.");

    }
    /**
     * Cleans up the test environment by quitting the WebDriver.
     */
    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        GenerateDriverAll.cleanDriver(driver);
    }


}
