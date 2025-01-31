package pages;

//import org.apache.log4j.LogManager;
//import org.apache.log4j.Logger;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.*;

import static utils.utilsMethods.sleep;

public class ProductsPage extends BasePage {
    Logger logger = tests.SearchAndVerifyAfterLoginTest.logger;
    /**
     * Constructor to initialize the WebDriver and WebDriverWait.
     *
     * @param driver  the WebDriver instance
     */
    public ProductsPage(WebDriver driver) {
        super(driver, 10);
    }

    public boolean verifyProductsPage() {
        return validateElementExist(By.cssSelector("a[href='/products'][style='color: orange;']"));
    }

    public boolean isAllProductsVisible() {

        return isTextVisibleInH2Elements("ALL PRODUCTS");

    }

    public void searchProducts(String productName) {
        typeText(By.id("search_product"), productName);
        click(By.id("submit_search"));
        sleep(2);
    }

    public boolean isSearchedProductsVisible() {
        return isTextVisibleInH2Elements("SEARCHED PRODUCTS");
    }

    public int numOfSearchedProducts() {
        return getAllAvailableElements(By.xpath("//a[contains(text(), 'View Product')]")).size();

    }


    public List<WebElement> getNonOverlayElements(By locator) {
        List<WebElement> elements = getAllAvailableElements(locator);
        List<WebElement> nonOverlayElements = new ArrayList<>();

        for (WebElement element : elements) {
            boolean isInOverlay = false;
            WebElement parent = element;
            while (parent != null && !parent.getTagName().equals("html")) {
                try {
                    parent = parent.findElement(By.xpath(".."));
                    if (parent.getAttribute("class").contains("product-overlay")) {
                        isInOverlay = true;
                        break;
                    }
                } catch (NoSuchElementException e) {
                    break;
                }
            }
            if (!isInOverlay) {
                nonOverlayElements.add(element);
            }
        }

        return nonOverlayElements;
    }


    public void addSearchedProductToCart() {
        List<WebElement> buttons = getNonOverlayElements(By.className("add-to-cart"));
        Set<WebElement> clickedButtons = new HashSet<>();
        JavascriptExecutor js = (JavascriptExecutor) driver;

        for (WebElement button : buttons) {
            if (!clickedButtons.contains(button)) {
                js.executeScript("arguments[0].click();", button);
                sleep(2);
                click(By.cssSelector("button.btn.btn-success.close-modal.btn-block[data-dismiss='modal']"));
                logger.info("Product added to cart- " + getAddedItemTextFromButton(button));
                clickedButtons.add(button);
            }
        }
    }

    public String getAddedItemTextFromButton(WebElement button) {


        // Find the parent <div> element
        WebElement parentDiv = button.findElement(By.xpath(".."));

        // Retrieve the text from the <p> element within the parent <div>
        WebElement productDescription = parentDiv.findElement(By.tagName("p"));

        return productDescription.getText();
    }
}
