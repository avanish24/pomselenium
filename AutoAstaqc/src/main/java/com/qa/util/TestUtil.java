package com.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.base.TestBase;

public class TestUtil extends TestBase {

	public enum Condition {
		VISIBLE, ENABLED, CLICKABLE;

		public boolean conditionOf(String xpath) throws ElementNotVisibleException {
			wait = new WebDriverWait(driver, 5);

			switch (this) {
			case VISIBLE:
				return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).isDisplayed();
			case ENABLED:
				return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).isEnabled();
			case CLICKABLE:
				return wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath))).isEnabled();
			default:
				return false;
			}
		}

		public boolean conditionOf(WebElement element) throws ElementNotVisibleException {
			wait = new WebDriverWait(driver, 5);

			switch (this) {
			case VISIBLE:
				return wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
			case ENABLED:
				return wait.until(ExpectedConditions.visibilityOf(element)).isEnabled();
			case CLICKABLE:
				return wait.until(ExpectedConditions.elementToBeClickable(element)).isEnabled();
			default:
				return false;
			}
		}
	}

	public static long PAGE_LOAD_TIMEOUT = 30;
	public static long IMPLICIT_WAIT = 10;
	public static Map<Object, Object> datamap;

	public static WebDriverWait wait;

	/****** To Read Excel Data ******/
	public static String TESTDATA_SHEET_PATH = System.getProperty("user.dir") + prop.getProperty("excelPath");
	public static Workbook book;
	public static Sheet sheet;

	public static Object[][] obj;

	public static void clickNWait(String xPath1, String xPath2, int iMaxSec) throws Exception {
		boolean clickSuccess = false;

		for (int i = 0; i < 2 * iMaxSec; i++) {
			try {
				WebElement element1 = driver.findElement(By.xpath(xPath1));
				// Scroll element1 into view
				scrollIntoView(element1);

				// Check if element1 is clickable
				if (Condition.CLICKABLE.conditionOf(element1)) {
					// Click element1
					element1.click();
					clickSuccess = true;

					// Check if element2 is clickable
					if (Condition.CLICKABLE.conditionOf(xPath2)) {
						// Exit the method
						return;
					} else {
						Log.info("Element2 is not clickable");
					}
				} else {
					Log.info("Element1 is not clickable");
				}

			} catch (Exception e) {
				if (clickSuccess) {
					Log.info("Element2 is not clickable");
				} else {
					Log.info("Element1 is not clickable");
				}
			}

			try {
//				Thread.sleep(500);
			} catch (Exception e) {
			}
		}

		if (clickSuccess) {
			throw new Exception("Element2 is not clickable");
		} else {
			throw new Exception("Element1 is not clickable");
		}
	}

	public static Object[][] dataSupplier(String sheetName) {

		try {
			File file = new File(TESTDATA_SHEET_PATH);
			FileInputStream fis = new FileInputStream(file);

			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sheet = wb.getSheet(sheetName);
			wb.close();
			int lastRowNum = sheet.getLastRowNum();
			int lastCellNum = sheet.getRow(0).getLastCellNum();
			obj = new Object[lastRowNum][1];

			for (int i = 0; i < lastRowNum; i++) {
				datamap = new HashMap<>();
				for (int j = 0; j < lastCellNum; j++) {
					datamap.put(sheet.getRow(0).getCell(j).toString(), sheet.getRow(i + 1).getCell(j).toString());
				}
				obj[i][0] = datamap;

			}

		} catch (FileNotFoundException e) {
			System.out.println("File not found at provided path...");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Unable to read excel sheet...");
			e.printStackTrace();
		}

		return obj;
	}

	public static String getAttribute(String xpath, String sAttributeNm, int iMaxSec) throws Exception {
		Exception exception = null;

		for (int i = 0; i < 2 * iMaxSec; i++) {
			try {

				if (Condition.CLICKABLE.conditionOf(xpath)) {
					WebElement element = driver.findElement(By.xpath(xpath));

					// Get the attribute for the element
					return element.getAttribute(sAttributeNm);
				}
			} catch (Exception e) {
				exception = e;
			}

			try {
				Thread.sleep(500);
			} catch (Exception e) {
			}
		}

		throw exception;
	}

	public static void scrollIntoView(Object element) {
		// Element is instance of WebElement
		if (element instanceof WebElement) {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

			// Element is instance of String, i.e. is xpath
		} else if (element instanceof String) {
			// Convert from object to String
			String xpath = (String) element;

			// Find WebElement located by this xpath
			WebElement we = driver.findElement(By.xpath(xpath));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", we);
		}
	}

	public static void takeScreenshotAtEndOfTest() throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		FileHandler.copy(scrFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));

	}

	public static boolean waitforElement(String xPath, int iMaxSec) {
		try {
			for (int i = 0; i < 2 * iMaxSec; i++) {
				try {
					if (driver.findElement(By.xpath(xPath)).isDisplayed())
						return true;
				} catch (Exception e) {
					if (!(e instanceof NoSuchElementException))
						throw e;
				}
				Thread.sleep(500);
				System.out.println("waitforElement : xPath = '" + xPath + "'" + String.valueOf(i));
				Log.info("waitforElement : xPath = '" + xPath + "'" + String.valueOf(i));
			}
		} catch (Exception e) {
			System.out.println("Caught: " + e);
			Log.info("Exception caught in 'waitforElement' --- \r\n" + e.getMessage());
		}
		return false;
	}

	public static boolean waitForElementPresent(WebElement element) {
		boolean flag = false;
		wait = new WebDriverWait(driver, 5);
		if (wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed()) {
			flag = true;
			System.out.println("Wait for Element Present Executed for : " + element);
		}

		return flag;
	}
}
