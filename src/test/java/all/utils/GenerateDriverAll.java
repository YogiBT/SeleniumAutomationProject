package all.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;


public class GenerateDriverAll {

    //private static WebDriver driver;

    public static WebDriver initDriver(String browserType, String url) {

        WebDriver driver = switch (browserType.toLowerCase()) {
            case "chrome" -> {
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
//                chromeOptions.addArguments("--headless");
                chromeOptions.addArguments("--no-first-run", "--disable-sync");
                chromeOptions.addArguments("--incognito", "--disable-sync");

                yield new ChromeDriver(chromeOptions);
            }
            case "firefox" -> {
                /*
                WebDriverManager.firefoxdriver().setup();
                yield new FirefoxDriver();
                */

                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.setBinary("/Applications/Firefox.app/Contents/MacOS/firefox"); // Update this path
                yield new FirefoxDriver(firefoxOptions);
            }
            case "edge" -> {
                WebDriverManager.edgedriver().setup();
                EdgeOptions options = new EdgeOptions();
                options.setBinary("/Applications/Microsoft Edge.app/Contents/MacOS/Microsoft Edge");
                options.addArguments("--no-first-run", "--disable-sync");
                //System.setProperty("webdriver.edge.driver", "C:\\bin\\msedgedriver.exe");
                yield new EdgeDriver(options);}
            default -> throw new IllegalArgumentException("Browser \"" + browserType + "\" not supported.");
        };

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.get(url);

        return driver;
    }

    public static void cleanDriver(WebDriver driver){
        driver.quit();
    }
}



