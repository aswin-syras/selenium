package com.yuja.evp.pagetestmethods;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.yuja.evp.pagehelpers.AdminPanelBrandingPageHelpers;
import com.yuja.evp.pagehelpers.MediaPlayerHelpers;

public class AdminPanelBrandingTestMethods extends AdminPanelBrandingPageHelpers{
	
	MediaLibraryPageTestMethods medialibrary = new MediaLibraryPageTestMethods();
	MediaPlayerHelpers player = new MediaPlayerHelpers();
	
	public void setTheDefaultTheme(String username, String password, String theme) throws InterruptedException {
		medialibrary.navigateToMyMediaUserLogin(username, password);
		checkTheme(theme);
		medialibrary.navigateToMyMediaUserLogin(username, password);
		medialibrary.navigateToMyMedia(username);
		player.GotoHTMLPlayer("test");
		WebElement sidebar = waitForElement(By.cssSelector("#playbar-sidebar-menuitem"), 1);
		if (sidebar == null) {
			reportStep("Theme didn't update", "FAIL", true);
		} else { reportStep("Theme updated", "PASS", false);} 
	}
}
