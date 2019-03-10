package com.qa.util.ExtentReports;

import java.io.File;
import java.util.Date;

import com.qa.base.TestBase;
import com.relevantcodes.extentreports.ExtentReports;

//OB: ExtentReports extent instance created here. That instance can be reachable by getReporter() method.

public class ExtentManager {

	private static ExtentReports extent;
	public static String FolderName = TestBase.reportsFolder;

	public synchronized static ExtentReports getReporter() {
		if (extent == null) {
			// Set HTML reporting file location
			Date d = new Date();
			String date_time = d.toString().replace(":", "_").replace(" ", "_");
			extent = new ExtentReports(FolderName + "Report" + date_time + ".html", true);
			extent.loadConfig(new File(System.getProperty("user.dir") + "//ReportsConfig.xml"));
			extent.addSystemInfo("Environment", TestBase.prop.getProperty("Environment"));
		}

		return extent;
	}
}