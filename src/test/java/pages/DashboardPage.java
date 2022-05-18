package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.BrowserFactory;
import utils.CommonMethods;

import java.util.List;

public class DashboardPage extends CommonMethods {

    @FindBy(xpath = "//*[@class='menu']/ul/li")
    public List<WebElement> navTabs;

    @FindBy(xpath = "//a[@id='welcome']")
    public WebElement welcomeMessage;

    @FindBy(id = "menu_pim_viewPimModule")
    public WebElement pimOption;

    @FindBy(id = "menu_pim_viewEmployeeList")
    public WebElement empListOption;

    @FindBy(id = "menu_pim_addEmployee")
    public WebElement addEmpOption;

    public DashboardPage() {
        PageFactory.initElements(BrowserFactory.getDriver(),this);
    }
}
