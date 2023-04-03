package com.yuja.evp.pagehelpers;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.yuja.evp.modalhelpers.MediaDetailsModalHelperMethods;

import com.yuja.evp.pagetestmethods.MediaLibraryPageTestMethods;
import com.yuja.evp.reports.Report;
import com.yuja.evp.utilities.Driver;
import com.yuja.evp.utilities.Helpers;

import net.jodah.failsafe.internal.util.Assert;

public class AccessiblityPageHelpers extends Helpers{
	
private SignInPageHelpers signInPage = new SignInPageHelpers();
MediaLibraryPageTestMethods mediaLibrary = new MediaLibraryPageTestMethods();
MediaDetailsModalHelperMethods mediaDetailsModal= new MediaDetailsModalHelperMethods();
NavigationBarHelpers navigationBar = new NavigationBarHelpers();
MediaLibraryPageHelpers medialibraryHelpers=new MediaLibraryPageHelpers();
	
	public void navigateToAdminPanelAccessiblityPageUserLogin(String userName, String password) throws InterruptedException{
		signInPage.navigateToLoginPage();
		Driver.getDriver().manage().window().maximize();
		signInPage.loginFast(userName, password);
		waitForElement(By.id("navbar-header"), 10);
		URL = prop.getProperty("URL")+"/P/Institution/AccessibilityOptions/";
		launchUrl(URL, "Test Automation Enterprise Video Platform");
		}
	
	public void setDefaultCaptioningForUploads(String defaultAutocaptionSetting) throws InterruptedException {
		Select options = new Select(Driver.getDriver().findElement(By.id("si_defaultCaptioningOption")));
		options.selectByValue(defaultAutocaptionSetting);
		Report.reportStep(Driver.getDriver(), defaultAutocaptionSetting + " is chosen from the dropdown ", "PASS", false);
		clickElement("Click save button",By.xpath("//button[@id='singlebutton']"),10);
		clickElement("Click toast close button",By.xpath("//button[@class=\"right-close-button btn dismisscolor inline-btn\"]"),10);
		Driver.getDriver().navigate().refresh();
		Thread.sleep(3000);
	   }
	
