package example2.pages;

import all.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AutomationExercisePage extends BasePage {

    public AutomationExercisePage(WebDriver driver) {
        super(driver, 10);
    }


    public boolean fillSubscription(String email){
        scrollToElement(By.cssSelector("#susbscribe_email"));
        typeText(By.cssSelector("#susbscribe_email"), email);
        return true;
    }
}
