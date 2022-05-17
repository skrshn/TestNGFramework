package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;

public class EmployeeSearchPage extends CommonMethods {

    @FindBy(id = "empsearch_id")
    public WebElement employeeIDField;

    @FindBy(xpath = "(//*[@type='text'])[1]")
    public WebElement searchName;

    @FindBy(id = "searchBtn")
    public WebElement searchButton;

    @FindBy(xpath = "//*[@id='resultTable']/tbody/tr/td[2]/a")
    public WebElement verifyEmployeeAddedField;



    public EmployeeSearchPage() {
        PageFactory.initElements(driver,this);
    }
}
