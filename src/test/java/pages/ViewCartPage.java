package pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Objects;

public class ViewCartPage extends BasePage {
    Logger logger = tests.ProductQuantityinCartTest.logger;

    public ViewCartPage(WebDriver driver) {
        super(driver,10 );
    }

    public boolean verifyViewCart() {
        logger.info("Verifying that driver is in View Cart page");
        return validateElementExist(By.cssSelector("a[href='/view_cart'][style='color: orange;']"));
    }

    public String getItemName() {
        logger.info("Getting item name");
        return Objects.requireNonNull(getAllAvailableElements(By.cssSelector("a[href='/product_details/1']")).getFirst().getText());
    }

    public String getItemDescription() {
        logger.info("Getting item description");
        return Objects.requireNonNull(getAllAvailableElements(By.cssSelector("td.cart_description p")).getFirst().getText());
    }

    public int getCartQuantity() {
        logger.info("Getting cart quantity");
        return Integer.parseInt(Objects.requireNonNull(getAllAvailableElements(By.cssSelector("td.cart_quantity button.disabled")).getFirst().getText()));
    }
}
