package com.yuja.lms.moodle;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.yuja.evp.modalhelpers.AddMediaModalHelperMethods;
import com.yuja.evp.pagehelpers.AdminPanelRosterPageHelpers;
import com.yuja.evp.pagehelpers.MediaLibraryPageHelpers;
import com.yuja.evp.pagehelpers.NavigationBarHelpers;
import com.yuja.evp.pagehelpers.QuizPageHelpers;
import com.yuja.evp.pagetestmethods.MediaLibraryPageTestMethods;
import com.yuja.evp.utilities.Helpers;

public class MoodlePage extends QuizPageHelpers {
	//run
	
	private WebElement mediaUploadModal = null;
	
	
	MediaLibraryPageHelpers mediaLibrary = new MediaLibraryPageHelpers();
	NavigationBarHelpers navbar=new NavigationBarHelpers();
	AdminPanelRosterPageHelpers roster=new AdminPanelRosterPageHelpers();
	
	private void setMediaUploadModal() {
		System.out.println("Fetching Media Upload modal...");
		this.mediaUploadModal = waitForElement(By.xpath("//button[@title=\"Upload Video\"]"), 10);
		System.out.println("Media Upload modal fetched");
	}
	
	public void navigateToLoginPage() {
		String URL = "https://tmoodle2.yuja.com/login/index.php";
		launchUrl(URL, "Moodle login page");
	}
	
	public void loginFast(String username, String password) {
		fillInUserId(username);
		fillInPassword(password);
		clickSignInButton();
		Boolean sitehomeexist=verifyElementExist("dashboard", By.xpath("//div[@class=\"media\"]//span[text()='Dashboard']"));
		System.out.println(sitehomeexist);
		if(sitehomeexist==false) {
			clickElement("show sidebar", By.xpath("//button[@class='btn nav-link float-sm-left mr-1 btn-light bg-gray']"));
			}
	}
	
	public void logout() {
		clickElement("user button", By.xpath("//span[@class=\"userbutton\"]"));
		clickElement("logout button", By.xpath("//a[@data-title=\"logout,moodle\"]"));
		
	}
	
	public void navigateToCourse(String userName, String password){
		navigateToLoginPage();
		driver.manage().window().maximize();
		loginFast(userName, password);
		waitForElement(By.xpath("//span[@class='site-name d-none d-md-inline']"), 10);
		String URL = "https://tmoodle2.yuja.com/course/view.php?id=142";
		launchUrl(URL, "Moodle automation course page");
	}
	
	public void selectCourseFromSiteHome(String courseName) {
		clickElement("site home", By.xpath("//a[@href=\"https://tmoodle2.yuja.com/?redirect=0\"]"));
		List<WebElement> courseList= getElementList(By.xpath("//a[@class='aalink']"));
		for(int i=0;i<courseList.size();i++) {
		WebElement element=courseList.get(i);
		String obtainedCourseName = element.getText();
		if(obtainedCourseName.equals(courseName)) {
			clickElement("click on given course",element);
			break;
		    }
		 }
	}
	
	public void createMoodleCourse(String adminUserName, String adminPassword, String courseName, String courseShortName) throws InterruptedException{
		navigateToLoginPage();
		driver.manage().window().maximize();
		loginFast(adminUserName, adminPassword);
		waitForElement(By.xpath("//span[@class='site-name d-none d-md-inline']"), 10);
		String URL = "https://tmoodle2.yuja.com/admin/search.php#linkcourses";
		launchUrl(URL, "Moodle automation course creation page");
		clickElement("Add a new course button", By.xpath("//a[text()='Add a new course']"));
		sendKeys("Enter course full name", By.xpath("//input[@name='fullname']"),courseName+getRandomInteger(1000));
		sendKeys("Enter course short name", By.xpath("//input[@name='shortname']"),courseShortName+getRandomInteger(1000));
		clickElement("save and display button", By.xpath("//input[@name='saveanddisplay']"));
		clickElement("Course media page", By.xpath("//a[@data-key='coursehome']"));
	}
	
	public String createMoodleUser(String adminUserName, String adminPassword,  String email, String newUsername, String newUserPassword) throws InterruptedException{
		navigateToLoginPage();
		driver.manage().window().maximize();
		loginFast(adminUserName, adminPassword);
		waitForElement(By.xpath("//span[@class='site-name d-none d-md-inline']"), 10);
		String URL = "https://tmoodle2.yuja.com/admin/search.php#linkusers";
		launchUrl(URL, "Moodle automation user creation page");
		clickElement("add a new user button", By.xpath("//a[text()='Add a new user']"));
		String newUserNameRandom=newUsername+getRandomInteger(1000);
		sendKeys("Enter user name", By.xpath("//input[@name='username']"),newUserNameRandom);
		WebElement passwordField=driver.findElement(By.xpath("//em[text()='Click to enter text']"));
		clickElement("Enter password",passwordField);
		typeKeys(newUserPassword);
		sendKeys("Enter first name", By.xpath("//input[@name='firstname']"),newUserNameRandom);
		sendKeys("Enter surname", By.xpath("//input[@name='lastname']"),newUserNameRandom);
		sendKeys("Enter email", By.xpath("//input[@name='email']"),email+getRandomInteger(10000));
		clickElement("save and display button", By.xpath("//input[@name='submitbutton']"));
		return newUserNameRandom;
	}
	
	public void NavigateToLTI(String embedMediaTitle){
		clickEmbedMediaMoodle(embedMediaTitle);
		switchToIframe("switch to lti frame", By.id("contentframe"), 10);
	}
	
	public void moodleGradebook(){
		clickElement("grades button", By.xpath("(//span[text()='Grades'])[2]"));
		switchToIframe("switch to lti frame", By.id("contentframe"), 10);
	}
	
	
	
	//used directly in test class. Login create publish embed attend and access the yuja gradebook and check lms grade sync via activity log
	
