package example1.tests;

import all.utils.GenerateDriverAll;
import example1.actions.Action;
import example1.utils.GenerateDriver;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public class SuiteLoginTests {

    WebDriver driver;
    Action actions;


    @BeforeSuite
    public void setUp()  {
        driver = GenerateDriverAll.initDriver("chrome", "https://practicetestautomation.com/practice-test-login/");
        actions = new Action(driver);
    }


    @Test
    public void testLogin() {
        assert actions.performLoginSuccessfully();
    }



    @AfterSuite
    public void tearDown() {
        GenerateDriver.cleanDriver(driver);
    }

}
