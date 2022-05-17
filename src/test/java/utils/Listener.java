package utils;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listener implements ITestListener {

    @Override
    public void onTestSuccess(ITestResult result) {
        CommonMethods.takeScreenShot("passed/"+result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        CommonMethods.takeScreenShot("failed/"+result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        CommonMethods.takeScreenShot("skipped/"+result.getName());
    }

}