	public void loginCreateandPublishEmbedQuiz(String userName, String password,String embedMediaTitle,String mediaTitle,String name,String question,String option1, String option2,String possibleans1, String possibleans2,String hint,String stuserName, String stpassword, String sa,String fitbans,String courseName,String marks,String studentFullName, String studentNameinActivitylog) throws Exception {
		navigateToCourse(userName,password);
		NavigateToLTI(embedMediaTitle);
		Thread.sleep(3000);
		String quizNewName=createandPublishQuiz(mediaTitle,name,question, option1,  option2, possibleans1, possibleans2, hint,courseName);
		String quizFinalName="'"+quizNewName+"'";
		driver.switchTo().defaultContent();
		String URL = "https://tmoodle2.yuja.com/course/view.php?id=142";
		launchUrl(URL, "Moodle automation course page");
	        accessCIMMediaChooser();
	        CIMMediaChooserQuizEmbed(quizNewName);
	        logout();
	        navigateToCourse(stuserName,stpassword);
	        clickElement("click embed media title", By.xpath("//span[text()="+quizFinalName+"]"));
	        switchToIframe("switch to lti frame", By.id("contentframe"), 10);
	        switchToIframe("switch to video player frame", By.id("yujahtml5playerInVideoPoll"), 10);
	        clickPlaybutton();
		Thread.sleep(5000);
		driver.switchTo().parentFrame();
		StudentattendallquestionsCorrectly(sa, fitbans);
		driver.switchTo().defaultContent();
		logout();
		navigateToCourse(userName,password);
		NavigateToLTI(embedMediaTitle);
		Thread.sleep(3000);
		checkGradebookTestafterLoginforMultiple(mediaTitle,quizNewName,courseName ,studentFullName,marks );
		clickElement("Click Manage Media",By.xpath("//span[@id='topBarTabName3']"),10);
		Thread.sleep(2000);
		checkActivityLogforQuizSync(mediaTitle, studentNameinActivitylog,marks, quizNewName);
		Thread.sleep(2000);
		clickElement("Click close button",By.xpath("(//button[@data-automation=\"btnCloseModalDialog\"])[2]"),10);
		mediaDetailsModal.clickDeleteQuizButton();
		clickElement("yes",By.xpath("//button[@title='Yes']"),10);
	}
	
	//used directly in test class. Login create embed attend and access the yuja gradebook do manual sync and check lms grade sync via activity log
	
	public void loginCreateandEmbedPlaybackQuiz(String userName, String password,String embedMediaTitle,String videoNameforPlaybackquiz,String playbackQuizTitle,String stuserName, String stpassword, String courseName,String marks,String studentFullName, String studentNameinActivityLog) throws Exception {
	
		navigateToCourse(userName,password);
		NavigateToLTI(embedMediaTitle);
		clickElement("Click Manage Media",By.xpath("//span[@id='topBarTabName3']"),10);
		Thread.sleep(2000);
		mediaLibrary.accessMediaMoreMenu(videoNameforPlaybackquiz);
		mediaDetailsModal.clickQuizzes();
		String quizNewName=createPlaybackquiz(playbackQuizTitle,videoNameforPlaybackquiz);
		String quizFinalName="'"+quizNewName+"'";
		driver.switchTo().defaultContent();
		String URL = "https://tmoodle2.yuja.com/course/view.php?id=142";
		launchUrl(URL, "Moodle automation course page");
		accessCIMMediaChooser();
	        CIMMediaChooserQuizEmbed(quizNewName);
		logout();
		navigateToCourse(stuserName,stpassword);
		clickElement("click embed media title", By.xpath("//span[text()="+quizFinalName+"]"));
		switchToIframe("switch to lti frame", By.id("contentframe"), 10);
	        switchToIframe("switch to video player frame", By.id("yujahtml5playerInVideoPoll"), 10);
		clickPlaybutton();
		Thread.sleep(48000);
		driver.switchTo().defaultContent();
		logout();
		navigateToCourse(userName,password);
		NavigateToLTI(embedMediaTitle);
		Thread.sleep(3000);
		checkGradebookforPlaybackQuiz(videoNameforPlaybackquiz, courseName, marks, studentFullName,quizNewName);
		clickElement("Click Manage Media",By.xpath("//span[@id='topBarTabName3']"),10);
		Thread.sleep(2000);
		checkActivityLogforQuizSync(videoNameforPlaybackquiz, studentNameinActivityLog,marks, quizNewName);
		Thread.sleep(2000);
		clickElement("Click close button",By.xpath("(//button[@data-automation=\"btnCloseModalDialog\"])[2]"),10);
		mediaDetailsModal.clickDeleteQuizButton();
		clickElement("yes",By.xpath("//button[@title='Yes']"),10);    
	}
	
	// Method directly used in test class. Login embed media and access as student
	
	public void loginEmbedMedia(String userName, String password,String name,String stuserName,String stpassword, String embedMediaName) throws InterruptedException {
		navigateToCourse(userName,password);
		accessCIMMediaChooser();
		CIMMediaChooserMediaEmbed(name);
        logout();
	    navigateToCourse(stuserName,stpassword);
	    NavigateToLTI(embedMediaName);
	   
	}
	
	//Method directly used in test class. login upload media and check in media chooser as well as media library
	
	public void CIMMediaChooserMediaUpload(String userName, String password, String mediaDirectoryPath, String LTILinkName) throws InterruptedException {
		navigateToCourse(userName,password);
		accessCIMMediaChooser();
		Thread.sleep(5000);
		clickElement("click upload media", By.cssSelector("div[id=\"uploadMediaTab\"]"));
		Thread.sleep(2000);
		mediaChooserBulkUploadMedia(mediaDirectoryPath);
		List<String> mediaTitlelist=getMediaTitleArrayfromDirectory(mediaDirectoryPath);
		System.out.println(mediaTitlelist);
        Actions action = new Actions(driver);
		action.sendKeys(Keys.ESCAPE).build().perform();
	    Thread.sleep(4000);
		String URL = "https://tmoodle2.yuja.com/course/view.php?id=142";
		launchUrl(URL, "Moodle automation course page");
		NavigateToLTI(LTILinkName);
		clickElement("Click Manage Media",By.xpath("//span[@id='topBarTabName3']"),10);
		checkMediaExitsinMediaLibrary(mediaTitlelist);
		Thread.sleep(4000);
	}
	
	public void setAndResetAutomaticProvision(String userName,String password,String userOrcourse,Boolean enabledOrDisabled) {
		HashMap<String, String> provisionType = new HashMap<String, String>();
		provisionType.put("user", "(//input[@id='autoProvisionId'])[2]");
		provisionType.put("course", "(//input[@id=\"autoProvisionClassesId\"])[2]");
	    mediaLibrary.navigateToMyMediaUserLogin(userName, password);
	    URL = prop.getProperty("URL")+"P/Institution/APIManagementServlet/";
        launchUrl(URL, "Test Automation Enterprise Video Platform");
        Select integrationDropdown = new Select(driver.findElement(By.id("apiPicker")));
        integrationDropdown.selectByValue("lti3");
        WebElement checkBox=driver.findElement(By.xpath(provisionType.get(userOrcourse)));
        boolean autoProvisionCourseCheckboxSelected=checkBox.isSelected();
        System.out.println(autoProvisionCourseCheckboxSelected);
        if(autoProvisionCourseCheckboxSelected^enabledOrDisabled) {
        	clickElement("checkbox",checkBox );
            clickElement("save",By.xpath("//form[@id=\"lti3SettingsGeneral\"]//button[@title=\"Save\"]") );
            clickElement("save confirm",By.xpath("//div[@class=\"modal-content\"]//button[@title=\"Confirm\"]") );}
            else {
            	reportStep("The checkbox is setting was same as required","PASS",false);
            }	
        }
	
