package Utilities;

import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenShotUtils {

    private static final String SCREENSHOT_DIR = "test-outputs/Screenshots/";

    public static void takeScreenshotOnFailure(WebDriver driver, String testName) {
        // Prepare timestamped file name
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String fileName = SCREENSHOT_DIR + testName + "_" + timestamp + ".png";

        try {
            // Take screenshot and save to file
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File(fileName));

            // Log and attach to Allure
            LogsUtils.error("📸 Screenshot saved: " + fileName);
            Allure.addAttachment("Failure Screenshot - " + testName,
                    new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));

        } catch (IOException e) {
            LogsUtils.error("❌ Failed to save screenshot: " + e.getMessage());
        } catch (Exception e) {
            LogsUtils.error("❌ Unexpected error while capturing screenshot: " + e.getMessage());
        }
    }
}