package Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BasePage {
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void click(By locator) {
        Utility.clickingOnElement(driver, locator);
    }

    public String getText(By locator) {
        return Utility.getText(driver, locator);
    }

    public void selectFromDropdown(By locator, String option) {
        Utility.selectingFromDropDown(driver, locator, option);
    }
}
