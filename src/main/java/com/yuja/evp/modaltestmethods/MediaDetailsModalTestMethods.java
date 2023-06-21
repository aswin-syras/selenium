package com.yuja.evp.modaltestmethods;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.yuja.evp.modalhelpers.MediaDetailsModalHelperMethods;
import com.yuja.evp.pagehelpers.MediaLibraryPageHelpers;
import com.yuja.evp.pagehelpers.NavigationBarHelpers;
import com.yuja.evp.reports.Report;
import com.yuja.evp.utilities.Driver;

import net.jodah.failsafe.internal.util.Assert;

public class MediaDetailsModalTestMethods extends MediaDetailsModalHelperMethods {

	MediaLibraryPageHelpers mediaLibrary = new MediaLibraryPageHelpers();
	NavigationBarHelpers navhelper = new NavigationBarHelpers();

	public void ShareMedia(String replaceVideo, String shareUserName, String shareUserPassword)throws InterruptedException {
		WebElement mediaFile = getMedia(replaceVideo);
		hoverOverElement(mediaFile);
		clickElement("Share button", By.cssSelector("[data-automation=\"btnInVideoMenuShare\"]"), 20);
		Thread.sleep(3000);
		WebElement accesstextbox = waitForElement(By.xpath("//*[@id=\"shareResourceModalDialog\"]/div[1]/div/div/div[2]/div[1]/div[1]/div[2]/div/form/div[1]"),10);
		clickElement("Access dropdown", accesstextbox);
		Thread.sleep(3000);
		typeKeys(shareUserName);
		clickElement(shareUserName, By.xpath("//*[@id=\"select2-drop\"]/ul/li/div"));

		WebElement modal = waitForElement(By.xpath("//*[@id=\"shareResourceModalDialog\"]/div[1]/div/div"), 10);
		clickElement(modal, "Share button", By.cssSelector("[title=\"Share\"]"), 10);
		Thread.sleep(1000);
		clickElement(modal, "Save button", By.cssSelector("[title=\"Save\"]"), 10);
		Thread.sleep(1000);
		clickElement(modal, "Share modal close button", By.cssSelector("[title=\"Close\"]"), 10);
	}
	
	public void checkPublishandUnpublishMediaDetails(String userName, String password, String mediaTitle, String destinationChannelName) throws InterruptedException{
		//publishing the media
		mediaLibrary.navigateToMyMediaUserLogin(userName, password);
		publishMediaFromMediaDetails(mediaTitle, destinationChannelName);
		//checking that the media was published
		mediaLibrary.navigateToChannel(destinationChannelName);
		boolean mediaIsPublished = mediaExists(mediaTitle);
		if(mediaIsPublished) {
			Report.reportStep(Driver.getDriver(), "Media with title " + mediaTitle + " was succesfully published " + destinationChannelName, "PASS", false);
		}
		else {
			Report.reportStep(Driver.getDriver(), "Media with title " + mediaTitle + " was not published " + destinationChannelName, "FAIL", true);
		}
		//unpublishing the media
		mediaLibrary.unpublishMedia(mediaTitle);
		//check that media was unpublished
		mediaIsPublished = mediaExists(mediaTitle);
		if(!mediaIsPublished) {
			Report.reportStep(Driver.getDriver(), "Media with title " + mediaTitle + " was succesfully unpublished " + destinationChannelName, "PASS", false);
		}
		else {
			Report.reportStep(Driver.getDriver(), "Media with title " + mediaTitle + " was not unpublished " + destinationChannelName, "FAIL", true);
		}
	}
	public void replaceMedia(String userName, String password, String replaceMedia, String VideoReplaced,String userID,String shareUserName, String shareUserPassword, String destinationChannelName) throws InterruptedException {
			mediaLibrary.navigateToMyMediaUserLogin(userName, password);
			mediaLibrary.bulkMediaUpload("src\\fileResources\\replaceTest");
			boolean processed = mediaLibrary.mediaIsProcessed(replaceMedia, 60, 8);
			if(processed) 
			{	
				publishMediaFromMediaDetails(replaceMedia,destinationChannelName);
				ReplaceVideo(replaceMedia,VideoReplaced);
				CheckforReplaceMedia(replaceMedia,VideoReplaced,userName,password,destinationChannelName);
			}
			else
			{
				Report.reportStep(Driver.getDriver(), replaceMedia + " media not fully proccessed", "FAIL", true);
				Assert.state(processed, "Video failed to process");
			}
	}
}
