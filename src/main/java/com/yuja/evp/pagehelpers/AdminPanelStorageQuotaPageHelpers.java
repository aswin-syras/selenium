package com.yuja.evp.pagehelpers;

import org.openqa.selenium.By;

import com.yuja.evp.reports.Report;
import com.yuja.evp.utilities.Driver;

import helperinterfaces.UICheck;

public class AdminPanelStorageQuotaPageHelpers extends AdminPanelGeneralPageHelpers implements UICheck{
	
	public void CheckPageUI() {
		URL = prop.getProperty("URL")+"/P/Institution/QuotaManagement/";
		launchUrl(URL, "Test Automation Enterprise Video Platform");
		String sectionTitle = Driver.getDriver().findElement(By.id("secondPartText")).getText();
		boolean check  = waitForElement(By.id("establish-quota"),10).isDisplayed();
		if(check && sectionTitle.equals("Storage Quota")) {
			Report.reportStep(Driver.getDriver(), sectionTitle + " Page loaded successfully", "PASS", false);
			System.out.println(sectionTitle);
		} else Report.reportStep(Driver.getDriver(), sectionTitle + "failed to load", "fail", true);	
	}
}
