package Tests;

import BaseTest.BaseTest;
import Pages.HomePage;
import Pages.CategoryPage;
import Pages.ProductPage;
import Utilities.Utility;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CategoryAndProductTest extends BaseTest {

    private static final Logger log = LoggerFactory.getLogger(CategoryAndProductTest.class);

    @Test
    public void testCategoryToProductFlow() {
        log.info("Step 1: Opening Amazon.eg homepage");
        driver.get("https://www.amazon.eg");
        Utility.waitForPageToLoad(driver, 5000);

        // Step 2: Navigate to product from HomePage
        HomePage homePage = new HomePage(driver);
        log.info("Step 2: Navigating to Flash Card product via category menu.");
        homePage.navigateToFlashCardProduct();

        // Step 3: Validate elements in CategoryPage
        CategoryPage categoryPage = new CategoryPage(driver);
        log.info("Step 3: Validating UI elements and content in the category page.");
        categoryPage.assertCategoryPageUI();

        // Step 4: Validate product details on ProductPage
        ProductPage productPage = new ProductPage(driver);
        log.info("Step 4: Asserting that the correct product is displayed on product page.");
        productPage.assertProductDetails();
    }
}