	public void setRoleMapping(String userName, String password, String yujaRolefromAdmin, String yujaRolefromTeacher, String yujaRolefromStudent) throws InterruptedException {
		 mediaLibrary.navigateToMyMediaUserLogin(userName, password);
		 URL =  prop.getProperty("URL")+"P/Institution/APIManagementServlet/";
	     launchUrl(URL, "Test Automation Enterprise Video Platform");
	     Select integrationDropdown = new Select(driver.findElement(By.id("apiPicker")));
	     integrationDropdown.selectByValue("lti3");
	     Select lmsDropdown = new Select(driver.findElement(By.id("LTI3LMSSelect")));
	     lmsDropdown.selectByVisibleText("Moodle");
	     Select moodleAdminRole = new Select(driver.findElement(By.xpath("(//select[@id=\"moodleAdminRoleTo\"])[2]")));
	     moodleAdminRole.selectByVisibleText(yujaRolefromAdmin);
	     Select moodleTeacherRole = new Select(driver.findElement(By.xpath("(//select[@id=\"moodleTeacherRoleTo\"])[2]")));
	     moodleTeacherRole.selectByVisibleText(yujaRolefromTeacher);
	     Select moodleStudentRole = new Select(driver.findElement(By.xpath("(//select[@id=\"moodleStudentRoleTo\"])[2]")));
	     moodleStudentRole.selectByVisibleText(yujaRolefromStudent);
	     clickElement("save",By.xpath("//form[@id=\"lti3SettingsGeneral\"]//button[@title=\"Save\"]") );
         clickElement("save confirm",By.xpath("//div[@class=\"modal-content\"]//button[@title=\"Confirm\"]") );
	}
	
	public void setUserTypeToLockedorUnlocked(String lockOrUnlock,String userType) throws InterruptedException {
		 URL = prop.getProperty("URL")+"P/Institution/TypeRoster/";
	     launchUrl(URL, "Test Automation Enterprise Video Platform");
	     roster.rosterButtons("rolemappinguser");
		 
		 if(lockOrUnlock=="lock") {
			clickElement("usertype",By.xpath("//a[@data-automation=\"btnUserType\"]") );
			Select userTypeDropdown = new Select(driver.findElement(By.xpath("//div[@class='editable-input']//select")));
			userTypeDropdown.selectByVisibleText(userType);
		    clickElement("save",By.xpath("//button[@class=\"btn btn-primary btn-sm editable-submit\"]") );
			 
			clickElement("lock",By.xpath("//a[@data-automation=\"btnLockUserType\"]") );
			Select lockDropdown = new Select(driver.findElement(By.xpath("//div[@class='editable-input']//select")));
			lockDropdown.selectByVisibleText("Locked");
			clickElement("save",By.xpath("//button[@class=\"btn btn-primary btn-sm editable-submit\"]") );
			}
		 if(lockOrUnlock=="unlock") {
			clickElement("lock",By.xpath("//a[@data-automation=\"btnLockUserType\"]") );
			Select lockDropdown = new Select(driver.findElement(By.xpath("//div[@class='editable-input']//select")));
			lockDropdown.selectByVisibleText("Not Locked");
			clickElement("save",By.xpath("//button[@class=\"btn btn-primary btn-sm editable-submit\"]") );
			}
	}
	
    	
	
    public void setStartPageOptionAsMediaChannel() {
        URL = prop.getProperty("URL")+"P/Institution/MenuManagement/";
        launchUrl(URL, "Test Automation Enterprise Video Platform");
        Select startPageOptionITManager = new Select(driver.findElement(By.id("select_defaultLandingPageIT")));
        startPageOptionITManager.selectByValue("mediaChannel");
        Select startPageOptionInstructor = new Select(driver.findElement(By.id("select_defaultLandingPageInstructor")));
        startPageOptionITManager.selectByValue("mediaChannel");
        Select startPageOptionStudent = new Select(driver.findElement(By.id("select_defaultLandingPageStudent")));
        startPageOptionITManager.selectByValue("mediaChannel");
        clickElement("save",By.xpath("//form[@id=\"saveSetupPrefences\"]//button[@id=\"setupPrefences_saveButton\"]"));
     }
	
	// Method directly used in test class.
	public void checkAutoprovisionOfCourse(String adminUserName, String adminPassword, String courseName, String courseShortName, String externalToolCustomName, String externalToolVisibleName, String userName, String password ) throws InterruptedException {
		setAndResetAutomaticProvision(userName,password,"course",true);
		setStartPageOptionAsMediaChannel();
		createMoodleCourse(adminUserName, adminPassword,courseName,courseShortName);
		String moodleCourseName=driver.findElement(By.xpath("//div[@class=\"page-header-headings\"]")).getText();
		addExternalTool(externalToolCustomName,externalToolVisibleName);
		NavigateToLTI("'TEST AUTOMATION STAGING 1.3'");
		String provisionedCourseName=driver.findElement(By.xpath("//li[@class='nav-item active']")).getAttribute("title");
	
		if(provisionedCourseName.contains(moodleCourseName)) {
				reportStep("The moodle course is successfully autoprovisioned in yuja", "PASS", false);
			} else {
				reportStep("The moodle course is not successfully autoprovisioned in yuja", "FAIL", true);
			}	
		}
		
		
	// Method directly used in test classes
	public void checkManualprovisionOfNewCourse(String adminUserName, String adminPassword, String courseName, String courseShortName, String externalToolCustomName, String externalToolVisibleName, String userName, String password ) throws InterruptedException {
		setAndResetAutomaticProvision(userName,password,"course",false);
		setStartPageOptionAsMediaChannel();
		createMoodleCourse(adminUserName, adminPassword,courseName,courseShortName);
		addExternalTool(externalToolCustomName,externalToolVisibleName);
		NavigateToLTI("'TEST AUTOMATION STAGING 1.3'");
		clickElement("Create new course button", By.xpath("//a[@title=\"Create a Course\"]"));
		waitForElement(By.xpath("//input[@id=\"className\"]"),10);
		sendKeys("Enter manual course full name", By.xpath("//input[@id=\"className\"]"),courseName+getRandomInteger(1000));
		sendKeys("Enter manual course short name", By.xpath("//input[@id=\"classCode\"]"),courseShortName+getRandomInteger(1000));
		clickElement("Create new course button", By.xpath("//button[@data-automation=\"btnCreateYourGroup\"]"));
		waitForElement(By.xpath("//li[@class='nav-item active']"),10);
		String provisionedCourseName=driver.findElement(By.xpath("//li[@class='nav-item active']")).getAttribute("title");
		
       if(provisionedCourseName.contains(courseName)) {
    	   reportStep("The moodle course is successfully manuallyprovisioned in yuja", "PASS", false);
		} else {
			reportStep("The moodle course is not successfully manuallyprovisioned in yuja", "FAIL", true);
		}	
       }
	
