package Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage extends BasePage {

    // Locators
    private By productTitle = By.cssSelector("#productTitle");
    private By productPrice = By.cssSelector("span.a-price-whole");

    // Constructor
    public ProductPage(WebDriver driver) {
        super(driver);
    }

    // Assertion method to verify selected item
    public void assertProductDetails() {
        Utility.findWebElement(driver, productTitle);
        Utility.assertThat(driver, productTitle, "بطاقات فلاش تعليمية سحرية للاطفال من ماكيدرايز، 60 بطاقة عالية التباين باللونين الاسود والابيض في مجموعة من 30 بطاقة");

        Utility.assertThat(driver, productPrice,("82"));
    }
}
