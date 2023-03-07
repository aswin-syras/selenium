package com.yuja.evp.pagehelpers;

public class AdminPanelDataManagementPageHelpers extends AdminPanelGeneralPageHelpers{
	public void CheckPageUI() {
		URL = "https://staging-demo.yuja.com/P/Institution/MediaArchive/";
		launchUrl(URL, "Xavier University Enterprise Video Platform");
	}
}
