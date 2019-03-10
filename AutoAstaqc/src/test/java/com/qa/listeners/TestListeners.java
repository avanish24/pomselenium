package com.qa.listeners;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.qa.base.TestBase;
import com.qa.util.ExtentReports.ExtentManager;
import com.qa.util.ExtentReports.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;

public class TestListeners extends TestBase implements ITestListener {

	public static String iTestDesc;

	private static String getTestMethodDesc(ITestResult iTestResult) {

		if (iTestResult.getMethod().getDescription() == null) {
			iTestDesc = getTestMethodName(iTestResult).replace(":", "_").replace(" ", "_");
			return iTestDesc;
		} else {

			iTestDesc = iTestResult.getMethod().getDescription().replace(":", "_").replace(" ", "_");
			return iTestDesc;
		}

	}

	private static String getTestMethodName(ITestResult iTestResult) {
		return iTestResult.getMethod().getConstructorOrMethod().getName();

	}

	public static String TakeScreenshot() {
		Date d = new Date();
		String screenshotFile = d.toString().replace(":", "_").replace(" ", "_") + ".png";
		String filePath = ExtentManager.FolderName + "//screenshots//" + iTestDesc + "//" + screenshotFile;
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		// Save this file to the location

		try {
			FileUtils.copyFile(scrFile, new File(filePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return filePath;
	}

	// After ending all tests, below method runs.
	@Override
	public void onFinish(ITestContext iTestContext) {
		System.out.println(" I am in onFinish method " + iTestContext.getName());
		// Do tear down operations for extentreports reporting!
		ExtentTestManager.endTest();
		ExtentManager.getReporter().flush();
	}

	// Before starting all tests, below method runs.
	@Override
	public void onStart(ITestContext iTestContext) {
		System.out.println("I am in onStart method " + iTestContext.getName());
		iTestContext.setAttribute("WebDriver", TestBase.driver);

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
		System.out.println("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
		ExtentTestManager.Report.log(LogStatus.FAIL, "Test failed but it is in defined success ratio",
				ExtentTestManager.Report.addScreenCapture(TakeScreenshot()));
	}

	@Override
	public void onTestFailure(ITestResult iTestResult) {
		System.out.println("I am in onTestFailure method " + getTestMethodName(iTestResult) + " failed");
		ExtentTestManager.Report.log(LogStatus.FAIL, "Test Failed",
				ExtentTestManager.Report.addScreenCapture(TakeScreenshot()));

	}

	@Override
	public void onTestSkipped(ITestResult iTestResult) {
		System.out.println("I am in onTestSkipped method " + getTestMethodName(iTestResult) + " skipped");
		ExtentTestManager.Report.log(LogStatus.SKIP, "Test Skipped",
				ExtentTestManager.Report.addScreenCapture(TakeScreenshot()));
	}

	@Override
	public void onTestStart(ITestResult iTestResult) {
		iTestDesc = getTestMethodDesc(iTestResult);
		System.out.println("I am in onTestStart method " + getTestMethodName(iTestResult) + " start");
		ExtentTestManager.startTest(iTestDesc, "");
		ExtentTestManager.getTest().log(LogStatus.INFO, "Test Started",
				ExtentTestManager.Report.addScreenCapture(TakeScreenshot()));
	}

	@Override
	public void onTestSuccess(ITestResult iTestResult) {
		System.out.println("I am in onTestSuccess method " + getTestMethodName(iTestResult) + " succeed");

		ExtentTestManager.Report.log(LogStatus.PASS, iTestDesc + " Test Successfully completed",
				ExtentTestManager.Report.addScreenCapture(TakeScreenshot()));

	}

}