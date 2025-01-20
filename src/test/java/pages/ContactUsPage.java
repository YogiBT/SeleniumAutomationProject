package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import tests.ContactUsFormPageTest;

import java.time.Duration;
import java.util.List;

public class ContactUsPage extends BasePage{

    public Logger logger = tests.ContactUsFormPageTest.logger;

    public ContactUsPage(WebDriver driver) {

        super(driver, 3);

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
        logger.info("Get In Touch is visible - " + elementText);

    }
    public void verifyContactUsPageURL(){
        String currentURL = driver.getCurrentUrl();
        Assert.assertEquals(currentURL, "https://automationexercise.com/contact_us");
        //Logger logger = tests.ContactUsFormPageTest.logger;
        logger.info("Contact Us page URL is correct - " + currentURL);
    }

    public void inputName(String name) {
        typeText(By.cssSelector("input[type='text'][name='name']"), name);
        logger.info("Name entered - {}" ,name);
        Assert.assertEquals(name, "Jon Snow");
    }

    public void inputEmail(String email) {
        typeText(By.cssSelector("input[type='email'][name='email']"), email);
        logger.info("Email entered - {}" ,email);
        Assert.assertEquals(email,"jonsnow@gmail.com");
    }
    public void inputSubject(String subject) {
        typeText(By.cssSelector("input[type='text'][name='subject']"), subject);
        logger.info("Subject entered - {}",subject);
    }

    public void inputMessage(String message) {
        typeText(By.id("message"), message);
        logger.info("Message entered - {}", message);
    }
    public void enterDetails(String name, String email, String subject, String message) {
        inputName(name);
        inputEmail(email);
        inputSubject(subject);
        inputMessage(message);
    }

    public void uploadFile(String filePath) {
        WebElement uploadElement = driver.findElement(By.cssSelector("input[type='file'][name='upload_file']"));
        uploadElement.sendKeys(filePath);
        logger.info("File uploaded - " + filePath);
    }

    public void submit() {
        click(By.cssSelector("input[type='submit'][data-qa='submit-button']"));
        logger.info("Form submitted");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.alertIsPresent());

        // Switch to the alert and accept it
        Alert alert = driver.switchTo().alert();
        alert.accept();
        logger.info("Alert accepted");
    }
    public void verifySuccessMessage() {
        String successMessage = driver.findElement(By.cssSelector("div.alert.alert-success")).getText();
        Assert.assertEquals(successMessage, "Success! Your details have been submitted successfully.");
        logger.info("Success message displayed - '{}' " , successMessage);
    }

    public void goBackHome() {
        click(By.cssSelector("a.btn.btn-success[href='/']"));
        logger.info("Going back to the home page");
        Assert.assertEquals(driver.getCurrentUrl(), "https://automationexercise.com/");
    }
}
