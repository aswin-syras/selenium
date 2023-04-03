package com.yuja.evp.pagehelpers;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.yuja.evp.reports.Report;
import com.yuja.evp.utilities.Driver;
import com.yuja.evp.utilities.Helpers;

import helperinterfaces.UICheck;

import java.util.HashMap;

	public class AdminPanelRosterPageHelpers extends Helpers implements UICheck {
	
	private SignInPageHelpers signInPage = new SignInPageHelpers();
	
	public void navigateToAdminPanelRosterPageUserLogin(String userName, String password){
		signInPage.navigateToLoginPage();
		Driver.getDriver().manage().window().maximize();
		signInPage.loginFast(userName, password);
		waitForElement(By.id("navbar-header"), 10);
		URL =  prop.getProperty("URL")+"/P/Institution/TypeRoster/";
		launchUrl(URL, "Test Automation Enterprise Video Platform");
	}
	
	public void clickCreateUserButton() {
		clickElement("Create User", By.cssSelector("[data-automation=btnCreateUser]"));
	}

	public void fillInUserInfo(String UserID, String role) throws InterruptedException {
		HashMap<String, String> UserCase = new HashMap<String, String>();
	    UserCase.put("Manager", "//*[@id=\"select2-drop\"]/ul/li[3]/div");
	    UserCase.put("Instructor","//*[@id=\"select2-drop\"]/ul/li[2]/div");
	    UserCase.put("Student","//*[@id=\"select2-drop\"]/ul/li[1]/div");
	    UserCase.put("SubAdmin","//*[@id=\"select2-drop\"]/ul/li[4]/div");
	    sendKeys("User ID", By.xpath("//*[@id=\"rosterNewUserUserID\"]"), UserID);
		sendKeys("Password", By.xpath("//*[@id=\"rosterNewUserPassword\"]"), "Asdfghjkl12345!@#$%");
		sendKeys("Password", By.xpath("//*[@id=\"rosterNewUserPassword2\"]"), "Asdfghjkl12345!@#$%");
		clickElement("Selection List", By.id("s2id_rosterNewUserTypeSelect"));	
		clickElement(UserID, By.xpath(UserCase.get(role)));
		List<WebElement> element1 = Driver.getDriver().findElements(By.className("modal-content"));	
		WebElement element2= element1.get(element1.size()-1);
		clickElement(element2, "Confirm", By.cssSelector("[title=\"Confirm\"]"), 10);
		Thread.sleep(1000);
		List<WebElement> element3 = Driver.getDriver().findElements(By.className("modal-content"));	
		WebElement element4 = element3.get(element3.size()-1);
		clickElement(element4, "Confirm", By.cssSelector("[title=\"No, do it later\"]"), 10);
	}
	
	public void deleteUser(String userName) throws InterruptedException {
		sendKeys("Search User", By.id("rosterSearchUserID"),userName);
		clickElement("Search button", By.cssSelector("[data-automation=btnSearchUser]"));
		clickElement("Click Checkbox", By.xpath("//input[@value= '" + userName +"']")); 
		clickElement("Delete Users button", By.id("btnDeleteUsers"));
		List<WebElement> element3 = Driver.getDriver().findElements(By.className("modal-content"));	
		WebElement element4= element3.get(element3.size()-1);
		clickElement(element4, "Confirm", By.cssSelector("[title=\"Yes\"]"), 3);
		List<WebElement> element1 = Driver.getDriver().findElements(By.className("modal-content"));	
		WebElement element2= element1.get(element1.size()-1);
		clickElement(element2, "No button", By.cssSelector("[title=\"No\"]"), 3);			
	}
	
	public void clickActAsUserButton() throws InterruptedException {
		WebElement element = Driver.getDriver().findElement(By.id("page-content-wrapper"));
		clickElement(element, "Act as User Button", By.cssSelector("[data-automation=btnActAsUser]"),3);
		Thread.sleep(1000);
	}			
	
	public void selectActAsUser(String userName) throws InterruptedException {
		List<WebElement> element = Driver.getDriver().findElements(By.className("modal-dialog"));	
		WebElement elementt= element.get(element.size()-1);
		List<WebElement> element1 = Driver.getDriver().findElements(By.className("select2-container"));
		WebElement element2= element1.get(element1.size()-1);
		clickElement("Click the Dropdown list", element2);
		List<WebElement> ele = Driver.getDriver().findElements(By.className("select2-search"));	
		WebElement elemm= ele.get(ele.size()-1);
	    sendKeysModal(elemm,"Entering User Name", By.className("select2-input") , userName);
		List<WebElement> element3 = Driver.getDriver().findElements(By.className("select2-results"));
		WebElement element4= element3.get(element1.size()-1);
		clickElement("Click the Selected Result", element4);	
		clickElement(elementt, "Proceed Button", By.cssSelector("[title=\"Proceed\"]"), 3);	
		verifyElementExist("Act As User button", By.id("act-as-user-button"), 10);
		WebElement elem = Driver.getDriver().findElement(By.id("act-as-user-button"));
		clickElement(elem, "Stop as User", By.xpath("/html/body/div[4]/div/div[1]/button"),5);	
	}
	
	public void rosterButtons(String UserID) throws InterruptedException {
		sendKeys("Search User", By.id("rosterSearchUserID"),UserID);
		clickElement("Search button", By.cssSelector("[data-automation=btnSearchUser]"),10);
		Thread.sleep(1000);
		WebElement useridColumn = Driver.getDriver().findElement(By.cssSelector("[data-automation=\"colUserId\"]"));
		clickElement(useridColumn, "Show Actions Button", By.cssSelector("[data-automation=\"btnShowActions\"]"), 10);	
	}
	
	public void resetPasswordButton() throws InterruptedException {
		WebElement useridColumn = Driver.getDriver().findElement(By.cssSelector("[data-automation=\"colUserId\"]"));
		clickElement(useridColumn, "Reset Password Button", By.cssSelector("[data-automation=\"btnChangePassword\"]"), 10);
		List<WebElement> modalList = Driver.getDriver().findElements(By.className("modal-dialog"));
		WebElement changePasswordModal= modalList.get(modalList.size()-1);
		clickElement(changePasswordModal, "Ok Button", By.cssSelector("[title=\"OK\"]"), 3);	
		List<WebElement> modalList2 = Driver.getDriver().findElements(By.className("modal-body"));
		WebElement changePasswordModal2 =  modalList2.get(modalList2.size()-1);
		String idAttribute= changePasswordModal2.getAttribute("id");
		String[] idPart = idAttribute.split("(?<=\\d)(?=\\D)");
		String passId1= idPart[0] + "newPass";
		String passId2= idPart[0] + "newPass2";
		sendKeys("Entering New Password", By.id(passId1),"Asdfghjkl12345!@#$%");
		sendKeys("Confirm New Password", By.id(passId2),"Asdfghjkl12345!@#$%");
		List<WebElement> buttonSectionList = Driver.getDriver().findElements(By.id("buttonSection"));
		WebElement buttonSection = buttonSectionList.get(buttonSectionList.size()-1);
		clickElement(buttonSection, "Confirm button", By.cssSelector("[title=\"Confirm\"]"), 10);
	}
	
	public void addToCourseButton() throws InterruptedException {
		String CourseName= "UnA";
		WebElement useridColumn = Driver.getDriver().findElement(By.cssSelector("[data-automation=\"colUserId\"]"));
		clickElement(useridColumn, "Add to Course Button", By.cssSelector("[data-automation=\"btnAddToGroup\"]"), 3);
		List<WebElement> modalList= Driver.getDriver().findElements(By.className("modal-dialog"));
		WebElement addToCourseModal = modalList.get(2);
		clickElement(addToCourseModal, "Select a Course Button", By.id("s2id_group"), 3);
		sendKeysModal(addToCourseModal,"Typing Course Name", By.xpath("//*[@id=\"select2-drop\"]/div/input") , CourseName);
		clickElement(addToCourseModal, "Select the Course from List", By.xpath("//*[@id=\"select2-drop\"]/ul/li[2]/div"),3);              
		clickElement(addToCourseModal, "Confirm Addition to Course", By.cssSelector("[data-automation=\"btnTypeRosterAddToGroupConfirm\"]"), 3);  
	}
	
	public void mediaLibraryButton() throws InterruptedException {
		WebElement useridColumn = Driver.getDriver().findElement(By.cssSelector("[data-automation=\"colUserId\"]"));
		clickElement(useridColumn, "Show Media Library", By.cssSelector("[data-automation=\"btnShowMediaLibrary\"]"), 3);		
		verifyElementExist("Media Library Page", By.id("firstPartText"),5);
	}
	
	public void deleteUserButton() throws InterruptedException {
		WebElement useridColumn = Driver.getDriver().findElement(By.cssSelector("[data-automation=\"colUserId\"]"));		
		clickElement(useridColumn, "Delete Button", By.cssSelector("[data-automation=\"btnDeleteUser\"]"), 3);
		List<WebElement> modalList1 = Driver.getDriver().findElements(By.className("modal-content"));
		WebElement deleteModal1= modalList1.get(modalList1.size()-1);
		clickElement(deleteModal1, "Yes Button to Delete", By.cssSelector("[title=\"Yes\"]"), 3);  
		List<WebElement> modalList2 = Driver.getDriver().findElements(By.className("modal-content"));
		WebElement moveMediaCollectionModal= modalList2.get(modalList2.size()-1);
		clickElement(moveMediaCollectionModal, "No Button", By.cssSelector("[title=\"No\"]"), 3); 
	}
	
	public void CheckPageUI() {
		URL = prop.getProperty("URL")+"/P/Institution/TypeRoster/";
		launchUrl(URL, "Test Automation Enterprise Video Platform");
		String sectionTitle = Driver.getDriver().findElement(By.id("secondPartText")).getText();
		boolean check  = waitForElement(By.id("rosterContent"),10).isDisplayed();
		if(check && sectionTitle.equals("Roster")) {
			Report.reportStep(Driver.getDriver(), sectionTitle + " Page loaded successfully", "PASS", false);
			System.out.println(sectionTitle);
		} else Report.reportStep(Driver.getDriver(), sectionTitle + "failed to load", "fail", true);	
	}
	
}
		
		
