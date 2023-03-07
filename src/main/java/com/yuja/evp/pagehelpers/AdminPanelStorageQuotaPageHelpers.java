package com.yuja.evp.pagehelpers;

import org.openqa.selenium.By;

import helperinterfaces.UICheck;

public class AdminPanelStorageQuotaPageHelpers extends AdminPanelGeneralPageHelpers implements UICheck{
	public void CheckPageUI() {
		URL = "https://staging-demo.yuja.com/P/Institution/QuotaManagement/";
		launchUrl(URL, "Xavier University Enterprise Video Platform");
		String sectionTitle = driver.findElement(By.id("secondPartText")).getText();
		boolean check  = waitForElement(By.id("establish-quota"),10).isDisplayed();
		if(check && sectionTitle.equals("Storage Quota")) {
			reportStep(sectionTitle + " Page loaded successfully", "PASS", false);
			System.out.println(sectionTitle);
		}
		else
			reportStep(sectionTitle + "failed to load", "fail", true);	
	}
}
