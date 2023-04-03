package com.yuja.evp.pagetestmethods;

import java.awt.AWTException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;

import com.yuja.evp.modalhelpers.AddMediaModalHelperMethods;
import com.yuja.evp.pagehelpers.AdminPanelBrandingPageHelpers;
import com.yuja.evp.pagehelpers.MediaPlayerHelpers;
import com.yuja.evp.pagehelpers.NavigationBarHelpers;
import com.yuja.evp.reports.Report;
import com.yuja.evp.utilities.Driver;

public class MediaPlayerSideBarTestMethods extends MediaPlayerHelpers{
	
	NavigationBarHelpers navigationBar = new NavigationBarHelpers();
	AddMediaModalHelperMethods modal = new AddMediaModalHelperMethods();
	MediaLibraryPageTestMethods medialibrary = new MediaLibraryPageTestMethods();
	AdminPanelBrandingPageHelpers branding = new AdminPanelBrandingPageHelpers();
	ChromeOptions options = new ChromeOptions();
	
	public void SidebarFunctionalityTest(String userName, String password, String mediaTitle) throws NullPointerException, InterruptedException, AWTException {
		medialibrary.navigateToMyMediaUserLogin(userName, password);
		GotoHTMLPlayer(mediaTitle);
		WebElement sidebar = waitForElement(By.cssSelector("#playbar-sidebar-menuitem"), 1);
		if (sidebar == null) {
			clickElement("Gear icon", By.xpath("//*[@id=\"changeVideoSettingsBtn\"]"));
			clickElement("Sidebar from gear icon", By.xpath("//*[@id=\"sidebar-menuitem\"]"));
		} else { clickElement("Media Player Sidebar", By.xpath("//*[@id=\"tocButton\"]"));
		}

		// captions
		clickElement("Caption button", By.xpath("//img[@title='Captions']"));
		List<WebElement> op = Driver.getDriver().findElements(By.id("captionsContent"));
		int size = op.size();
		String captions; // Arraylist to store captions
		ArrayList<String> captionList = new ArrayList<String>();

		// adding captions to arraylist
		for (int i = 0; i < size; i++) {
			captions = op.get(i).getText();
			captionList.add(captions);
		}
		Report.reportStep(Driver.getDriver(), "Caption list \"" + captionList, "PASS", false);

		// Notes
		clickElement("Note button", By.xpath("//img[@title='Notes']"));
		Driver.getDriver().findElement(By.xpath("//textarea[@id='noteBox']")).click();
		String arraynotes[] = { "first note", "second note", "third note" };
		int length = arraynotes.length;
		for (int i = 0; i < length; i++) {
			typeKeys(arraynotes[i]);
			Driver.getDriver().findElement(By.xpath("//img[@id='noteButton']")).click();
			Driver.getDriver().findElement(By.xpath("//textarea[@id='noteBox']")).click();
		}
		Thread.sleep(2000);

		// Comments
		clickElement("Comment button", By.xpath("//img[@title='Comments']"));
		Driver.getDriver().findElement(By.xpath("//textarea[@id='commentBox']")).click();
		String arraycomments[] = { "first comment", "second comment", "third comment" };
		int arraylength = arraycomments.length;
		for (int i = 0; i < arraylength; i++) {
			typeKeys(arraycomments[i]);
			Driver.getDriver().findElement(By.xpath("//img[@id='commentButton']")).click();
			Driver.getDriver().findElement(By.xpath("//textarea[@id='commentBox']")).click();
		}

		// Resources
		clickElement("Resources button", By.xpath("//img[@title='Resources']"));
		clickElement("Download caption file", By.xpath("//img[@id='downloadTranscriptButton']"));
		Thread.sleep(3000);
		if (captionFileExistsInSystem()) {
			Report.reportStep(Driver.getDriver(), "File was succesfully downloaded and deleted", "PASS", false);
		} else {
			Report.reportStep(Driver.getDriver(), "File was not downloaded ", "FAIL", true);
		}
	}	
	
	public void gear_icon(String userName, String password, String mediaTitle) throws InterruptedException {
		try {
		medialibrary.navigateToMyMediaUserLogin(userName, password);
		GotoHTMLPlayer(mediaTitle);
		Thread.sleep(3000);
		}
		catch(Exception e) {
			System.out.println();
		}
	}

}
