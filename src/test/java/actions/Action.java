package actions;


import org.openqa.selenium.WebDriver;
import pages.HomePage;

public class Action {
    HomePage homePage;

    public Action(WebDriver driver) {
        homePage = new HomePage(driver);
    }

    public boolean ariveAtHomePage() {

        return (homePage.validateHome());
    }
}
