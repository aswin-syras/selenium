package com.yuja.evp.pagehelpers;

import org.openqa.selenium.By;

import helperinterfaces.UICheck;

public class AdminPanelRtmpStreamPageHelpers extends AdminPanelGeneralPageHelpers implements UICheck{
	public void CheckPageUI() {
		URL = "https://staging-demo.yuja.com/P/Institution/IngestedLiveStreamManagement/";
		launchUrl(URL, "Xavier University Enterprise Video Platform");
		String sectionTitle = driver.findElement(By.id("secondPartText")).getText();
		boolean check  = waitForElement(By.id("pageTitleHeader"),10).isDisplayed();
		if(check && sectionTitle.equals("RTMP Streams")) {
			reportStep(sectionTitle + " Page loaded successfully", "PASS", false);
			System.out.println(sectionTitle);
		}
		else
			reportStep(sectionTitle + "failed to load", "fail", true);	
	}
}
