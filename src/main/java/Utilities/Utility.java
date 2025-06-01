package Utilities;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;

public class Utility {

    // Default timeouts and retry settings
    private static final int DEFAULT_TIMEOUT = 10;
    private static final int DEFAULT_MAX_RETRIES = 15;

    public static void clickingOnElement(WebDriver driver, By locator) {
        final int maxRetries = DEFAULT_MAX_RETRIES;

        for (int attempt = 1; attempt <= maxRetries; attempt++) {
            try {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
                WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));

                scrollToElement(driver, locator);
                element.click();
                return; // Success
            } catch (StaleElementReferenceException | ElementClickInterceptedException e) {
                if (attempt == maxRetries) {
                    throw e;
                }

                try {
                    Thread.sleep(10000); // Wait before next retry
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt(); // Restore interrupted status
                    LogsUtils.error("Retry sleep interrupted.");
                    throw new RuntimeException("Retry sleep interrupted", ie);
                }

            } catch (TimeoutException e) {
                LogsUtils.error("Timeout waiting for element to be clickable: " + locator);
                throw e;
            } catch (Exception e) {
                LogsUtils.error("Unexpected error clicking element: " + e.getMessage());
                throw e;
            }
        }
    }


    public static WebElement findWebElement(WebDriver driver, By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return element;
        } catch (TimeoutException e) {
            throw e;
        } catch (Exception e) {
            LogsUtils.error("Error finding element: " + locator + " - " + e.getMessage());
            throw e;
        }
    }

    public static void sendData(WebDriver driver, By locator, String text, boolean clearFirst) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));

            if (clearFirst) {
                element.clear();
            }

            element.sendKeys(text);
        } catch (TimeoutException e) {
            throw e;
        } catch (Exception e) {
            LogsUtils.error("Failed to type text into element: " + locator + " - " + e.getMessage());
            throw e;
        }
    }

    private static void scrollToElement(WebDriver driver, By locator) {
        try {
            WebElement element = driver.findElement(locator);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
            // Small pause to allow smooth scrolling
            Thread.sleep(300);
        } catch (Exception e) {
            LogsUtils.error("Failed to scroll to element: " + locator + " - " + e.getMessage());
        }
    }
    public static void assertThat(WebDriver driver, By locator, String expectedText) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));

            boolean isTextMatched = wait.until(ExpectedConditions.textToBe(locator, expectedText));

            if (!isTextMatched) {
                throw new TimeoutException("Text did not match: Expected [" + expectedText + "]");
            }

            driver.findElement(locator);
        } catch (TimeoutException e) {
            LogsUtils.error("Timeout waiting for text [" + expectedText + "] on element: " + locator);
            throw e;
        } catch (Exception e) {
            LogsUtils.error("Error waiting for text on element: " + locator + " - " + e.getMessage());
            throw e;
        }
    }

    public static String getText(WebDriver driver, By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElement(locator).getText();

    }

    public static WebDriverWait generalWait(WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public static void scrolling(WebDriver driver, By locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", findWebElement(driver, locator));
    }

    public static void selectingFromDropDown(WebDriver driver, By locator, String option) {
        new Select(findWebElement(driver, locator)).selectByVisibleText(option);
    }

    public static String getTimeStamp() {
        return new SimpleDateFormat("yyyy-MM-dd-hh-mm-ssa").format(new Date());
    }

    public static int generateRandomNumber(int upperBound) { //0 >> upper-1  > 5
        return new Random().nextInt(upperBound) + 1;
    }

    //Set >> unique >> 1,2,3,4,3 > condition
    public static Set<Integer> generateUniqueNumber(int numberOfProductsNeeded, int totalNumberOfProducts) //5 >> 50
    {
        Set<Integer> generatedNumbers = new HashSet<>();
        while (generatedNumbers.size() < numberOfProductsNeeded) { //11111 > 1 2 10 5 7
            int randomNumber = generateRandomNumber(totalNumberOfProducts);
            generatedNumbers.add(randomNumber);
        }
        return generatedNumbers;
    }


    public static String generateRandomNationalID() {
        StringBuilder nationalID = new StringBuilder("2"); // Start with "2"
        Random random = new Random();

        // Generate the remaining 13 digits
        for (int i = 0; i < 13; i++) {
            nationalID.append(random.nextInt(10)); // Appends a random digit (0-9)
        }

        return nationalID.toString();
    }

    public static String generateRandomEmail() {
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"; // Allowed characters for email prefix
        StringBuilder emailPrefix = new StringBuilder();
        Random random = new Random();

        // Generate a random email prefix of length between 8 and 15 characters
        int prefixLength = 8 + random.nextInt(8); // Random length between 8 and 15
        for (int i = 0; i < prefixLength; i++) {
            emailPrefix.append(characters.charAt(random.nextInt(characters.length())));
        }

        // Append the Gmail domain
        emailPrefix.append("@gmail.com");

        return emailPrefix.toString();
    }

    public static boolean VerifyURL(WebDriver driver, String expectedURL) {
        try {
            generalWait(driver).until(ExpectedConditions.urlToBe(expectedURL));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static Set<Cookie> getAllCookies(WebDriver driver) {
        return driver.manage().getCookies();
    }

    public static void restoreSession(WebDriver driver, Set<Cookie> cookies) {
        for (Cookie cookie : cookies)
            driver.manage().addCookie(cookie);
    }

    public static File getLatestFile(String folderPath) {
        File folder = new File(folderPath);
        File[] files = folder.listFiles();
        assert files != null;
        if (files.length == 0)
            return null;
        Arrays.sort(files, Comparator.comparingLong(File::lastModified).reversed());

        return files[0];
    }

    public static void refreshPage(WebDriver driver) {
        driver.navigate().refresh();
    }

    public static void openUrl(WebDriver driver, String url) {
        driver.get(url);
    }

    public static void waitForPageToLoad(WebDriver driver, int timeout) {
        new WebDriverWait(driver, Duration.ofSeconds(timeout)).until(webDriver ->
                ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete")
        );
    }

    public static String generateRandomPhoneNumber(String prefix) {
        // Ensure the prefix has exactly 3 digits
        if (prefix == null || prefix.length() != 3 || !prefix.matches("\\d{3}")) {
            throw new IllegalArgumentException("Prefix must be exactly 3 digits.");
        }

        // Generate the remaining 8 digits randomly
        Random random = new Random();
        StringBuilder phoneNumber = new StringBuilder(prefix);
        for (int i = 0; i < 8; i++) {
            phoneNumber.append(random.nextInt(10)); // Appends a random digit (0-9)
        }

        return phoneNumber.toString();
    }

}