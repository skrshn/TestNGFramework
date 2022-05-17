package utils;

import pages.DashboardPage;
import pages.AddEmployeePage;
import pages.EmployeeSearchPage;
import pages.LoginPage;

public class PageInitializers {
    public static LoginPage loginPage;
    public static EmployeeSearchPage emSearchPage;
    public static AddEmployeePage emAddPage;
    public static DashboardPage dashboardPage;

    public static void initializePageObjects() {
        loginPage = new LoginPage();
        emSearchPage = new EmployeeSearchPage();
        emAddPage = new AddEmployeePage();
        dashboardPage = new DashboardPage();
    }
}