	// Method directly used in test class.
	public void checkManualprovisionToExistingCourse(String adminUserName, String adminPassword, String courseName, String courseShortName, String externalToolCustomName, String externalToolVisibleName, String userName, String password ) throws InterruptedException {
		setAndResetAutomaticProvision(userName,password,"course",false);
		setStartPageOptionAsMediaChannel();
		createMoodleCourse(adminUserName, adminPassword,courseName,courseShortName);
		addExternalTool(externalToolCustomName,externalToolVisibleName);
		NavigateToLTI("'TEST AUTOMATION STAGING 1.3'");
		clickElement("Link to an existing course button", By.xpath("//a[@title=\"Link to an Existing Course\"]"));
		waitForElement(By.xpath("//select[@id=\"classSelectBox\"]"),10);
		clickElement("dropdown", By.xpath("//select[@id=\"classSelectBox\"]"));
		clickElement("choose the auto existing course", By.xpath("//select[@id=\"classSelectBox\"]//option[text()='aec - Mar 2023 - Auto Existing Course']"));
		clickElement("Click OK button after selecting the existing course", By.xpath("//button[@title=\"OK\"]"));
		waitForElement(By.xpath("//li[@class='nav-item active']"),15);
		String provisionedCourseName=driver.findElement(By.xpath("//li[@class='nav-item active']")).getAttribute("title");
		
       if(provisionedCourseName.contains("Auto Existing Course")) {
    	   reportStep("The moodle course is successfully manuallyprovisioned  and connected to existing course in yuja", "PASS", false);
		} else {
			reportStep("The moodle course is not successfully manuallyprovisioned connected to existing course in yuja", "FAIL", true);
		}	
       }
	
	// Method directly used in test class.
	public void checkAutoprovisionOfUser(String adminUserName, String adminPassword, String userName, String password, String email, String newUsername, String newUserPassword, String courseName, String role, String courserole ) throws InterruptedException {
		
		HashMap<String, String> CourseRoleType = new HashMap<String, String>();
		CourseRoleType.put("GroupMember", "//div[@data-automation=\"divGroupMembers\"]//div[@class=\"user-list-item\"]");
		CourseRoleType.put("GroupOwner", "//div[@data-aitomation=\"divGroupOwners\"]//div[@class=\"user-list-item\"]");
		
		setAndResetAutomaticProvision(userName,password,"user",true);
		String studentUserName=createMoodleUser(adminUserName, adminPassword,email,newUsername,newUserPassword);
		selectCourseFromSiteHome(courseName);
		
		clickElement("participants", By.xpath("//a[@data-key=\"participants\"]"));
		clickElement("enroll users", By.xpath("//input[@value=\"Enrol users\"]"));
		sendKeys("Enter username", By.xpath("(//input[@class=\"form-control\" and contains(id,form_autocomplete_input-1677790244437)])[2]"),studentUserName);
		clickElement("click from Dropdown", By.xpath("//ul[@class=\"form-autocomplete-suggestions\"]//li"));
		
		Select assignRoledropdown = new Select(driver.findElement(By.id("id_roletoassign")));
		assignRoledropdown.selectByVisibleText(role);
		clickElement("enroll selected users button", By.xpath("//button[text()=\"Enrol selected users and cohorts\"]"));
		logout();
		navigateToLoginPage();
		driver.manage().window().maximize();
		loginFast(studentUserName, newUserPassword);
		selectCourseFromSiteHome(courseName);
		NavigateToLTI("'TEST AUTOMATION STAGING 1.3'");
		waitForElement(By.xpath("//*[@id=\"bi_userInfoDropdown\"]"),15);
		navbar.clickMyAccountButton();
		navbar.clickMyAccountDropdownOption();
		
		String myaccountStudentName=driver.findElement(By.xpath("//input[@id='firstName']")).getAttribute("value");
		navbar.clickCoursesAndGroups();
		sendKeys("enter course name",By.xpath("//input[@class=\"group-management-inputbox form-control search-box\"]"),courseName);
		clickElement("choose course after search",By.xpath("//ul[@id=\"courses-and-groups\"]//li"));
		List<WebElement> memberList= getElementList(By.xpath(CourseRoleType.get(courserole)));
		String obtainedmemberFirstname=null;
		for(int i=0;i<memberList.size();i++) {
			WebElement element=memberList.get(i);
			String obtainedmemberName = element.getText();
			String obtainedmembersplitFirstname[]=obtainedmemberName.split(" ",2);
		    obtainedmemberFirstname=obtainedmembersplitFirstname[0];
		    System.out.println(obtainedmemberFirstname);
			if(obtainedmemberFirstname.equals(studentUserName) && obtainedmemberFirstname.equals(myaccountStudentName)){
				reportStep("The moodle user is enrolled to  course in yuja", "PASS", false);
				break;
				}
		}
	
		if(obtainedmemberFirstname.equals(myaccountStudentName) && obtainedmemberFirstname.equals(studentUserName)) {
			System.out.println(myaccountStudentName+" "+studentUserName + " "+obtainedmemberFirstname );
			reportStep("The moodle user is successfully provisioned  and enrolled to  course in yuja", "PASS", false);
			}else {
			reportStep("The moodle user is  not successfully provisioned  and enrolled to  course in yuja", "FAIL", true);
			}
		}
	// Method directly used in test class.
	
