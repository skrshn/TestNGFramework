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

public class AddEmployeeTest extends TestBase {
    @Test(groups ="regression")
    public void addEmployee() {
        LoginPage loginPage=new LoginPage();
        DashboardPage dashboardPage=new DashboardPage();
        EmployeeSearchPage emSearchPage= new EmployeeSearchPage();
        AddEmployeePage emAddPage = new AddEmployeePage();

        loginPage.loginMethod(ConfigReader.getPropertyValue("username"), ConfigReader.getPropertyValue("password"));

        CommonMethods.click(dashboardPage.pimOption);
        CommonMethods.click(dashboardPage.addEmpOption);
        SoftAssert softAssert = new SoftAssert();

        List<Map<String, String>> newEmployees = ExcelReader.excelIntoMap(Constants.TESTDATA_FILEPATH, "EmployeeData");
        Iterator<Map<String, String>> itr = newEmployees.iterator();
        while (itr.hasNext()) {
            Map<String, String> newEmployee = itr.next();
            CommonMethods.sendText(emAddPage.firstName, newEmployee.get("FirstName"));
            CommonMethods.sendText(emAddPage.middleName, newEmployee.get("MiddleName"));
            CommonMethods.sendText(emAddPage.lastName, newEmployee.get("LastName"));
            String empID = emAddPage.employeeID.getAttribute("value");

            CommonMethods.click(emAddPage.saveButton);

            CommonMethods.click(dashboardPage.pimOption);
            CommonMethods.click(dashboardPage.empListOption);

            CommonMethods.sendText(emSearchPage.employeeIDField, empID);
            CommonMethods.click(emSearchPage.searchButton);
            softAssert.assertEquals(emSearchPage.verifyEmployeeAddedField.getText(), empID);
            softAssert.assertAll();

            CommonMethods.click(dashboardPage.addEmpOption);
        }
    }
}
