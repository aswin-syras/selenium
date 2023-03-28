package com.yuja.evp.testclasses;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.yuja.evp.pagetestmethods.AdminPanelBrandingTestMethods;

public class AdminPanelBrandingTest extends BaseTest {

	@Test(description="Admin_panel_branding_set_theme")
	@Parameters({"TestName"})
	public void Admin_panel_branding_set_theme(String testName) throws InterruptedException {
		AdminPanelBrandingTestMethods AdminPanelBranding = new AdminPanelBrandingTestMethods();
			AdminPanelBranding.setTheDefaultTheme(prop.getProperty("ManagerID"), prop.getProperty("Password"),"Automation theme");
	}
}
