package com.qa.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

import com.qa.base.TestBase;
import com.qa.util.Action;
import com.qa.util.Log;
import com.qa.util.TestUtil;

public class Navigation extends TestBase {

	@FindBy(xpath = OR.sFV_Cons_AddConstraintBtn)
	@CacheLookup
	public static WebElement eFV_Cons_AddConstraintBtn;

	private static int iwaitMaxSec = 1;

	public static boolean selectNavbarItem(String selection) {
		WebElement tooltip;
		boolean found = false;

		// Get nav bar items
		List<WebElement> navItems = driver.findElements(By.xpath(OR.sGlobalNavItem));

		// Navbar items found
		if (navItems.size() > 0) {
			for (WebElement item : navItems) {
				tooltip = item.findElement(By.xpath(OR.sGlobalNavTooltip));

				if (tooltip.getAttribute("innerHTML").equalsIgnoreCase(selection)) {
					item.click();
					found = true;
					break;
				}
			}

			// Navbar item selection not found
			if (!found) {
				Log.info("Navbar does not have '" + selection + "' available.");
				System.out.println("\n***** Navbar slection not found *****\n");
				return false;
			}

			// No navbar items found
		} else {
			Log.info("Navbar does not have any options available.");
			System.out.println("\n***** Navbar does not have any options available *****\n");
			return false;
		}

		return true;
	}

	public static boolean selectSidebarItem(String sMenu2Select) {
		WebElement eSidebarMenuIcon, eTooltip;
		boolean bFound = false;

		// Get all options listed in the sidebar
		List<WebElement> eSidebarMenuTooltip = driver.findElements(By.xpath(OR.sSidebarMenuTooltip));

		if ((eSidebarMenuTooltip.size()) > 0) // locate the given option
		{
			for (int i = 0; i < eSidebarMenuTooltip.size(); i++) {
				eTooltip = eSidebarMenuTooltip.get(i);
				if (eTooltip.getAttribute("innerHTML").equalsIgnoreCase(sMenu2Select)) {
					eSidebarMenuIcon = eTooltip.findElement(By.xpath(OR.sSidebarMenuIcon));
					Log.info("Sidebar menu '" + sMenu2Select + "', is available.");
					eSidebarMenuIcon.click();
					bFound = true;
					break;
				}
			}
			if (!bFound) {
				Log.info("Sidebar does not have '" + sMenu2Select + "', available.");
				System.out.println("\n***** Sidebar menu not found *****\n");
				return bFound;
			}
		} else {
			Log.info("Sidebar does not have any options available.");
			System.out.println("\n***** Sidebar does not have any options available *****\n");
			return bFound;
		}
		return bFound;
	}

	public static boolean selectTab(String sTabName) {

		WebElement eTab;
		boolean bFound = false;

		// Get all tabs
		List<WebElement> eTabs = driver.findElements(By.xpath(OR.sTabsInFlyout));

		if ((eTabs.size()) > 0) {
			for (int i = 0; i < eTabs.size(); i++) {
				eTab = eTabs.get(i);
				if (eTab.getText().equalsIgnoreCase(sTabName)) {
					eTab.click();
					bFound = true;
					break;
				}
			}
			if (!bFound) {
				Log.info("Tab '" + sTabName + "', is not available.");
				System.out.println("\n***** Tab not found *****\n");
				return bFound;
			}
		} else {
			Log.info("No tabs available.");
			System.out.println("\n***** No tabs available *****\n");
			return bFound;
		}
		return bFound;

	}

	DashboardsPage dashboardsPage;
	// FundingVehiclesPage fundingVehiclesPage;
	String datasetName;

	String fundingVehicleName;

	String dropdownSelection;

	String pageName;

	public Navigation() {
	}

