package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactUsPage extends BasePage{


    public ContactUsPage(WebDriver driver) {
        super(driver, 10);
    }


    public boolean validateContactUs() {
        int find = validateElementExists(By.cssSelector("h2.title:nth-child(2)"));

        return find > 0;
    }




}
