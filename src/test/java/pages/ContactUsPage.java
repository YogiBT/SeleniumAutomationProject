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

/**
 * Page class for the Contact Us page.
 */
public class ContactUsPage extends BasePage{

    public Logger logger = tests.ContactUsFormPageTest.logger;

    public ContactUsPage(WebDriver driver) {

        super(driver, 3);

    }

    // NEED TO FIX THIS
    public boolean validateContactUs() {

        return validateElementExist(By.cssSelector("a[href='/contact_us'][style='color: orange;']"));

    }
    /**
     * Verifies if the Get In Touch section is visible.
     */
    public String isGetInTouchVisble() {

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
        return elementText;
    }
    /**
     * Verifies the contact us page URL.
     */
    public void verifyContactUsPageURL(){
        String currentURL = driver.getCurrentUrl();
        Assert.assertEquals(currentURL, "https://automationexercise.com/contact_us");

        logger.info("Contact Us page URL is correct - " + currentURL);
    }
    /**
     * Enters the name in the contact us form.
     *
     * @param name the name to enter in the contact us form
     */
    public void inputName(String name) {

        typeText(By.cssSelector("input[type='text'][name='name']"), name);
        logger.info("Name entered - {}" ,name);
        Assert.assertEquals(name, "Jon Snow");
    }
    /**
     * Enters the email in the contact us form.
     *
     * @param email the email to enter in the contact us form
     */
    public void inputEmail(String email) {
        typeText(By.cssSelector("input[type='email'][name='email']"), email);
        logger.info("Email entered - {}" ,email);
        Assert.assertEquals(email,"jonsnow@gmail.com");
    }
    /**
     * Enters the subject in the contact us form.
     *
     * @param subject the subject to enter in the contact us form
     */
    public void inputSubject(String subject) {
        typeText(By.cssSelector("input[type='text'][name='subject']"), subject);
        logger.info("Subject entered - {}",subject);
    }
    /**
     * Enters the message in the contact us form.
     *
     * @param message the message to enter in the contact us form
     */
    public void inputMessage(String message) {
        typeText(By.id("message"), message);
        logger.info("Message entered - {}", message);
    }

    /**
     * Enters the details in the contact us form.
     *
     * @param name    the name to enter in the contact us form
     * @param email   the email to enter in the contact us form
     * @param subject the subject to enter in the contact us form
     * @param message the message to enter in the contact us form
     */
    public void enterDetails(String name, String email, String subject, String message) {
        inputName(name);
        inputEmail(email);
        inputSubject(subject);
        inputMessage(message);
    }
    /**
     * Uploads the file.
     *
     * @param filePath the file path to upload
     */
    public void uploadFile(String filePath) {
        WebElement uploadElement = driver.findElement(By.cssSelector("input[type='file'][name='upload_file']"));
        uploadElement.sendKeys(filePath);
        logger.info("File uploaded - " + filePath);
    }
    /**
     * Submits the form.
     */
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

    /**
     * Verifies the success message.
     */
    public String verifySuccessMessage() {
        return driver.findElement(By.cssSelector("div.alert.alert-success")).getText();

    }
    /**
     * Navigates back to the home page.
     */
    public void goBackHome() {
        click(By.cssSelector("a.btn.btn-success[href='/']"));
        //logger.info("Going back to the home page");
        //Assert.assertEquals(driver.getCurrentUrl(), "https://automationexercise.com/");
    }
}
