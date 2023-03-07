package com.yuja.evp.testclasses;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.yuja.evp.pagetestmethods.AdminPanelGeneralTestMethods;

public class AdminPanelTest extends BaseTest{
	
	final String PASSWORD = "jamNOW123!@#123";
	final String MANAGER_USER = "automation_manager";
	
	 @Test(description="Admin_Panel_Test")
	  @Parameters({"TestName"})
	  public void Admin_Panel_Test(String testName) {
		 AdminPanelGeneralTestMethods adminPanel = new AdminPanelGeneralTestMethods();
		try {
			adminPanel.AdminPanelCheckPageUI(MANAGER_USER, PASSWORD);
			//adminPanel.OverViewPage();
		}catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	 }
}