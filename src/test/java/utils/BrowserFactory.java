package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.bouncycastle.util.io.TeeInputStream;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import testBase.TestBase;

import java.util.concurrent.TimeUnit;

public class BrowserFactory{
    private BrowserFactory() {
    }

    private static BrowserFactory instance = new BrowserFactory();

    public static BrowserFactory getInstance() {
        return instance;
    }

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void setDriver(String browserType) {
        if (browserType.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("headless");
            options.addArguments("--window-size=1440x900");
            WebDriverManager.chromedriver().setup();
            driver.set(new ChromeDriver(options));
            driver.get().manage().window().maximize();
            driver.get().manage().timeouts().implicitlyWait(Constants.IMPLICIT_WAIT, TimeUnit.SECONDS);
        }

    }

    public static void closeBrowser() {
        driver.get().quit();
        driver.remove();
    }
}
