package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage{


    public HomePage(WebDriver driver) {
        super(driver, 10);
    }

    public void clickContactUs() {
        click(By.cssSelector(".nav > li:nth-child(8) > a:nth-child(1)"));
    }

    public boolean validateHome() {
        int find = validateElementExists(By.cssSelector(".logo > a:nth-child(1) > img:nth-child(1)"));

        return find > 0;
    }





}
