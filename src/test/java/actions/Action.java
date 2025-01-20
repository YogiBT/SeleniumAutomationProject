package actions;


import org.openqa.selenium.WebDriver;
import pages.ContactUsPage;
import pages.HomePage;

import static utils.utilsMethods.sleep;

public class Action {
    HomePage homePage;
    ContactUsPage contactUsPage;

    public Action(WebDriver driver) {
        homePage = new HomePage(driver);
        contactUsPage = new ContactUsPage(driver);
    }

    public boolean ariveAtHomePage() throws Exception {
        homePage.testPageLoadStatus();
        //homePage.testPageStatus();
        return homePage.verifyHomePageURL();
    }
    public int getStatusCode_homePage() throws Exception {
        return homePage.getStatusCode(homePage.getCurrentUrl());
    }

    public boolean ariveAtContactUsPage() {
        homePage.clickContactUs();
        return (contactUsPage.validateContactUs());
    }
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
}
