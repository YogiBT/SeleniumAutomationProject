package pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;

public class ProductDetailPage extends BasePage {
    Logger logger = tests.ProductQuantityinCartTest.logger;

    public ProductDetailPage(WebDriver driver) {
        super(driver,10 );
    }


    public boolean verifyProductDetails() {
        logger.info("Verifying that driver is in Product Details page");
        return validateElementExist(By.cssSelector("a[href='/products'][style='color: orange;']"));

    }

    public void increaseProductQuantity(int quantity) {
        logger.info("Increasing product quantity to: " + quantity);
        for (int i = 0; i < quantity-1; i++) {
            typeText(By.id("quantity"), String.valueOf(Keys.ARROW_UP));
        }

    }

    public int getQuantity() {
        logger.info("Getting product quantity");
        return Integer.parseInt(Objects.requireNonNull(getAllAvailableElements(By.id("quantity")).getFirst().getDomProperty("value")));

    }

    public void clickAddToCart() {
        logger.info("Clicking on Add to Cart button");
        click(By.xpath("/html/body/section/div/div/div[2]/div[2]/div[2]/div/span/button"));
    }

    public void clickViewCart() {
        logger.info("Clicking on View Cart button");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("p.text-center"), "Your product has been added to cart."));
        click(By.xpath("//u[contains(text(), 'View Cart')]"));
    }
}
