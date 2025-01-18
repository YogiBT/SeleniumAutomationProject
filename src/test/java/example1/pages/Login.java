package example1.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class Login extends BasePage {

    public Login(WebDriver driver) {
        super(driver, 10);
    }

    public void setUsername(String username) {
        typeText(By.id("username"), username);
    }

    public void setPassword(String password) {
        typeText(By.id("password"), password);
    }

    public void clickSubmit() {
        click(By.id("submit"));
    }

    public boolean validateLogin() {
        int find = validateElementExists(By.xpath("//h1[contains(text(),'Logged In Successfully')]"));
        return find > 0;
    }

}