	public void deleteAllExistingPermissions(String typeOfButton) throws InterruptedException {
        
        HashMap<String, String> editButtonType = new HashMap<String, String>();
        editButtonType.put("human caption", "button[data-automation='btnEditExternalCaptionUser']");
        editButtonType.put("auto caption", "button[data-automation='btnEditAutoCaptionUser']");
        WebElement editButton = waitForElement(By.cssSelector(editButtonType.get(typeOfButton)),10);
        clickElement("Clicking edit button", editButton);
        List<WebElement> closebuttons = getElementList(By.cssSelector("[class=\"media-modal-small-times-icon media-modal-close-btn share-access-icons\"]"));
        int activeButtons = closebuttons.size();
        
        while(activeButtons > 0) {
            clickElement("clicking close icon", closebuttons.get(0));
            closebuttons = getElementList(By.cssSelector("[class=\"media-modal-small-times-icon media-modal-close-btn share-access-icons\"]"));
            activeButtons = closebuttons.size();
         }
        }
	
	
	public void addPermissionAutocaptionUsers(String permissionBase, String userName){
		HashMap<String, String> roleInfo = new HashMap<String, String>();
		roleInfo.put("Role based", "option[value=\"ROLE\"]");
		roleInfo.put("User based", "option[value=\"USER\"]");
		System.out.println("permissionBase = " + permissionBase);
		WebElement persmissionDropdown = waitForElement(By.cssSelector("select[aria-label=\"Add Permission\"]"), 10);
		clickElement("Persmission dropdown", persmissionDropdown);
		clickElement(persmissionDropdown, "Persmission option", By.cssSelector(roleInfo.get(permissionBase)), 10);
		
		if(permissionBase.equals("User based")) {
			clickElement("Users dropdown",By.xpath("//span[@class=\"select2-chosen\"]"),10);
			typeKeys(userName);
			keyboardEnter();
            }
		
		if(permissionBase.equals("Role based")) {
			Select rolesDropdown = new Select(Driver.getDriver().findElement(By.xpath("//div[@class=\"form-group\"]/select[@class=\"form-control\"]")));
			rolesDropdown.selectByVisibleText(userName);
		   }
		clickElement("add Persmission button", By.xpath("//button[@title='Add Permission']"), 10);
	    }
		
	
	public void savePermission() {
		clickElement("Click save button",By.xpath("//div[@class='modal-footer']/button[@class=\"dialog-button-positive btn defaultcolor\"]"),10);
		}
	
	
	public void checkUserAutocaptionButtonDisabled(String userUsername, String userPassword, String autocaptionVideo) throws InterruptedException {
		 mediaLibrary.navigateToMyMediaUserLogin(userUsername, userPassword);
		 mediaLibrary.bulkMediaUpload("src\\fileResources\\humancaptionvideo");
		 boolean processed = medialibraryHelpers.mediaIsProcessed(autocaptionVideo, 60, 3);
			if(processed) {
				Report.reportStep(Driver.getDriver(), autocaptionVideo + " media fully proccessed", "PASS", false);
				// Driver.getDriver().navigate().refresh();
				 WebElement media1=waitForElement(By.xpath("//div[@class=\"videoWrapper\"]"),10);
				 hoverOverElement(media1);
				 clickElement(media1, "More menu", By.cssSelector("[data-automation=\"btnInVideoMenuMore\"]"), 10);
				 mediaDetailsModal.clickAccessiblity();
				 WebElement autocaptionButton=Driver.getDriver().findElement(By.id("autoCaptionButtonID"));
				 boolean actualState=autocaptionButton.isEnabled();
				 if(mediaDetailsModal.captionButtonEnabledStateIsAsExpected("auto caption",false, actualState, 10, "humancaption")) {
		        	 Report.reportStep(Driver.getDriver(), "Autocaptioning is sucessfully disabled","PASS",false);
		         }
		         else {
		        	 Report.reportStep(Driver.getDriver(), "Autocaptioning is not sucessfully disabled","FAIL", true);
		         }
		           mediaDetailsModal.clickCloseMoreMenu();
		          // Driver.getDriver().navigate().refresh();
				   mediaLibrary.deleteMedia(autocaptionVideo);
				   navigationBar.userLogOut();
			}
			else {
				Report.reportStep(Driver.getDriver(), autocaptionVideo + " media not fully proccessed", "FAIL", true);
				Assert.state(processed, "Video failed to process");
			}
		}
	