    public void checkAutoprovisionOfUserinMultipleCourses(String adminUserName, String adminPassword, String userName, String password, String email, String newUsername, String newUserPassword, String[] courseName, String role, String courserole ) throws InterruptedException {
		
		HashMap<String, String> CourseRoleType = new HashMap<String, String>();
		CourseRoleType.put("GroupMember", "//div[@data-automation=\"divGroupMembers\"]//div[@class=\"user-list-item\"]");
		CourseRoleType.put("GroupOwner", "//div[@data-aitomation=\"divGroupOwners\"]//div[@class=\"user-list-item\"]");
		
		setAndResetAutomaticProvision(userName,password,"user",true);
		String studentUserName=createMoodleUser(adminUserName, adminPassword,email,newUsername,newUserPassword);
		String myaccountStudentName=null;
		
		for(String course:courseName) {
		selectCourseFromSiteHome(course);
		clickElement("participants", By.xpath("//a[@data-key=\"participants\"]"));
		clickElement("enroll users", By.xpath("//input[@value=\"Enrol users\"]"));
		sendKeys("Enter username", By.xpath("(//input[@class=\"form-control\" and contains(id,form_autocomplete_input-1677790244437)])[2]"),studentUserName);
		clickElement("click from Dropdown", By.xpath("//ul[@class=\"form-autocomplete-suggestions\"]//li"));
		
		Select assignRoledropdown = new Select(driver.findElement(By.id("id_roletoassign")));
		assignRoledropdown.selectByVisibleText(role);
		clickElement("enroll selected users button", By.xpath("//button[text()=\"Enrol selected users and cohorts\"]"));
		}
		logout();
		navigateToLoginPage();
		driver.manage().window().maximize();
		loginFast(studentUserName, newUserPassword);
		
		for(String course:courseName) {
		selectCourseFromSiteHome(course);
		NavigateToLTI("'TEST AUTOMATION STAGING 1.3'");
		waitForElement(By.xpath("//*[@id=\"bi_userInfoDropdown\"]"),15);
		navbar.clickMyAccountButton();
		navbar.clickMyAccountDropdownOption();
		myaccountStudentName=driver.findElement(By.xpath("//input[@id='firstName']")).getAttribute("value");
		navbar.clickCoursesAndGroups();
		sendKeys("enter course name",By.xpath("//input[@class=\"group-management-inputbox form-control search-box\"]"),course);
		clickElement("choose course after search",By.xpath("//ul[@id=\"courses-and-groups\"]//li"));
		List<WebElement> memberList= getElementList(By.xpath(CourseRoleType.get(courserole)));
		String obtainedmemberFirstname=null;
		for(int i=0;i<memberList.size();i++) {
			WebElement element=memberList.get(i);
			String obtainedmemberName = element.getText();
			String obtainedmembersplitFirstname[]=obtainedmemberName.split(" ",2);
		    obtainedmemberFirstname=obtainedmembersplitFirstname[0];
		    System.out.println(obtainedmemberFirstname);
			if(obtainedmemberFirstname.equals(studentUserName) && obtainedmemberFirstname.equals(myaccountStudentName)){
				reportStep("The moodle user is enrolled to  course in yuja", "PASS", false);
				break;
				}
		    }
		driver.switchTo().defaultContent();
		clickElement("Moodle Title", By.xpath("//a[@href='https://tmoodle2.yuja.com']"));
		}
		logout();
		roster.navigateToAdminPanelRosterPageUserLogin(userName,password);
		roster.rosterButtons(myaccountStudentName);
		List<WebElement> rowList=getElementList(By.xpath("//table[@id=\"rosterTable\"]//tr"));
		if(rowList.size()==2) {
			reportStep("A single moodle user is successfully provisioned when enrolled to multiple courses", "PASS", false);
		}else {
		    reportStep("A single moodle user is successfully provisioned when enrolled to multiple courses", "FAIL", true);
			}
		}
//Method directly used in test class.

   public void checkManualprovisionOfUser(String adminUserName, String adminPassword, String userName, String password, String email, String newUsername, String newUserPassword, String courseName, String role, String courserole, String type, String existingStudentUsername ) throws InterruptedException {
	
	   HashMap<String, String> CourseRoleType = new HashMap<String, String>();
	   CourseRoleType.put("GroupMember", "//div[@data-automation=\"divGroupMembers\"]//div[@class=\"user-list-item\"]");
	   CourseRoleType.put("GroupOwner", "//div[@data-aitomation=\"divGroupOwners\"]//div[@class=\"user-list-item\"]");
	
	   setAndResetAutomaticProvision(userName,password,"user",false);
	   String studentUserName=createMoodleUser(adminUserName, adminPassword,email,newUsername,newUserPassword);
	   selectCourseFromSiteHome(courseName);
	
	   clickElement("participants", By.xpath("//a[@data-key=\"participants\"]"));
	   clickElement("enroll users", By.xpath("//input[@value=\"Enrol users\"]"));
	   sendKeys("Enter username", By.xpath("(//input[@class=\"form-control\" and contains(id,form_autocomplete_input-1677790244437)])[2]"),studentUserName);
	   clickElement("click from Dropdown", By.xpath("//ul[@class=\"form-autocomplete-suggestions\"]//li"));
	
	   Select assignRoledropdown = new Select(driver.findElement(By.id("id_roletoassign")));
	   assignRoledropdown.selectByVisibleText(role);
	   clickElement("enroll selected users button", By.xpath("//button[text()=\"Enrol selected users and cohorts\"]"));
	   logout();
	   navigateToLoginPage();
	   driver.manage().window().maximize();
	   loginFast(studentUserName, newUserPassword);
	   selectCourseFromSiteHome(courseName);
	   NavigateToLTI("'TEST AUTOMATION STAGING 1.3'");
	
	   if(type=="new") {
		   waitForElement(By.xpath("//input[@id=\"newPass1\"]"),15);
		   sendKeys("Enter password", By.xpath("//input[@id=\"newPass1\"]"),password);
		   clickElement("check the agree checkbox",By.xpath("//input[@id=\"termsCheck\"]"));
		   clickElement("create user button",By.xpath("//button[@title=\"Create\"]"));
		   waitForElement(By.xpath("//*[@id=\"bi_userInfoDropdown\"]"),15);
		   navbar.clickMyAccountButton();
		   navbar.clickMyAccountDropdownOption();
		   String myaccountStudentName=driver.findElement(By.xpath("//input[@id='firstName']")).getAttribute("value");
		   navbar.clickCoursesAndGroups();
		   sendKeys("enter course name",By.xpath("//input[@class=\"group-management-inputbox form-control search-box\"]"),courseName);
		   clickElement("choose course after search",By.xpath("//ul[@id=\"courses-and-groups\"]//li"));
		   List<WebElement> memberList= getElementList(By.xpath(CourseRoleType.get(courserole)));
		   String obtainedmemberFirstname=null;
		   for(int i=0;i<memberList.size();i++) {
			   WebElement element=memberList.get(i);
			   String obtainedmemberName = element.getText();
			   String obtainedmembersplitFirstname[]=obtainedmemberName.split(" ",2);
			   obtainedmemberFirstname=obtainedmembersplitFirstname[0];
			   System.out.println(obtainedmemberFirstname);
			   if(obtainedmemberFirstname.equals(studentUserName) && obtainedmemberFirstname.equals(myaccountStudentName)){
				   reportStep("The moodle user is enrolled to  course in yuja", "PASS", false);
				   break;
			   }
			  }
		   if(obtainedmemberFirstname.equals(myaccountStudentName) && obtainedmemberFirstname.equals(studentUserName)){
			   System.out.println(myaccountStudentName+" "+studentUserName + " "+obtainedmemberFirstname );
			   reportStep("The moodle user is successfully provisioned  and enrolled to  course in yuja", "PASS", false);
		   }else {
			   reportStep("The moodle user is  not successfully provisioned  and enrolled to  course in yuja", "FAIL", true);
		   }
	   }
	
	   else if(type=="existing") {
		   waitForElement(By.xpath("//div[@id=\"loginTab\"]"),15);
		   clickElement("existing user tab",By.xpath("//div[@id=\"loginTab\"]"));
		   sendKeys("Enter username", By.xpath("//input[@name=\"userID\"]"),existingStudentUsername);
		   sendKeys("Enter password", By.xpath("//input[@id=\"password\"]"),"jamNOW123!@#123");
		   clickElement("link button",By.xpath("//button[@id=\"loginButton\"]"));
		   waitForElement(By.xpath("//*[@id=\"bi_userInfoDropdown\"]"),15);
		   navbar.clickMyAccountButton();
		   navbar.clickMyAccountDropdownOption();
		   String StudentName=driver.findElement(By.xpath("//input[@id=\"personalMeetingRoomInput\"]")).getAttribute("value");
		   String obtainedmembersplitFirstname[]=StudentName.split("/");
		   String obtainedStudentUsername=obtainedmembersplitFirstname[2];
		   System.out.println(obtainedStudentUsername);
		   if(obtainedStudentUsername.equals(existingStudentUsername)){
			   reportStep("The moodle user is successfully provisioned to existing user in yuja", "PASS", false);
				}else {
					reportStep("The moodle user is  not successfully provisioned to existing user in yuja", "FAIL", true);
				}
			}
		}
   
