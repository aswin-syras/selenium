package com.yuja.evp.testclasses;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.yuja.evp.pagetestmethods.AdminPanelGeneralTestMethods;

public class AdminPanelTest extends BaseTest{
	
	 @Test(description="Admin_panel_test")
	  @Parameters({"TestName"})
	  public void Admin_panel_test(String testName) throws InterruptedException {
		AdminPanelGeneralTestMethods adminPanel = new AdminPanelGeneralTestMethods();
		adminPanel.AdminPanelCheckPageUI(prop.getProperty("ManagerID"), prop.getProperty("Password"));
	 }
}