    public void checkUserCanSendAutocaptionrequest(String userUsername, String userPassword, String autocaptionVideo) throws InterruptedException {
	    mediaLibrary.navigateToMyMediaUserLogin(userUsername, userPassword);
	    mediaLibrary.bulkMediaUpload("src\\fileResources\\humancaptionvideo");
		 boolean processed = medialibraryHelpers.mediaIsProcessed(autocaptionVideo, 60, 3);
			if(processed) {
				Report.reportStep(Driver.getDriver(), autocaptionVideo + " media fully proccessed", "PASS", false);
				//Driver.getDriver().navigate().refresh();
				WebElement media=waitForElement(By.xpath("//div[@class=\"videoWrapper\"]"),10);
				
				hoverOverElement(media);
				clickElement(media, "More menu", By.cssSelector("[data-automation=\"btnInVideoMenuMore\"]"), 10);
				mediaDetailsModal.clickAccessiblity();
				WebElement autocaptionButton=Driver.getDriver().findElement(By.id("autoCaptionButtonID"));
				boolean actualState=autocaptionButton.isEnabled();
				System.out.println(actualState);
				mediaDetailsModal.captionButtonEnabledStateIsAsExpected("auto caption",true, actualState, 10,autocaptionVideo );
				mediaDetailsModal.clickAutoCaptionButton();
				clickElement("Send autocaption request button", By.id("sendAutoCaptionRequest"));
				mediaDetailsModal.clickCloseMoreMenu();
				mediaLibrary.deleteMedia(autocaptionVideo);
				navigationBar.userLogOut();
			}
			else {
				Report.reportStep(Driver.getDriver(), autocaptionVideo + " media not fully proccessed", "FAIL", true);
				Assert.state(processed, "Video failed to process");
			}
		}
    
   
	public void checkAdminPanelAccessiblityStatusTableforUser(String adminUsername, String adminPassword, String userFullname) throws InterruptedException {
	    navigateToAdminPanelAccessiblityPageUserLogin(adminUsername, adminPassword);
		String autocaptionRequester = Driver.getDriver().findElement(By.xpath("//table[@id='autoCaptionStatusTable']/tbody/tr/td[2]")).getText();
	    System.out.println(autocaptionRequester);
		if(autocaptionRequester.equals(userFullname)){
		   Report.reportStep(Driver.getDriver(), "instructor send the autocaption request successfully and displayed in status table","PASS",false);
	   }
	   else {
		   Report.reportStep(Driver.getDriver(), "instructor send the autocaption request successfully and not seen in the status table","FAIL", true);
	     }
	  }
    
    
    public void addPermissionHumancaptionUsers(String permissionBase, String userName,String humanCaptionProviders,String requireApproval){
		HashMap<String, String> roleInfo = new HashMap<String, String>();
		roleInfo.put("Role based", "option[value=\"ROLE\"]");
		roleInfo.put("User based", "option[value=\"USER\"]");
		System.out.println("permissionBase = " + permissionBase);
		WebElement persmissionDropdown = waitForElement(By.cssSelector("select[aria-label=\"Add Permission\"]"), 10);
		clickElement("Persmission dropdown", persmissionDropdown);
		clickElement(persmissionDropdown, "Persmission option", By.cssSelector(roleInfo.get(permissionBase)), 10);
		
		if(permissionBase.equals("User based")) {
			clickElement("Users dropdown",By.xpath("//span[@class=\"select2-chosen\"]"),10);
			typeKeys(userName);
			keyboardEnter();
            }
		
		if(permissionBase.equals("Role based")) {
			Select rolesDropdown = new Select(Driver.getDriver().findElement(By.xpath("//div[@class=\"form-group\"]/select[@class=\"form-control\"]")));
			rolesDropdown.selectByVisibleText(userName);
		   }
		Select captionproviderDropdown = new Select(Driver.getDriver().findElement(By.xpath("//select[@aria-label=\"Select Caption Provider\"]")));
	    captionproviderDropdown.selectByVisibleText(humanCaptionProviders);
		Select requireApprovalDropdown = new Select(Driver.getDriver().findElement(By.xpath("//select[@aria-label='Require Approval']")));
		requireApprovalDropdown.selectByVisibleText(requireApproval);
		clickElement("add Persmission button", By.xpath("//button[@title='Add Permission']"), 10);
	    }
    
