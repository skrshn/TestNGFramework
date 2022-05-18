package testBase;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.BrowserFactory;
import utils.ConfigReader;
import utils.Constants;
import utils.PageInitializers;

import java.util.concurrent.TimeUnit;

public class TestBase extends PageInitializers {
    @BeforeMethod(alwaysRun = true)
    public void openBrowserAndLaunchApplication() {
        ConfigReader.readProperties(Constants.CONFIGURATION_FILEPATH);

        switch (ConfigReader.getPropertyValue("browser")) {
            case "chrome":
                BrowserFactory.setDriver("chrome");
                break;
            case "firefox":
                BrowserFactory.setDriver("firefox");
                break;
            default:
                throw new RuntimeException("Invalid browser name");
        }
        BrowserFactory.getDriver().get(ConfigReader.getPropertyValue("url"));
        BrowserFactory.getDriver().manage().timeouts().implicitlyWait(Constants.IMPLICIT_WAIT, TimeUnit.SECONDS);
        initializePageObjects();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (BrowserFactory.getDriver() != null) {
            BrowserFactory.closeBrowser();
        }
    }
}
