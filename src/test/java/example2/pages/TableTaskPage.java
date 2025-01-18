package example2.pages;

import all.pages.BasePage;
import org.openqa.selenium.WebDriver;


public class TableTaskPage extends BasePage {


    public TableTaskPage(WebDriver driver) {
        super(driver, 10);
    }

    public String getChromeCpuValue(){
        return "TEST";
    }



}

