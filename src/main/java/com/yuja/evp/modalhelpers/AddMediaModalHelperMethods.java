package com.yuja.evp.modalhelpers;

import java.nio.file.Paths;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.yuja.evp.utilities.Helpers;

public class AddMediaModalHelperMethods extends Helpers{
	
	private WebElement mediaUploadModal = null;
	
	public Boolean mediaUploaded(String mediaTitle, String mediaPath) {
		try {
			uploadMedia(mediaPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mediaExists(mediaTitle);
	}
	
	private void setMediaUploadModal() {
		System.out.println("Fetching Media Upload modal...");
		this.mediaUploadModal = waitForElement(By.xpath("//*[@id=\"addItemModalDialog\"]"), 10);
		System.out.println("Media Upload modal fetched");
	}
	
	public void uploadMedia(String mediaPath) throws InterruptedException {
		setMediaUploadModal();
		mediaPath = Paths.get(mediaPath).toAbsolutePath().toString();
		System.out.println("mediaPath = " + mediaPath);
		System.out.println("Uploading media...");
		sendKeysModal(mediaUploadModal, "uploading media", By.xpath("//*[@id=\"addItemModalDialog\"]/div[1]/div/div/div[2]/div/div/div/div[2]/div[1]/div/div/div[1]/div/input"), mediaPath);		
		Thread.sleep(1000);
		clickElement("Hide Progress icon", By.xpath("//*[@id=\"drag-and-drop-uploader\"]/div/div/div[2]/div/div[2]/div/div[1]/div/div[2]/div/button/img"), 60);
		Thread.sleep(3000);
	}
	
}
