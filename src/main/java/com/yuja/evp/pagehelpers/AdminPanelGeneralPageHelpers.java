package com.yuja.evp.pagehelpers;

import org.openqa.selenium.By;

import com.yuja.evp.utilities.Driver;
import com.yuja.evp.utilities.Helpers;

public class AdminPanelGeneralPageHelpers extends Helpers {
	
	public static String title;
	private SignInPageHelpers signInPage = new SignInPageHelpers();

	public void navigatetoAdminPanelUserLogin(String userName, String password) {
		signInPage.navigateToLoginPage();
		Driver.getDriver().manage().window().maximize();
		signInPage.loginFast(userName, password);
		waitForElement(By.id("navbar-header"), 10);
		URL = prop.getProperty("URL")+"P/Institution/OverviewManagement";
		launchUrl(URL, "Test Automation Enterprise Video Platform");
	}
}
