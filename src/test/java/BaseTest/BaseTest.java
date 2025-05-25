package BaseTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import java.util.concurrent.TimeUnit;
import Utilities.ScreenShotUtils;

public abstract class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();

        // Uncomment for Jenkins/CI runs
        options.addArguments("--headless=new");  // Modern headless mode

        // Common options for stable execution
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");  // Important for Docker/CI environments
        options.addArguments("--disable-gpu");
        options.addArguments("--remote-allow-origins=*");

        // Performance options
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-cache");
        options.addArguments("--disable-application-cache");

        // Avoid detection
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36");
        options.addArguments("--incognito");

        // Initialize driver
        driver = new ChromeDriver(options);

        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
    }

    /**
     * Clean up after each test method
     * Take screenshot on failure before quitting the driver
     */
    @AfterMethod
    public void tearDown(ITestResult result) {
        try {
            // Take screenshot on test failure
            if (result.getStatus() == ITestResult.FAILURE && driver != null) {
                ScreenShotUtils.takeScreenshotOnFailure(driver, result.getName());
            }
        } finally {
            // Always quit the driver
            if (driver != null) {
                driver.quit();
                driver = null;  // Prevent memory leaks
            }
        }
    }

    /**
     * Getter method for WebDriver - useful for listeners and utilities
     */
    public WebDriver getDriver() {
        return driver;
    }
}