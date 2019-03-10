package com.qa.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.base.TestBase;
import com.qa.util.TestUtil;

public class LoginPage extends TestBase {

	@FindBy(xpath = OR.sInvalidLoginTxt)
	@CacheLookup
	public static WebElement InvalidLoginTxt;

	@FindBy(xpath = OR.sPortalLoginOptions)
	@CacheLookup
	public static WebElement LoginOptionsDD;

	@FindBy(xpath = OR.sPortalEmailInput)
	@CacheLookup
	public static WebElement UserNameTxtBx;

	@FindBy(xpath = OR.sPortalPasswordInput)
	@CacheLookup
	public static WebElement passWordTxtBx;

	@FindBy(xpath = OR.sPortalLoginBtn)
	@CacheLookup
	public static WebElement LoginBtn;

	@FindBy(xpath = OR.sDashboardPageLbl)
	@CacheLookup
	public static WebElement dashboardPageLbl;

	@FindBy(xpath = OR.sPasswordPage)
	@CacheLookup
	public static WebElement PasswordPage;

	@FindBy(xpath = OR.sMainNav)
	@CacheLookup
	public static WebElement MainNav;

	@FindBy(xpath = OR.sDownArrowMenu)
	@CacheLookup
	public static WebElement DownArrowMenu;

	@FindBy(xpath = OR.sLogoutBtn)
	@CacheLookup
	public static WebElement LogoutBtn;

	public static boolean logout() {
		Boolean flag;
		try {
			// DownArrowMenu.click();
			// Reporter.log("Clicked on DownArrowMenu...");
			Thread.sleep(2000);
			LogoutBtn.click();
			flag = true;
			// Reporter.log("Clicked on Logout button...");
		} catch (Exception e) {

			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	public LoginPage() {
		PageFactory.initElements(driver, this);
	}

	public void invalidlogin(Object username, Object password) {
		try {
			String userNamestr = (String) username;
			Thread.sleep(3000);
			UserNameTxtBx.sendKeys(userNamestr);
			// Reporter.log("UserName is entered...");

			String passwordstr = (String) password;
			Thread.sleep(3000);
			passWordTxtBx.sendKeys(passwordstr);
			// Reporter.log("Password is entered...");

			Thread.sleep(2000);
			LoginBtn.click();
			driver.switchTo().alert().accept();
			// Reporter.log("Clicked on Login button...");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public DashboardsPage loginKi(Object username, Object password) throws Exception {
		try {
			String userNamestr = (String) username;
			Thread.sleep(1000);
			LoginOptionsDD.click();
			UserNameTxtBx.sendKeys(userNamestr);
			// Reporter.log("UserName is entered...");

			String passwordstr = (String) password;
			Thread.sleep(500);
			passWordTxtBx.sendKeys(passwordstr);
			// Reporter.log("Password is entered...");

//			Thread.sleep(500);
//			LoginBtn.click();
			TestUtil.clickNWait(OR.sPortalLoginBtn, OR.sDashboardPageLbl, 10);

			// Reporter.log("Clicked on Login button...");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return new DashboardsPage();
	}

	public boolean validateLoginPageLoad() {
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		return LoginOptionsDD.isDisplayed();
	}

	public boolean validateLoginSuccess() {
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		return dashboardPageLbl.isDisplayed();
	}

}