    public void checkUserCanSeeHumancaptionproviders(String userUsername, String userPassword,String humanCaptionProviders, String autocaptionVideo) throws InterruptedException {
	    mediaLibrary.navigateToMyMediaUserLogin(userUsername, userPassword);
	    mediaLibrary.bulkMediaUpload("src\\fileResources\\humancaptionvideo");
		 boolean processed = medialibraryHelpers.mediaIsProcessed(autocaptionVideo, 60, 3);
			if(processed) {
				Report.reportStep(Driver.getDriver(), autocaptionVideo + " media fully proccessed", "PASS", false);
				//Driver.getDriver().navigate().refresh();
				WebElement media=Driver.getDriver().findElement(By.xpath("//div[@class=\"videoWrapper\"]"));
				
				hoverOverElement(media);
				clickElement(media, "More menu", By.cssSelector("[data-automation=\"btnInVideoMenuMore\"]"), 10);
				mediaDetailsModal.clickAccessiblity();
				WebElement humancaptionButton=Driver.getDriver().findElement(By.id("humanCaptionButtonID"));
				boolean actualState=humancaptionButton.isEnabled();
				mediaDetailsModal.captionButtonEnabledStateIsAsExpected("human caption",true, actualState, 10,autocaptionVideo );
				mediaDetailsModal.clickHumanCaptionButton();
				
				if(humanCaptionProviders=="All Caption Providers") {
					 List<WebElement> captionProviderList = getElementList(By.xpath("//ul[@id='optionBtn']/li[@role='tab']"));
					 
					 ArrayList<String> captionProvidersArray = new ArrayList<String>();
					 for(int i=0;i<captionProviderList.size();i++) {
						 String captionProvider=captionProviderList.get(i).getText().trim();
						 Thread.sleep(2000);
						 System.out.println(captionProvider);
						 captionProvidersArray.add(captionProvider);
					 }
					
					 ArrayList<String> expectedlist = new ArrayList<>(Arrays.asList("3PlayMedia", "Rev", "CaptionSync","YuJa Pro","Cielo24"));
		             if(captionProvidersArray.equals(expectedlist)) {
						 Report.reportStep(Driver.getDriver(), "All caption providers are available","PASS",false);
					   }
					   else {
						   Report.reportStep(Driver.getDriver(), "All caption providers are not present","FAIL", true);
					   }
				}
				
				if(humanCaptionProviders=="Three Play: Working") {
					 List<WebElement> captionProviderList = getElementList(By.xpath("//ul[@id=\"optionBtn\"]/li[@role=\"tab\"]"));
					 ArrayList<String> captionProvidersArray = new ArrayList<String>();
					 for(int i=0;i<captionProviderList.size();i++) {
						 String captionProvider=captionProviderList.get(i).getText().trim();
						 captionProvidersArray.add(captionProvider);
						 captionProvidersArray.remove("");
					 }
					 ArrayList<String> expectedlist = new ArrayList<>(Arrays.asList("3PlayMedia"));
					 System.out.println(captionProvidersArray);
					 System.out.println(expectedlist);

					 if(captionProvidersArray.equals(expectedlist)) {
						 Report.reportStep(Driver.getDriver(), "Only 3 play media is  available","PASS",false);
					   }
					   else {
						   Report.reportStep(Driver.getDriver(), "3 play media is not available","FAIL", true);
					   }
				}
				
				if(humanCaptionProviders=="Rev: Rev") {
					 List<WebElement> captionProviderList = getElementList(By.xpath("//li[@role=\"tab\"]"));
					 ArrayList<String> captionProvidersArray = new ArrayList<String>();
					 for(int i=0;i<captionProviderList.size();i++) {
						 String captionProvider=captionProviderList.get(i).getText().trim();
						 captionProvidersArray.add(captionProvider);
						 captionProvidersArray.remove("");
					 }
					 ArrayList<String> expectedlist = new ArrayList<>(Arrays.asList("Rev"));
					 System.out.println(captionProvidersArray);
					 System.out.println(expectedlist);

					 if(captionProvidersArray.equals(expectedlist)) {
						 Report.reportStep(Driver.getDriver(), "Only Rev is  available","PASS",false);
					   }
					   else {
						   Report.reportStep(Driver.getDriver(), "Rev is not  available","FAIL", true);
					   }
				}
				
				if(humanCaptionProviders=="Caption Sync: Arjun Caption Captionsync") {
					 List<WebElement> captionProviderList = getElementList(By.xpath("//li[@role=\"tab\"]"));
					 ArrayList<String> captionProvidersArray = new ArrayList<String>();
					 for(int i=0;i<captionProviderList.size();i++) {
						 String captionProvider=captionProviderList.get(i).getText().trim();
						 captionProvidersArray.add(captionProvider);
						 captionProvidersArray.remove("");
					 }
					 ArrayList<String> expectedlist = new ArrayList<>(Arrays.asList("CaptionSync"));
					 System.out.println(captionProvidersArray);
					 System.out.println(expectedlist);
					 if(captionProvidersArray.equals(expectedlist)) {
						 Report.reportStep(Driver.getDriver(), "Only CaptionSync is  available","PASS",false);
					   }
					   else {
						   Report.reportStep(Driver.getDriver(), "CaptionSync is not  available","FAIL", true);
					   }
				}
				
				if(humanCaptionProviders=="YuJa Pro Captioning") {
					 List<WebElement> captionProviderList = getElementList(By.xpath("//li[@role=\"tab\"]"));
					 ArrayList<String> captionProvidersArray = new ArrayList<String>();
					 for(int i=0;i<captionProviderList.size();i++) {
						 String captionProvider=captionProviderList.get(i).getText().trim();
						 captionProvidersArray.add(captionProvider);
						 captionProvidersArray.remove("");
					 }
					 ArrayList<String> expectedlist = new ArrayList<>(Arrays.asList("YuJa Pro"));
					 System.out.println(captionProvidersArray);
					 System.out.println(expectedlist);

					 if(captionProvidersArray.equals(expectedlist)) {
						 Report.reportStep(Driver.getDriver(), "Only YuJa Pro is  available","PASS",false);
					   }
					   else {
						   Report.reportStep(Driver.getDriver(), "YuJa Pro is not  available","FAIL", true);
					   }
				}
				
				if(humanCaptionProviders=="Cielo24: yuja") {
					 List<WebElement> captionProviderList = getElementList(By.xpath("//li[@role=\"tab\"]"));
					 ArrayList<String> captionProvidersArray = new ArrayList<String>();
					 for(int i=0;i<captionProviderList.size();i++) {
						 String captionProvider=captionProviderList.get(i).getText().trim();
						 captionProvidersArray.add(captionProvider);
						 captionProvidersArray.remove("");
					 }
					 ArrayList<String> expectedlist = new ArrayList<>(Arrays.asList("Cielo24"));
					 System.out.println(captionProvidersArray);
					 System.out.println(expectedlist);

					 if(captionProvidersArray.equals(expectedlist)) {
						 Report.reportStep(Driver.getDriver(), "Only Cielo24 is  available","PASS",false);
					   }
					   else {
						   Report.reportStep(Driver.getDriver(), "Cielo24 is not  available","FAIL", true);
					   }
				}
				
				clickElement("humancaption modal close button", By.id("veryFastCloseFooterBtn_Todo"),10);
				mediaDetailsModal.clickCloseMoreMenu();
				//Driver.getDriver().navigate().refresh();
				mediaLibrary.deleteMedia(autocaptionVideo);
				navigationBar.userLogOut();
			}
			else {
				Report.reportStep(Driver.getDriver(), autocaptionVideo + " media not fully proccessed", "FAIL", true);
				Assert.state(processed, "Video failed to process");
			}
		
   }
    
