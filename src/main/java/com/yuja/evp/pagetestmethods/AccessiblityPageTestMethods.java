package com.yuja.evp.pagetestmethods;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.yuja.evp.modalhelpers.MediaDetailsModalHelperMethods;
import com.yuja.evp.pagehelpers.AccessiblityPageHelpers;
import com.yuja.evp.pagehelpers.MediaLibraryPageHelpers;
import com.yuja.evp.pagehelpers.NavigationBarHelpers;

import net.jodah.failsafe.internal.util.Assert;

public class AccessiblityPageTestMethods extends AccessiblityPageHelpers {
	
	NavigationBarHelpers navigationBar = new NavigationBarHelpers();
	MediaLibraryPageHelpers mediaLibrary = new MediaLibraryPageHelpers();
	MediaDetailsModalHelperMethods mediaDetailsModal= new MediaDetailsModalHelperMethods();
	
	public void checkAutoCaptioningPermissionForUser(String adminUsername,String adminPassword,String permissionBase, String userType,String userUsername,String userPassword,String userFullname, String autocaptionVideo ) throws InterruptedException {
		navigateToAdminPanelAccessiblityPageUserLogin(adminUsername, adminPassword);
		setDefaultCaptioningForUploads("none");
		deleteAllExistingPermissions("auto caption");
		addPermissionAutocaptionUsers(permissionBase,userType);
		savePermission();
		navigationBar.userLogOut();
		checkUserCanSendAutocaptionrequest(userUsername,userPassword,autocaptionVideo);
		checkAdminPanelAccessiblityStatusTableforUser(adminUsername, adminPassword, userFullname);
		deleteAllExistingPermissions("auto caption");
	    savePermission();
	    navigationBar.userLogOut();
	    checkUserAutocaptionButtonDisabled(userUsername,userPassword,autocaptionVideo);
	    }
	
	
	public void checkHumanCaptioningPermissionForUser(String adminUsername,String adminPassword,String permissionBase, String userType,String userUsername,String userPassword,String userFullname, String humanCaptionProviders, String requireApproval, String humancaptionVideo ) throws InterruptedException {
		navigateToAdminPanelAccessiblityPageUserLogin(adminUsername, adminPassword);
		deleteAllExistingPermissions("human caption");
		addPermissionHumancaptionUsers(permissionBase,userType,humanCaptionProviders,requireApproval);
		savePermission();
		navigationBar.userLogOut();
		checkUserCanSeeHumancaptionproviders(userUsername,userPassword,humanCaptionProviders,humancaptionVideo);
		navigateToAdminPanelAccessiblityPageUserLogin(adminUsername, adminPassword);
		deleteAllExistingPermissions("human caption");
	    savePermission();
	    navigationBar.userLogOut();
	    checkUserHumancaptionButtonDisabled(userUsername,userPassword,humancaptionVideo);
	    }
 
	
	public void checkHumanCaptionButtonUpdate( String adminUserName, String adminPassword, String permissionBase, String userName, String humanCaptionProviders, String requireApproval, String humancaptionVideo) throws InterruptedException {
		mediaLibrary.navigateToMyMediaUserLogin(adminUserName, adminPassword);
		WebElement media=driver.findElement(By.xpath("//div[@class=\"videoWrapper\"]"));
		hoverOverElement(media);
		clickElement(media, "More menu", By.cssSelector("[data-automation=\"btnInVideoMenuMore\"]"), 10);
		mediaDetailsModal.clickAccessiblity();
		WebElement humancaptionButton=driver.findElement(By.id("humanCaptionButtonID"));
		boolean originalIsEnabled = humancaptionButton.isEnabled();
	    boolean afterIsEnabled = !originalIsEnabled;
	    URL = "https://staging-demo.yuja.com/P/Institution/AccessibilityOptions/";
	    launchUrl(URL, "Xavier University Enterprise Video Platform");
		WebElement editButton = waitForElement(By.cssSelector("button[data-automation='btnEditExternalCaptionUser']"),10);
	    clickElement("Clicking edit button", editButton); 
	         
	         if(originalIsEnabled) {
	        	 List<WebElement> closebuttons = getElementList(By.cssSelector("[class=\"media-modal-small-times-icon media-modal-close-btn share-access-icons\"]"));
	             int activeButtons = closebuttons.size();
	             
	             while(activeButtons > 0) {
	                 clickElement("clicking close icon", closebuttons.get(0));
	                 closebuttons = getElementList(By.cssSelector("[class=\"media-modal-small-times-icon media-modal-close-btn share-access-icons\"]"));
	                 activeButtons = closebuttons.size();
	                 savePermission();
	              }
	         }
	         else {
	        	Select rolesDropdown = new Select(driver.findElement(By.xpath("//div[@class=\"form-group\"]/select[@class=\"form-control\"]")));
	 			rolesDropdown.selectByVisibleText("IT Manager");
	 			clickElement("add Persmission button", By.xpath("//button[@title='Add Permission']"), 10);
	 			savePermission();
	         }
	        Thread.sleep(3000); 
	        clickElement("Click Manage Media",By.xpath("//span[@id='topBarTabName3']"),10);
	        WebElement media1=driver.findElement(By.xpath("//div[@class=\"videoWrapper\"]"));
	 		hoverOverElement(media1);
	 		clickElement(media1, "More menu", By.cssSelector("[data-automation=\"btnInVideoMenuMore\"]"), 10);
	 		mediaDetailsModal.clickAccessiblity();
	 		
	         if(mediaDetailsModal.captionButtonEnabledStateIsAsExpected("human caption",afterIsEnabled, originalIsEnabled, 10, "humancaption")) {
	        	 reportStep("All caption providers are available","PASS",false);
	         }
	         else {
	        	 reportStep("All caption providers are not present","FAIL", true);
	         }
		}
}
	
	
