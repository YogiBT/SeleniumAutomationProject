package actions;


import org.openqa.selenium.WebDriver;
import pages.ContactUsPage;
import pages.HomePage;
import pages.TestCasePage;

import static utils.utilsMethods.sleep;

public class Action {
    HomePage homePage;
    ContactUsPage contactUsPage;
    TestCasePage testCasePage;

    /**
        * Constructor for the Action class.
     * @param driver the WebDriver instance
    */
    public Action(WebDriver driver) {
        homePage = new HomePage(driver);
        contactUsPage = new ContactUsPage(driver);
        testCasePage = new TestCasePage(driver);
    }

    /**
        * Tests the home page arrival.
        * @return true if the home page is reached, false otherwise
        * @throws Exception if the page load status is not successful
     */
    public boolean ariveAtHomePage() throws Exception {
        homePage.testPageLoadStatus();
        //homePage.testPageStatus();
        return homePage.verifyHomePageURL();
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
        contactUsPage.verifyContactUsPageURL();
        contactUsPage.isGetInTouchVisble();
        contactUsPage.enterDetails(name,email,subject,message);
        contactUsPage.uploadFile(file);
        sleep(2);
        contactUsPage.submit();
        contactUsPage.verifySuccessMessage();
        sleep(2);
        contactUsPage.goBackHome();
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
}
