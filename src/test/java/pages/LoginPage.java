package pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginPage extends BasePage {
    Logger logger = tests.SearchAndVerifyAfterLoginTest.logger;
    public LoginPage(WebDriver driver) {
        super(driver, 10);
    }


    public boolean verifyLoginPage() {
        return validateElementExist(By.cssSelector("a[href='/login'][style='color: orange;']"));
    }
    /**
     * Verifies the login page status.
     */
    public void testPageStatus() throws Exception {
        testPageLoadStatus();
    }

    public void login(String email, String password) {
        typeText(By.cssSelector("input[data-qa='login-email']"), email);
        typeText(By.cssSelector("input[data-qa='login-password']"), password);
        click(By.cssSelector("button[data-qa='login-button']"));
    }
}
