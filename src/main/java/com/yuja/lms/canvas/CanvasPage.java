package com.yuja.lms.canvas;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.yuja.evp.pagehelpers.AdminPanelRosterPageHelpers;
import com.yuja.evp.pagehelpers.MediaLibraryPageHelpers;
import com.yuja.evp.pagehelpers.NavigationBarHelpers;
import com.yuja.evp.pagehelpers.QuizPageHelpers;
import com.yuja.evp.reports.Report;
import com.yuja.evp.utilities.Driver;
import com.yuja.lms.moodle.MoodlePage;
import com.yuja.lms.moodle.RoleMappingObject;

public class CanvasPage extends QuizPageHelpers {
	
//private WebElement mediaUploadModal = null;
     
	
	
	MediaLibraryPageHelpers mediaLibrary = new MediaLibraryPageHelpers();
	NavigationBarHelpers navbar=new NavigationBarHelpers();
	AdminPanelRosterPageHelpers roster=new AdminPanelRosterPageHelpers();
	MoodlePage mp=new MoodlePage();
	
//	private void setMediaUploadModal() {
//		System.out.println("Fetching Media Upload modal...");
//		this.mediaUploadModal = waitForElement(By.xpath("//button[@title=\"Upload Video\"]"), 10);
//		System.out.println("Media Upload modal fetched");
//	}
	
	public void navigateToLoginPage() {
		String URL =  prop.getProperty("LMS.Canvas")+"login/canvas";
		launchUrl(URL, "LMS login page");
	}
	
	public void loginFast(String username, String password) {
		fillInUserId(username);
		fillInPassword(password);
		clickSignInButton();
	}
	
	public void logout() throws InterruptedException {
		clickElement("account button", By.xpath("//button[@id='global_nav_profile_link']"));
		Thread.sleep(2000);
		clickElement("logout button", By.xpath("//form[@action=\"/logout\"]//button[@type='submit']"));
	}
	
	public void navigateToCourse(String userName, String password){
		navigateToLoginPage();
		Driver.getDriver().manage().window().maximize();
		loginFast(userName, password);
		waitForElement(By.xpath("//a[@href='https://panotesting.instructure.com/']"), 10);
		String URL = prop.getProperty("LMS.Canvas")+"courses/817";
		launchUrl(URL, "Canvas automation course page");
		Boolean assignmentexist=verifyElementExist("Assignments", By.xpath("//a[@href='/courses/817/assignments']"));
		System.out.println(assignmentexist);
		if(assignmentexist==false) {
			clickElement("show sidebar", By.xpath("//i[@class='icon-hamburger']"));}
	}
	
	public void selectAssignment(String assignmentName) {
		clickElement("Assignment", By.xpath("//a[@href='/courses/817/assignments']"));
		List<WebElement> assignmentList= getElementList(By.xpath("//a[@class=\"ig-title\"]"));
		for(int i=0;i<assignmentList.size();i++) {
		WebElement element=assignmentList.get(i);
		String obtainedAssignmentName = element.getText();
		System.out.println(obtainedAssignmentName);
		System.out.println(assignmentName);
		if(obtainedAssignmentName.equals(assignmentName)) {
			clickElement("click on given course",element);
			break;
		    }
		 }
	}
	
	public void selectCourseFromDashboard(String courseName) {
		clickElement("Dashboard", By.xpath("//a[@href=\"https://panotesting.instructure.com/\"]"));
		waitForElement(By.xpath("//a[@class=\"ic-DashboardCard__link\"]"), 30);
		List<WebElement> courseList= getElementList(By.xpath("//a[@class=\"ic-DashboardCard__link\"]"));
		for(int i=0;i<courseList.size();i++) {
		WebElement element=courseList.get(i);
		String obtainedCourseName = element.getText();
		System.out.println(obtainedCourseName);
		if(obtainedCourseName.contains(courseName)) {
			clickElement("click on given course",element);
			break;
		    }
		 }
	}
	
	public void createCanvasCourse(String adminUserName, String adminPassword, String courseName, String courseShortName) throws InterruptedException{
		navigateToLoginPage();
		Driver.getDriver().manage().window().maximize();
		loginFast(adminUserName, adminPassword);
		String URL = prop.getProperty("LMS.Canvas")+"accounts/1";
		launchUrl(URL, "Canvas automation course creation page");
		clickElement("Add a new course button", By.xpath("//button[@aria-label=\"Create new course\"]"));
		sendKeys("Enter course name", By.xpath("(//input[@class='qBMHb_cwos qBMHb_ycrn qBMHb_EMjX'])[4]"),courseName+getRandomInteger(1000));
		sendKeys("Enter reference code", By.xpath("(//input[@class='qBMHb_cwos qBMHb_ycrn qBMHb_EMjX'])[5]"),courseShortName+getRandomInteger(1000));
		clickElement("add course button", By.xpath("//button[@type=\"submit\"]"));
		clickElement("go to new course page", By.xpath("//div[@id=\"flashalert_message_holder\"]//a"));
		waitForElement(By.xpath("//button[@class=\"ui-button btn-publish\"]"), 30);
		clickElement("publish course", By.xpath("//button[@class=\"ui-button btn-publish\"]"));
		clickElement("choose course home page", By.xpath("//span[@class=\"Vmatu_cSXm\"]"));
		clickElement("choose and publish button", By.xpath("(//button[@type='button'])[18]"));
		waitForElement(By.xpath("//i[@class='icon-hamburger']"), 30);
	}
	
	public String createCanvasUser(String adminUserName, String adminPassword,  String email, String newUsername, String newUserPassword) throws InterruptedException{
		navigateToLoginPage();
		Driver.getDriver().manage().window().maximize();
		loginFast(adminUserName, adminPassword);
		String URL = prop.getProperty("LMS.Canvas")+"accounts/1/users";
		launchUrl(URL, "Canvas automation user creation page");
		clickElement("add a new user button", By.xpath("//button[@aria-label='Add people']"));
		String newUserNameRandom=newUsername+getRandomInteger(1000);
		sendKeys("Enter full name", By.xpath("//input[@label='Full Name']"),newUserNameRandom);
		sendKeys("Enter email", By.xpath("//input[@label='Email']"),email+getRandomInteger(10000));
		clickElement("add user button", By.xpath("//button[@type='submit']"));
		clickElement("go to new user page", By.xpath("//div[@id=\"flash_message_holder\"]//a"));
		waitForElement(By.xpath("//a[text()='Add Login']"), 30);
		clickElement("Add Login", By.xpath("//a[text()='Add Login']"));
		sendKeys("Enter username", By.xpath("//input[@id=\"pseudonym_unique_id\"]"),newUserNameRandom);
		sendKeys("Enter password", By.xpath("//input[@id=\"pseudonym_password\"]"),newUserPassword);
		sendKeys("Enter password", By.xpath("//input[@id=\"pseudonym_password_confirmation\"]"),newUserPassword);
		clickElement("Add Login", By.xpath("//button[text()=\"Add Login\"]"));
		return newUserNameRandom;
	}
	
	public void NavigateToLTI(String embedMediaTitle){
		clickEmbedMediaCanvas(embedMediaTitle);
		switchToIframe("switch to lti frame", By.id("tool_content"), 10);
	}
	
	
	
