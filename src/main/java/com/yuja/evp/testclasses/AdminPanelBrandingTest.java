package com.yuja.evp.testclasses;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.yuja.evp.pagetestmethods.AdminPanelBrandingTestMethods;

public class AdminPanelBrandingTest extends BaseTest {
	final String PASSWORD = "jamNOW123!@#123";
	final String MANAGER_USER = "automation_manager";
	final String INSTRUCTOR_USER = "automation_instructor";
	final String STUDENT_USER = "automation_student";
	
	@Test(description="Admin_panel_branding_set_theme")
	@Parameters({"TestName"})
	public void Admin_panel_branding_set_theme(String testName) throws InterruptedException {
		AdminPanelBrandingTestMethods AdminPanelBranding = new AdminPanelBrandingTestMethods();
			AdminPanelBranding.setTheDefaultTheme(MANAGER_USER, PASSWORD);
	}
}
