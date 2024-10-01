package utalities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import testBase.BaseClass;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Extentreport implements ITestListener {

    public ExtentReports extent;
    public ExtentTest test;
    private static ExtentSparkReporter sparkReporter;
    String repName;

    public void onstart(ITestContext testContext) {

        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        repName = "Test-Report-" + timeStamp + ".html";
        sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "\\reports\\" +repName);

        sparkReporter.config().setDocumentTitle("opencart Automation Report");
        sparkReporter.config().setReportName("Function testing");
        sparkReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Application", "Swaglabs");
        extent.setSystemInfo("UserName", System.getProperty("user.name"));
        extent.setSystemInfo("Env", "Test");


        String os = testContext.getCurrentXmlTest().getParameter("os");
        extent.setSystemInfo("Operating system", os);

        String browser = testContext.getCurrentXmlTest().getParameter("browser");
        extent.setSystemInfo("Browser", browser);

        List<String> includedgroups = testContext.getCurrentXmlTest().getIncludedGroups();
        if (!includedgroups.isEmpty()) {
            extent.setSystemInfo("Groups", includedgroups.toString());

        }
    }

    public void OnTestSuccess(ITestResult result) {
        test = extent.createTest(result.getTestClass().getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.PASS, result.getName() + "successfully excuted");

    }

    public void TestFailure(ITestResult result) {
        test = extent.createTest(result.getTestClass().getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.FAIL, result.getName() + "Case Failed");
        test.log(Status.INFO, result.getThrowable().getMessage());

        try {
            String imgpath = new BaseClass().capturescreenshot(result.getName());
            test.addScreenCaptureFromPath(imgpath);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void OnTestSkipped(ITestResult result) {
        test = extent.createTest(result.getTestClass().getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.SKIP, result.getName() + " Test skkiped");

    }

    public void onfinish(ITestContext testContext){
    extent.flush();

String pathofextentreport =System.getProperty("user.dir") + "\\reports\\" + repName;
File extentfile = new File(pathofextentreport);

try {
    Desktop.getDesktop().browse(extentfile.toURI());
} catch (IOException e) {
    throw new RuntimeException(e);
}
    }

}



