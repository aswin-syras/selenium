package com.yuja.evp.pagetestmethods;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.yuja.evp.modalhelpers.AddMediaModalHelperMethods;
import com.yuja.evp.modalhelpers.FolderDetailsModalHelperMethods;
import com.yuja.evp.modalhelpers.MediaDetailsModalHelperMethods;
import com.yuja.evp.modaltestmethods.MediaDetailsModalTestMethods;
import com.yuja.evp.pagehelpers.MediaChannelPageHelpers;
import com.yuja.evp.pagehelpers.MediaLibraryPageHelpers;
import com.yuja.evp.pagehelpers.NavigationBarHelpers;
import com.yuja.evp.reports.Report;
import com.yuja.evp.utilities.Driver;

import net.jodah.failsafe.internal.util.Assert;


public class MediaLibraryPageTestMethods extends MediaLibraryPageHelpers {
	
	MediaChannelPageHelpers mediaChannel = new MediaChannelPageHelpers();
	NavigationBarHelpers navigationBar = new NavigationBarHelpers();
	AddMediaModalHelperMethods modal = new AddMediaModalHelperMethods();
	MediaLibraryPageHelpers mediaLibrary = new MediaLibraryPageHelpers();
	MediaDetailsModalHelperMethods mediaDetailsModal = new MediaDetailsModalHelperMethods();
	
	public void shareMediaTest(String shareAccessLevel, String mediaName, String sharerUserName, String sharerPassword, String recipientUserName, String recipientPassword, String recipientActualName) throws InterruptedException {
		String[] fullAccessInformation = {"Full access option", "[title=\"Full control of the media including deletion rights\"]"};
		String[] EditAccessInformation = {"Edit access option", "[title=\"Editing and publishing capabilities without deletion rights\"]"};
		String[] readAccessInformation = {"Read only option", "[title=\"Ability to view and stream the content only\"]"};
		
		HashMap<String, String[]> acessLevelInformation = new HashMap<String, String[]>();
		acessLevelInformation.put("fullAccess", fullAccessInformation);
		acessLevelInformation.put("editAccess", EditAccessInformation);
		acessLevelInformation.put("readAccess", readAccessInformation);
		
		String fieldName = acessLevelInformation.get(shareAccessLevel)[0];
		String cssSelector = acessLevelInformation.get(shareAccessLevel)[1];
		
		navigateToMyMediaUserLogin(sharerUserName, sharerPassword);
		
		///////////////// the following block of code performs the sharing of media
		
		WebElement media = getMedia(mediaName);
		hoverOverElement(media);
		
		clickElement(media, "More menu share option", By.cssSelector("[data-automation=\"btnInVideoMenuShare\"]"), 10);
		
		Thread.sleep(3000);
		
		clickElement("Access dropdown", By.xpath("//*[@id=\"shareResourceModalDialog\"]/div[1]/div/div/div[2]/div[1]/div[1]/div[2]/div/form/div[2]/select"), 10);
		clickElement(fieldName, By.cssSelector(cssSelector), 10);
		
		clickElement("Share with textbox", By.xpath("//*[@id=\"s2id_sharedUserSelect\"]/ul"), 10);
		verifyElementExist("Dropdown list", By.xpath("//*[@id=\"select2-drop\"]/ul"), 10);

		typeKeys(recipientActualName);
		Thread.sleep(1000);
		
		WebElement modal = waitForElement(By.xpath("//*[@id=\"shareResourceModalDialog\"]/div[1]/div/div"), 10);
		clickElement(modal, "Dropdown element", By.xpath("//*[@id=\"select2-drop\"]/ul/li"), 10);
		Thread.sleep(1000);
		clickElement(modal, "Share button", By.cssSelector("[title=\"Share\"]"), 10);
		Thread.sleep(1000);
		clickElement(modal, "Save button", By.cssSelector("[title=\"Save\"]"), 10);
		Thread.sleep(1000);
		clickElement(modal, "Close button", By.cssSelector("[title=\"Close\"]"), 10);
		Thread.sleep(1000);
		
		////////////////
		
		navigationBar.userLogOut();
		navigateToSharedWithMeUserLogin(recipientUserName, recipientPassword);
		if(mediaExists(mediaName)){
			WebElement video = getMedia(mediaName);
			hoverOverElement(video);
			flash(video);
			List<WebElement> moreMenuOptions = video.findElements(By.cssSelector("[role=\"menuitem\"]"));
			int numberOfMoreMenuOptions = moreMenuOptions.size();
			if(numberOfMoreMenuOptions == 6) {
				Report.reportStep(Driver.getDriver(), "The media with the title \"" + mediaName + "\" was shared with full access", "PASS", false);
			}
			else {
				Report.reportStep(Driver.getDriver(), "The media with the title \"" + mediaName + "\" was not shared with full access", "FAIL", true);
			}
		}
		else {
			Report.reportStep(Driver.getDriver(), "The media with the title \"" + mediaName + "\" was not shared succesfully", "FAIL", true);
		}
	}
	
	public void editFolder(String userName, String password, String originalFolderName, String newFolderName, String newDescription, String newTag) throws Exception {
		navigateToMyMediaUserLogin(userName, password);
		createNewFolder(originalFolderName);
		accessFolderMoreMenu(originalFolderName);
		FolderDetailsModalHelperMethods modal = new FolderDetailsModalHelperMethods();
		
		modal.editCurrentFolderDetails(newFolderName, newDescription, newTag);
		modal.compareFieldEntires(newFolderName, newDescription, newTag);
		modal.deleteCurrentFolder();
	}
	
