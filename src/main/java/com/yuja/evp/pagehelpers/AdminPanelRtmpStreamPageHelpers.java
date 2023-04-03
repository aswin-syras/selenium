package com.yuja.evp.pagehelpers;

import org.openqa.selenium.By;

import com.yuja.evp.reports.Report;
import com.yuja.evp.utilities.Driver;

import helperinterfaces.UICheck;

public class AdminPanelRtmpStreamPageHelpers extends AdminPanelGeneralPageHelpers implements UICheck{
	
	public void CheckPageUI() {
		URL = prop.getProperty("URL")+"P/Institution/IngestedLiveStreamManagement/";
		launchUrl(URL, "Test Automation Enterprise Video Platform");
		String sectionTitle = Driver.getDriver().findElement(By.id("secondPartText")).getText();
		boolean check  = waitForElement(By.id("pageTitleHeader"),10).isDisplayed();
		if(check && sectionTitle.equals("RTMP Streams")) {
			Report.reportStep(Driver.getDriver(), sectionTitle + " Page loaded successfully", "PASS", false);
			System.out.println(sectionTitle);
		} else Report.reportStep(Driver.getDriver(), sectionTitle + "failed to load", "fail", true);	
	}
}
