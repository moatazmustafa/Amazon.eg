package Listeners;

import Utilities.LogsUtils;
import Utilities.ScreenShotUtils;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;

public class ITestResultListenerClass implements ITestListener {

    @Override
    public void onTestStart(@NotNull ITestResult result) {
        LogsUtils.info("✅ Test started: " + result.getName());
    }

    @Override
    public void onTestSuccess(@NotNull ITestResult result) {
        LogsUtils.info("✅ Test passed: " + result.getName());
    }

    @Override
    public void onTestSkipped(@NotNull ITestResult result) {
        LogsUtils.warn("⚠️ Test skipped: " + result.getName());
    }

    @Override
    public void onTestFailure(@NotNull ITestResult result) {
        LogsUtils.error("❌ Test failed: " + result.getName());

        try {
            Object testInstance = result.getInstance();
            Field driverField = testInstance.getClass().getDeclaredField("driver");
            driverField.setAccessible(true);
            WebDriver driver = (WebDriver) driverField.get(testInstance);

            if (driver != null) {
                ScreenShotUtils.takeScreenshotOnFailure(driver, result.getName());
            } else {
                LogsUtils.error("⚠️ WebDriver is null. Skipping screenshot.");
            }
        } catch (Exception e) {
            LogsUtils.error("🚫 Error capturing screenshot: " + e.getMessage());
        }
    }
}