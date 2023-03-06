package com.yuja.evp.pagetestmethods;

import com.yuja.evp.pagehelpers.RosterPageHelpers;

public class RosterPageTestMethods extends RosterPageHelpers{
	
	public void createAndDeleteUser(String loginUserName, String adminPassword, String role) {
		try {
			String newUserID = "Testing" + role + "User" + getRandomInteger(100);
			navigateToAdminPanelRosterPageUserLogin(loginUserName, adminPassword);
			clickCreateUserButton();
			fillInUserInfo(newUserID, role);
			deleteUser(newUserID);
			Thread.sleep(100);
			}				
		catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();		
			}
			}
	
	public void actAsUser(String userName, String adminID, String adminPassword) {
		try {
			navigateToAdminPanelRosterPageUserLogin(adminID,adminPassword);
		    clickActAsUserButton();
		    selectActAsUser(userName);			
			}		
		catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();			
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
				System.out.println(e.getMessage());
				e.printStackTrace();			
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
			System.out.println(e.getMessage());
			e.printStackTrace();			
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
				System.out.println(e.getMessage());
				e.printStackTrace();			
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
			System.out.println(e.getMessage());
			e.printStackTrace();			
			}
			}	
			}
