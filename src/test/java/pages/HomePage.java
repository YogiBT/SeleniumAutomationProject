package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage{


    public HomePage(WebDriver driver) {
        super(driver, 10);
    }

    public void clickSubmit() {
        click(By.id("submit"));
    }

    public boolean validateHome() {
        int find = validateElementExists(By.cssSelector(".logo > a:nth-child(1) > img:nth-child(1)"));

        return find > 0;
    }




}
