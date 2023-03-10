package com.yuja.evp.testclasses;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.yuja.evp.pagetestmethods.AdminPanelGeneralTestMethods;

public class AdminPanelTest extends BaseTest{
	
	final String PASSWORD = "jamNOW123!@#123";
	final String MANAGER_USER = "automation_manager";
	
	 @Test(description="Admin_panel_test")
	  @Parameters({"TestName"})
	  public void Admin_panel_test(String testName) throws InterruptedException {
		AdminPanelGeneralTestMethods adminPanel = new AdminPanelGeneralTestMethods();
		adminPanel.AdminPanelCheckPageUI(MANAGER_USER, PASSWORD);
	 }
}