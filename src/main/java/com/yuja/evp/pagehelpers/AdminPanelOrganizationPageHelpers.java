package com.yuja.evp.pagehelpers;

public class AdminPanelOrganizationPageHelpers extends AdminPanelGeneralPageHelpers {
	public void CheckPageUI() {
		URL = "https://staging-demo.yuja.com/P/Institution/Organization/";
		launchUrl(URL, "Xavier University Enterprise Video Platform");
	}
}
