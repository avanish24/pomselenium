package com.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.Listeners;

import com.qa.pages.DashboardsPage;
import com.qa.pages.LoginPage;
import com.qa.util.ExtentManager;
import com.qa.util.TestUtil;
import com.qa.util.WebEventListener;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.qa.listeners.*;

@Listeners(TestListeners.class)
public class TestBase {

	public static WebDriver driver;
	public static Properties prop;
	public static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
	public static ExtentReports rep = ExtentManager.getInstance();
	public static ExtentTest test;
	public static String reportsFolder;
	public String workingDir = System.getProperty("user.dir");
	public String sXPathStrReplace = "${variable}";

	LoginPage loginPage;
	DashboardsPage dashboardsPage;

	public TestBase() {
		try {

			prop = new Properties();
			FileInputStream fis = new FileInputStream(
					workingDir + "\\src\\main\\java\\com\\ki\\qa\\config\\config.properties");

			prop.load(fis);

			// START FOR REPORTS FOLDER GENERATION this is chained to Extent
			// Manager(FolderName) which is chained to TestListeners
			workingDir = System.getProperty("user.dir");
			Calendar calendar = Calendar.getInstance();
			reportsFolder = workingDir + "//ExtentReports//" + (calendar.get(Calendar.YEAR)) + "_"
					+ calendar.get(Calendar.MONTH) + "_" + calendar.get(Calendar.DATE) + "_"
					+ calendar.get(Calendar.HOUR_OF_DAY) + +calendar.get(Calendar.MINUTE)
					+ +calendar.get(Calendar.SECOND) + "//";
			// END

		} catch (FileNotFoundException e) {
			System.out.println("File not found at provided path...");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Unable to load property file...");
			e.printStackTrace();
		}

	}

	public void Fail(String msg, boolean bCapture) {
		test.log(LogStatus.FAIL, msg);
		if (bCapture) {
			takeScreenShot();
		}
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void Info(String msg, boolean bCapture) {
		test.log(LogStatus.INFO, msg);
		if (bCapture) {
			takeScreenShot();
		}
	}

	public void initialization() {
		String browserName = prop.getProperty("browser");

		if (browserName.equalsIgnoreCase("ff")) {
			System.setProperty("webdriver.gecko.driver", workingDir + "\\Drivers\\geckodriver\\geckodriver.exe");
			driver = new FirefoxDriver();
		}

		else if (browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", workingDir + "\\Drivers\\chromedriver\\chromedriver.exe");
			driver = new ChromeDriver();
		}

		e_driver = new EventFiringWebDriver(driver);
		// Now create object of EventListerHandler to register it with
		// EventFiringWebDriver
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		driver.get(prop.getProperty("url"));

	}

	// Reports
	public void Pass(String msg, boolean bCapture) {
		test.log(LogStatus.PASS, msg);
		if (bCapture) {
			takeScreenShot();
		}
	}

	public void takeScreenShot() {
		Date d = new Date();
		String screenshotFile = d.toString().replace(":", "_").replace(" ", "_") + ".png";
		String filePath = System.getProperty("user.dir") + "screenshots\\" + screenshotFile;
		// store screenshot in that file
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		try {
			FileUtils.copyFile(scrFile, new File(filePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test.log(LogStatus.INFO, test.addScreenCapture(filePath));
	}

}
