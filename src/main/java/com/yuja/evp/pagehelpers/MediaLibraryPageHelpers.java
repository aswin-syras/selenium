package com.yuja.evp.pagehelpers;

import java.io.File;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.yuja.evp.modalhelpers.AddMediaModalHelperMethods;
import com.yuja.evp.modalhelpers.FolderDetailsModalHelperMethods;
import com.yuja.evp.reports.Report;
import com.yuja.evp.utilities.Driver;
import com.yuja.evp.utilities.Helpers;

import net.jodah.failsafe.internal.util.Assert;

public class MediaLibraryPageHelpers extends Helpers{
	
	private SignInPageHelpers signInPage = new SignInPageHelpers();
	MediaChannelPageHelpers mediaChannel = new MediaChannelPageHelpers();
	NavigationBarHelpers navigationBar = new NavigationBarHelpers();
	
	
	public void navigateToMyMedia(String userName){
		URL = prop.getProperty("URL")+"/P/VideoManagement/MediaLibrary/Users/"+userName+"/MyMediaCollections";
		launchUrl(URL, "Test Automation Enterprise Video Platform");
	}
	
	public void navigateToMyMediaUserLogin(String userName, String password){
		signInPage.navigateToLoginPage();
		Driver.getDriver().manage().window().maximize();
		signInPage.loginFast(userName, password);
		waitForElement(By.id("navbar-header"), 10);
		navigateToMyMedia(userName);
	}
	
	public void navigateToSharedWithMeUserLogin(String userName, String password){
		signInPage.navigateToLoginPage();
		Driver.getDriver().manage().window().maximize();
		signInPage.loginFast(userName, password);
		waitForElement(By.id("navbar-header"), 10);
		URL = prop.getProperty("URL")+"/P/VideoManagement/MediaLibrary/Users/"+userName+"/Shared";
		launchUrl(URL, "Test Automation Enterprise Video Platform");
	}
	
	public void navigateToSharedWithMe(String userName){
		URL = prop.getProperty("URL")+"/P/VideoManagement/MediaLibrary/Users/"+userName+"/Shared";
		launchUrl(URL, "Test Automation Enterprise Video Platform");
	}
	
	public void navigateToInternalLibraryUserLogin(String userName, String password) {
		signInPage.navigateToLoginPage();
		Driver.getDriver().manage().window().maximize();
		signInPage.loginFast(userName, password);
		waitForElement(By.id("navbar-header"), 10);
		URL = prop.getProperty("URL")+"/P/VideoManagement/MediaLibrary/InstitutionPrivateChannel";
		launchUrl(URL, "Test Automation Enterprise Video Platform");
	}
	
	public void navigateToInternalLibrary(){
		URL = prop.getProperty("URL")+"/P/VideoManagement/MediaLibrary/InstitutionPrivateChannel";
		launchUrl(URL, "Test Automation Enterprise Video Platform");
	}
	
	public void navigateToFavoriteUserLogin(String userName, String password) {
		signInPage.navigateToLoginPage();
		Driver.getDriver().manage().window().maximize();
		signInPage.loginFast(userName, password);
		waitForElement(By.id("navbar-header"), 10);
		URL =prop.getProperty("URL")+"/P/VideoManagement/MediaLibrary/Users/"+userName+"/Favorites";
		launchUrl(URL, "Test Automation Enterprise Video Platform");
	}
	
	public void navigateToFavorites(String userName){
		URL = prop.getProperty("URL")+"/P/VideoManagement/MediaLibrary/Users/"+userName+"/Favorites";
		launchUrl(URL, "Test Automation Enterprise Video Platform");
	}
	