	public void bulkFavoriteAndUnfavoriteTest(String userName, String password, String folderName) throws Exception {
		navigateToMyMediaUserLogin(userName, password);
		createNewFolder(folderName);
		accessFolder(folderName);
		if(bulkMediaUpload("src\\fileResources\\lmsAudio")) {
			System.out.println("media uploaded");
		} else {
			throw new Exception();
		}
		selectAllFolderContents();
		bulkFavoriteSelectedMedia();
		
		int numberOfMediaItemsInOriginalFolder = getElementList(By.cssSelector("[class='videoWrapper']")).size();
		Thread.sleep(2000);
		navigateToFavorites(userName);
		int numberOfMediaItemsInFavoriteFolder = getElementList(By.cssSelector("[class='videoWrapper']")).size();
		if(numberOfMediaItemsInOriginalFolder == numberOfMediaItemsInFavoriteFolder){
			Report.reportStep(Driver.getDriver(), "Bulk favorite succesful", "PASS", false);
		}else {
				Report.reportStep(Driver.getDriver(), "Bulk favorite unsuccesful", "FAIL", true);
				throw new Exception("Bulk favorite unsuccesful");
		}
		
		selectAllFolderContents();
		bulkUnfavoriteSelectedMedia();
		Thread.sleep(1000);
		navigateToFavorites(userName);
		driver.navigate().refresh();
		Thread.sleep(2000);
		numberOfMediaItemsInFavoriteFolder = getElementList(By.cssSelector("[class='videoWrapper']")).size();
		if(numberOfMediaItemsInFavoriteFolder == 0) {
			Report.reportStep(Driver.getDriver(), "Bulk unfavorite succesful", "PASS", false);
		}
		else {
			Report.reportStep(Driver.getDriver(), "Bulk unfavorite unsuccesful", "FAIL", true);
			throw new Exception("Bulk unfavorite unsuccesful");
		}
		
		navigateToMyMedia(userName);
		deleteFolder(folderName);
	}
	
	public void replaceMedia(String userName, String password, String replaceVideo, String VideoReplaced,String shareUserID,String shareUserName, String shareUserPassword, String publishCourseName) throws InterruptedException {
		navigateToMyMediaUserLogin(userName, password);
		MediaDetailsModalTestMethods ReplaceObj = new MediaDetailsModalTestMethods();
		Driver.getDriver().findElement(By.xpath("//*[@id=\"btnUploadMedia\"]")).click();;
		mediaLibrary.bulkMediaUpload("src\\fileResources\\replaceTest");
		boolean processed = mediaIsProcessed(replaceVideo, 60, 3);
		if(processed) {
			Report.reportStep(Driver.getDriver(), replaceVideo + " media fully proccessed", "PASS", false);
		}
		else {
			Report.reportStep(Driver.getDriver(), replaceVideo + " media not fully proccessed", "FAIL", true);
			Assert.state(processed, "Video failed to process");
		}
		publishMediaFromMediaLibrary(replaceVideo,publishCourseName);
		//ReplaceObj.ShareMedia(replaceVideo,shareUserName,shareUserPassword);
		ReplaceObj.ReplaceVideo(replaceVideo,VideoReplaced);
		ReplaceObj.CheckforReplaceMedia(replaceVideo,VideoReplaced,userName,password,publishCourseName);	
	}
	
	public void checkDeleteMedia(String userName, String password) throws InterruptedException {
		mediaLibrary.navigateToMyMediaUserLogin(userName, password);
		mediaLibrary.bulkMediaUpload("src\\fileResources\\replaceTest");
		deleteMedia("testReplaceVideo");
		boolean mediaDoesntExist = !mediaExists("testReplaceVideo");
		System.out.println("mediaExist = " + mediaDoesntExist);
		if(mediaDoesntExist) {
			Report.reportStep(Driver.getDriver(), "Media was succesfully deleted", "PASS", false);
		}
		else {
			Report.reportStep(Driver.getDriver(), "Media was not succesfully deleted", "FAIL", true);
		}
	}
	
	public void checkPublishandUnpublishMediaLibrary(String userName, String password, String mediaTitle, String destinationChannelName) throws InterruptedException {
		//publishing the media
		navigateToMyMediaUserLogin(userName, password);
		boolean checkmedia = mediaExists(mediaTitle);
		if(checkmedia) {
			mediaLibrary.publishMediaFromMediaLibrary(mediaTitle, destinationChannelName);
			//checking that the media was published
			navigateToChannel(destinationChannelName);
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
		else
		{
			Report.reportStep(Driver.getDriver(), "No media with title " +mediaTitle,"FAIL",true);
		}
	}

	public void uploadMedia(String userName, String password, String newFolderName, String filesDirectoryPath) throws InterruptedException {
		mediaLibrary.navigateToMyMediaUserLogin(userName, password);
		mediaLibrary.createNewFolder(newFolderName);
		mediaLibrary.accessFolder(newFolderName);
		mediaLibrary.bulkMediaUpload(filesDirectoryPath);
	}
	
	public void checkCreateAndDeleteFolder(String userName, String password, String newFolderName) throws InterruptedException {
		newFolderName = newFolderName + " " + getRandomInteger(100);
		mediaLibrary.navigateToMyMediaUserLogin(userName, password);
		mediaLibrary.createNewFolder(newFolderName);
		if(folderExists(newFolderName)) {
			Report.reportStep(Driver.getDriver(), "folder was succesfully created", "PASS", false);
			mediaLibrary.deleteFolder(newFolderName);
			if(!folderExists(newFolderName)) {
				Report.reportStep(Driver.getDriver(), "folder was succesfully deleted", "PASS", false);
			}
			else {
				Report.reportStep(Driver.getDriver(), "folder was not deleted", "FAIL", true);
			}
		} 
		else 
		{
			Report.reportStep(Driver.getDriver(), "folder was not succesfully created", "FAIL", true);
		}
	}
	
}

	

