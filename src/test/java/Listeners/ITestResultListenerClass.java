package Listeners;

import Utilities.LogsUtils;
import Utilities.ScreenShotUtils;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.jetbrains.annotations.NotNull;

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
    public void onTestFailure(ITestResult result) {
        LogsUtils.info("❌ Test failed: " + result.getName());
        // Screenshot capture is handled in BaseTest.tearDown() method
        // No need to duplicate the functionality here
    }

}