   public void checkRoleMapping(String userName, String password, int expectedMainMenuListSize, String moodleRole, String courserole) throws InterruptedException{
	   
	   HashMap<String, String> CourseRoleType = new HashMap<String, String>();
	   CourseRoleType.put("GroupMember", "//div[@data-automation=\"divGroupMembers\"]//div[@class=\"user-list-item\"]");
	   CourseRoleType.put("GroupOwner", "//div[@data-aitomation=\"divGroupOwners\"]//div[@class=\"user-list-item\"]");
	   
	   navigateToCourse(userName,password);
	   clickElement("participants", By.xpath("//a[@data-key=\"participants\"]"));
	   List<WebElement> memberListInMoodle= getElementList(By.xpath("//table[@id='participants']//tbody//tr//th//a"));
	   String obtainedmoodlememberFirstname=null;
	   for(int i=0;i<memberListInMoodle.size();i++) {
			WebElement element=memberListInMoodle.get(i);
			String obtainedmemberName = element.getText();
			String obtainedmembersplitFirstname[]=obtainedmemberName.split(" ",2);
			obtainedmoodlememberFirstname=obtainedmembersplitFirstname[0];
		    System.out.println(obtainedmoodlememberFirstname);
			if(obtainedmoodlememberFirstname.equals("rolemappinguser")) {
				int rowposition=i+1;
				clickElement("edit role button", By.xpath("(//span[@class=\"quickediticon visibleifjs\"])["+rowposition+"]"));
				clickElement("close current role button", By.xpath("//span[@class=\"badge badge-info mb-3 mr-1\"]//span"));
				sendKeys("enter moodle role",By.xpath("//input[contains(@id,'form_autocomplete_input')]"),moodleRole);
				clickElement("click autosuggestion",By.xpath("//li[contains(@id,'form_autocomplete_suggestions')]"));
				Thread.sleep(2000);
				clickElement("save role button", By.xpath("//i[@title=\"Save changes\"]"));
				Thread.sleep(2000);
				reportStep("New moodle role is assigned to user", "PASS", false);
				break;
				}
			}
	   logout();
	   navigateToLoginPage();
	   driver.manage().window().maximize();
	   loginFast("rolemappinguser", "jamNOW123/");
	   selectCourseFromSiteHome("AUTOMATION MOODLE COURSE");
	   NavigateToLTI("'TEST AUTOMATION STAGING 1.3'");
	   waitForElement(By.xpath("//button[@title=\" Main Menu\"]"),15);
	   clickElement("main menu button", By.xpath("//button[@title=\" Main Menu\"]"));
	   List<WebElement> mainmenuList= getElementList(By.xpath("//a[@class='yujaAjax']"));
	   int mainMenuListSize=mainmenuList.size();
	   clickElement("Courses and Groups button", By.cssSelector("[data-automation=\"btnCourseAndGroups\"]"));
	   sendKeys("enter course name",By.xpath("//input[@class=\"group-management-inputbox form-control search-box\"]"),"AUTOMATION MOODLE COURSE");
	   clickElement("choose course after search",By.xpath("//ul[@id=\"courses-and-groups\"]//li"));
	   List<WebElement> memberList= getElementList(By.xpath(CourseRoleType.get(courserole)));
	   String obtainedmemberFirstname=null;
	   for(int i=0;i<memberList.size();i++) {
			WebElement element=memberList.get(i);
			String obtainedmemberName = element.getText();
			String obtainedmembersplitFirstname[]=obtainedmemberName.split(" ",2);
		    obtainedmemberFirstname=obtainedmembersplitFirstname[0];
		    System.out.println(obtainedmemberFirstname);
			if(obtainedmemberFirstname.equals("rolemappinguser")){
				reportStep("The moodle user role is correctly mapped to course in yuja", "PASS", false);
				break;
				}
		   }
	
		if(mainMenuListSize==expectedMainMenuListSize && obtainedmemberFirstname.equals("rolemappinguser")) {
			reportStep("The moodle user is  enrolled to  course in yuja with correct system role and course role", "PASS", false);
			}else {
			reportStep("The moodle user is  not enrolled to  course in yuja with correct system role and course role", "FAIL", true);
			}
		driver.switchTo().defaultContent();
		logout();
		}
	   
