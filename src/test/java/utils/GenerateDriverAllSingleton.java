package utils;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

public class GenerateDriverAllSingleton {

    private static WebDriver driver;

    public static WebDriver getDriver(String browser,String url) {
        if (driver == null) {
            switch (browser.toLowerCase()) {
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    driver = new FirefoxDriver(firefoxOptions);
                    break;
                case "edge":
                    WebDriverManager.edgedriver().setup();
                    EdgeOptions edgeOptions = new EdgeOptions();
                    driver = new EdgeDriver(edgeOptions);
                    break;
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--no-first-run", "--disable-sync", "--incognito");

                    driver = new ChromeDriver(chromeOptions);

                default:
                    WebDriverManager.chromedriver().setup();
                    //ChromeOptions chromeOptions = new ChromeOptions();
                    //chromeOptions.addArguments("--no-first-run", "--disable-sync", "--incognito");
                    //driver = new ChromeDriver(chromeOptions);
                    break;
            }
            driver.manage().window().maximize();

            driver.manage().deleteAllCookies();
            driver.get(url);
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
