package com.yuja.evp.pagehelpers;

import org.openqa.selenium.By;

import com.yuja.evp.utilities.Helpers;

import helperinterfaces.UICheck;

public class AdminPanelOverviewPageHelpers extends Helpers implements UICheck{
	
	public void CheckPageUI() {
		URL = prop.getProperty("URL")+"P/Institution/OverviewManagement";
		launchUrl(URL, "Test Automation Enterprise Video Platform");
		String sectionTitle = driver.findElement(By.id("secondPartText")).getText();
		boolean check  = waitForElement(By.id("userBtn"),10).isDisplayed();
		if(check && sectionTitle.equals("Overview")) {
			reportStep(sectionTitle + " Page loaded successfully", "PASS", false);
			System.out.println(sectionTitle);
		} else reportStep("Admin Panel " + sectionTitle + "failed to load", "fail", true);	
	}
}
