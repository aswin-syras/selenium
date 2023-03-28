package com.yuja.evp.modalhelpers;


import java.util.HashMap;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.yuja.evp.pagehelpers.MediaLibraryPageHelpers;
import com.yuja.evp.pagehelpers.NavigationBarHelpers;
import com.yuja.evp.utilities.Helpers;
import com.yuja.evp.utilities.Helpers;

public class MediaDetailsModalHelperMethods extends Helpers {
	
	MediaLibraryPageHelpers mediaLibrary = new MediaLibraryPageHelpers();

	public void clickQuizzes() {
		clickElement("Quizzes",By.cssSelector("[data-automation=\"btnQuizzesDetails\"]"),10);
	}	
	
	public void clickGradebookButton() {
		clickElement("Click Gradebook button",By.xpath("(//button[@class='btn  btn-inline quiz-btn'])[5]"),10);
	}
	
	public void clickCloseMoreMenu() {
		clickElement("Click Close More menu",By.xpath("//button[@data-automation='btnCancelMediaDetails']"),10);
	}
	
	public void clickManageQuizButton() {
		clickElement("Click Manage Quiz button",By.xpath("(//button[@class='btn  btn-inline quiz-btn'])[1]"),10);
	}
	
	public void clickUpdatePostQuiz() {
		clickElement("Click Update post quiz button",By.xpath("//button[@title='Update']"),10);
	}
	
	public void clickQuizLinksdrop() {
		clickElement("Click Quiz Links Drop",By.xpath("//i[@class='fa fa-angle-down']"),10);
	}
	
	public void clickDeleteQuizButton() {
		clickElement("Click Delete Quiz button",By.xpath("(//button[@class='btn  btn-inline quiz-btn'])[3]"),10);
	}
	
	public void clickActivityLogButton() {
		clickElement("Click Activity Log button",By.xpath("(//button[@class='btn  btn-inline quiz-btn'])[4]"),10);
	}
	
	public WebElement getActivityLogforQuizSync() {
		WebElement log=driver.findElement(By.xpath("//div[@class='panel panel-default quizLogPanel']/div[2]/div"));
		return log;
	}
	
	public void clickPlaybackQuizzes() {
		clickElement("Quizzes",By.cssSelector("[data-automation=\"btnQuizzesDetails\"]"),10);
	}
	public void clickAccessiblity() {
		clickElement("Accessiblity",By.cssSelector("[data-automation='btnAccessibilityDetails']"),10);
	}
	public void clickAutoCaptionButton() {
		clickElement("Autocaption Button",By.id("autoCaptionButtonID"),10);
	}
	public void clickHumanCaptionButton() {
		clickElement("Humancaption Button",By.id("humanCaptionButtonID"),10);
	}


   public void publishMediaFromMediaDetails(String mediaTitle, String destinationChannelName) throws InterruptedException {
		mediaLibrary.accessMediaMoreMenu(mediaTitle);
		clickElement("Action button",By.xpath("//*[@id=\"file-modal-tab\"]/div/div/button"));
		clickElement("Publish button",By.xpath("//*[@id=\"file-modal-tab\"]/div/div/div/div[1]/button"));
		clickElement("publish video textbox",By.xpath("//*[@id=\"publishSearchBar\"]"));
		typeKeys(destinationChannelName);
		WebElement confirmDialog =  waitForElement(By.cssSelector(".folderEntry.modal-folder-entry.sublevel-2"), 10);
		clickElement(destinationChannelName,confirmDialog);
		List<WebElement> dialogList = getElementList(By.cssSelector(".modal-md.modal-dialog"));
		WebElement confirm = dialogList.get(dialogList.size()-1);
		System.out.println("fetching selector...");
		WebElement SelectModal =  confirm.findElement(By.xpath("//*[@id=\"di_folderSelectionModalDialog\"]/div[2]/button[2]"));
		clickElement("Select button", SelectModal);
		Thread.sleep(3000);		
		clickElement("Save General button", By.cssSelector("[data-automation=\"btnSaveMediaDetails\"]"));
		clickElement("Media Details Modal close button", By.cssSelector("[data-automation=\"btnCloseModalDialog\"]"));
		Thread.sleep(2000);
	}
	