	//used directly in test class. Login create publish embed attend and access the yuja gradebook and check lms grade sync via activity log
	
	public void loginCreateandPublishEmbedQuiz(String userName, String password,String embedMediaTitle,String mediaTitle,String name,String question,String option1, String option2,String possibleans1, String possibleans2,String hint,String stuserName, String stpassword, String sa,String fitbans,String courseName,String marks,String studentFullName, String studentNameinActivitylog) throws Exception {
		try {
		navigateToCourse(userName,password);
		NavigateToLTI(embedMediaTitle);
		Thread.sleep(3000);
		String quizNewName=createandPublishQuiz(mediaTitle,name,question, option1,  option2, possibleans1, possibleans2, hint,courseName);
		String quizFinalName="'"+quizNewName+"'";
		Driver.getDriver().switchTo().defaultContent();
		String URL = prop.getProperty("LMS.Canvas")+"courses/817";
		launchUrl(URL, "Canvas automation course page");
	    accessCIMMediaChooser(embedMediaTitle,quizNewName);
	    CIMMediaChooserQuizEmbed(quizNewName);
	    logout();
	    navigateToCourse(stuserName,stpassword);
	    clickElement("Assignment", By.xpath("//a[@href='/courses/817/assignments']"));
	    clickElement("Click embed link",By.xpath("//div[@class=\"ig-info\"]//a[contains(text(),'"+quizNewName+"')]"),10);
	    switchToIframe("switch to lti frame", By.id("tool_content"), 10);
	    switchToIframe("switch to video player frame", By.id("yujahtml5playerInVideoPoll"), 10);
	    clickPlaybutton();
		Thread.sleep(5000);
		Driver.getDriver().switchTo().parentFrame();
		StudentattendallquestionsCorrectly(sa, fitbans);
		Driver.getDriver().switchTo().defaultContent();
		logout();
		navigateToCourse(userName,password);
		NavigateToLTI(embedMediaTitle);
		Thread.sleep(3000);
		checkGradebookTestafterLoginforMultiple(mediaTitle,quizNewName,courseName ,studentFullName,marks);
		clickElement("Click Manage Media",By.xpath("//span[@id='topBarTabName3']"),10);
		Thread.sleep(2000);
		checkActivityLogforQuizSync(mediaTitle,studentNameinActivitylog,marks, quizNewName);
		Thread.sleep(2000);
		clickElement("Click close button",By.xpath("(//button[@data-automation=\"btnCloseModalDialog\"])[2]"),10);
		mediaDetailsModal.clickDeleteQuizButton();
		clickElement("yes",By.xpath("//button[@title='Yes']"),10);
		} catch(Exception e) {
			e.printStackTrace();
			Report.reportStep(Driver.getDriver(), "The test case did not complete all of its steps", "FAIL", false);
		}
	}
	
	//used directly in test class. Login create embed attend and access the yuja gradebook do manual sync and check lms grade sync via activity log
	
	public void loginCreateandEmbedPlaybackQuiz(String userName, String password,String embedMediaTitle,String videoNameforPlaybackquiz,String playbackQuizTitle,String stuserName, String stpassword, String courseName,String marks,String studentFullName, String studentNameinActivityLog) throws Exception {
	    try {
		navigateToCourse(userName,password);
		NavigateToLTI(embedMediaTitle);
		clickElement("Click Manage Media",By.xpath("//span[@id='topBarTabName3']"),10);
		Thread.sleep(2000);
		mediaLibrary.accessMediaMoreMenu(videoNameforPlaybackquiz);
		mediaDetailsModal.clickQuizzes();
		String quizNewName=createPlaybackquiz(playbackQuizTitle,videoNameforPlaybackquiz);
		String quizFinalName="'"+quizNewName+"'";
		Driver.getDriver().switchTo().defaultContent();
		String URL = prop.getProperty("LMS.Canvas")+"courses/817";
		launchUrl(URL, "Canvas automation course page");
		accessCIMMediaChooser(embedMediaTitle,quizNewName);
	    CIMMediaChooserQuizEmbed(quizNewName);
		logout();
		navigateToCourse(stuserName,stpassword);
		clickElement("Assignment", By.xpath("//a[@href='/courses/817/assignments']"));
		Thread.sleep(2000);
		clickElement("Click embed link",By.xpath("//div[@class=\"ig-info\"]//a[contains(text(),'"+quizNewName+"')]"),10);
		Thread.sleep(5000);
		switchToIframe("switch to lti frame", By.id("tool_content"), 10);
	    switchToIframe("switch to video player frame", By.id("yujahtml5playerInVideoPoll"), 10);
		clickPlaybutton();
		Thread.sleep(48000);
		Driver.getDriver().switchTo().defaultContent();
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
	    } catch(Exception e) {
			e.printStackTrace();
			Report.reportStep(Driver.getDriver(), "The test case did not complete all of its steps", "FAIL", false);
		}
	}
	
	// Method directly used in test class. Login embed media and access as student
	
	public void loginEmbedMedia(String userName, String password,String name,String stuserName,String stpassword, String embedMediaName, String LTILinkName) throws InterruptedException {
		try {
		navigateToCourse(userName,password);
		accessCIMMediaChooserforAnnouncements(name, LTILinkName);
		CIMMediaChooserMediaEmbed(name);
        logout();
	    navigateToCourse(stuserName,stpassword);
	    clickElement("Announcement", By.xpath("//a[@href='/courses/817/announcements']"));
	    clickElement("choosen announcement", By.xpath("//h3[text()="+embedMediaName+"]"));
	    Thread.sleep(3000);
		} catch(Exception e) {
			e.printStackTrace();
			Report.reportStep(Driver.getDriver(), "The test case did not complete all of its steps", "FAIL", false);
		}
	 }
	
	//Method directly used in test class. login upload media and check in media chooser as well as media library
	
	public void CIMMediaChooserMediaUpload(String userName, String password, String mediaDirectoryPath, String LTILinkName, String name) throws InterruptedException {
		try {
		navigateToCourse(userName,password);
		accessCIMMediaChooserforAnnouncements(name, LTILinkName);
		Thread.sleep(5000);
		clickElement("click upload media", By.cssSelector("div[id=\"uploadMediaTab\"]"));
		Thread.sleep(2000);
		mp.mediaChooserBulkUploadMedia(mediaDirectoryPath);
		List<String> mediaTitlelist=mp.getMediaTitleArrayfromDirectory(mediaDirectoryPath);
		System.out.println(mediaTitlelist);
		clickElement("click choose media", By.xpath("//div[@id=\"chooseMediaTab\"]"));
		clickElement("select entered media", By.xpath("//div[@id=\"videoSelectionContainer\"]/div/div[3]/div/div/div"));
		Thread.sleep(3000);
		Driver.getDriver().findElement(By.xpath("//button[@id=\"embedButton\"]")).click();
		Thread.sleep(4000);
		clickEmbedMediaCanvas(LTILinkName);
		Thread.sleep(2000);
	    Alert prompt=Driver.getDriver().switchTo().alert();
		prompt.accept();
		switchToIframe("switch to lti frame", By.id("tool_content"), 10);
		Thread.sleep(4000);
		clickElement("Click Manage Media",By.xpath("//span[@id='topBarTabName3']"),10);
		mp.checkMediaExitsinMediaLibrary(mediaTitlelist);
		} catch(Exception e) {
			e.printStackTrace();
			Report.reportStep(Driver.getDriver(), "The test case did not complete all of its steps", "FAIL", false);
		}
	}
	
