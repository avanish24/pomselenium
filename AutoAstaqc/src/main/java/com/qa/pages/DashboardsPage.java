package com.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.base.TestBase;

public class DashboardsPage extends TestBase {

	@FindBy(xpath = OR.sDashboardPageLbl)
	@CacheLookup
	public static WebElement dashboardPageLbl;

	@FindBy(xpath = OR.sDashboardsLink)
	@CacheLookup
	public static WebElement DashboardsLink;

	public DashboardsPage() {
		PageFactory.initElements(driver, this);
	}

}
