package example2.pages;

import all.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class ForgotPasswordPage extends BasePage {


    public ForgotPasswordPage(WebDriver driver) {
        super(driver, 10);
    }


    public void typeEmail(String email){
        typeText(By.id("email"), email);
    }

    public void clickSubmit(){
        click(By.cssSelector("#forgot_password > button"));
    }

    public boolean validateForgotPassword(){
        return validateElementExist(By.id("confirmation-alert"));
    }

}

