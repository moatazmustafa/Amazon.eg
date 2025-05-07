
package Listeners;

import Utilities.LogsUtils;
import Utilities.Utility;
import io.qameta.allure.Allure;
import org.jetbrains.annotations.NotNull;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestContext;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class IInvokedMethodListenerClass implements IInvokedMethodListener {

    @Override
    public void afterInvocation(IInvokedMethod method, @NotNull ITestResult result, ITestContext context) {
        String testName = result.getName();

        switch (result.getStatus()) {
            case ITestResult.SUCCESS -> LogsUtils.info("✅ Method passed: " + testName);
            case ITestResult.FAILURE -> LogsUtils.error("❌ Method failed: " + testName);
            case ITestResult.SKIP   -> LogsUtils.warn("⚠️ Method skipped: " + testName);
        }

        // Attach latest log file to Allure
        try {
            File logFile = Utility.getLatestFile(LogsUtils.LOGS_PATH);
            if (logFile != null) {
                Allure.addAttachment("Execution Log", Files.readString(Path.of(logFile.getPath())));
            } else {
                LogsUtils.warn("⚠️ No log file found to attach.");
            }
        } catch (IOException e) {
            LogsUtils.error("🚫 Failed to attach log file to Allure: " + e.getMessage());
        }
    }
}
