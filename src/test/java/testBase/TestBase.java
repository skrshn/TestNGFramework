package testBase;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import utils.*;

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
        initializePageObjects();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (BrowserFactory.getDriver() != null) {
            BrowserFactory.closeBrowser();
        }
    }
}
