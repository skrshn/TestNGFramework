package test;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.AddEmployeePage;
import pages.DashboardPage;
import pages.EmployeeSearchPage;
import pages.LoginPage;
import testBase.TestBase;
import utils.CommonMethods;
import utils.ConfigReader;
import utils.Constants;
import utils.ExcelReader;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class LoginTest extends TestBase {

    @DataProvider(name = "Credentials")
    public Object[][] loginData() {
        return new Object[][]{{"Admin", "abc", "Invalid credentials"},
                {"A", "Hum@nhrm123", "Invalid credentials"},
                {"Admin", "", "Password cannot be empty"},
                {"", "Hum@nhrm123", "Username cannot be empty"}
        };
    }

    @Test(groups = "regression")
    public void logoIsPresent() {
        LoginPage loginPage=new LoginPage();

        boolean isDisplayed = loginPage.logo.isDisplayed();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(isDisplayed);
        //softAssert.assertAll();
    }

    @Test(dataProvider = "Credentials", groups = "regression")
    public void invalidCredentials(String username, String password, String expectedMessage) {
        LoginPage loginPage=new LoginPage();

        loginPage.usernameField.sendKeys(username);
        loginPage.passwordField.sendKeys(password);
        loginPage.loginButton.click();
        String actualMessage = CommonMethods.getText(loginPage.errorMessage);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualMessage,expectedMessage);
        softAssert.assertAll();
    }
}
