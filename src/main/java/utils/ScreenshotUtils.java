/*package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class ScreenshotUtils {
    private static WebDriver driver;

    // Initialize WebDriver
    public static void setDriver(WebDriver webDriver) {
        driver = webDriver;
    }

    public static String captureScreenshot(String testName) {
        if (driver == null) {
            throw new IllegalStateException("WebDriver is not initialized. Cannot capture screenshot.");
        }

        String screenshotPath = "screenshots/" + testName + ".png";
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(srcFile, new File(screenshotPath));
            return screenshotPath;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
*/