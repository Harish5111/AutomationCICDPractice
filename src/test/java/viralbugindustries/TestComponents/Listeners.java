package viralbugindustries.TestComponents;
import org.testng.ITestListener;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import viralbugindustries.resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener {
	
	ExtentTest test;
	ExtentReports extent = ExtentReporterNG.getReportObject();
	ThreadLocal<ExtentTest> extenttest = new ThreadLocal<ExtentTest>();

	@Override
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
		extenttest.set(test);
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
		extenttest.get().log(Status.PASS, "Test Passed");
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		extenttest.get().fail(result.getThrowable());
		String filePath = null;
	    try {
	        driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
	        filePath = getScreenShot(result.getMethod().getMethodName(), driver);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    if (filePath != null) {
	    	extenttest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
	    }
	}
	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}
	
}
