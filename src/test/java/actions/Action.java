package actions;


import org.openqa.selenium.WebDriver;
import pages.ContactUsPage;
import pages.HomePage;

public class Action {
    HomePage homePage;
    ContactUsPage contactUsPage;

    public Action(WebDriver driver) {
        homePage = new HomePage(driver);
        contactUsPage = new ContactUsPage(driver);
    }

    public boolean ariveAtHomePage() throws Exception {
        homePage.testPageLoadStatus();
        homePage.testPageStatus();
        homePage.verifyHomePageURL();
        return (homePage.validateHome());
    }
    public int getStatusCode_homePage() throws Exception {
        return homePage.getStatusCode(homePage.getCurrentUrl());
    }

    public boolean ariveAtContactUsPage() {
        homePage.clickContactUs();
        return (contactUsPage.validateContactUs());
    }
    public void isGetInTouchVisble() {
        contactUsPage.verifyContactUsPageURL();
        contactUsPage.isGetInTouchVisble();
    }
}
