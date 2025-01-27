package actions;


import org.openqa.selenium.WebDriver;
import pages.*;

import static utils.utilsMethods.sleep;

public class Action {
    HomePage homePage;
    ContactUsPage contactUsPage;
    TestCasePage testCasePage;
    ProductDetailPage productDetailPage;
    ViewCartPage viewCartPage;
    WebDriver driver;

    /**
        * Constructor for the Action class.
     * @param driver the WebDriver instance
    */
    public Action(WebDriver driver) {
        this.driver = driver;
        this.homePage = new HomePage(driver);
        this.contactUsPage = new ContactUsPage(driver);
        this.testCasePage = new TestCasePage(driver);
        this.productDetailPage = new ProductDetailPage(driver);
        this.viewCartPage = new ViewCartPage(driver);
    }

    /**
     * Tests the home page arrival.
     *
     * @return true if the home page is reached, false otherwise
     * @throws Exception if the page load status is not successful
     */
    public String ariveAtHomePage() throws Exception {
        homePage.testPageLoadStatus();
        //homePage.testPageStatus();
        //return homePage.verifyHomePageURL();
        return homePage.getCurrentUrl();
    }

    public boolean verifyHomePage() {
        return homePage.verifyHomePage();
    }
    /**
        * Gets the status code of the home page.
        * @return the status code of the home page
        * @throws Exception if the status code is not successful
    */
    public int getStatusCode_homePage() throws Exception {
        return homePage.getStatusCode(homePage.getCurrentUrl());
    }

    /**
        * Tests the contact us page arrival.
        * @return true if the contact us page is reached, false otherwise
    */
    public boolean ariveAtContactUsPage() {
        homePage.clickContactUs();
        return (contactUsPage.validateContactUs());
    }

    public String getInTouchVisible() {
        return contactUsPage.isGetInTouchVisble();
    }
    /**
        * Enters the details in the contact us page.
        * @param name the name to enter in the contact us form
        * @param email the email to enter in the contact us form
        * @param subject the subject to enter in the contact us form
        * @param message the message to enter in the contact us form
        * @param file the file to upload in the contact us form
        * @throws InterruptedException if the thread is interrupted
    */
    public void enterDetailsInContactUsPage(String name, String email,String subject ,String message,String file) throws InterruptedException {

        contactUsPage.enterDetails(name,email,subject,message);
        contactUsPage.uploadFile(file);
        sleep(1);
        contactUsPage.submit();
        sleep(1);
    }

    /**
        * Tests the arrival to the test case page.
        * @return true if the test case page is reached, false otherwise
    */
    public boolean ariveAtTestCasePage() {
        homePage.verifyHomePageURL();
        homePage.clickTestCase();
        return testCasePage.verifyTestCasePageURL();
    }

    public String verifyContactPageURL() {
        return contactUsPage.getCurrentUrl();
    }

    public String verifySuccessMessage() {
        return contactUsPage.verifySuccessMessage();
    }


    public void goBackHome() {
        contactUsPage.goBackHome();
    }

    public void clickViewProduct() {
        homePage.clickViewProduct();

    }

    public String verifyProductDetailsURL() {
        return productDetailPage.getCurrentUrl();
    }

    public boolean verifyProductDetails() {
        return productDetailPage.verifyProductDetails();
    }

    public void increaseProductQuantity(int quantity) {
        productDetailPage.increaseProductQuantity(quantity);
    }

    public int getQuantity() {
        //sleep(5);
        return productDetailPage.getQuantity();

    }

    public void addToCart() {
        productDetailPage.clickAddToCart();
        //sleep(4);
        productDetailPage.clickViewCart();
    }

    public boolean verifyViewCart() {
        return viewCartPage.verifyViewCart();
    }

    public String getItemName() {
        return viewCartPage.getItemName();
    }

    public String getItemDescription() {
        return viewCartPage.getItemDescription();
    }

    public int getCartQuantity() {
        return viewCartPage.getCartQuantity();
    }

    public void scrollDown() {
        homePage.scrollDown();
        homePage.waitForCopyrightTextToBeVisible();
    }

    public boolean verifySubscriptionVisible() {
        //sleep(2);
        return homePage.isSubscriptionVisible();

    }

    public void clickUpArrow() {
        //sleep(4);
        homePage.clickUpArrow();

        //sleep(3);

    }

    public boolean checkScrollLocation_HomePage(String location) {
        return homePage.checkScrollLocation(location);
    }

    public String getMainText() {
        return homePage.getFirstH2Text();
        //return homePage.isMainTextVisible();
    }

    public boolean topImageIsVisible() {
        return homePage.isTopImageVisible();
    }
}
