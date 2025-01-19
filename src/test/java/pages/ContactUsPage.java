package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class ContactUsPage extends BasePage{


    public ContactUsPage(WebDriver driver) {
        super(driver, 10);
    }


    public boolean validateContactUs() {
        int find = validateElementExists(By.cssSelector("h2.title:nth-child(2)"));

        return find > 0;
    }

    public void isGetInTouchVisble() {

        List<WebElement> elements = getAllAvailableElements(By.cssSelector("h2.title.text-center"));
        String elementText="";
        for (WebElement element : elements) {
            if (element.isDisplayed()) {
                if (element.getText().equals("GET IN TOUCH")) {
                    elementText = element.getText();
                    break;
                }
            }
        }
        Assert.assertEquals(elementText, "GET IN TOUCH");

    }
    public void verifyContactUsPageURL(){
        String currentURL = driver.getCurrentUrl();
        Assert.assertEquals(currentURL, "https://automationexercise.com/contact_us");
    }

}
