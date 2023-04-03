package com.yuja.evp.pagehelpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.yuja.evp.utilities.Driver;
import com.yuja.evp.utilities.Helpers;


public class HomePageHelpers extends Helpers{
	
	String pageTitleExpected = "Xavier University Media Library";
	
	public void navigateToHomePage() {
		URL = "https://staging-demo.yuja.com/";
		launchUrl(URL, "Xavier University Media Library");
	}
	
	//workflow methods
	public void loginViaCredentials() {
		selectYujaCredentials();
		clickLoginButton();
	}
	
	public void loginViaSSO() {
		selectSSO();
		clickLoginButton();
	}
	
	public boolean fetchedPageTitle() {
		waitForElement(By.id("firstPartText"), 10);
		return getPageTitle().equals(pageTitleExpected);
	}
	
	public boolean fetchedSSOPageTitle() {
		String ssoPageTitle = "JumpCloud User Portal - Login";
		waitForElement(By.xpath("/html/head/title"), 10);
		return getPageTitle().equals(ssoPageTitle);
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////
	//helper methods
	private void selectYujaCredentials() {
		openLoginSelectDropDown();
		clickElement("Yuja credentials dropdown option", By.xpath("//*[@id=\"loginSelect\"]/div/li[1]/button"));
	}
	
	private void selectSSO() {
		openLoginSelectDropDown();
		clickElement("SSO dropdown option", By.xpath("//*[@id=\"loginSelect\"]/div/li[2]/button"));
	}
	
	
	private void openLoginSelectDropDown(){
		clickElement("Dropdown menu", By.xpath("//*[@id=\"dropdownMenuButton\"]/span[2]"));
	}
	
	private void clickLoginButton() {
		clickElement("Login button", By.xpath("//*[@id=\"loginBtn\"]"));
	}
	
	private String getPageTitle() {
		return Driver.getDriver().getTitle();
	}

}
