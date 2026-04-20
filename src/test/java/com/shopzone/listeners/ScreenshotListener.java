package com.shopzone.listeners;

import com.shopzone.base.BaseTest;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenshotListener implements ITestListener {

    private static final String SCREENSHOT_DIR = "screenshots";

    @Override
    public void onTestFailure(ITestResult result) {
        Object testInstance = result.getInstance();
        if (testInstance instanceof BaseTest) {
            WebDriver driver = ((BaseTest) testInstance).getDriver();
            if (driver != null) {
                takeScreenshot(driver, result.getName());
            }
        }
    }

    private void takeScreenshot(WebDriver driver, String testName) {
        try {
            Path dir = Paths.get(SCREENSHOT_DIR);
            Files.createDirectories(dir);

            String timestamp = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            String fileName = testName + "_" + timestamp + ".png";

            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Files.copy(screenshot.toPath(), dir.resolve(fileName));
        } catch (IOException e) {
            System.err.println("No se pudo guardar el screenshot: " + e.getMessage());
        }
    }
}