	public void setRoleMapping(String userName, String password, String yujaRolefromAdmin, String yujaRolefromTeacher, String yujaRolefromTA, String yujaRolefromDesigner, String yujaRolefromStudent, String yujaRolefromObserver) throws InterruptedException {
		 mediaLibrary.navigateToMyMediaUserLogin(userName, password);
		 URL =  prop.getProperty("URL")+"/P/Institution/APIManagementServlet/";
	     launchUrl(URL, "Test Automation Enterprise Video Platform");
	     Select integrationDropdown = new Select(Driver.getDriver().findElement(By.id("apiPicker")));
	     integrationDropdown.selectByValue("lti3");
	     Select lmsDropdown = new Select(Driver.getDriver().findElement(By.id("LTI3LMSSelect")));
	     lmsDropdown.selectByVisibleText("Canvas");
	     Select canvasAdminRole = new Select(Driver.getDriver().findElement(By.xpath("(//select[@id=\"canvasAdminRoleTo\"])[2]")));
	     canvasAdminRole.selectByVisibleText(yujaRolefromAdmin);
	     Select canvasTeacherRole = new Select(Driver.getDriver().findElement(By.xpath("(//select[@id=\"canvasTeacherRoleTo\"])[2]")));
	     canvasTeacherRole.selectByVisibleText(yujaRolefromTeacher);
	     Select canvasTARole = new Select(Driver.getDriver().findElement(By.xpath("(//select[@id=\"canvasTARoleTo\"])[2]")));
	     canvasTARole.selectByVisibleText(yujaRolefromTA);
	     Select canvasDesignerRole = new Select(Driver.getDriver().findElement(By.xpath("(//select[@id=\"canvasDesignerRoleTo\"])[2]")));
	     canvasDesignerRole.selectByVisibleText(yujaRolefromDesigner);
	     Select canvasStudentRole = new Select(Driver.getDriver().findElement(By.xpath("(//select[@id=\"canvasStudentRoleTo\"])[2]")));
	     canvasStudentRole.selectByVisibleText(yujaRolefromStudent);
	     Select canvasObserverRole = new Select(Driver.getDriver().findElement(By.xpath("(//select[@id=\"canvasObserverRoleTo\"])[2]")));
	     canvasObserverRole.selectByVisibleText(yujaRolefromObserver);
	     clickElement("save",By.xpath("//form[@id=\"lti3SettingsGeneral\"]//button[@title=\"Save\"]") );
         clickElement("save confirm",By.xpath("//div[@class=\"modal-content\"]//button[@title=\"Confirm\"]") );
     }

	// Method directly used in test class.
	public void checkAutoprovisionOfCourse(String adminUserName, String adminPassword, String courseName, String courseShortName, String userName, String password, String embedMediaTitle ) throws InterruptedException {
		try {
		mp.setAndResetAutomaticProvision(userName,password,"course",true);
		mp.setStartPageOptionAsMediaChannel();
		createCanvasCourse(adminUserName, adminPassword,courseName,courseShortName);
		String canvasCourseName=Driver.getDriver().findElement(By.xpath("(//span[@class=\"ellipsible\"])[2]")).getText();
		System.out.println(canvasCourseName);
		NavigateToLTI(embedMediaTitle);
		String provisionedCourseName=Driver.getDriver().findElement(By.xpath("//li[@class='nav-item active']")).getAttribute("title");
	
		if(provisionedCourseName.contains(canvasCourseName)) {
			Report.reportStep(Driver.getDriver(),"The canvas course is successfully autoprovisioned in yuja", "PASS", false);
			} else {
				Report.reportStep(Driver.getDriver(),"The canvas course is not successfully autoprovisioned in yuja", "FAIL", true);
			}	
		} catch(Exception e) {
			e.printStackTrace();
			Report.reportStep(Driver.getDriver(), "The test case did not complete all of its steps", "FAIL", false);
		}
	 }
		
		
	// Method directly used in test classes
	public void checkManualprovisionOfNewCourse(String adminUserName, String adminPassword, String courseName, String courseShortName, String userName, String password, String embedMediaTitle ) throws InterruptedException {
		try {
		mp.setAndResetAutomaticProvision(userName,password,"course",false);
		mp.setStartPageOptionAsMediaChannel();
		createCanvasCourse(adminUserName, adminPassword,courseName,courseShortName);
		NavigateToLTI(embedMediaTitle);
		clickElement("Create new course button", By.xpath("//a[@title=\"Create a Course\"]"));
		waitForElement(By.xpath("//input[@id=\"className\"]"),10);
		sendKeys("Enter manual course full name", By.xpath("//input[@id=\"className\"]"),courseName+getRandomInteger(1000));
		sendKeys("Enter manual course short name", By.xpath("//input[@id=\"classCode\"]"),courseShortName+getRandomInteger(1000));
		clickElement("Create new course button", By.xpath("//button[@data-automation=\"btnCreateYourGroup\"]"));
		waitForElement(By.xpath("//li[@class='nav-item active']"),10);
		String provisionedCourseName=Driver.getDriver().findElement(By.xpath("//li[@class='nav-item active']")).getAttribute("title");
		
        if(provisionedCourseName.contains(courseName)) {
    	   Report.reportStep(Driver.getDriver(),"The canvas course is successfully manuallyprovisioned in yuja", "PASS", false);
		   } else {
			Report.reportStep(Driver.getDriver(),"The canvas course is not successfully manuallyprovisioned in yuja", "FAIL", true);
		   }
		} catch(Exception e) {
			e.printStackTrace();
			Report.reportStep(Driver.getDriver(), "The test case did not complete all of its steps", "FAIL", false);
		}
      }
	
	// Method directly used in test class.
	public void checkManualprovisionToExistingCourse(String adminUserName, String adminPassword, String courseName, String courseShortName, String userName, String password, String embedMediaTitle ) throws InterruptedException {
		try {
		mp.setAndResetAutomaticProvision(userName,password,"course",false);
		mp.setStartPageOptionAsMediaChannel();
		createCanvasCourse(adminUserName, adminPassword,courseName,courseShortName);
	    NavigateToLTI(embedMediaTitle);
		clickElement("Link to an existing course button", By.xpath("//a[@title=\"Link to an Existing Course\"]"));
		waitForElement(By.xpath("//select[@id=\"classSelectBox\"]"),10);
		clickElement("dropdown", By.xpath("//select[@id=\"classSelectBox\"]"));
		clickElement("choose the auto existing course", By.xpath("//select[@id=\"classSelectBox\"]//option[text()='aecc - Apr 2023 - Auto Existing Canvas Course']"));
		clickElement("Click OK button after selecting the existing course", By.xpath("//button[@title=\"OK\"]"));
		waitForElement(By.xpath("//li[@class='nav-item active']"),15);
		String provisionedCourseName=Driver.getDriver().findElement(By.xpath("//li[@class='nav-item active']")).getAttribute("title");
		
       if(provisionedCourseName.contains("Auto Existing Canvas Course")) {
    	   Report.reportStep(Driver.getDriver(),"The canvas course is successfully manuallyprovisioned  and connected to existing course in yuja", "PASS", false);
		} else {
			Report.reportStep(Driver.getDriver(),"The canvas course is not successfully manuallyprovisioned connected to existing course in yuja", "FAIL", true);
		}	
		} catch(Exception e) {
			e.printStackTrace();
			Report.reportStep(Driver.getDriver(), "The test case did not complete all of its steps", "FAIL", false);
		}
       }
	
