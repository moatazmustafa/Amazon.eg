package Tests;

import Pages.AdminPage;
import Pages.BasePage;
import Pages.SSRegistrationPage;
import Utilities.Utility;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class RegistrationTest extends BasePage{
    private static final Logger log = LoggerFactory.getLogger(RegistrationTest.class);

    WebDriver driver;
    SSRegistrationPage SSRegistrationPage;
    AdminPage adminPage;

    @BeforeTest
    public void prepare() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-extensions");
        options.addArguments("--start-maximized");
        options.addArguments("--disable-cache");
        options.addArguments("--incognito"); // Enable incognito mode

        driver = new ChromeDriver(options);
        driver.manage().deleteAllCookies();

        // Initialize Page Objects
        SSRegistrationPage = new SSRegistrationPage(driver);
        adminPage = new AdminPage(driver);

    }

    @AfterTest
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testRegistrationFlow() throws InterruptedException {
        log.info("Starting Registration Test");
        // Step 1: Navigate to Registration Page
        log.info("Navigating to Registration Page");
        BasePage.openUrl("https://rstore.raneen.com/ss_zayed/smartstore/screen/setup");
        log.info("Setup Registration Screen");
        SSRegistrationPage.selectScreenType("التسجيل");
        SSRegistrationPage.enterIdentifier("motaz registration");
        SSRegistrationPage.enterPassword("mm@123456");
        log.info("Clicking on Submit Button");
        Utility.clickingOnElement(driver, By.xpath("/html/body/div[2]/main/div[4]/div/div[2]/form/div[5]/div/button"));
        log.info("Enter Random Phone Number Starts With (015)");
        String randomPhoneNumber = Utility.generateRandomPhoneNumber("015");
        SSRegistrationPage.enterPhone(randomPhoneNumber);
        log.info("Generate Barcode");
        SSRegistrationPage.generateMyBarcode();

        // Step 2: Open Admin Portal
        log.info("Opening Admin Portal");
        driver.switchTo().newWindow(WindowType.TAB);
        Object[] windowHandles = driver.getWindowHandles().toArray();
        driver.switchTo().window((String) windowHandles[1]);
        driver.get("https://www.raneen.com/admin/admin/");
        log.info("Logging in to Admin Portal");
        adminPage.login("moatazz.mustafa", "mm@123456");
        log.info("Navigating to Ocean OTP Tab");
        adminPage.navigateToOceanTab();
        adminPage.navigateToOtpTab();
        Utility.findWebElement(driver,By.xpath("//tbody/tr/td[3]")); // find otp value
        log.info("Getting OTP");
        String otp = adminPage.getOtp();

        // Step 3: Complete Registration
        log.info("Switch To Registration Screen");
        driver.switchTo().window(driver.getWindowHandles().iterator().next());
        log.info("Enter OTP");
        SSRegistrationPage.enterOtp(otp);
        log.info("Enter First Name");
        SSRegistrationPage.enterFirstName("moataz");
        log.info("Enter Last Name");
        SSRegistrationPage.enterLastName("mustafa");
        log.info("Click On Submit Registration");
        SSRegistrationPage.submitRegistration();

        // Step 4: Verify Registration in Admin Portal
        log.info("Switch To Admin Portal");
        driver.switchTo().window((String) windowHandles[1]);
        Utility.waitForPageToLoad(driver, 10);
        log.info("Navigating To Customers Tab");
        Utility.clickingOnElement(driver, By.xpath("/html/body/div[1]/nav/ul/li[8]/a"));  //customer tab
        log.info("Navigating To All Customers Tab");
        Utility.clickingOnElement(driver, By.xpath("/html/body/div[1]/nav/ul/li[8]/div/ul/li[1]/a"));
        Utility.waitForPageToLoad(driver, 10);
        log.info("Clear Search Field");
        Utility.findWebElement(driver, By.xpath("/html/body/div[2]/main/div[2]/div/div/div[2]/div[2]/div[1]/div[2]/input")).clear();
        log.info("Enter The Random Phone Number");
        Utility.sendData(driver, By.xpath("/html/body/div[2]/main/div[2]/div/div/div[2]/div[2]/div[1]/div[2]/input"), randomPhoneNumber);
        Thread.sleep(15000);
        log.info("Click On Search Button");
        Utility.clickingOnElement(driver, By.xpath("//div[2]/div/div[2]/button")); // Search
        Thread.sleep(5000);

        // Open Customer Details
        Thread.sleep(10000);
        Utility.findWebElement(driver, By.xpath("/html/body/div[2]/main/div[2]/div/div/div[2]/div[4]/table/tbody/tr[2]/td[20]/a")); // click on edit
        log.info("Open Customer Details");
        Utility.clickingOnElement(driver, By.xpath("/html/body/div[2]/main/div[2]/div/div/div[2]/div[4]/table/tbody/tr[2]/td[20]/a")); // Open customer details
        Thread.sleep(3000);
        log.info("Delete Customer");
        Utility.clickingOnElement(driver, By.xpath("//button[3]/span")); // Action
        Thread.sleep(3000);
        log.info("Save");
        Utility.clickingOnElement(driver, By.xpath("//footer/button[2]/span")); // Save or confirm
        log.info("Test Completed Successfully");
    }
}