	public void navigateTo(String datasetName, String fundingVehicleName, String dropdownSelection) throws Exception {
		// fundingVehiclesPage = new FundingVehiclesPage();

		this.datasetName = datasetName;
		this.fundingVehicleName = fundingVehicleName;
		this.dropdownSelection = dropdownSelection;

		// Find the header elements in the app bar
		List<WebElement> headers = driver.findElements(By.xpath(OR.oAppBar + OR.oAppBarHeaders));

		// Get the texts displayed in the header elements
		String[] headerTexts = new String[headers.size()];
		for (int i = 0; i < headers.size(); i++) {

			// TestUtil.waitForElementPresent(headers.get(i));
			String h = headers.get(i).getText();

			if (i > 0 && h.length() > 2) {
				headerTexts[i] = h.substring(2, h.length());
			} else {
				headerTexts[i] = h;
			}
		}

		// Correct page
		if (headerTexts.length > 0 && headerTexts[0].equals("Funding Vehicle Maintenance")) {
			// Correct funding vehicle, correct dataset, and correct dropdown
			if (headerTexts.length >= 4 && headerTexts[1].equals(datasetName)
					&& headerTexts[2].equals(fundingVehicleName) && headerTexts[3].equals(dropdownSelection)) {
				// Do nothing

				// Correct funding vehicle and dataset, but incorrect dropdown selected
			} else if (headerTexts.length >= 4 && headerTexts[1].equals(datasetName)
					&& headerTexts[2].equals(fundingVehicleName) && !headerTexts[3].equals(dropdownSelection)) {
				headers.get(2).click();
				Action.selectDropdownItem(OR.sFV_Menu_DD,
						OR.sSelectDropDownValues1 + dropdownSelection + OR.sSelectDropDownValues2, iwaitMaxSec);

				// Incorrect dataset or incorrect funding vehicle
			} else if (headerTexts.length >= 3
					&& (!headerTexts[1].equals(datasetName) || !headerTexts[2].equals(fundingVehicleName))) {
				headers.get(0).click();
				if (searchForFundingVehicle(fundingVehicleName, datasetName)) {
					Action.selectDropdownItem(OR.sFV_Menu_DD,
							OR.sSelectDropDownValues1 + dropdownSelection + OR.sSelectDropDownValues2, iwaitMaxSec);
				}

				// Not drilled into a funding vehicle
			} else {
				if (searchForFundingVehicle(fundingVehicleName, datasetName)) {
					Action.selectDropdownItem(OR.sFV_Menu_DD,
							OR.sSelectDropDownValues1 + dropdownSelection + OR.sSelectDropDownValues2, iwaitMaxSec);
				}
			}

			// Incorrect page
		} else {
			navigateToFundingVehicles();
			if (searchForFundingVehicle(fundingVehicleName, datasetName)) {
				Action.selectDropdownItem(OR.sFV_Menu_DD,
						OR.sSelectDropDownValues1 + dropdownSelection + OR.sSelectDropDownValues2, iwaitMaxSec);
			}
		}
	}

	public void navigateToDesiredPage(String pageName, String pageXpath) throws Exception {
		// fundingVehiclesPage = new FundingVehiclesPage();

		this.pageName = pageName;

		// Find the header elements in the app bar
		List<WebElement> headers = driver.findElements(By.xpath(OR.oAppBar + OR.oAppBarHeaders));

		// Get the texts displayed in the header elements
		String[] headerTexts = new String[headers.size()];
		for (int i = 0; i < headers.size(); i++) {

			// TestUtil.waitForElementPresent(headers.get(i));
			String h = headers.get(i).getText();

			if (i > 0 && h.length() > 2) {
				headerTexts[i] = h.substring(2, h.length());
			} else {
				headerTexts[i] = h;
			}
		}

		// Correct page
		if (headerTexts.length > 0 && headerTexts[0].equals(pageName)) {
			// Do nothing
		}
		// Incorrect page
		else {
			if (pageName.equals("Funding Vehicle Maintenance") || pageName.equals("Submissions")
					|| pageName.equals("Holiday Calendars")) {
				Action.clickKiElement(OR.sMaintenanceIcon, pageXpath, iwaitMaxSec);
				Action.clickKiElement(pageXpath, iwaitMaxSec);
				Log.info("----Clicked on 'Maintenance' icon and clicked on " + pageName + " link----");
			} else {
				Action.clickKiElement(pageXpath, iwaitMaxSec);
				Log.info("----Clicked on " + pageName + " link----");
			}
		}
	}

	private void navigateToFundingVehicles() throws Exception {
		Action.clickKiElement(OR.sPrimaryNavBtn, OR.sMaintenanceLink, iwaitMaxSec);
		Action.clickKiElement(OR.sMaintenanceLink, OR.sFundingVehiclesLink, iwaitMaxSec);
		Action.clickKiElement(OR.sFundingVehiclesLink, OR.sSearchFVName, iwaitMaxSec);
	}

	// Returns true if funding vehicle found Else returns false
	private boolean searchForFundingVehicle(String fundingVehicleName, String datasetName) throws Exception {
		// Search for funding vehicle name
		Action.typeInTextbox(OR.sSearchFVName, fundingVehicleName, iwaitMaxSec);

		// Get table rows
		List<WebElement> fvTableRows = driver.findElements(By.xpath(OR.oFVTableRow));

		// Locate the row with the desired dataset name
		for (int i = 1; i <= fvTableRows.size(); i++) {
			WebElement fvDatasetName = driver
					.findElement(By.xpath(OR.oFVTableRow + OR.oFVDatasetName1 + i + OR.oFVDatasetName2));

			// Locate and click on the link to drill into funding vehicle
			if (fvDatasetName.getText().equals(datasetName)) {
				WebElement fvLink = driver
						.findElement(By.xpath(OR.oFVTableRow + OR.oFVTableFVName1 + i + OR.oFVTableFVName2));
				TestUtil.waitForElementPresent(fvLink);
				fvLink.click();

				return true;
			}
		}

		System.out.println("\n***** Funding vehicle not found *****\n");
		return false;
	}

}