	// Method directly used in test class.
	public void checkAutoprovisionOfUser(String adminUserName, String adminPassword, String userName, String password, String email, String newUsername, String newUserPassword, String courseName, String embedMediaTitle ) throws InterruptedException {
		try {
		mp.setAndResetAutomaticProvision(userName,password,"user",true);
		String studentUserName=createCanvasUser(adminUserName, adminPassword,email,newUsername,newUserPassword);
		selectCourseFromDashboard(courseName);
		clickElement("people", By.xpath("//a[@class='people']"));
		clickElement("Add users", By.xpath("//a[@id='addUsers']"));
		clickElement("choose login id", By.xpath("(//span[@class=\"Vmatu_cSXm\"])[2]"));
		WebElement loginidField=Driver.getDriver().findElement(By.xpath("//div[@class=\"chpSa_byIz\"]//textarea"));
		clickElement("Enter loginid",loginidField);
		typeKeys(studentUserName);
		clickElement("Next button", By.xpath("//button[@id=\"addpeople_next\"]"));
		clickElement("Next button", By.xpath("//button[@id=\"addpeople_next\"]"));
        logout();
		navigateToLoginPage();
		Driver.getDriver().manage().window().maximize();
		loginFast(studentUserName, newUserPassword);
		clickElement("accept use policy", By.xpath("//input[@name=\"user[terms_of_use]\"]"));
		clickElement("submit", By.xpath("//button[@class=\"Button Button--primary\"]"));
		clickElement("accept course invite", By.xpath("//button[@name=\"accept\"]"));
		selectCourseFromDashboard(courseName);
		NavigateToLTI(embedMediaTitle);
		waitForElement(By.xpath("//*[@id=\"bi_userInfoDropdown\"]"),15);
		navbar.clickMyAccountButton();
		navbar.clickMyAccountDropdownOption();
		String myaccountStudentName=Driver.getDriver().findElement(By.xpath("//input[@id='firstName']")).getAttribute("value");
		navbar.clickCoursesAndGroups();
		sendKeys("enter course name",By.xpath("//input[@class=\"group-management-inputbox form-control search-box\"]"),courseName);
		clickElement("choose course after search",By.xpath("//ul[@id=\"courses-and-groups\"]//li"));
		List<WebElement> memberList= getElementList(By.xpath("//div[@data-automation=\"divGroupMembers\"]//div[@class=\"user-list-item\"]"));
		String obtainedmemberFirstname=null;
		for(int i=0;i<memberList.size();i++) {
			WebElement element=memberList.get(i);
			String obtainedmemberName = element.getText();
			String obtainedmembersplitFirstname[]=obtainedmemberName.split(" ",2);
		    obtainedmemberFirstname=obtainedmembersplitFirstname[0];
		    System.out.println(obtainedmemberFirstname);
			if(obtainedmemberFirstname.equals(studentUserName) && obtainedmemberFirstname.equals(myaccountStudentName)){
				Report.reportStep(Driver.getDriver(),"The canvas user is enrolled to  course in yuja", "PASS", false);
				break;
				}
		}
	       if(obtainedmemberFirstname.equals(myaccountStudentName) && obtainedmemberFirstname.equals(studentUserName)) {
			System.out.println(myaccountStudentName+" "+studentUserName + " "+obtainedmemberFirstname );
			Report.reportStep(Driver.getDriver(),"The canvas user is successfully provisioned  and enrolled to  course in yuja", "PASS", false);
			}else {
				Report.reportStep(Driver.getDriver(),"The canvas user is  not successfully provisioned  and enrolled to  course in yuja", "FAIL", true);
			}
		} catch(Exception e) {
			e.printStackTrace();
			Report.reportStep(Driver.getDriver(), "The test case did not complete all of its steps", "FAIL", false);
		}
		}
	// Method directly used in test class.
	
    public void checkAutoprovisionOfUserinMultipleCourses(String adminUserName, String adminPassword, String userName, String password, String email, String newUsername, String newUserPassword, String[] courseName, String embedmediatitle ) throws InterruptedException {
		try {
    	mp.setAndResetAutomaticProvision(userName,password,"user",true);
		String studentUserName=createCanvasUser(adminUserName, adminPassword,email,newUsername,newUserPassword);
		String myaccountStudentName=null;
		
		for(String course:courseName) {
			waitForElement(By.xpath("//a[@href=\"https://panotesting.instructure.com/\"]"),20);
			selectCourseFromDashboard(course);
			clickElement("people", By.xpath("//a[@class='people']"));
			clickElement("Add users", By.xpath("//a[@id='addUsers']"));
			clickElement("choose login id", By.xpath("(//span[@class=\"Vmatu_cSXm\"])[2]"));
			WebElement loginidField=Driver.getDriver().findElement(By.xpath("//div[@class=\"chpSa_byIz\"]//textarea"));
			clickElement("Enter loginid",loginidField);
			typeKeys(studentUserName);
			clickElement("Next button", By.xpath("//button[@id=\"addpeople_next\"]"));
			clickElement("Next button", By.xpath("//button[@id=\"addpeople_next\"]"));
	       }
		logout();
		navigateToLoginPage();
		Driver.getDriver().manage().window().maximize();
		loginFast(studentUserName, newUserPassword);
		clickElement("accept use policy", By.xpath("//input[@name=\"user[terms_of_use]\"]"));
		clickElement("submit", By.xpath("//button[@class=\"Button Button--primary\"]"));
		clickElement("accept course invite", By.xpath("//button[@name=\"accept\"]"));
		
		for(String course:courseName) {
		selectCourseFromDashboard(course);
		NavigateToLTI(embedmediatitle);
		waitForElement(By.xpath("//*[@id=\"bi_userInfoDropdown\"]"),15);
		navbar.clickMyAccountButton();
		navbar.clickMyAccountDropdownOption();
		myaccountStudentName=Driver.getDriver().findElement(By.xpath("//input[@id='firstName']")).getAttribute("value");
		navbar.clickCoursesAndGroups();
		sendKeys("enter course name",By.xpath("//input[@class=\"group-management-inputbox form-control search-box\"]"),course);
		clickElement("choose course after search",By.xpath("//ul[@id=\"courses-and-groups\"]//li"));
		List<WebElement> memberList= getElementList(By.xpath("//div[@data-automation=\"divGroupMembers\"]//div[@class=\"user-list-item\"]"));
		String obtainedmemberFirstname=null;
		for(int i=0;i<memberList.size();i++) {
			WebElement element=memberList.get(i);
			String obtainedmemberName = element.getText();
			String obtainedmembersplitFirstname[]=obtainedmemberName.split(" ",2);
		    obtainedmemberFirstname=obtainedmembersplitFirstname[0];
		    System.out.println(obtainedmemberFirstname);
			if(obtainedmemberFirstname.equals(studentUserName) && obtainedmemberFirstname.equals(myaccountStudentName)){
				Report.reportStep(Driver.getDriver(),"The canvas user is enrolled to  course in yuja", "PASS", false);
				break;
				}
		    }
		Driver.getDriver().switchTo().defaultContent();
		}
		logout();
		roster.navigateToAdminPanelRosterPageUserLogin(userName,password);
		roster.rosterButtons(myaccountStudentName);
		List<WebElement> rowList=getElementList(By.xpath("//table[@id=\"rosterTable\"]//tr"));
		if(rowList.size()==2) {
			Report.reportStep(Driver.getDriver(),"A single canvas user is successfully provisioned when enrolled to multiple courses", "PASS", false);
		}else {
			Report.reportStep(Driver.getDriver(),"A single canvas user is successfully provisioned when enrolled to multiple courses", "FAIL", true);
			}
		} catch(Exception e) {
			e.printStackTrace();
			Report.reportStep(Driver.getDriver(), "The test case did not complete all of its steps", "FAIL", false);
		}
		}
//Method directly used in test class.

