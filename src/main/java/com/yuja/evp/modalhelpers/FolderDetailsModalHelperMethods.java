package com.yuja.evp.modalhelpers;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.yuja.evp.utilities.Helpers;

public class FolderDetailsModalHelperMethods extends Helpers{

	static String oldTitle,oldDescription;
	private WebElement mediaDetailsModal = null;
	
	private void setmediaDetailsModal() {
		System.out.println("Fetching Media Upload modal...");
		this.mediaDetailsModal = waitForElement(By.xpath("/html/body/div[8]/div/div[2]/div/div"), 10);;
		System.out.println("Media Details modal fetched");
	}

	public void deleteCurrentFolder() {
		setmediaDetailsModal();
		mediaDetailsModal.findElement(By.xpath("//*[@id=\"file-modal-tab\"]/div/div/button")).click();
		mediaDetailsModal.findElement(By.xpath("//*[@id=\"file-modal-tab\"]/div/div/div/div/button")).click();
		WebElement deleteModal = waitForElement(By.xpath("//*[@id=\"di_editVideoModal\"]/div[2]/div[1]/div/div"), 10);
		WebElement confirmBox = deleteModal.findElement(By.xpath("//*[@id=\"di_editVideoModal\"]/div[2]/div[1]/div/div/div[2]/div[1]/div/input"));
		confirmBox.sendKeys("Confirm");
		WebElement yes = deleteModal.findElement(By.cssSelector("[title=\"Yes\"]"));
		yes.click();
	}
	
	public void editCurrentFolderDetails(String newFolderName, String newDescription, String newTag) throws Exception {
		setmediaDetailsModal();
		Thread.sleep(3000);
		String title = mediaDetailsModal.findElement(By.xpath("//*[@id=\"titleText\"]")).getAttribute("value");
		String description = mediaDetailsModal.findElement(By.xpath("//*[@id=\"descriptionArea\"]")).getAttribute("value");
		String tags = mediaDetailsModal.findElement(By.xpath("//*[@id=\"s2id_tagSelector\"]/ul")).getAttribute("value");
		oldTitle = mediaDetailsModal.findElement(By.xpath("//*[@id=\"titleText\"]")).getAttribute("value");
		oldDescription = mediaDetailsModal.findElement(By.xpath("//*[@id=\"descriptionArea\"]")).getAttribute("value");mediaDetailsModal.findElement(By.xpath("//*[@id=\"descriptionArea\"]")).getAttribute("value");

		mediaDetailsModal.findElement(By.xpath("//*[@id=\"titleText\"]")).clear();
		mediaDetailsModal.findElement(By.xpath("//*[@id=\"titleText\"]")).sendKeys("QA Testing");
		mediaDetailsModal.findElement(By.xpath("//*[@id=\"descriptionArea\"]")).clear();
		mediaDetailsModal.findElement(By.xpath("//*[@id=\"descriptionArea\"]")).sendKeys("QA Description Test");
		mediaDetailsModal.findElement(By.xpath("//*[@id=\"s2id_tagSelector\"]/ul")).click();
		String[] arraytag = {"tag1","tag2","tag3"};
		int length = arraytag.length;
		for(int i = 0; i < length ; i++) {
			typeKeys(arraytag[i]);
			mediaDetailsModal.findElement(By.xpath("//*[@id=\"select2-drop\"]/ul/li/div")).click();
		}
		Thread.sleep(3000);
		mediaDetailsModal.findElement(By.xpath("/html/body/div[8]/div/div[2]/div/div/div[3]/div/button[2]")).click();
	}
	
	public void compareFieldEntires(String newFolderName, String newDescription, String newTag) throws Exception {
		setmediaDetailsModal();
		String title = mediaDetailsModal.findElement(By.xpath("//*[@id=\"titleText\"]")).getAttribute("value");
		String description = mediaDetailsModal.findElement(By.xpath("//*[@id=\"descriptionArea\"]")).getAttribute("value");
		String tags = mediaDetailsModal.findElement(By.xpath("//*[@id=\"s2id_tagSelector\"]/ul")).getAttribute("value");
		
		List<WebElement> op = mediaDetailsModal.findElements(By.className("select2-search-choice"));
		int size = op.size();
	
		String tagName; //Arraylist to store tags
		ArrayList<String> tagNames = new ArrayList<String>();
		
		//adding tags to arraylist
		for(int i = 0; i< size ; i++ ) {
			tagName = op.get(i).getText();
			tagNames.add(tagName);
		}
		if(tagNames.contains("Tag") && oldTitle.equals(title) && oldDescription.equals(description)) {
			reportStep("The folder with the title \"" + oldTitle + "\" failed to edit", "FAIL", true);
		}
		else {
			reportStep("The folder with the title \"" + oldTitle + "\" is now edited and the new title is \"" + title + "\"", "PASS", false);
			reportStep("Added descrption \"" + description + "\" and tags \"" + tagNames + "\"", "PASS", false);
			reportStep("The folder with the title \"" + title + "\" has been deleted ", "PASS", false);
		}
	}
	
}
