package com.yuja.evp.pagetestmethods;

import com.yuja.evp.pagehelpers.AdminPanelBrandingPageHelpers;

public class AdminPanelBrandingTestMethods extends AdminPanelBrandingPageHelpers{
	
	MediaLibraryPageTestMethods medialibrary = new MediaLibraryPageTestMethods();	
	
	public void setTheDefaultTheme(String username, String password, String theme) throws InterruptedException {
		medialibrary.navigateToMyMediaUserLogin(username, password);
		checkTheme(theme);
	}
}
