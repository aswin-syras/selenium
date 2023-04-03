package com.yuja.evp.pagehelpers;

import org.openqa.selenium.By;

import com.yuja.evp.utilities.Driver;
import com.yuja.evp.utilities.Helpers;

public class MediaChannelPageHelpers extends Helpers{
	
	private SignInPageHelpers signInPage = new SignInPageHelpers();
	
	public static String pageTitleExpected = "Xavier University Enterprise Video Platform";
	
	public void navigateToMediaChannel() {
		URL = "https://staging-demo.yuja.com/P/VideoManagement/MediaLibrary/MediaChannel/144647";
		launchUrl(URL, "Xavier University Media Library");
		signInPage.loginFast("manager_leo", "jamNOW123!@#123");
	}
	
	public boolean fetchedPageTitle() {
		waitForElement(By.id("firstPartText"), 10);
		return getPageTitle().equals(pageTitleExpected);
	}
	
	private String getPageTitle() {
		return Driver.getDriver().getTitle();
	}

}