    public void checkUserHumancaptionButtonDisabled(String userUsername, String userPassword, String autocaptionVideo) throws InterruptedException {
		 mediaLibrary.navigateToMyMediaUserLogin(userUsername, userPassword);
		 mediaLibrary.bulkMediaUpload("src\\fileResources\\humanCaptionVideo");
		 boolean processed = medialibraryHelpers.mediaIsProcessed(autocaptionVideo, 60, 3);
			if(processed) {
				Report.reportStep(Driver.getDriver(), autocaptionVideo + " media fully proccessed", "PASS", false);
				Driver.getDriver().navigate().refresh();
				 WebElement media1=waitForElement(By.xpath("//div[@class=\"videoWrapper\"]"),10);
				 
				 hoverOverElement(media1);
				 clickElement(media1, "More menu", By.cssSelector("[data-automation=\"btnInVideoMenuMore\"]"), 10);
				 mediaDetailsModal.clickAccessiblity();
				 WebElement humancaptionButton=Driver.getDriver().findElement(By.id("humanCaptionButtonID"));
				 boolean actualState=humancaptionButton.isEnabled();
				 System.out.println(actualState);
				 boolean captionEnabledState=mediaDetailsModal.captionButtonEnabledStateIsAsExpected("human caption",false, actualState, 10,autocaptionVideo );
				 System.out.println(captionEnabledState);
				   if (captionEnabledState == true) {
						Report.reportStep(Driver.getDriver(), " humancaption button is not enabled ", "PASS", true);
						
					} else {
						Report.reportStep(Driver.getDriver(), " humancaption button is enabled", "FAIL", false);
					}
				   mediaDetailsModal.clickCloseMoreMenu();
				  // Driver.getDriver().navigate().refresh();
				   mediaLibrary.deleteMedia(autocaptionVideo);
				   navigationBar.userLogOut();
				
			}
			else {
				Report.reportStep(Driver.getDriver(), autocaptionVideo + " media not fully proccessed", "FAIL", true);
				Assert.state(processed, "Video failed to process");
			}
    }
}


