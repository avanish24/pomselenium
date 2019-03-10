package com.qa.util;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.base.TestBase;
import com.qa.util.TestUtil.Condition;

public class Action extends TestBase {
	static WebDriverWait wait;
	public static int implicitWaitInterim = 10;

	// Check element or object present
	public static boolean checkElementPresent(String objectName, String value) throws InterruptedException {
		long tStart, tCurrent;
		tStart = System.currentTimeMillis();
		tCurrent = 0;
		@SuppressWarnings("unused")
		String errorMessage = null;
		boolean elementStatus = false;
		// String locator = ObjectRepository.getORdata(screen,objectName, "Locator");
		do {
			driver.manage().timeouts().implicitlyWait(implicitWaitInterim, TimeUnit.SECONDS);
			new WebDriverWait(driver, TestUtil.PAGE_LOAD_TIMEOUT).until(webDriver -> ((JavascriptExecutor) webDriver)
					.executeScript("return document.readyState").equals("complete"));
			try {
				// Checks the element present in the page
				elementStatus = driver.findElement(By.xpath(objectName)).isDisplayed();
				if (elementStatus) {
					Log.info("Element ---> " + objectName + " found ");
					return elementStatus;
				}
			} catch (Exception e) {
				errorMessage = e.getMessage();
			}
			tCurrent = System.currentTimeMillis();
		} while (tCurrent - tStart < Integer.parseInt(value));
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		if (tCurrent - tStart >= Integer.parseInt(value)) {
			Log.info(objectName + "---> is not found");
		}
		return elementStatus;
	}

	public static boolean clickCheckbox(String xpath, boolean check, int iMaxSec) throws Exception {
		Exception exception = null;

		for (int i = 0; i < 2 * iMaxSec; i++) {
			try {

				if (Condition.CLICKABLE.conditionOf(xpath)) {
					WebElement checkbox = driver.findElement(By.xpath(xpath));

					// Get current value of checkbox
					boolean alreadyChecked = checkbox.findElement(By.xpath("./div")).getAttribute("class")
							.contains("checked");

					// Click
					if (check != alreadyChecked)
						checkbox.click();

					return true;
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

	public static boolean clickKiElement(String xpath, int iMaxSec) throws Exception {
		return clickKiElement(xpath, null, null, iMaxSec);
	}

	public static boolean clickKiElement(String xpath1, String xpath2, Condition e2condition, int iMaxSec)
			throws Exception {
		Exception exception = null;

		for (int i = 0; i < 2 * iMaxSec; i++) {
			try {
				WebElement element1 = driver.findElement(By.xpath(xpath1));
				// Scroll element1
				scrollIntoView(element1);

				if (Condition.CLICKABLE.conditionOf(element1)) {
//					WebElement element1 = driver.findElement(By.xpath(xpath1));

					// Click element1
					element1.click();

					// Return whether element2 is visible or is null
					if (xpath2 == null) {
						return true;
					} else if (e2condition.conditionOf(xpath2)) {
						WebElement element2 = driver.findElement(By.xpath(xpath2));

						// Scroll element2
						scrollIntoView(element2);

						return true;
					}
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

	public static boolean clickKiElement(String xpath1, String xpath2, int iMaxSec) throws Exception {
		return clickKiElement(xpath1, xpath2, Condition.CLICKABLE, iMaxSec);
	}

	public static List<WebElement> getRowsFromTable(String tableXpath, String rowClassName, int searchColumnIndex,
			String searchString, int iMaxSec) throws Exception {
		WebElement table = null;
		List<WebElement> allRows;
		ArrayList<WebElement> foundRows = new ArrayList<WebElement>();
		Exception exception = null;

		for (int i = 0; i < 2 * iMaxSec; i++) {
			try {
				if (Condition.VISIBLE.conditionOf(tableXpath)) {
					table = driver.findElement(By.xpath(tableXpath));

					break;
				}
			} catch (Exception e) {
				exception = e;
			}

			try {
				Thread.sleep(500);
			} catch (Exception e) {
			}
		}

		if (table == null) {
			if (exception != null)
				throw exception;
			else
				return foundRows;
		}

		// Build the xpath for a single row in the table
		String rowPath = "//*[@class='";
		if (rowClassName == null || rowClassName.equals(""))
			rowPath += "table-row']";
		else
			rowPath += rowClassName + "']";

		// Get all rows in the table
		allRows = table.findElements(By.xpath(rowPath));

		// Verify that there are rows in the table
		// and that the search column index does not exceed the number of columns
		if (allRows.size() == 0 || allRows.get(0).findElements(By.xpath("./*")).size() < searchColumnIndex)
			return foundRows;

		// Find rows that contain the search string in the respective search column
		for (WebElement row : allRows) {
			if (row.findElement(By.xpath("./*[" + searchColumnIndex + "]")).getText().equals(searchString))
				foundRows.add(row);
		}

		return foundRows;
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

	public static boolean selectDropdownItem(String dropdownMenuXpath, String dropdownItemXpath, int iMaxSec)
			throws Exception {
		Exception exception = null;

		for (int i = 0; i < 2 * iMaxSec; i++) {
			try {
				if (Condition.CLICKABLE.conditionOf(dropdownMenuXpath)) {
					WebElement dropdownMenu = driver.findElement(By.xpath(dropdownMenuXpath));

					// Click dropdown menu
					dropdownMenu.click();

					// Check if dropdown item is visible and clickable
					if (Condition.CLICKABLE.conditionOf(dropdownItemXpath)) {
						WebElement dropdownItem = driver.findElement(By.xpath(dropdownItemXpath));

						// Click dropdown item
						dropdownItem.click();
						return true;
					}
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

	public static boolean setDate(String xpath, String date, int iMaxSec) throws Exception {
		Exception exception = null;

		// Return false if date not in the format YYYY-MM-DD
		if (!(date.substring(4, 5).equals("-") || date.substring(7, 8).equals("-")
				|| date.substring(0, 4).matches("\\d+") || date.substring(5, 7).matches("\\d+")
				|| date.substring(8, 10).matches("\\d+"))) {
			exception = new InvalidParameterException("Date must be in the format YYYY-MM-DD");
			throw exception;
		}

		for (int i = 0; i < 2 * iMaxSec; i++) {
			try {
				if (Condition.ENABLED.conditionOf(xpath)) {
					WebElement dateField = driver.findElement(By.xpath(xpath));

					// Clear on datefield to bring it into focus
					dateField.click();

					// Clear datefield
					dateField.clear();

					// Enter date
					dateField.sendKeys(date);

					// Tab out of date field
					dateField.sendKeys(Keys.TAB);

					// Return true if date field retained date, else return false
					return dateField.getAttribute("value").equals(date);
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

	public static boolean typeInTextbox(String xpath, String sText, int iMaxSec) throws Exception {
		Exception exception = null;

		for (int i = 0; i < 2 * iMaxSec; i++) {
			try {
				if (Condition.ENABLED.conditionOf(xpath)) {
					WebElement textbox = driver.findElement(By.xpath(xpath));

					// Click on textbox to bring it into focus
					textbox.click();

					// Clear any text already in textbox
					textbox.clear();

					// Enter sText into textbox
					textbox.sendKeys(sText);

					// Tab out of textbox
					textbox.sendKeys(Keys.TAB);

					// Return true if textbox retained message, else return false
					return textbox.getAttribute("value").equals(sText);
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
}
