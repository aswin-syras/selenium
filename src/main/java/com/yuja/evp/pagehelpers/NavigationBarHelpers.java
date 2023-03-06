package com.yuja.evp.pagehelpers;

import org.openqa.selenium.By;

import com.yuja.evp.utilities.Helpers;

public class NavigationBarHelpers extends Helpers{
	
	public void userLogOut() {
		clickMyAccountButton();
		clickLogoutDropdownOption();
	}
	
	public boolean checkMainMenuLinks() {
		//implement this method so that it checks all for the functionality of all links in the main menu dropdown; that way it can all be incorporated into a single test case
		return true;
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void clickMyAccountButton() {
		clickElement("My Account button", By.xpath("//*[@id=\"bi_userInfoDropdown\"]"));
	}
	
	public void clickLogoutDropdownOption() {
		clickElement("Logout My Account dropdown option", By.xpath("//*[@id=\"logoutText\"]"));
	}
	public void clickMyAccountDropdownOption() {
		clickElement(" My Account dropdown option", By.xpath("//*[@id=\"myAccountText\"]"));
	}
	
	public void clickNotifications() {
		clickElement("Notificaton",By.xpath("//button[@title='Notifications']"),10);
	}
	// following methods added by Ankit --last update 11-14-2022 ----------------------
	
	
	public void clickMainMenuButton() {
		clickElement("Main Menu button", By.id("mainMenuBtn"));
		
	}
	
	public void clickAdminPanel() {
		clickMainMenuButton();
		clickElement("Admin Panel button", By.cssSelector("[data-automation=\"btnAdminPanel\"]"));
		
	}
	
	public void clickCoursesAndGroups() {
		clickMainMenuButton();
		clickElement("Courses and Groups button", By.cssSelector("[data-automation=\"btnCourseAndGroups\"]"));
		
	}
	
	public void clickHimalayas() {
		clickMainMenuButton();
		clickElement("Himalayas button", By.cssSelector("[data-automation=\"btnHimalayas\"]"));
	}
	
	public void clickUsageAndAnalytics() {
		clickMainMenuButton();
		clickElement("Usage & Analytics button", By.cssSelector("[data-automation=\"btnUsageAnalytics\"]"));
		
	}
	
	public void clickDiscussionFeed() {
		clickMainMenuButton();
		clickElement("Discussion Feed button", By.cssSelector("[data-automation=\"btnDiscussionFeed\"]"));
	}
	
	

}
