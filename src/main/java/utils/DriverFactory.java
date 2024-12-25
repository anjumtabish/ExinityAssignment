package utils;

/*import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;/*

import java.util.concurrent.TimeUnit;

public class DriverFactory {
	

    /*private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    /**
     * Initializes the WebDriver instance based on the browser specified in config.properties.
     */
   /* public static void initializeDriver() {
        String browser = ConfigReader.getProperty("browser").toLowerCase();
        WebDriver driver;

        switch (browser) {
            case "chrome":
                driver = new ChromeDriver();
                break;

            case "firefox":
                driver = new FirefoxDriver();
                break;

            case "edge":
                driver = new EdgeDriver();
                break;

            case "safari":
                driver = new SafariDriver();
                break;

            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        driverThreadLocal.set(driver);
    }

    /**
     * Retrieves the WebDriver instance for the current thread.
     *
     * @return The WebDriver instance
     */
   /* public static WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    /**
     * Quits the WebDriver instance and removes it from ThreadLocal storage.
     */
 /*   public static void quitDriver() {
        WebDriver driver = driverThreadLocal.get();
        if (driver != null) {
            driver.quit();
            driverThreadLocal.remove();
        }
    }
    */
//}
