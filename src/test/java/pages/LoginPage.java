package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.BrowserFactory;
import utils.CommonMethods;

public class LoginPage extends CommonMethods {

    //object repository
    @FindBy(id = "txtUsername")
    public WebElement usernameField;

    @FindBy(name = "txtPassword")
    public WebElement passwordField;

    @FindBy(id = "btnLogin")
    public WebElement loginButton;

    @FindBy(xpath = "//div[@id='divLogo']/img")
    public WebElement logo;

    @FindBy(xpath = "//span[text()='Invalid credentials']")
    public WebElement invalidCredentialsErrorMessage;

    @FindBy(xpath = "//span[text()='Username cannot be empty']")
    public WebElement userNameCannotBeEmptyErrorMessage;

    @FindBy(xpath = "//span[text()='Password cannot be empty']")
    public WebElement passwordCannotBeEmptyErrorMessage;

    @FindBy(xpath = "//input[@type='submit']/following-sibling::span")
    public WebElement errorMessage;

    public LoginPage() {
        PageFactory.initElements(BrowserFactory.getDriver(), this);
    }

    public void loginMethod(String username, String password) {
        LoginPage loginPage=new LoginPage();

        CommonMethods.sendText(usernameField, username);
        CommonMethods.sendText(passwordField, password);
        CommonMethods.click(loginPage.loginButton);
    }
}
