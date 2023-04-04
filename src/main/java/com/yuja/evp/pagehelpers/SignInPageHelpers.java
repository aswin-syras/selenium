package com.yuja.evp.pagehelpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.yuja.evp.utilities.Driver;
import com.yuja.evp.utilities.Helpers;

public class SignInPageHelpers extends Helpers{
	
	public static String pageTitleExpected = "YuJa: Please Sign In";
	
	public void navigateToLoginPage() {
		URL = prop.getProperty("loginPageURL");
		launchUrl(URL, "Test Automation Enterprise Video Platform");
	}
	
	public void loginFast(String username, String password) {
		fillInUserId(username);
		fillInPassword(password);
		openLoginSelectDropDown();
		selectYujaCredentialsLogin();
		checkKeepMeSignedIn();
		clickSignInButton();
	}
	
	public void loginViaSSO() {
		openLoginSelectDropDown();
		selectSSOLogin();
		clickSignInButtonSSO();
	}
	
	public boolean checkInvalidCredentialsMessage() {
		String expectedMessage = "Unable to log in: Invalid Credentials";
		WebElement loginMessage = waitForElement(By.xpath("//*[@id=\"loginMessage\"]/div/div/span"), 20);
		
		return loginMessage.getText().equals(expectedMessage);
	}
	
	public boolean fetchedPageTitle() {
		waitForElement(By.xpath("/html/head/title"), 10);
		return getPageTitle().equals(pageTitleExpected);
	}
	
	public boolean fetchedSSOPageTitle() {
		String ssoPageTitle = "JumpCloud User Portal - Login";
		waitForElement(By.xpath("/html/head/title"), 10);
		return getPageTitle().equals(ssoPageTitle);
	}
	
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public String getPageTitle() {
		return Driver.getDriver().getTitle();
	}
	
	private void fillInUserId(String username) {
		sendKeys("User ID", By.xpath("//*[@id=\"loginuserid\"]"), username);
	}
	
	private void fillInPassword(String password) {
		sendKeys("Password", By.xpath("//*[@id=\"password\"]"), password);
	}
	
	private void openLoginSelectDropDown() {
		clickElement("Login method select dropdown", By.xpath("//*[@id=\"loginSelect\"]"));
	}
	
	private void selectYujaCredentialsLogin() {
		openLoginSelectDropDown();
		clickElement("Yuja Credentials dropdown option", By.xpath("//*[@id=\"loginSelect\"]/option[1]"));
	}
	
	public void selectSSOLogin() {
		openLoginSelectDropDown();
		clickElement("SSO dropdown option", By.xpath("//*[@id=\"loginSelect\"]/option[2]"));
	}
	
	
	private void clickSignInButton() {
		clickElement("Sign In button", By.xpath("//*[@id=\"loginButton\"]"));
	}
	
	private void clickSignInButtonSSO() {
		clickElement("Sign in button SSO", By.xpath("//*[@id=\"loginButtonSSO\"]"));
	}
	
	
	private void checkKeepMeSignedIn() {
		clickElement("Keep me signed in box", By.xpath("//*[@id=\"rememberMeLabel\"]/input"));
	}
	
	
	
}
