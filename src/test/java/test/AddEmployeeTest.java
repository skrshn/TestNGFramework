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
public class AddEmployeeTest extends TestBase {
    @DataProvider(name = "addEmp")
    public Object[][] addEmp() {
        Object[][] arrayObject = ExcelReader.getExcelData(Constants.TESTDATA_FILEPATH, "EmployeeData");
        return arrayObject;
    }

    @Test(groups = "regression", dataProvider = "addEmp")
    public void addEmployee(String fName, String mName, String lName) {
        LoginPage loginPage = new LoginPage();
        DashboardPage dashboardPage = new DashboardPage();
        EmployeeSearchPage emSearchPage = new EmployeeSearchPage();
        AddEmployeePage emAddPage = new AddEmployeePage();

        loginPage.loginMethod(ConfigReader.getPropertyValue("username"), ConfigReader.getPropertyValue("password"));

        CommonMethods.click(dashboardPage.pimOption);
        CommonMethods.click(dashboardPage.addEmpOption);
        SoftAssert softAssert = new SoftAssert();

        CommonMethods.sendText(emAddPage.firstName, fName);
        CommonMethods.sendText(emAddPage.middleName, mName);
        CommonMethods.sendText(emAddPage.lastName, lName);
        String empID = emAddPage.employeeID.getAttribute("value");

        CommonMethods.click(emAddPage.saveButton);

        CommonMethods.click(dashboardPage.pimOption);
        CommonMethods.click(dashboardPage.empListOption);

        CommonMethods.sendText(emSearchPage.employeeIDField, empID);
        CommonMethods.click(emSearchPage.searchButton);
        softAssert.assertEquals(emSearchPage.verifyEmployeeAddedField.getText(), empID);
        softAssert.assertAll();

        //CommonMethods.click(dashboardPage.addEmpOption);

    }
}