	   //Method directly used in test class
   
   
	   public void roleMappingUserTypeUnlocked(String userName, String password, String adminUserName, String adminPassword, String userType) throws InterruptedException {
		   
		   HashMap<String, RoleMappingObject> hm = new HashMap<String, RoleMappingObject>();

		   RoleMappingObject t1 = new RoleMappingObject(2, "Manager", "GroupMember");		  
		   hm.put("Instructor1",t1);
		   RoleMappingObject t2 = new RoleMappingObject(2, "Teacher", "GroupMember");		  
		   hm.put("Instructor",t2);
		   RoleMappingObject t3 = new RoleMappingObject(3, "Student", "GroupOwner");		 
		   hm.put("Student",t3);
		   
		   setRoleMapping(userName, password, "IT Manager", "Student","Instructor");
		   setUserTypeToLockedorUnlocked("unlock",userType);
		   String key=null;
		   getCheckRoleMapping(adminUserName, adminPassword, hm);		  
		   for (Entry<String, RoleMappingObject> entry : hm.entrySet()) {
			   key=entry.getKey();
		   if(key=="Instructor1") {
			   RoleMappingObject value = entry.setValue(t1);
			   value.setNumberOfMainMenuOptions(3);
			   value.setMoodleRole("Manager");
			   value.setCourseRole("GroupOwner");
			  }
		   if(key=="Instructor") {
			   RoleMappingObject value = entry.setValue(t2);
			   value.setNumberOfMainMenuOptions(3);
			   value.setMoodleRole("Teacher");
			   value.setCourseRole("GroupOwner");
			   }
		   if(key=="Student") {
			   RoleMappingObject value = entry.setValue(t3);
			   value.setNumberOfMainMenuOptions(2);
			   value.setMoodleRole("Student");
			   value.setCourseRole("GroupMember");
			   }}
		   
           setRoleMapping(userName, password, "IT Manager", "Instructor","Student");
           getCheckRoleMapping(adminUserName, adminPassword, hm);
	   }
		   
	   public void getCheckRoleMapping(String adminUserName, String adminPassword, HashMap<String, RoleMappingObject> hm) throws InterruptedException {
		   for (Entry<String, RoleMappingObject> entry : hm.entrySet()) {
			    String key2 = entry.getKey();
			    RoleMappingObject value = entry.getValue();
			    checkRoleMapping(adminUserName, adminPassword,value.getNumberOfMainMenuOptions(),value.getMoodleRole(),value.getCourseRole() );
			    }
	   }
	   
	 //Method directly used in test class
		   
      public void roleMappingUserTypeLocked(String userName, String password, String adminUserName, String adminPassword, String userType,int numberOfMainMenuOptions) throws InterruptedException {
		   
		   HashMap<String, RoleMappingObject> hm = new HashMap<String, RoleMappingObject>();

		   RoleMappingObject t1 = new RoleMappingObject(numberOfMainMenuOptions, "Manager", "GroupMember");		  
		   hm.put("Instructor1",t1);
		   RoleMappingObject t2 = new RoleMappingObject(numberOfMainMenuOptions, "Teacher", "GroupMember");		  
		   hm.put("Instructor",t2);
		   RoleMappingObject t3 = new RoleMappingObject(numberOfMainMenuOptions, "Student", "GroupOwner");		 
		   hm.put("Student",t3);
		   
		   setRoleMapping(userName, password, "IT Manager", "Student","Instructor");
		   setUserTypeToLockedorUnlocked("lock",userType);
		   String key=null;
		   getCheckRoleMapping(adminUserName, adminPassword, hm);		  
		   for (Entry<String, RoleMappingObject> entry : hm.entrySet()) {
			   key=entry.getKey();
		   if(key=="Instructor1") {
			   RoleMappingObject value = entry.setValue(t1);
			   value.setNumberOfMainMenuOptions(numberOfMainMenuOptions);
			   value.setMoodleRole("Manager");
			   value.setCourseRole("GroupOwner");
			  }
		   if(key=="Instructor") {
			   RoleMappingObject value = entry.setValue(t2);
			   value.setNumberOfMainMenuOptions(numberOfMainMenuOptions);
			   value.setMoodleRole("Teacher");
			   value.setCourseRole("GroupOwner");
			   }
		   if(key=="Student") {
			   RoleMappingObject value = entry.setValue(t3);
			   value.setNumberOfMainMenuOptions(numberOfMainMenuOptions);
			   value.setMoodleRole("Student");
			   value.setCourseRole("GroupMember");
			   }}
		   
           setRoleMapping(userName, password, "IT Manager", "Instructor","Student");
           getCheckRoleMapping(adminUserName, adminPassword, hm);
	   } 
		  

   
	//Check media exists in the media library deletion only work for last uploaded media
	
	public void checkMediaExitsinMediaLibrary(List<String> mediaTitlelist) throws InterruptedException {
		String media = "";
		for(int i = 0; i<mediaTitlelist.size(); i++) {
		 media = mediaTitlelist.get(i);
		 System.out.println(media);
		 if(mediaExists(media)) {
			 System.out.println("media " +media+ "exists");
			// mediaLibrary.deleteMedia(media);
		 }
	  }
	}
	

	
	//Search for media in search bar of media chooser and embed
	
	public void CIMMediaChooserMediaEmbed(String name) throws InterruptedException {
		Thread.sleep(5000);
		clickElement("select media", By.cssSelector("div[id=\"VideoTab\"]"));
		searchMediaName(name);
		keyboardEnter();
		Thread.sleep(12000);
		clickElement("select entered media", By.xpath("//div[@id=\"videoSelectionContainer\"]/div/div[2]/div/div/div"));
		Thread.sleep(3000);
		clickElement("click save and return to course", By.xpath("//input[@name='submitbutton2']"));
	}
	
	//Search for quiz in search bar of media chooser and embed
	
	public void CIMMediaChooserQuizEmbed(String name) throws InterruptedException {
		Thread.sleep(2000);
		clickElement("select quizzes", By.cssSelector("div[id=\"QuizTab\"]"));
		searchQuizName(name);
		keyboardEnter();
		Thread.sleep(3000);
		clickElement("select entered quiz", By.cssSelector("div[class=\"list-item list-item-large add-media-list-item media-item-container\"]"));
		Thread.sleep(3000);
		clickElement("click save and return to course", By.xpath("//input[@name='submitbutton2']"));
	}
	
	private void accessCIMMediaChooser() throws InterruptedException {
		clickElement("Turn Editing on button", By.cssSelector("button[id^=\"single_button\"]"));
		clickElement("add activity or resource button", By.cssSelector("span[class=\"section-modchooser-text\"]"));
		Thread.sleep(3000);
		clickElement("cim mediachooser button", By.xpath("//a[@href='https://tmoodle2.yuja.com/course/modedit.php?add=lti&return=0&course=142&sr&typeid=76&section=0&sr=0']"));
		clickElement("select content", By.cssSelector("button[name=\"selectcontent\"]"));
        Thread.sleep(3000);
		switchToIframe("switch to mediachooser frame", By.id("contentitem-page-iframe"), 10);
	}
	