	public boolean bulkMediaUpload(String mediaDirectoryPath) {
		
		boolean end;
		AddMediaModalHelperMethods modal = new AddMediaModalHelperMethods();
		File mediaDirectory = new File(mediaDirectoryPath);
		String[] mediaList = mediaDirectory.list();
		String mediaTitle;
		String mediaExtension;
		String mediaPath;
		
		try {
			for(String media : mediaList) {
				try {
					mediaTitle = media.substring(0, media.indexOf('.'));
					mediaPath = mediaDirectoryPath + "\\" + media;
					mediaExtension = media.substring(media.indexOf('.')+1);
					
					clickUploadButton();
					
					System.out.println("Attempting to upload " + media);
					
					if(modal.mediaUploaded(mediaTitle, mediaPath)) System.out.println(media + " upload success");
					else throw new Exception(media + " upload fail");
					Thread.sleep(1000);
				} catch(Exception e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
					Report.reportStep(Driver.getDriver(), "e.getMessage(): " + e.getMessage() + ", @Method "+Scenario_Name +" exception to be handled", "Fail", true);
				}
			}
			end = true;
		}catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			end = false;
		}
		return end;
	 }// end of  uploadMedia

	public boolean mediaIsProcessed(String mediaName, int timeoutInSeconds, int maxSearchAttemps) {
        int failCount = 0;
        boolean isProcessed = false;
        WebElement media;
        while(!isProcessed && failCount < maxSearchAttemps) {
            media = getMedia(mediaName);
            try {
                Driver.getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
                //WebElement processingThumbnail =  media.findElement(By.cssSelector("[class=\"hcenter vcenter\"] > img[src=\"/Dashboard/icons/defaultThumbnailProcessing.svg\"]"));
                WebElement processingThumbnail =(WebElement)new WebDriverWait(Driver.getDriver(),10).until(ExpectedConditions.elementToBeClickable(By.cssSelector("[class=\"hcenter vcenter\"] > img[src=\"/Dashboard/icons/defaultThumbnailProcessing.svg\"]")));
                WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeoutInSeconds)); 
                System.out.println("Src attribute is: "+ processingThumbnail.getAttribute("src"));
                //wait.until( (WebDriverWait) -> {return !processingThumbnail.isDisplayed(); } );
                wait.until( ExpectedConditions.invisibilityOf(processingThumbnail));
                System.out.println("Display " +!processingThumbnail.isDisplayed());
                return !processingThumbnail.isDisplayed();
            } catch(NoSuchElementException e) {
                Driver.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                isProcessed = true;
            } catch(StaleElementReferenceException e) {
                hoverOverElement(media);
                WebElement favoriteIcon = media.findElement(By.cssSelector(".overlay > .overlaycontent > .favorite-thumb > img[src=\"/Dashboard/icons/favIcon.svg\"]"));
                System.out.println("Src attribute is: "+ favoriteIcon.getAttribute("src"));
                if(favoriteIcon != null) {
                    isProcessed = true;
                }        
            }	
            catch(TimeoutException e) {
                failCount++;
                Driver.getDriver().navigate().refresh();
            }
            catch(WebDriverException e) {
            	isProcessed = true;
            }
        }
        Driver.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return isProcessed;
     }
	//img[src=\"/Dashboard/icons/defaultThumbnailProcessing.svg\"]
	//src=\"/Dashboard/icons/video-thumbnail-8.jpg"\
    public boolean mediaIsProcessed(String mediaName, int timeoutInSeconds) {
        return mediaIsProcessed(mediaName, timeoutInSeconds, 4);
     }
	
	public void createNewFolder(String folderName) throws InterruptedException {
		clickNewFolderButton();
		enterNewFolderName(folderName);
		clickNewFolderSaveButton();
		Thread.sleep(1000);
	}
	
	public void deleteFolder(String folderName, String userName) throws InterruptedException {
		navigateToMyMedia(userName);
		Thread.sleep(4000);
		WebElement folder = getFolder(folderName);
		clickElement("folder", folder);
		clickMoreActionsButton();
		clickDeleteFolderButton();
		confirmMoveToRecycleBin();
		Thread.sleep(1000);
	}
	
	public void deleteMedia(String mediaName) throws InterruptedException {
		WebElement mediaLibraryElement =  getMedia(mediaName);
		hoverOverElement(mediaLibraryElement);
		clickElement(mediaLibraryElement,"delete button",By.cssSelector("[data-automation='btnInVideoMenuDelete']"),10);
		confirmMoveToRecycleBin();
		Thread.sleep(1000);
	}
	
	
	public Boolean deleteFolderViaMoreMenu(String folderName) throws InterruptedException{
		//fetch folder and open media details modal
		
		accessFolderMoreMenu(folderName);
		
		FolderDetailsModalHelperMethods modal = new FolderDetailsModalHelperMethods();
		modal.deleteCurrentFolder();
		Thread.sleep(3000);
		
		waitForElement(null, 10);
		
		return !folderExists(folderName);
	}
	
	public void clickUploadButton() {
		clickElement("Upload button", By.xpath("//*[@id=\"btnUploadMedia\"]"));
	}
	
	public void confirmMoveToRecycleBin() {
		sendKeys("Confirm textboox", By.xpath("//*[@id=\"deleteModalDialog\"]/div[1]/div/div/div[2]/div[1]/div/input"), "Confirm");
		clickElement("Yes button", By.xpath("//*[@id=\"deleteModalDialog\"]/div[1]/div/div/div[2]/div[2]/button[2]"));
	}
	
	protected void accessFolderMoreMenu(String folderName) throws InterruptedException {
		Thread.sleep(3000);
		WebElement folder = getFolder(folderName);
		hoverOverElement(folder);
		folder.findElement(By.cssSelector("[data-automation=\"btnReactMenuTrigger\"]")).click();
		folder.findElement(By.cssSelector("[data-automation=\"btnInVideoMenuMore\"]")).click();
	}
	 public void accessMediaMoreMenu(String mediaTitle) throws InterruptedException {
		Thread.sleep(3000);
		WebElement media = getMedia(mediaTitle);
		System.out.println("inside accessMediaMoremenu");
		System.out.println(media);
		System.out.println("          ");
		hoverOverElement(media);
		System.out.println("inside accessMediaMoremenu");
		clickElement(media, "More menu button from the video hover", By.cssSelector("[data-automation=\"btnInVideoMenuMore\"]"), 30);
	}
	
	public void selectAllFolderContents(){
		clickMoreActionsButton();
		clickSelectallButton();
	}
	
	public String getToastmessagetext() {
		String toastmessage= Driver.getDriver().findElement(By.xpath("//div[text()='Success']")).getAttribute("innerHTML");
		return toastmessage;
	}
	
	// Methods for bulk fav and unfav
	
	private void clickMoreActionsButton() {
		clickElement("MoreActions button", By.cssSelector("[data-automation=\"btnMediaLibraryMoreActions\"]"));
	}
	
	private void clickSelectallButton() {
		clickElement("SelectAll button", By.xpath("//div[@data-automation='btnSelectAll']"));
	}
	
	private void clickDeleteFolderButton() {
		clickElement("Delete folder button", By.cssSelector("[data-automation=\"btnDeleteFolder\"]"));
	}
	
	protected void bulkFavoriteSelectedMedia() {
		clickElement("Bulk Favorite button", By.xpath("//div[@title='Bulk Favorite']"));
		clickElement("Bulk Favorite Yes button", By.xpath("//button[@title='Yes']"));
	}
	
	protected void bulkUnfavoriteSelectedMedia() throws InterruptedException {
		clickElement("Bulk Unfavorite button", By.cssSelector("[title=\"Bulk Unfavorite\"]"));
		clickElement("Bulk Favorite Yes button", By.xpath("//button[@title='Yes']"));
		Thread.sleep(1000);
	}
	
	private void clickNewFolderButton() {
		clickElement("NewFolder button", By.xpath("//span[text()='NEW FOLDER']"));
	}
		
	private void enterNewFolderName(String name) {
		sendKeys("Folder Name", By.xpath("//input[@id='inputNewFolder']"), name);
	}
		
	private void clickNewFolderSaveButton() {
		clickElement("NewFolder Save button", By.xpath("//button[@title='Save']"));
	}
		
	public void accessFolder(String folderName) throws InterruptedException {
		WebElement newFolder = getFolder(folderName);
		doubleClick("doubleclickelement",newFolder);
		Thread.sleep(2000);
	}	
	
	public void publishMediaFromMediaLibrary(String mediaTitle, String destinationChannelName) throws InterruptedException {
		WebElement mediaFile = getMedia(mediaTitle);
		hoverOverElement(mediaFile);
		clickElement(mediaFile,"Publish button from the video hover",By.cssSelector("[data-automation=\"btnInVideoMenuPublish\"]"), 20);
		clickElement("publish video textbox",By.xpath("//*[@id=\"publishSearchBar\"]"));
		typeKeys(destinationChannelName);
		WebElement confirmDialog =  waitForElement(By.cssSelector(".folderEntry.modal-folder-entry.sublevel-2"), 10);
		clickElement(destinationChannelName,confirmDialog);
		List<WebElement> dialogList = getElementList(By.cssSelector(".modal-md.modal-dialog"));
		WebElement confirm = dialogList.get(dialogList.size()-1);
		System.out.println("fetching selector...");
		WebElement SelectModal =  confirm.findElement(By.xpath("//*[@id=\"di_folderSelectionModalDialog\"]/div[2]/button[2]"));
		clickElement("Select button", SelectModal);
	}
	
	public void unpublishMedia(String mediaName) throws InterruptedException {
		boolean mediaExists = mediaExists(mediaName);
		if (mediaExists) {
			WebElement mediaFile = getMedia(mediaName);
			hoverOverElement(mediaFile);
			clickElement("Unpublish button",By.cssSelector("[data-automation=\"btnInVideoMenuUnpublish\"]"));
			clickElement("Yes ",By.xpath("//*[@id=\"unlinkModalDialog\"]/div[1]/div/div/div[2]/div[2]/button[2]"));
		}
		else
		{	
			Report.reportStep(Driver.getDriver(), "Media with title " + mediaName + " does not exist ", "FAIL", true);
			Assert.state(mediaExists, "No media with title " + mediaName);
		}
	}
	
	public void navigateToChannel(String channelName) throws InterruptedException {
		clickElement("Manage Media button", By.cssSelector("[data-fullname=\"Manage Media\"]"));
		clickElement("All channels button", By.xpath("//span[contains(text(),'All Channels')]"));
		clickElement("Select channels", By.cssSelector("[data-automation=\"btnMediaLibMenuSearchChannel\"]"));
		WebElement ClickFindDialog = waitForElement(By.cssSelector(".Select-placeholder"), 10);
		clickElement("Search channel button", ClickFindDialog);
		typeKeys(channelName);
		Thread.sleep(1000);
		WebElement ClickChannel = waitForElement(By.cssSelector(".Select-menu-outer"), 10);
		clickElement(channelName, ClickChannel);
	}
	
}
