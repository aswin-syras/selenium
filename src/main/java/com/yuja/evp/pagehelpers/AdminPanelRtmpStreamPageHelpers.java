package com.yuja.evp.pagehelpers;

public class AdminPanelRtmpStreamPageHelpers extends AdminPanelGeneralPageHelpers {
	public void CheckPageUI() {
		URL = "https://staging-demo.yuja.com/P/Institution/IngestedLiveStreamManagement/";
		launchUrl(URL, "Xavier University Enterprise Video Platform");
	}
}
