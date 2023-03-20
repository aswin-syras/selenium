package com.yuja.evp.pagehelpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.yuja.evp.utilities.Helpers;

public class SignInPageHelpers extends Helpers{
	
	public static String pageTitleExpected = "YuJa: Please Sign In";
	//Test

//	String sheetName="Login_Page";
//	public HashMap<String,String> tdrow;
//	ExcelUtils excelUtils = new ExcelUtils();
	
//	public void login() {
//		
//		//tdrow=excelUtils.testCaseRetrieve(refTestDataName,sheetName);		
//		URL = prop.getProperty(Environment);
//		launchUrl(URL, "Welcome to the YuJa Enterprise Video Platform");
//		
//		if (verifyElementExistReturn(By.xpath(prop.getProperty("LoginPage.Verify"))) == true)
//		{
//			
//			//Entering the credentials and click sign in
//			sendKeys("User ID", By.xpath(prop.getProperty("LoginPage.UserID.Text")), prop.getProperty("UserID"));
//			sendKeys("Password", By.xpath(prop.getProperty("LoginPage.Password.Text")), prop.getProperty("Password"));
//			clickElement("Sign In", By.xpath(prop.getProperty("LoginPage.SignIn.Button")));
//			
//			//Verify Page displayed 
//			 
//			verifyElementExist("Media Library Page is displayed #B", By.xpath(prop.getProperty("MediaLibrary.Verify").replace("&Value", "Media Library")));	
//			//Clearing the Memory
//			//tdrow.clear();
//		}
//		else
//		{
//			reportStep("User Logged in already", "Pass",false);
//		}
//	}
	
	///////////////////////////////////////////////////////////////////////////
	
	public void navigateToLoginPage() {
		//URL = "https://staging-demo.yuja.com/Login?accesstype=YuJa%20Credentials";
		launchUrl(prop.getProperty("URL"), "Xavier University Media Library");
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
		return driver.getTitle();
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
