package com.qa.testcases;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.listeners.TestListeners;
import com.qa.pages.DashboardsPage;
import com.qa.pages.LoginPage;
import com.qa.util.TestUtil;
import com.qa.util.ExtentReports.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;

public class LoginPageTest extends TestBase {

	public static TestListeners iTestResult;
	LoginPage loginPage;
	DashboardsPage dashboardsPage;
	String sheetName = "Login";
	String sheetName1 = "InvalidLogin";

	/*
	 * public LoginPageTest() { super(); }
	 */

	@DataProvider(name = "getLoginTestData")
	public Object[][] getLoginTestData() throws Exception {
		Object data[][] = TestUtil.dataSupplier(sheetName);

		return data;
	}

	@Test(priority = 1, dataProvider = "getLoginTestData", description = "QT-2543 Valid Login", enabled = true)
	public void loginTest(Map<Object, Object> map) throws Exception {
		loginPage = new LoginPage();
		boolean loginPageLoadflag = loginPage.validateLoginPageLoad();
		Assert.assertTrue(loginPageLoadflag, "Login Page loaded...");
		Reporter.log("Login Page loaded successfully..");
		dashboardsPage = new DashboardsPage();
		ExtentTestManager.Report.log(LogStatus.PASS, "Cecking",
				ExtentTestManager.Report.addScreenCapture(TestListeners.TakeScreenshot()));
		dashboardsPage = loginPage.loginKi(map.get("Username"), map.get("Password"));
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		boolean loginSuccessFlag = loginPage.validateLoginSuccess();
		Assert.assertTrue(loginSuccessFlag, "User logged in successfully..");
		Reporter.log("User is logged in to Application..", loginSuccessFlag);
		// ExtentTestManager.getTest().log(LogStatus.INFO, "Logged in with:
		// ",map.get("Username")+ " Role: "+ map.get("Role"));
		// LoginPage.logout();
		// Reporter.log("Logged out from Application..");

	}

	@BeforeMethod
	public void setUp() {
		initialization();
	}

	/*
	 * @DataProvider(name = "getInvalidLoginTestData") public Object[][]
	 * getInvalidLoginTestData() throws Exception { Object data[][] =
	 * TestUtil.dataSupplier(sheetName1);
	 * 
	 * return data; }
	 * 
	 * @Test(priority=2,dataProvider="getInvalidLoginTestData", description =
	 * " : Invalid Login", enabled = false) public void
	 * invalidloginKiTest(Map<Object, Object> map) { boolean loginPageLoadflag =
	 * loginPage.validateLoginPageLoad();
	 * Assert.assertTrue(loginPageLoadflag,"Login Page loaded...");
	 * Reporter.log("Login Page loaded successfully..");
	 * loginPage.invalidlogin(map.get("Username"),map.get("Password")); String
	 * expectedMsg = "Username/password invalid"; String actualMsg =
	 * LoginPage.InvalidLoginTxt.getText(); AssertJUnit.assertEquals(actualMsg,
	 * expectedMsg, "Unable to login kindly check your credentials..");
	 * 
	 * }
	 */

	@AfterMethod
	public void tearDown() throws Exception {

		driver.quit();
		Reporter.log("Driver instance closed..");
	}

}
