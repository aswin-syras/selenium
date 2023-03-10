package com.yuja.evp.pagehelpers;

import org.openqa.selenium.By;

import helperinterfaces.UICheck;

public class AdminPanelBrandingPageHelpers extends AdminPanelGeneralPageHelpers implements UICheck{
	
	public void CheckPageUI() {
		URL = "https://staging-demo.yuja.com/P/Institution/InstitutionBranding/";
		launchUrl(URL, "Xavier University Enterprise Video Platform");
		String sectionTitle = driver.findElement(By.id("secondPartText")).getText();
		boolean check  = waitForElement(By.id("institutionBranding"),10).isDisplayed();
		if(check && sectionTitle.equals("Branding")) {
			reportStep(sectionTitle + " Page loaded successfully", "PASS", false);
			System.out.println(sectionTitle);
		}
		else reportStep(sectionTitle + "failed to load", "fail", true);	
	}
}
