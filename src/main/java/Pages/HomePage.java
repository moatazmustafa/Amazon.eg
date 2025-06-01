package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import Utilities.Utility;

public class HomePage extends BasePage {

    private static final Logger log = LoggerFactory.getLogger(HomePage.class);

    // Locators
    private final By allCategoryButton = By.id("nav-hamburger-menu");
    private final By seeAllButton = By.cssSelector("a.hmenu-item.hmenu-compressed-btn");
    private final By toysGamesBabyMenu = By.cssSelector("a[data-menu-id='13']");
    private final By allToysAndGames = By.cssSelector("div[data-menu-id='13'] a[href*='node=18022503031']");
    private final By flashCardProduct = By.cssSelector("span.a-truncate-cut[aria-hidden='true']");
    private final By accountMenu = By.xpath("//span[contains(text(),'الحساب والقوائم')]");
    private final By accountName = By.id("nav-link-accountList-nav-line-1");
    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void navigateToFlashCardProduct()  {
        log.info("Step 1: Clicking on 'All Categories' menu button.");
        Utility.findWebElement(driver, allCategoryButton);
        Utility.clickingOnElement(driver, allCategoryButton);

        log.info("Step 2: Clicking on 'عرض الكل' (See All) button.");
        Utility.findWebElement(driver, seeAllButton);
        Utility.assertThat(driver, seeAllButton, ("عرض الكل"));
        Utility.clickingOnElement(driver, seeAllButton);

        log.info("Step 3: Clicking on 'الألعاب ومنتجات الأطفال' (Toys & Baby) section.");
        Utility.findWebElement(driver, toysGamesBabyMenu);
        Utility.clickingOnElement(driver, toysGamesBabyMenu);

        log.info("Step 4: Asserting 'القائمة الرئيسية' section title is visible.");
        Utility.assertThat(driver, By.cssSelector("#hmenu-content > div.hmenu.hmenu-visible.hmenu-translateX > a > div"), "القائمة الرئيسية");

        log.info("Step 5: Asserting 'الدمى والألعاب' section is available.");
        Utility.assertThat(driver, By.cssSelector("#hmenu-content > div.hmenu.hmenu-visible.hmenu-translateX > section > ul > section > div"), "الدمى والألعاب");

        log.info("Step 6: Asserting link text 'جميع الدمى والألعاب' is present.");
        Utility.assertThat(driver, allToysAndGames, "جميع الدمى والألعاب");


        log.info("Step 7: Clicking on 'جميع الدمى والألعاب' to navigate to product listing.");
        WebElement menuItem = driver.findElement(By.xpath("//div[@data-menu-id='13']//a[contains(@href, 'node=18022503031')]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", menuItem);
    }

    public void hoverOnAccountMenu() {
        WebElement menuElement = Utility.findWebElement(driver,accountMenu);
        Actions actions = new Actions(driver);
        actions.moveToElement(menuElement).perform();

        By startHereLink = By.linkText("ابدأ من هنا.");
        Utility.clickingOnElement(driver, startHereLink);
        log.info("Clicked on 'ابدأ من هنا.' to start sign-up");
    }
    public void assertAccountName() {
        Utility.findWebElement(driver, accountName);
        Utility.assertThat(driver, accountName, "مرحباً moataz");
    }
}
