package com.yuja.evp.pagehelpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.yuja.evp.modalhelpers.AddMediaModalHelperMethods;
import com.yuja.evp.pagetestmethods.MediaLibraryPageTestMethods;
import com.yuja.evp.utilities.Helpers;


public class MediaPlayerHelpers extends Helpers {
	NavigationBarHelpers navigationBar = new NavigationBarHelpers();
	AddMediaModalHelperMethods modal = new AddMediaModalHelperMethods();
	MediaLibraryPageTestMethods medialibrary = new MediaLibraryPageTestMethods();	
	//ChromeOptions options = new ChromeOptions();
	
	public void GotoHTMLPlayer(String mediaTitle) throws InterruptedException {
		boolean checkMedia = medialibrary.mediaExists(mediaTitle);
		if(checkMedia) {
			WebElement media = medialibrary.getMedia(mediaTitle);
			medialibrary.hoverOverElement(media);
			clickElement(mediaTitle,media);	
			switchToIframe("video frame", By.cssSelector("iframe#ifi_videoPlayerContainer"), 10);
			clickElement("Play button clicked",By.xpath("//*[@id=\"previewPlay\"]") );
			Thread.sleep(2000);
			clickElement("Paused",By.xpath("//*[@id=\"focusablePlayPauseButton\"]/div"));	
		}
	}
	
	public void gearicon() {
		clickElement("Settings icon",By.className("settings-icon"));
	}
}



