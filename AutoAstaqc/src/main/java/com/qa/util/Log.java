package com.qa.util;

import java.io.FileOutputStream;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

import com.qa.base.TestBase;

public class Log extends TestBase {
	// public static Properties prop;
	// public static Properties prop;

	// Initialize Log4j logs
	private static Logger Log = Logger.getLogger(Log.class.getName());

	public static void configureLog4j(String strPath, String strTimeOfExecution) {
		String PATTERN = "%d{yyyy-MM-dd HH:mm:ss} %-5p %c{1}:%L - %m%n";
		// System.out.println(strPath+File.separator+"Log"+strTimeOfExecution+".log");
		System.out.println("---Print something---");
		// prop = new Properties();
		System.out.println("prop.getProperty(browser) ====" + prop.getProperty("browser"));
		System.out.println("prop.getProperty(LogOption) ====" + prop.getProperty("LogOption"));
		if (prop.getProperty("LogOption").equalsIgnoreCase("Console")
				|| prop.getProperty("LogOption").equalsIgnoreCase("Console&File")) {
			ConsoleAppender console = new ConsoleAppender(); // create appender
			// configure the appender
			console.setLayout(new PatternLayout(PATTERN));
			console.setThreshold(Level.INFO);
			console.activateOptions();
			// add appender to any Logger (here is root)
			Logger.getRootLogger().addAppender(console);
		}

		if (prop.getProperty("LogOption").equalsIgnoreCase("File")
				|| prop.getProperty("LogOption").equalsIgnoreCase("Console&File")) {
			fileCreate(strPath + "Log" + strTimeOfExecution + ".log");
			FileAppender fa = new FileAppender();
			fa.setName("FileLogger");
			fa.setFile(strPath + "Log" + strTimeOfExecution + ".log");
			fa.setLayout(new PatternLayout(PATTERN));
			fa.setThreshold(Level.INFO);
			fa.setAppend(true);
			fa.activateOptions();

			// add appender to any Logger (here is root)
			Logger.getRootLogger().addAppender(fa);
			// repeat with all other desired appenders
		}
	}

	public static void debug(String message) {
		Log.debug(message);
	}

	// Print log for the ending of the test method
	public static void endTestMethod(String sTestCaseName) {
		Log.info("**************************************************************************************************");
		Log.info("#####################                  END: " + sTestCaseName + "       ##################### ");
		Log.info("**************************************************************************************************");
	}

	public static void error(String message) {
		Log.error(message);
	}

	public static void fatal(String message) {
		Log.fatal(message);
	}

	private static void fileCreate(String strFileName) {
		try {
			new FileOutputStream(strFileName, false).close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Default logging methods
	public static void info(String message) {
		Log.info(message);
	}

	// Print log for the beginning of the test method
	public static void startTestMethod(String sTestCaseName) {
		Log.info("**************************************************************************************************");
		Log.info("#####################                 START: " + sTestCaseName + "       ##################### ");
		Log.info("**************************************************************************************************");

	}

	public static void warn(String message) {
		Log.warn(message);
	}

}
