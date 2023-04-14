package com.yuja.evp.pagetestmethods;

import com.yuja.evp.pagehelpers.AdminPanelRosterPageHelpers;
import com.yuja.evp.reports.Report;
import com.yuja.evp.utilities.Driver;

public class RosterPageTestMethods extends AdminPanelRosterPageHelpers{
	
	public void createAndDeleteUser(String loginUserName, String adminPassword, String role) {
		try {
			String newUserID = "Testing" + role + "User" + getRandomInteger(100);
			navigateToAdminPanelRosterPageUserLogin(loginUserName, adminPassword);
			clickCreateUserButton();
			fillInUserInfo(newUserID, role);
			deleteUser(newUserID);
			Thread.sleep(100);
			} catch(Exception e) {
				e.printStackTrace();
				Report.reportStep(Driver.getDriver(), "Test scenario did not complete all of its steps", "FAIL", false);
		}
	}
	
	public void actAsUser(String userName, String adminID, String adminPassword) {
		try {
			navigateToAdminPanelRosterPageUserLogin(adminID,adminPassword);
		    clickActAsUserButton();
		    selectActAsUser(userName);			
			}		
		catch(Exception e) {
			e.printStackTrace();
			Report.reportStep(Driver.getDriver(), "Test scenario did not complete all of its steps", "FAIL", false);			
			}
			}
	
	public void checkResetPasswordButton(String loginUserName, String adminPassword, String role) {
		try {
			String newUserID = "Testing" + role + "User" + getRandomInteger(100);
			navigateToAdminPanelRosterPageUserLogin(loginUserName,adminPassword);
			clickCreateUserButton();
			fillInUserInfo(newUserID,role);
			rosterButtons(newUserID);
			resetPasswordButton();
			URL = "https://staging-demo.yuja.com/P/Institution/TypeRoster/";
			launchUrl(URL, "Xavier University Enterprise Video Platform");
			deleteUser(newUserID);
			}	
		catch(Exception e) {
			e.printStackTrace();
			Report.reportStep(Driver.getDriver(), "Test scenario did not complete all of its steps", "FAIL", false);		
			}
			}	
		
	public void checkAddToCourseButton(String loginUserName, String adminPassword, String role) {
		try {
			String newUserID = "Testing" + role + "User" + getRandomInteger(100);
			navigateToAdminPanelRosterPageUserLogin(loginUserName,adminPassword);
			clickCreateUserButton();
			fillInUserInfo(newUserID,role);
			rosterButtons(newUserID);
			addToCourseButton();
			URL = "https://staging-demo.yuja.com/P/Institution/TypeRoster/";
			launchUrl(URL, "Xavier University Enterprise Video Platform");
			deleteUser(newUserID);
			}	
		catch(Exception e) {
			e.printStackTrace();
			Report.reportStep(Driver.getDriver(), "Test scenario did not complete all of its steps", "FAIL", false);			
			}
			}	
		
	public void checkMediaLibraryButton(String loginUserName, String adminPassword, String role) {
		try {
			String newUserID = "Testing" + role + "User" + getRandomInteger(100);
			navigateToAdminPanelRosterPageUserLogin(loginUserName,adminPassword);
			clickCreateUserButton();
			fillInUserInfo(newUserID,role);
			rosterButtons(newUserID);
			mediaLibraryButton();
			URL = "https://staging-demo.yuja.com/P/Institution/TypeRoster/";
			launchUrl(URL, "Xavier University Enterprise Video Platform");
			deleteUser(newUserID);		
			}	
		catch(Exception e) {
			e.printStackTrace();
			Report.reportStep(Driver.getDriver(), "Test scenario did not complete all of its steps", "FAIL", false);			
			}
			}	
		
	public void checkDeleteUserButton(String loginUserName, String adminPassword, String role) {
		try {
			String newUserID = "Testing" + role + "User" + getRandomInteger(100);
			navigateToAdminPanelRosterPageUserLogin(loginUserName,adminPassword);
			clickCreateUserButton();
			fillInUserInfo(newUserID,role);
			rosterButtons(newUserID);
			deleteUserButton();	
			}	
		catch(Exception e) {
			e.printStackTrace();
			Report.reportStep(Driver.getDriver(), "Test scenario did not complete all of its steps", "FAIL", false);			
			}
			}	
			}
