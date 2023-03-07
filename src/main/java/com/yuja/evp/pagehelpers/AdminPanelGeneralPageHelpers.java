package com.yuja.evp.pagehelpers;

import org.openqa.selenium.By;
import com.yuja.evp.utilities.Helpers;

public class AdminPanelGeneralPageHelpers extends Helpers {
	
	public static String title;
	private SignInPageHelpers signInPage = new SignInPageHelpers();
	public void navigatetoAdminPanelUserLogin(String userName, String password) {
		signInPage.navigateToLoginPage();
		driver.manage().window().maximize();
		signInPage.loginFast(userName, password);
		waitForElement(By.id("navbar-header"), 10);
		URL = "https://staging-demo.yuja.com/P/Institution/OverviewManagement";
		launchUrl(URL, "Xavier University Enterprise Video Platform");
	}
}
