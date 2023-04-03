package com.yuja.evp.modalhelpers;

import java.nio.file.Paths;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.yuja.evp.reports.Report;
import com.yuja.evp.utilities.Driver;
import com.yuja.evp.utilities.Helpers;

public class AddMediaModalHelperMethods extends Helpers{
	boolean mediaUploaded = false;
	
	private WebElement mediaUploadModal = null;
	
	public Boolean mediaUploaded(String mediaTitle, String mediaPath) {
		try {
			mediaUploaded = uploadMedia(mediaTitle, mediaPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mediaUploaded;
	}
	
	private void setMediaUploadModal() {
		System.out.println("Fetching Media Upload modal...");
		this.mediaUploadModal = waitForElement(By.xpath("//*[@id=\"addItemModalDialog\"]"), 10);
		System.out.println("Media Upload modal fetched");
	}
	
	public boolean uploadMedia(String mediaTitle, String mediaPath) {
		setMediaUploadModal();
		mediaPath = Paths.get(mediaPath).toAbsolutePath().toString();
		System.out.println("mediaPath = " + mediaPath);
		System.out.println("Uploading media...");
		sendKeysModal(mediaUploadModal, "uploading media", By.xpath("//*[@id=\"addItemModalDialog\"]/div[1]/div/div/div[2]/div/div/div/div[2]/div[1]/div/div/div[1]/div/input"), mediaPath);		
		WebElement hideProgressIcon = waitForElement(By.xpath("//*[@id=\"drag-and-drop-uploader\"]/div/div/div[2]/div/div[2]/div/div[1]/div/div[2]/div/button/img"), 60);
		boolean mediaUploaded;
		int count = 0;
		if(hideProgressIcon != null) {
			try {
				while(count < 20) {
					mediaUploaded = mediaExists(mediaTitle);
					if(mediaUploaded) {
						Thread.sleep(250);
						clickElement("Hide Progress icon", hideProgressIcon);
						return mediaUploaded;
					} else {
						count++;
						Thread.sleep(500);
					}
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		Report.reportStep(Driver.getDriver(), "Error uploading media : " + mediaTitle, "Fail", true);
		return false;
	}
	
}
