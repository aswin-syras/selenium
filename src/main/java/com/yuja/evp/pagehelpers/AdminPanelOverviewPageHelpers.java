package com.yuja.evp.pagehelpers;

import org.openqa.selenium.By;

import com.yuja.evp.reports.Report;
import com.yuja.evp.utilities.Driver;
import com.yuja.evp.utilities.Helpers;

import helperinterfaces.UICheck;

public class AdminPanelOverviewPageHelpers extends Helpers implements UICheck{
	
	public void CheckPageUI() {
		URL = prop.getProperty("URL")+"P/Institution/OverviewManagement";
		launchUrl(URL, "Test Automation Enterprise Video Platform");
		String sectionTitle = Driver.getDriver().findElement(By.id("secondPartText")).getText();
		boolean check  = waitForElement(By.id("userBtn"),10).isDisplayed();
		if(check && sectionTitle.equals("Overview")) {
			Report.reportStep(Driver.getDriver(), sectionTitle + " Page loaded successfully", "PASS", false);
			System.out.println(sectionTitle);
		} else Report.reportStep(Driver.getDriver(), "Admin Panel " + sectionTitle + "failed to load", "fail", true);	
	}
}
