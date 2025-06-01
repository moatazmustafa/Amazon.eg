package Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CategoryPage extends BasePage {

    private static final Logger log = LoggerFactory.getLogger(CategoryPage.class);

    // Locators
    private final By categoryName = By.cssSelector("div.fst-h1-st.pageBanner h1");
    private final By filterSection = By.cssSelector("#n-title h1.a-text-bold");
    private final By seeAllResults = By.cssSelector("#apb-desktop-browse-search-see-all span.a-text-bold");
    private final By allCategoriesBar = By.cssSelector("#nav-xshop a[href*='node=18022503031']");
    private final By backToTop = By.cssSelector("a#navBackToTop");
    private final By aboutAmazon = By.xpath("//div[@class='navFooterColHead' and text()='اعرف المزيد عنا']");
    private final By aboutAmazonLink = By.cssSelector("a.nav_a[href*='node=22624915031']");
    private final By sellOnAmazon = By.xpath("//a[contains(@href,'sell.amazon.eg') and contains(text(),'البيع على أمازون')]");
    private final By languageButton = By.cssSelector("#icp-touch-link-language");
    private final By countryButton = By.cssSelector("#icp-touch-link-country");
    private final By footerLogo = By.cssSelector(".navFooterLogoLine .nav-logo-base");
    private final By flashCardTile = By.cssSelector("li.a-carousel-card div[data-asin='B09K6NTX9L'] a");
    private final By productOriginalPrice = By.cssSelector("div.acsProductBlockV1__price span.a-price-whole");
    private final By productReviews = By.cssSelector("i.a-icon-star-medium");
    private final By productImage = By.cssSelector("div.acsProductBlockV1__product_image img");

    public CategoryPage(WebDriver driver) {
        super(driver);
    }

    public void assertCategoryPageUI() {
        log.info("Step 1: Asserting main category title is 'دمى وألعاب'");
        Utility.findWebElement(driver, categoryName);
        Utility.assertThat(driver, categoryName, "دمى وألعاب");

        log.info("Step 2: Verifying filter section title exists.");
        Utility.findWebElement(driver, filterSection);

        log.info("Step 3: Checking flash card tile is visible.");
        Utility.findWebElement(driver, flashCardTile);

        log.info("Step 4: Asserting product original price is 82.");
        Utility.findWebElement(driver, productOriginalPrice);
        Utility.assertThat(driver, productOriginalPrice, "82");

        log.info("Step 6: Validating review stars are displayed.");
        Utility.findWebElement(driver, productReviews);

        log.info("Step 7: Confirming product image is visible.");
        Utility.findWebElement(driver, productImage);

        log.info("Step 8: Clicking on 'عرض كل النتائج' and validating it.");
        Utility.assertThat(driver, seeAllResults, "عرض كل النتائج");
        Utility.clickingOnElement(driver, seeAllResults);
        driver.navigate().back();

        log.info("Step 9: Checking footer section: 'اعرف المزيد عنا'");
        Utility.findWebElement(driver, aboutAmazon);
        Utility.assertThat(driver, aboutAmazon, "اعرف المزيد عنا");

        log.info("Step 10: Verifying footer link: 'معلومات عن أمازون'");
        Utility.findWebElement(driver, aboutAmazonLink);
        Utility.assertThat(driver, aboutAmazonLink, "معلومات عن أمازون");

        log.info("Step 11: Verifying 'البيع على أمازون' footer link.");
        Utility.findWebElement(driver, sellOnAmazon);
        Utility.assertThat(driver, sellOnAmazon, "البيع على أمازون");

        log.info("Step 12: Checking language setting is 'اللغة العربية'.");
        Utility.findWebElement(driver, languageButton);
        Utility.assertThat(driver, languageButton, "اللغة العربية");

        log.info("Step 13: Checking country setting is 'مصر'.");
        Utility.findWebElement(driver, countryButton);
        Utility.assertThat(driver, countryButton, "مصر");

        log.info("Step 14: Validating footer logo is present.");
        Utility.findWebElement(driver, footerLogo);

        log.info("Step 15: Clicking 'Back to Top' button and asserting its label.");
        Utility.assertThat(driver, backToTop, "الرجوع للأعلى");
        Utility.clickingOnElement(driver, backToTop);

        log.info("Step 16: Asserting top nav bar has the Toys category link.");
        Utility.findWebElement(driver, allCategoriesBar);

        log.info("Step 17: Clicking on Flash Card product to navigate to product page.");
        Utility.clickingOnElement(driver, flashCardTile);
    }
}
