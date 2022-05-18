package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import testBase.TestBase;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class CommonMethods extends TestBase {

    public static void sendText(WebElement element, String textToSend) {
        element.clear();
        element.sendKeys(textToSend);
    }

    public static String getText(WebElement element) {
        return element.getText();
    }

    public static WebDriverWait getWait() {
        WebDriverWait wait = new WebDriverWait(BrowserFactory.getDriver(), Duration.ofSeconds(Constants.EXPLICIT_WAIT));
        return wait;
    }

    public static void waitForClickability(WebElement element) {
        getWait().until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void click(WebElement element) {
        waitForClickability(element);
        element.click();
    }

    public static JavascriptExecutor getJSExecutor() {
        JavascriptExecutor js = (JavascriptExecutor) BrowserFactory.getDriver();
        return js;
    }

    public static void jsClick(WebElement element) {
        getJSExecutor().executeScript("arguments[0].click;", element);
    }

    public static void selectByVisibleText(String option) {
        Select select = new Select(emAddPage.employeeStatus);
        select.selectByVisibleText(option);
    }

    public static void takeScreenShot(String fileName) {
        File sourceFile = ((TakesScreenshot) BrowserFactory.getDriver()).getScreenshotAs(OutputType.FILE);

        File destFile = new File(Constants.SCREENSHOT_FILEPATH + fileName + getTimeStamp("yyyy-MM-dd-HH-mm-ss") + ".png");
        try {
            FileUtils.copyFile(sourceFile, destFile);
            Reporter.log("<a href='" + destFile.getAbsolutePath() + "'> <img src='" + destFile.getAbsolutePath() + "' height='100' width='250'/> </a>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getTimeStamp(String pattern) {
        Date date = new Date();
        //to format the date according to our choice we want to implement in this function
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }
}