	private void addExternalTool(String externalToolCustomName, String externalToolVisibleName) throws InterruptedException {
		clickElement("Turn Editing on button", By.cssSelector("button[id^=\"single_button\"]"));
		clickElement("add activity or resource button", By.cssSelector("span[class=\"section-modchooser-text\"]"));
		Thread.sleep(3000);
		clickElement("External tool", By.xpath("//a[@title='Add a new External tool']"));
		sendKeys("Enter external tool name", By.xpath("//input[@name='name']"),externalToolCustomName);
		Select toolDropdown = new Select(driver.findElement(By.xpath("//select[@name='typeid']")));
		toolDropdown.selectByVisibleText(externalToolVisibleName);
		clickElement("save and display button", By.xpath("//input[@name='submitbutton2']"));
	}
	
	
	
	private void searchQuizName(String quizname) {
		sendKeys("quiz name", By.xpath("//input[@id=\"media-chooser-search-input\"]"), quizname);
	}
	
	private void searchMediaName(String medianame) {
		sendKeys("media name", By.xpath("(//input[@id=\"media-chooser-search-input\"])[2]"), medianame);
	}
	
	private void fillInUserId(String username) {
		sendKeys("User ID", By.xpath("//input[@name='username']"), username);
	}
	
	private void fillInPassword(String password) {
		sendKeys("Password", By.xpath("//input[@name='password']"), password);
	}
	
	private void clickSignInButton() {
		clickElement("Sign In button", By.id("loginbtn"));
	}
	
	private void clickGeneral() {
		clickElement("General button", By.xpath("//a[text()='General']"));
	}
	
	public void clickEmbedMediaMoodle(String embedMediaTitle) {
		clickElement("click embed media title", By.xpath("//span[text()="+embedMediaTitle+"]"));
	}

	
   //Methods for uploading media in media chooser

	public Boolean mediaUploadedMediachooser(String mediaTitle, String mediaPath) {
		try {
			System.out.println("inside media upload media chooser");
			uploadMediaMC(mediaPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mediaExistsMediaChooser(mediaTitle);
	}

	
	//Accessing browse button in the upload window in media chooser
	
	public void uploadMediaMC(String mediaPath) throws InterruptedException {
		setMediaUploadModal();
		mediaPath = Paths.get(mediaPath).toAbsolutePath().toString();
		System.out.println("mediaPath = " + mediaPath);
		System.out.println("Uploading media...");
		sendKeysModal(mediaUploadModal, "Upload Video", By.xpath("//div[@class='upload-container']/div/div/div/div/div/input"), mediaPath);
	}
		
		
	//upload medias from the directory into media chooser
	
	public void mediaChooserBulkUploadMedia(String mediaDirectoryPath) {
		
		System.out.println("inside mediachooserupload");
		
		File mediaDirectory = new File(mediaDirectoryPath);
		String[] mediaList = mediaDirectory.list();
		String mediaTitle = null;
		String mediaExtension;
		String mediaPath;
		
		for(String media : mediaList) {
			try {
				mediaTitle = media.substring(0, media.indexOf('.'));
				mediaPath = mediaDirectoryPath + "\\" + media;
				mediaExtension = media.substring(media.indexOf('.')+1);
				
				System.out.println("Attempting to upload " + media);
				if(mediaUploadedMediachooser(mediaTitle, mediaPath)) System.out.println(media + " upload success");
				else throw new Exception(media + " upload fail");
				Thread.sleep(1000);
				clickElement("toast message close button", By.xpath("//div[@class='toast-message']/following-sibling::*"));
				clickElement("click upload media", By.cssSelector("div[id=\"uploadMediaTab\"]"));
				
			} catch(Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
				reportStep("e.getMessage(): " + e.getMessage() + ", @Method "+Scenario_Name +" exception to be handled", "Fail", true);
			}
		}
	}

	//Get the array of media titles uploaded from directory 
	
   public ArrayList<String> getMediaTitleArrayfromDirectory(String mediaDirectoryPath) {
	   
	   File mediaDirectory = new File(mediaDirectoryPath);
	   String[] mediaList = mediaDirectory.list();
	   String mediaTitle = null;
		
	   String mediaTitlelistName = "";
	   int i = 0;
	   
	   ArrayList<String> mediaTitlelist1 = new ArrayList<String>();
	   
	   for( i=0;i<mediaList.length;i++) {
			
			mediaTitlelistName = mediaList[i]; 
			mediaTitle = mediaTitlelistName.substring(0, mediaTitlelistName.indexOf('.'));
			mediaTitlelist1.add(mediaTitle);
		}
	   
	   return mediaTitlelist1;
	}
		
				
	//get a media element from the media chooser			
		
	public WebElement getMediaMediaChooser(String mediaTitle) {
		List<WebElement> mediaLibraryElementList = getElementList( By.cssSelector("div[class=\"list-item list-item-large add-media-list-item media-item-container\"]"));
		int listSize = mediaLibraryElementList.size();
		System.out.println(listSize);
		WebElement mediaLibraryElement = null;
		
		if(listSize == 0) {
			return mediaLibraryElement;
		}
		else { 
			String mediaLibraryElementName = "";
			int i = 0;
			
			reportStep("Fetching the folder with the name \"" + mediaTitle + "\"", "PASS", false);
			
			while(!mediaLibraryElementName.equals(mediaTitle) && i<listSize) {
				mediaLibraryElement = mediaLibraryElementList.get(i++);
				mediaLibraryElementName = mediaLibraryElement.findElement(By.className("choose-media-video-title")).getText();
			}
			return mediaLibraryElement;
		}
	}
	
	//Check a media exists in media chooser
	
	public boolean mediaExistsMediaChooser(String mediaTitle) {
		WebElement mediaLibraryElement =  getMediaMediaChooser(mediaTitle);
		String mediaLibraryElementName = mediaLibraryElement.findElement(By.className("choose-media-video-title")).getText();
		Boolean mediaLibraryElementExists = mediaTitle.equals(mediaLibraryElementName);
		if(mediaLibraryElementExists) {
		reportStep("The media with the title \"" + mediaTitle + "\" exists", "PASS", false);
		} else {
		reportStep("The media with the title \"" + mediaTitle + "\" does not exists", "FAIL", true);
		}
		return mediaLibraryElementExists;
	}
   
  }

