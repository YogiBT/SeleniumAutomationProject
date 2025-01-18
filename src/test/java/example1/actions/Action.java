package example1.actions;

import example1.pages.Login;
import org.openqa.selenium.WebDriver;

public class Action {

    Login login;

    public Action(WebDriver driver) {
        login = new Login(driver);
    }

    public boolean performLoginSuccessfully() {
        login.setUsername("student");
        login.setPassword("Password123");
        login.clickSubmit();
        return (login.validateLogin());

    }
}
