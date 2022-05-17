package test;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import testBase.TestBase;
import utils.CommonMethods;

public class LoginTest extends TestBase {

    @DataProvider(name = "Credentials")
    public Object[][] loginData() {
        return new Object[][]{{"Admin", "abc", "Invalid credentials"},
                {"A", "Hum@nhrm123", "Invalid credentials"},
                {"Admin", "", "Password cannot be empty"},
                {"", "Hum@nhrm123", "Username cannot be empty"}
        };
    }

    @Test
    public void logoIsPresent() {
        boolean isDisplayed = loginPage.logo.isDisplayed();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(isDisplayed);
        softAssert.assertAll();
    }

    @Test(dataProvider = "Credentials")
    public void invalidCredentials(String username, String password, String expectedMessage) {
        loginPage.usernameField.sendKeys(username);
        loginPage.passwordField.sendKeys(password);
        loginPage.loginButton.click();
        String actualMessage = CommonMethods.getText(loginPage.errorMessage);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualMessage,expectedMessage);
        softAssert.assertAll();
    }
}
