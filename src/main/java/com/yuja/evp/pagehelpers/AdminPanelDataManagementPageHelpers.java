package com.yuja.evp.pagehelpers;

import org.openqa.selenium.By;

import helperinterfaces.UICheck;

public class AdminPanelDataManagementPageHelpers extends AdminPanelGeneralPageHelpers implements UICheck{
	
	public void CheckPageUI() {
		URL = prop.getProperty("URL")+"P/Institution/MediaArchive/";
		launchUrl(URL, "Test Automation Enterprise Video Platform");
		String sectionTitle = driver.findElement(By.id("secondPartText")).getText();
		boolean check  = waitForElement(By.id("instcontent"),10).isDisplayed();
		if(check && sectionTitle.equals("Data Management")) {
			reportStep(sectionTitle + " Page loaded successfully", "PASS", false);
			System.out.println(sectionTitle);
		} else reportStep(sectionTitle + "failed to load", "fail", true);	
	}
}