	public void ReplaceVideo(String replaceVideo, String VideoReplaced) throws InterruptedException {
		mediaLibrary.accessMediaMoreMenu(replaceVideo);
		clickElement("Action button", By.xpath("//*[@id=\"file-modal-tab\"]/div/div/button"));
		clickElement("Replace button", By.xpath("//*[@id=\"file-modal-tab\"]/div/div/div/div[4]/button"));
		clickElement("Search button", By.cssSelector("[data-automation=\"txtAddDocumentSearch\"]"));
		typeKeys(VideoReplaced);
		clickElement("Search button", By.xpath("//*[@id=\"injectedVideoContent\"]/form/div[2]/button"));
		Thread.sleep(2000);
		clickElement("The video to be replaced",By.xpath("//*[@id=\"injectedVideoContent\"]/div[1]/div/div[1]/div/div/div/div[1]/img[1]"));
		Thread.sleep(2000);
		List<WebElement> dialogList = getElementList(By.cssSelector(".modal-dialog "));
		WebElement confirmDialog = dialogList.get(dialogList.size() - 1);
		WebElement okButton = confirmDialog.findElement(By.cssSelector(".modal-content > .modal-footer > #buttonSection > [title=\"OK\"]"));
		clickElement("OK button", okButton);
		Thread.sleep(3000);
		clickElement("Save General button", By.cssSelector("[data-automation=\"btnSaveMediaDetails\"]"));
		clickElement("Media Details Modal close button", By.cssSelector("[data-automation=\"btnCloseModalDialog\"]"));
		Thread.sleep(2000);
	}
	
	public void CheckforReplaceMedia(String replaceVideo, String VideoReplaced, String userName, String password, String publishCourseName) throws InterruptedException {
		mediaLibrary.navigateToChannel(publishCourseName);
		boolean check = mediaExists(VideoReplaced);
		if (check) {
			reportStep("Video Replaced", "PASS", false);
			WebElement mediaFile = getMedia(VideoReplaced);
			hoverOverElement(mediaFile);
			clickElement("Unpublish button",By.cssSelector("[data-automation=\"btnInVideoMenuUnpublish\"]"));
			clickElement("Yes ",By.xpath("//*[@id=\"unlinkModalDialog\"]/div[1]/div/div/div[2]/div[2]/button[2]"));			
			clickElement("My media ", By.xpath("//*[@id=\"di_sidebarFolderSection\"]/div[2]/div/div[1]"));
			driver.navigate().refresh();
			mediaLibrary.deleteMedia(replaceVideo);
			driver.navigate().refresh();
			boolean mediaDoesntExist = !mediaExists("testReplaceVideo");
			System.out.println("mediaExist = " + mediaDoesntExist);
			if (mediaDoesntExist) {
				reportStep("Media was succesfully deleted", "PASS", false);
			} else {
				reportStep("Media was not succesfully deleted", "FAIL", true);
			}
		} else 
		{
			reportStep("Video not Replaced", "FAIL", true);
		}
	}
	
	 public boolean captionButtonEnabledStateIsAsExpected(String typeOfButton, boolean newState, boolean originalState, int numberOfChecks, String mediaTitle) throws InterruptedException {
			HashMap<String, String> buttonTypes = new HashMap<String, String>();
			buttonTypes.put("human caption", "#humanCaptionButtonID");
			buttonTypes.put("auto caption", "#autoCaptionButtonID");
			boolean thisIsTrue = newState == originalState;
	
		   if(!thisIsTrue) {
			WebElement captionButton;
			for(int i = 0; !thisIsTrue && i < numberOfChecks; i++) {
				clickElement("Media Details Close button", By.cssSelector("[data-automation=\"btnCancelMediaDetails\"]"));
				driver.navigate().refresh();
				mediaLibrary.accessMediaMoreMenu(mediaTitle);
				clickElement("Accessibility tab", By.cssSelector("[data-automation=\"btnAccessibilityDetails\"]"));
                captionButton = waitForElement(By.cssSelector(buttonTypes.get(typeOfButton)), 10);
				thisIsTrue = newState == captionButton.isEnabled();
            }
		}
		return thisIsTrue;
	}
}