   public void checkManualprovisionOfUser(String adminUserName, String adminPassword, String userName, String password, String email, String newUsername, String newUserPassword, String courseName, String type, String existingStudentUsername, String embedmediatitle ) throws InterruptedException {
	   try {
	   mp.setAndResetAutomaticProvision(userName,password,"user",false);
	   String studentUserName=createCanvasUser(adminUserName, adminPassword,email,newUsername,newUserPassword);
	   selectCourseFromDashboard(courseName);
	   clickElement("people", By.xpath("//a[@class='people']"));
	   clickElement("Add users", By.xpath("//a[@id='addUsers']"));
	   clickElement("choose login id", By.xpath("(//span[@class=\"Vmatu_cSXm\"])[2]"));
	   WebElement loginidField=Driver.getDriver().findElement(By.xpath("//div[@class=\"chpSa_byIz\"]//textarea"));
	   clickElement("Enter loginid",loginidField);
	   typeKeys(studentUserName);
	   clickElement("Next button", By.xpath("//button[@id=\"addpeople_next\"]"));
	   clickElement("Next button", By.xpath("//button[@id=\"addpeople_next\"]"));
       logout();
	   navigateToLoginPage();
	   Driver.getDriver().manage().window().maximize();
	   loginFast(studentUserName, newUserPassword);
	   clickElement("accept use policy", By.xpath("//input[@name=\"user[terms_of_use]\"]"));
	   clickElement("submit", By.xpath("//button[@class=\"Button Button--primary\"]"));
	   clickElement("accept course invite", By.xpath("//button[@name=\"accept\"]"));
	   selectCourseFromDashboard(courseName);
	   NavigateToLTI(embedmediatitle);
	
	   if(type=="new") {
		   waitForElement(By.xpath("//input[@id=\"newPass1\"]"),15);
		   sendKeys("Enter password", By.xpath("//input[@id=\"newPass1\"]"),password);
		   sendKeys("Enter lastname", By.xpath("//input[@id=\"lastName\"]"),"NA");
		   clickElement("check the agree checkbox",By.xpath("//input[@id=\"termsCheck\"]"));
		   clickElement("create user button",By.xpath("//button[@title=\"Create\"]"));
		   waitForElement(By.xpath("//*[@id=\"bi_userInfoDropdown\"]"),15);
		   navbar.clickMyAccountButton();
		   navbar.clickMyAccountDropdownOption();
		   String myaccountStudentName=Driver.getDriver().findElement(By.xpath("//input[@id='firstName']")).getAttribute("value");
		   navbar.clickCoursesAndGroups();
		   sendKeys("enter course name",By.xpath("//input[@class=\"group-management-inputbox form-control search-box\"]"),courseName);
		   clickElement("choose course after search",By.xpath("//ul[@id=\"courses-and-groups\"]//li"));
		   List<WebElement> memberList= getElementList(By.xpath("//div[@data-automation=\"divGroupMembers\"]//div[@class=\"user-list-item\"]"));
		   String obtainedmemberFirstname=null;
		   for(int i=0;i<memberList.size();i++) {
			   WebElement element=memberList.get(i);
			   String obtainedmemberName = element.getText();
			   String obtainedmembersplitFirstname[]=obtainedmemberName.split(" ",2);
			   obtainedmemberFirstname=obtainedmembersplitFirstname[0];
			   System.out.println(obtainedmemberFirstname);
			   if(obtainedmemberFirstname.equals(studentUserName) && obtainedmemberFirstname.equals(myaccountStudentName)){
				   Report.reportStep(Driver.getDriver(),"The canvas user is enrolled to  course in yuja", "PASS", false);
				   break;
			   }
			  }
		   if(obtainedmemberFirstname.equals(myaccountStudentName) && obtainedmemberFirstname.equals(studentUserName)){
			   System.out.println(myaccountStudentName+" "+studentUserName + " "+obtainedmemberFirstname );
			   Report.reportStep(Driver.getDriver(),"The canvas user is successfully provisioned  and enrolled to  course in yuja", "PASS", false);
		   }else {
			   Report.reportStep(Driver.getDriver(),"The canvas user is  not successfully provisioned  and enrolled to  course in yuja", "FAIL", true);
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
		   String StudentName=Driver.getDriver().findElement(By.xpath("//input[@id=\"personalMeetingRoomInput\"]")).getAttribute("value");
		   String obtainedmembersplitFirstname[]=StudentName.split("/");
		   String obtainedStudentUsername=obtainedmembersplitFirstname[2];
		   System.out.println(obtainedStudentUsername);
		   if(obtainedStudentUsername.equals(existingStudentUsername)){
			   Report.reportStep(Driver.getDriver(),"The canvas user is successfully provisioned to existing user in yuja", "PASS", false);
				}else {
					Report.reportStep(Driver.getDriver(),"The canvas user is  not successfully provisioned to existing user in yuja", "FAIL", true);
				}
			}
	   } catch(Exception e) {
			e.printStackTrace();
			Report.reportStep(Driver.getDriver(), "The test case did not complete all of its steps", "FAIL", false);
		}
		}
   
   public void checkRoleMapping(String userName, String password, int expectedMainMenuListSize, String canvasRole, String courserole, String embedmediatitle ) throws InterruptedException{
	   
	   HashMap<String, String> CourseRoleType = new HashMap<String, String>();
	   CourseRoleType.put("GroupMember", "//div[@data-automation=\"divGroupMembers\"]//div[@class=\"user-list-item\"]");
	   CourseRoleType.put("GroupOwner", "//div[@data-aitomation=\"divGroupOwners\"]//div[@class=\"user-list-item\"]");
	   
	   navigateToCourse(userName,password);
	   clickElement("people", By.xpath("//a[@class='people']"));
	   List<WebElement> memberListInCanvas= getElementList(By.xpath("//table[@class=\"roster ic-Table ic-Table--hover-row ic-Table--condensed ic-Table--striped\"]//tbody//tr//a[@data-student_id]"));
	   for(int i=0;i<memberListInCanvas.size();i++) {
			WebElement element=memberListInCanvas.get(i);
			String obtainedmemberName = element.getText();
			if(obtainedmemberName.equals("rolemappingusercanvas")) {
				int rowposition=i+1;
				clickElement("3 dots button", By.xpath("(//table[@class='roster ic-Table ic-Table--hover-row ic-Table--condensed ic-Table--striped']//tbody//tr//a[@role=\"button\"])["+rowposition+"]"));
				clickElement("edit role", By.xpath("//li[@class=\"ui-menu-item\"]//a[@data-event=\"editRoles\"]"));
				Select roleDropdown = new Select(Driver.getDriver().findElement(By.id("role_id")));
				roleDropdown.selectByVisibleText(canvasRole);
				clickElement("update role", By.xpath("//button[@class=\"btn-primary ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only\"]")); 
				Report.reportStep(Driver.getDriver(),"New canvas role is assigned to user", "PASS", false);
				break;
				}
			}
	   logout();
	   navigateToLoginPage();
	   Driver.getDriver().manage().window().maximize();
	   loginFast("rolemappingusercanvas", "jamNOW123/");
	   selectCourseFromDashboard("AUTOMATION CANVAS COURSE");
	   NavigateToLTI(embedmediatitle);
	   waitForElement(By.xpath("//button[@title=\" Main Menu\"]"),15);
	   clickElement("main menu button", By.xpath("//button[@title=\" Main Menu\"]"));
	   List<WebElement> mainmenuList= getElementList(By.xpath("//a[@class='yujaAjax']"));
	   int mainMenuListSize=mainmenuList.size();
	   clickElement("Courses and Groups button", By.cssSelector("[data-automation=\"btnCourseAndGroups\"]"));
	   sendKeys("enter course name",By.xpath("//input[@class=\"group-management-inputbox form-control search-box\"]"),"AUTOMATION CANVAS COURSE");
	   clickElement("choose course after search",By.xpath("//ul[@id=\"courses-and-groups\"]//li"));
	   List<WebElement> memberList= getElementList(By.xpath(CourseRoleType.get(courserole)));
	   String obtainedmemberFirstname=null;
	   for(int i=0;i<memberList.size();i++) {
			WebElement element=memberList.get(i);
			String obtainedmemberName = element.getText();
			String obtainedmembersplitFirstname[]=obtainedmemberName.split(" ",2);
		    obtainedmemberFirstname=obtainedmembersplitFirstname[0];
		    System.out.println(obtainedmemberFirstname);
			if(obtainedmemberFirstname.equals("rolemappingusercanvas")){
				Report.reportStep(Driver.getDriver(),"The canvas user role is correctly mapped to course in yuja", "PASS", false);
				break;
				}
		   }
	
		if(mainMenuListSize==expectedMainMenuListSize && obtainedmemberFirstname.equals("rolemappingusercanvas")) {
			Report.reportStep(Driver.getDriver(),"The canvas user is  enrolled to  course in yuja with correct system role and course role", "PASS", false);
			}else {
				Report.reportStep(Driver.getDriver(),"The canvas user is  not enrolled to  course in yuja with correct system role and course role", "FAIL", true);
			}
		Driver.getDriver().switchTo().defaultContent();
		logout();
		}
	   
	   //Method directly used in test class
   
   
	   public void roleMappingUserTypeUnlocked(String userName, String password, String adminUserName, String adminPassword, String userType, String rolemappingusername, String embedmediatitle) throws InterruptedException {
		   try {
		   HashMap<String, RoleMappingObject> hm = new HashMap<String, RoleMappingObject>();

		   RoleMappingObject t1 = new RoleMappingObject(2, "CustomCourseAdmin", "GroupMember");		  
		   hm.put("role1",t1);
		   RoleMappingObject t2 = new RoleMappingObject(2, "Teacher", "GroupMember");		  
		   hm.put("role2",t2);
		   RoleMappingObject t3 = new RoleMappingObject(3, "TA", "GroupOwner");		 
		   hm.put("role3",t3);
		   RoleMappingObject t4 = new RoleMappingObject(2, "Designer", "GroupMember");		  
		   hm.put("role4",t4);
		   RoleMappingObject t5 = new RoleMappingObject(3, "Student", "GroupOwner");		  
		   hm.put("role5",t5);
		   RoleMappingObject t6 = new RoleMappingObject(2, "Observer", "GroupMember");		 
		   hm.put("role6",t6);
		   
		   setRoleMapping(userName, password, "Instructor", "Student","Instructor","Student","Instructor","Student");
		   mp.setUserTypeToLockedorUnlocked("unlock",userType,rolemappingusername);
		   String key=null;
		   getCheckRoleMapping(adminUserName, adminPassword, hm,embedmediatitle);		  
		   for (Entry<String, RoleMappingObject> entry : hm.entrySet()) {
			   key=entry.getKey();
		   if(key=="role1") {
			   RoleMappingObject value = entry.setValue(t1);
			   value.setNumberOfMainMenuOptions(2);
			   value.setlmsRole("CustomCourseAdmin");
			   value.setCourseRole("GroupOwner");
			  }
		   if(key=="role2") {
			   RoleMappingObject value = entry.setValue(t2);
			   value.setNumberOfMainMenuOptions(3);
			   value.setlmsRole("Teacher");
			   value.setCourseRole("GroupOwner");
			   }
		   if(key=="role3") {
			   RoleMappingObject value = entry.setValue(t3);
			   value.setNumberOfMainMenuOptions(2);
			   value.setlmsRole("TA");
			   value.setCourseRole("GroupMember");
			   }
		   if(key=="role4") {
			   RoleMappingObject value = entry.setValue(t4);
			   value.setNumberOfMainMenuOptions(3);
			   value.setlmsRole("Designer");
			   value.setCourseRole("GroupOwner");
			  }
		   if(key=="role5") {
			   RoleMappingObject value = entry.setValue(t5);
			   value.setNumberOfMainMenuOptions(2);
			   value.setlmsRole("Student");
			   value.setCourseRole("GroupMember");
			   }
		   if(key=="role6") {
			   RoleMappingObject value = entry.setValue(t6);
			   value.setNumberOfMainMenuOptions(2);
			   value.setlmsRole("Observer");
			   value.setCourseRole("GroupMember");
			   }
		   }
		   
           setRoleMapping(userName, password, "IT Manager", "Instructor","Student","Instructor","Student","Student");
           getCheckRoleMapping(adminUserName, adminPassword, hm,embedmediatitle);
		   } catch(Exception e) {
				e.printStackTrace();
				Report.reportStep(Driver.getDriver(), "The test case did not complete all of its steps", "FAIL", false);
			}
	   }
		   
	   public void getCheckRoleMapping(String adminUserName, String adminPassword, HashMap<String, RoleMappingObject> hm, String embedmediatitle) throws InterruptedException {
		   for (Entry<String, RoleMappingObject> entry : hm.entrySet()) {
			    String key2 = entry.getKey();
			    RoleMappingObject value = entry.getValue();
			    checkRoleMapping(adminUserName, adminPassword,value.getNumberOfMainMenuOptions(),value.getlmsRole(),value.getCourseRole(),embedmediatitle );
			    }
	   }
	   
	 //Method directly used in test class
		   
      public void roleMappingUserTypeLocked(String userName, String password, String adminUserName, String adminPassword, String userType,int numberOfMainMenuOptions, String rolemappingusername, String embedmediatitle) throws InterruptedException {
		   try {
		   HashMap<String, RoleMappingObject> hm = new HashMap<String, RoleMappingObject>();

		   RoleMappingObject t1 = new RoleMappingObject(numberOfMainMenuOptions, "CustomCourseAdmin", "GroupMember");		  
		   hm.put("role1",t1);
		   RoleMappingObject t2 = new RoleMappingObject(numberOfMainMenuOptions, "Teacher", "GroupMember");		  
		   hm.put("role2",t2);
		   RoleMappingObject t3 = new RoleMappingObject(numberOfMainMenuOptions, "TA", "GroupOwner");		 
		   hm.put("role3",t3);
		   RoleMappingObject t4 = new RoleMappingObject(numberOfMainMenuOptions, "Designer", "GroupMember");		  
		   hm.put("role4",t4);
		   RoleMappingObject t5 = new RoleMappingObject(numberOfMainMenuOptions, "Student", "GroupOwner");		  
		   hm.put("role5",t5);
		   RoleMappingObject t6 = new RoleMappingObject(numberOfMainMenuOptions, "Observer", "GroupMember");		 
		   hm.put("role6",t6);
		   
		   setRoleMapping(userName, password,"Instructor", "Student","Instructor","Student","Instructor","Student");
		   mp.setUserTypeToLockedorUnlocked("lock",userType,rolemappingusername);
		   String key=null;
		   getCheckRoleMapping(adminUserName, adminPassword, hm,embedmediatitle);		  
		   for (Entry<String, RoleMappingObject> entry : hm.entrySet()) {
			   key=entry.getKey();
			   if(key=="role1") {
				   RoleMappingObject value = entry.setValue(t1);
				   value.setNumberOfMainMenuOptions(numberOfMainMenuOptions);
				   value.setlmsRole("CustomCourseAdmin");
				   value.setCourseRole("GroupOwner");
				  }
			   if(key=="role2") {
				   RoleMappingObject value = entry.setValue(t2);
				   value.setNumberOfMainMenuOptions(numberOfMainMenuOptions);
				   value.setlmsRole("Teacher");
				   value.setCourseRole("GroupOwner");
				   }
			   if(key=="role3") {
				   RoleMappingObject value = entry.setValue(t3);
				   value.setNumberOfMainMenuOptions(numberOfMainMenuOptions);
				   value.setlmsRole("TA");
				   value.setCourseRole("GroupMember");
				   }
			   if(key=="role4") {
				   RoleMappingObject value = entry.setValue(t4);
				   value.setNumberOfMainMenuOptions(numberOfMainMenuOptions);
				   value.setlmsRole("Designer");
				   value.setCourseRole("GroupOwner");
				  }
			   if(key=="role5") {
				   RoleMappingObject value = entry.setValue(t5);
				   value.setNumberOfMainMenuOptions(numberOfMainMenuOptions);
				   value.setlmsRole("Student");
				   value.setCourseRole("GroupMember");
				   }
			   if(key=="role6") {
				   RoleMappingObject value = entry.setValue(t6);
				   value.setNumberOfMainMenuOptions(numberOfMainMenuOptions);
				   value.setlmsRole("Observer");
				   value.setCourseRole("GroupMember");
				   }
		   }
		   
           setRoleMapping(userName, password,  "IT Manager", "Instructor","Student","Instructor","Student","Student");
           getCheckRoleMapping(adminUserName, adminPassword, hm,embedmediatitle);
		   } catch(Exception e) {
				e.printStackTrace();
				Report.reportStep(Driver.getDriver(), "The test case did not complete all of its steps", "FAIL", false);
			}
	   } 
		  

   
	//Check media exists in the media library deletion only work for last uploaded media
	
//	public void checkMediaExitsinMediaLibrary(List<String> mediaTitlelist) throws InterruptedException {
//		String media = "";
//		for(int i = 0; i<mediaTitlelist.size(); i++) {
//		 media = mediaTitlelist.get(i);
//		 System.out.println(media);
//		 if(mediaExists(media)) {
//			 System.out.println("media " +media+ " exists");}
//			mediaLibrary.deleteMedia(media);
//		 
//	  }
//	}
//	

	
	//Search for media in search bar of media chooser and embed
	
	public void CIMMediaChooserMediaEmbed(String name) throws InterruptedException {
		Thread.sleep(5000);
		clickElement("select media", By.cssSelector("div[id=\"VideoTab\"]"));
		searchMediaName(name);
		keyboardEnter();
		Thread.sleep(12000);
		clickElement("select entered media", By.xpath("//div[@id=\"videoSelectionContainer\"]/div/div[3]/div/div/div"));
		Thread.sleep(3000);
		clickElement("insert button", By.xpath("//button[@id=\"embedButton\"]"));
		Thread.sleep(5000);
		clickElement("publish button", By.xpath("//button[@type=\"submit\"]"));
		
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
		Driver.getDriver().switchTo().defaultContent();
		clickElement("click Select", By.xpath("//span[text()='Select']"));
		clickElement("click save and publish to course", By.xpath("//button[contains(@class,'btn btn-default save_and_publish')]"));
	}
	
	private void accessCIMMediaChooser(String LTILinkName,String quizNewName) throws InterruptedException {
		clickElement("Assignment", By.xpath("//a[@href='/courses/817/assignments']"));
		clickElement("add assignment button", By.xpath("//a[@href='https://panotesting.instructure.com/courses/817/assignments/new']"));
		Thread.sleep(3000);
		sendKeys("Enter assignment name", By.id("assignment_name"),quizNewName);
		sendKeys("Enter assignment points", By.xpath("//input[@id='assignment_points_possible']"),"100");
		Select externaltoolDropdown = new Select(Driver.getDriver().findElement(By.id("assignment_submission_type")));
		externaltoolDropdown.selectByValue("external_tool");
		clickElement("Find button", By.xpath("//button[@id='assignment_external_tool_tag_attributes_url_find']"));
		clickElement("tool link", By.xpath("//li[@class=\"tool resource_selection\"]//a[text()="+LTILinkName+"]"));
		Thread.sleep(5000);
		switchToIframe("switch to mediachooser frame", By.id("resource_selection_iframe"), 10);
	}
	
	private void accessCIMMediaChooserforAnnouncements(String name, String LTILinkName) throws InterruptedException {
		clickElement("Announcement", By.xpath("//a[@href='/courses/817/announcements']"));
		clickElement("add announcement button", By.xpath("//a[@id=\"add_announcement\"]"));
		Thread.sleep(3000);
		sendKeys("Enter announcement name", By.xpath("//input[@id='discussion-title']"),name);
		clickElement("Apps button", By.xpath("//button[@title=\"Apps\"]"));
		clickElement("View all link", By.xpath("//div[@title=\"View All\"]"));
		
		List<WebElement> appList= getElementList(By.xpath("//li[@class=\"fOyUs_bGBk jpyTq_bGBk jpyTq_ycrn\"]"));
		for(int i=0;i<appList.size();i++) {
		WebElement element=appList.get(i);
		String obtainedAppName = element.getText();
		String obtainedsplitLTILinkname[]=LTILinkName.split("'",3);
		String originalLTIName=obtainedsplitLTILinkname[1];
		String expectedApp=originalLTIName+"\nView description";
		if(obtainedAppName.equals(expectedApp)) {
			clickElement("click on given course",element);
			break;
		    }
		 }
		Thread.sleep(5000);
		switchToIframe("switch to mediachooser frame", By.name("external_tool_launch"), 10);
	}
	
	
	
	
	private void searchQuizName(String quizname) {
		sendKeys("quiz name", By.xpath("//input[@id=\"media-chooser-search-input\"]"), quizname);
	}
	
	private void searchMediaName(String medianame) {
		sendKeys("media name", By.xpath("(//input[@id=\"media-chooser-search-input\"])[2]"), medianame);
	}
	
	private void fillInUserId(String username) {
		sendKeys("User ID", By.xpath("//input[@name='pseudonym_session[unique_id]']"), username);
	}
	
	private void fillInPassword(String password) {
		sendKeys("Password", By.xpath("//input[@name='pseudonym_session[password]']"), password);
	}
	
	private void clickSignInButton() {
		clickElement("Sign In button", By.xpath("//button[contains(@class,'Button Button--login')]"));
	}
	
	private void clickGeneral() {
		clickElement("General button", By.xpath("//a[text()='General']"));
	}
	
	public void clickEmbedMediaCanvas(String embedMediaTitle) {
		waitForElement(By.xpath("//a[text()="+embedMediaTitle+"]"),30);
		clickElement("click embed media title", By.xpath("//a[text()="+embedMediaTitle+"]"));
	}

	
   //Methods for uploading media in media chooser

//	public Boolean mediaUploadedMediachooser(String mediaTitle, String mediaPath) {
//		try {
//			System.out.println("inside media upload media chooser");
//			uploadMediaMC(mediaPath);
//			Thread.sleep(2000);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return mediaExistsMediaChooser(mediaTitle);
//	}
//
//	
//	//Accessing browse button in the upload window in media chooser
//	
//	public void uploadMediaMC(String mediaPath) throws InterruptedException {
//		setMediaUploadModal();
//		mediaPath = Paths.get(mediaPath).toAbsolutePath().toString();
//		System.out.println("mediaPath = " + mediaPath);
//		System.out.println("Uploading media...");
//		sendKeysModal(mediaUploadModal, "Upload Video", By.xpath("//div[@class='upload-container']/div/div/div/div/div/input"), mediaPath);
//	}
//		
//		
//	//upload medias from the directory into media chooser
//	
//	public void mediaChooserBulkUploadMedia(String mediaDirectoryPath) {
//		
//		System.out.println("inside mediachooserupload");
//		
//		File mediaDirectory = new File(mediaDirectoryPath);
//		String[] mediaList = mediaDirectory.list();
//		String mediaTitle = null;
//		String mediaExtension;
//		String mediaPath;
//		
//		for(String media : mediaList) {
//			try {
//				mediaTitle = media.substring(0, media.indexOf('.'));
//				mediaPath = mediaDirectoryPath + "\\" + media;
//				mediaExtension = media.substring(media.indexOf('.')+1);
//				
//				System.out.println("Attempting to upload " + media);
//				if(mediaUploadedMediachooser(mediaTitle, mediaPath)) System.out.println(media + " upload success");
//				else throw new Exception(media + " upload fail");
//				Thread.sleep(1000);
//				clickElement("toast message close button", By.xpath("//div[@class='toast-message']/following-sibling::*"));
//				clickElement("click upload media", By.cssSelector("div[id=\"uploadMediaTab\"]"));
//				
//			} catch(Exception e) {
//				System.out.println(e.getMessage());
//				e.printStackTrace();
//				Report.reportStep(Driver.getDriver(),"e.getMessage(): " + e.getMessage() + ", @Method "+Scenario_Name +" exception to be handled", "Fail", true);
//			}
//		}
//	}
//
//	//Get the array of media titles uploaded from directory 
//	
//   public ArrayList<String> getMediaTitleArrayfromDirectory(String mediaDirectoryPath) {
//	   
//	   File mediaDirectory = new File(mediaDirectoryPath);
//	   String[] mediaList = mediaDirectory.list();
//	   String mediaTitle = null;
//		
//	   String mediaTitlelistName = "";
//	   int i = 0;
//	   
//	   ArrayList<String> mediaTitlelist1 = new ArrayList<String>();
//	   
//	   for( i=0;i<mediaList.length;i++) {
//			
//			mediaTitlelistName = mediaList[i]; 
//			mediaTitle = mediaTitlelistName.substring(0, mediaTitlelistName.indexOf('.'));
//			mediaTitlelist1.add(mediaTitle);
//		}
//	   
//	   return mediaTitlelist1;
//	}
//		
//				
//	//get a media element from the media chooser			
//		
//	public WebElement getMediaMediaChooser(String mediaTitle) {
//		waitForElement(By.xpath("//div[text()='"+mediaTitle+"']"), 30);
//		List<WebElement> mediaLibraryElementList = getElementList( By.cssSelector("div[class=\"list-item list-item-large add-media-list-item media-item-container\"]"));
//		int listSize = mediaLibraryElementList.size();
//		System.out.println(listSize);
//		WebElement mediaLibraryElement = null;
//		
//		if(listSize == 0) {
//			return mediaLibraryElement;
//		}
//		else { 
//			String mediaLibraryElementName = "";
//			int i = 0;
//			
//			Report.reportStep(Driver.getDriver(),"Fetching the folder with the name \"" + mediaTitle + "\"", "PASS", false);
//			
//			while(!mediaLibraryElementName.equals(mediaTitle) && i<listSize) {
//				mediaLibraryElement = mediaLibraryElementList.get(i++);
//				mediaLibraryElementName = mediaLibraryElement.findElement(By.className("choose-media-video-title")).getText();
//				System.out.println(mediaLibraryElementName);
//			}
//			return mediaLibraryElement;
//		}
//	}
//	
//	//Check a media exists in media chooser
//	
//	public boolean mediaExistsMediaChooser(String mediaTitle) {
//		WebElement mediaLibraryElement =  getMediaMediaChooser(mediaTitle);
//		String mediaLibraryElementName = mediaLibraryElement.findElement(By.className("choose-media-video-title")).getText();
//		Boolean mediaLibraryElementExists = mediaTitle.equals(mediaLibraryElementName);
//		if(mediaLibraryElementExists) {
//			Report.reportStep(Driver.getDriver(),"The media with the title \"" + mediaTitle + "\" exists", "PASS", false);
//		} else {
//			Report.reportStep(Driver.getDriver(),"The media with the title \"" + mediaTitle + "\" does not exists", "FAIL", true);
//		}
//		return mediaLibraryElementExists;
//	}
//   
  }




