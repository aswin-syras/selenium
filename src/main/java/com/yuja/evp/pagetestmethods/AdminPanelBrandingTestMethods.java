package com.yuja.evp.pagetestmethods;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.yuja.evp.pagehelpers.AdminPanelBrandingPageHelpers;
import com.yuja.evp.pagehelpers.MediaPlayerHelpers;
import com.yuja.evp.reports.Report;
import com.yuja.evp.utilities.Driver;

public class AdminPanelBrandingTestMethods extends AdminPanelBrandingPageHelpers{
	
	MediaLibraryPageTestMethods medialibrary = new MediaLibraryPageTestMethods();
	MediaPlayerHelpers player = new MediaPlayerHelpers();
	
	public void setTheDefaultTheme(String username, String password, String theme) throws InterruptedException {
		try {
			medialibrary.navigateToMyMediaUserLogin(username, password);
			checkTheme(theme);
			medialibrary.navigateToMyMediaUserLogin(username, password);
			medialibrary.navigateToMyMedia(username);
			player.GotoHTMLPlayer("test");
			WebElement sidebar = waitForElement(By.cssSelector("#playbar-sidebar-menuitem"), 1);
			if (sidebar == null) {
				Report.reportStep(Driver.getDriver(), "Theme didn't update", "FAIL", true);
			} else { Report.reportStep(Driver.getDriver(), "Theme updated", "PASS", false);} 
			
		} catch(Exception e){
			Report.reportStep(Driver.getDriver(), "Test scenario did not complete all of its steps", "FAIL", false);
		}
	}
}
