package com.yuja.evp.pagehelpers;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.yuja.evp.modalhelpers.MediaDetailsModalHelperMethods;
import com.yuja.evp.pagetestmethods.MediaLibraryPageTestMethods;
import com.yuja.evp.reports.Report;
import com.yuja.evp.utilities.Driver;
import com.yuja.evp.utilities.Helpers;

public class QuizPageHelpers extends Helpers {
	
	private SignInPageHelpers signInPage = new SignInPageHelpers();
	MediaChannelPageHelpers mediaChannel = new MediaChannelPageHelpers();
	NavigationBarHelpers navigationBar = new NavigationBarHelpers();
	MediaLibraryPageTestMethods mediaLibrary = new MediaLibraryPageTestMethods();
	protected MediaDetailsModalHelperMethods mediaDetailsModal= new MediaDetailsModalHelperMethods();
	GradebookPageHelpers gradebook=new GradebookPageHelpers();
	MyAccountPageHelpers myAccount= new MyAccountPageHelpers();
	
	public String createDraftQuiz(String mediaTitle, String name,String question,String option1, String option2,String possibleans1, String possibleans2,String hint) throws Exception{
		clickElement("Click Manage Media",By.xpath("//span[@id='topBarTabName3']"),10);
		Thread.sleep(4000);
		mediaLibrary.accessMediaMoreMenu(mediaTitle);
		mediaDetailsModal.clickQuizzes();
		List<WebElement> quizpanelContainer= getElementList(By.xpath("//div[@class=\"quiz-panel-contents-container\"]"));
		if(quizpanelContainer!=null) {
			for(int i=0;i<quizpanelContainer.size();i++) {
				int index=i+1;
				clickElement("delete Quiz", By.xpath("(//div[@class=\"quiz-panel-contents-container\"])["+index+"]//div[@class=\"quiz-btn-group\"]//button//i[@class=\"fa fa-trash-o\"]"));
				clickElement("yes",By.xpath("//button[@title='Yes']"),10);
				}
			}
		clickElement("Create Video Quiz", By.id("createVideoQuizBtn"));
		Thread.sleep(2000);
		String obtainedQuizName=enterQuizName(name+getRandomInteger(1000));
		clickAddQuestion();
		createMultiplechoiceQuestion(question,option1,option2,hint);
		Thread.sleep(2000);
		clickAddQuestion();
		createSelectMultipleQuestion(question,option1,option2,hint);
		Thread.sleep(2000);
		clickAddQuestion();
		createShortAnsQuestion(question,hint);
		Thread.sleep(2000);
		clickAddQuestion();
		createTrueFalseQuestion(question,hint);
		Thread.sleep(2000);
		clickAddQuestion();
		createReflectivePause(question);
		Thread.sleep(2000);
		clickAddQuestion();
		createFITBQuestion(question,possibleans1,possibleans2,hint);
		clickQuizSavebutton();
		return obtainedQuizName;
	}
	
	public String createPlaybackquiz(String playbackQuizTitle,String mediaTitle) throws InterruptedException {
		List<WebElement> quizpanelContainer= getElementList(By.xpath("//div[@class=\"quiz-panel-contents-container\"]"));
		if(quizpanelContainer!=null) {
			for(int i=0;i<quizpanelContainer.size();i++) {
				int index=i+1;
				clickElement("delete Quiz", By.xpath("(//div[@class=\"quiz-panel-contents-container\"])["+index+"]//div[@class=\"quiz-btn-group\"]//button[3]"));
				clickElement("yes",By.xpath("//button[@title='Yes']"),10);
				}
			}
		clickElement("Create Playback Quiz", By.xpath("(//button[@class=\"btn btn-inline leftAlignIcon\"])[2]"));
		sendKeys("Playback Quiz Title", By.id("createPlaybackTitle"), playbackQuizTitle+getRandomInteger(1000));
		String quiznameObtained=Driver.getDriver().findElement(By.id("createPlaybackTitle")).getAttribute("value");
		clickElement("Click Create Button", By.xpath("//button[@title='Create']"));
		mediaDetailsModal.clickCloseMoreMenu();
		return quiznameObtained;
	}
	
	
	public String createandPublishQuiz(String mediaTitle, String name,String question,String option1, String option2,String possibleans1, String possibleans2,String hint, String courseName) throws Exception {
		String quiznewName=createDraftQuiz(mediaTitle, name, question,option1, option2, possibleans1, possibleans2,hint);
		clickQuizPostbutton();
		clickQuizSelectcourse(courseName);
		Thread.sleep(2000);
		clickQuizpostaftercourseSelection();
		Thread.sleep(5000);
		return quiznewName;
		
	}
		
	public void StudentattendallquestionsCorrectly(String sa, String fitbans) throws InterruptedException {
		clickmultiplechoiceoption();
		clickHintbuttonans();
		clickSaveAns();
		Thread.sleep(2000);
		clickselectmultipleoption();
		clickHintbuttonans();
		clickSaveAns();
		sendKeys("Short ans", By.id("shortAnswerInput"), sa);
		clickHintbuttonans();
		clickSaveAns();
		Thread.sleep(2000);
		clicktrueoption();
		clickHintbuttonans();
		clickSaveAns();
		Thread.sleep(2000);
		clickSaveAns();
		sendKeys("FITB ans", By.xpath("//input[@class='fillInTheBlankInput form-control']"), fitbans);
		clickHintbuttonans();
		clickSaveAns();
		clickQuizSubmit();
	}
	
	//Method to check quiz grade sync in LMS gradebook
	
	public void checkActivityLogforQuizSync(String mediaTitle,String studentFullName,String marks,String quizName) throws InterruptedException {
	
		mediaLibrary.accessMediaMoreMenu(mediaTitle);
		mediaDetailsModal.clickQuizzes();
		sendKeys("Search for quizzes", By.id("quiz-search-input"), quizName);
		mediaDetailsModal.clickActivityLogButton();
		String logtext=mediaDetailsModal.getActivityLogforQuizSync().getText();
		System.out.println(logtext);
		String expectedLogText="LMS User "+studentFullName+"'s grade of "+marks+ " successfully synced to LMS Gradebook for quiz titled '"+quizName+"'";
        System.out.println(expectedLogText);
        
		if(logtext.contains(expectedLogText)){
			Report.reportStep(Driver.getDriver(), "The student grade is sucessfully synced to LMS ", "PASS", false);
		} else {
			Report.reportStep(Driver.getDriver(), "The student grade is not sucessfully synced to LMS", "FAIL", true);
		}	
	}
	
	public void updatePostInfo(String userName,String password, String mediaTitle, String quizName,String courseName) throws InterruptedException {
		mediaLibrary.navigateToMyMediaUserLogin(userName, password);
		mediaLibrary.accessMediaMoreMenu(mediaTitle);
		mediaDetailsModal.clickQuizzes();
		sendKeys("Search for quizzes", By.id("quiz-search-input"), quizName);
		mediaDetailsModal.clickManageQuizButton();
		List<WebElement> courseList= getElementList(By.xpath("//table//tbody//tr//td//div[@class=\"row postCourseName\"]"));
		for(int i=0;i<courseList.size();i++) {
			WebElement element=courseList.get(i);
			String obtainedcourseName = element.getText();
			 System.out.println(obtainedcourseName);
			 System.out.println(courseName);
			if(obtainedcourseName.contains(courseName)){
				int rowposition=i+1;
				int rowpos=rowposition+2;
				clickElement("Click Update post date Dropdown",By.xpath("(//div[@class='postInfoIcon'])["+rowposition+"]"),10);
				clickElement("Click Uncheck no close date checkbox",By.xpath("(//input[@type='checkbox'])["+rowpos+"]"),10);
				sendKeys("Quiz close date", By.xpath("(//input[contains(@id,'publishPollEndDate')])["+rowposition+"]"), "1/5/23");
				break;
				}
			}
		mediaDetailsModal.clickUpdatePostQuiz();
		mediaDetailsModal.clickCloseMoreMenu();
		
	}
	
	public void loginAsCreator(String userName, String password) {
		mediaLibrary.navigateToMyMediaUserLogin(userName, password);
	}
	
	private void createMultiplechoiceQuestion(String question,String option1, String option2,String hint) throws InterruptedException {
		clickElement("Multiple Choice", By.cssSelector("[data-automation=\"btnMultipleChoice\"]"));
		Thread.sleep(2000);
		sendKeys("Multichoice question", By.id("questionBox"), question);
		sendKeys("Multichoice option 1", By.xpath("(//textarea[@placeholder=\"Enter option here\"])[1]"), option1);
		sendKeys("Multichoice option 2", By.xpath("(//textarea[@placeholder=\"Enter option here\"])[2]"), option2);
		clickElement("correct answer", By.xpath("//input[@class=\"correctCheck trackMod\"]"));
		Thread.sleep(3000);
		clickHintbutton();
		Thread.sleep(3000);
		sendKeys("hint", By.id("questionHint"), hint);
		clickElement("Save", By.xpath("//a[@title=\"Save\"]"));
	}
  
	private void createSelectMultipleQuestion(String question,String option1, String option2,String hint) throws InterruptedException {
		clickElement("SelectMultiple", By.cssSelector("[data-automation=\"btnSelectMultiple\"]"));
		Thread.sleep(2000);
		sendKeys("Multiple question", By.id("questionBox"), question);
		sendKeys("Select multiple option 1", By.xpath("(//textarea[@placeholder=\"Enter option here\"])[1]"), option1);
		sendKeys("Select Multiple option 2", By.xpath("(//textarea[@placeholder=\"Enter option here\"])[2]"), option2);
		clickElement("correct answer", By.xpath("//input[@class=\"correctCheck trackMod\"]"));
		sendKeys("hint", By.id("questionHint"), hint);
		clickElement("Save", By.xpath("//a[@title=\"Save\"]"));
	}
	
	private void createShortAnsQuestion(String question,String hint) throws InterruptedException {
		clickElement("Short Answer", By.cssSelector("[data-automation=\"btnShortAnswer\"]"));
		Thread.sleep(2000);
		sendKeys("Short ans question", By.id("questionBox"), question);
		sendKeys("hint", By.id("questionHint"), hint);
		clickElement("Save", By.xpath("//a[@title=\"Save\"]"));
	}
    
	private void createTrueFalseQuestion(String question,String hint) throws InterruptedException {
		clickElement("True or False", By.cssSelector("[data-automation=\"btnTrueFalse\"]"));
		Thread.sleep(2000);
		sendKeys("True or false question", By.id("questionBox"), question);
		clickElement("correct answer", By.xpath("//input[@class=\"correctCheck trackMod\"]"));
		sendKeys("hint", By.id("questionHint"), hint);
		clickElement("Save", By.xpath("//a[@title=\"Save\"]"));
	}
	
	private void createReflectivePause(String question) throws InterruptedException {
		clickElement("Reflective pause", By.cssSelector("[data-automation=\"btnReflectivePause\"]"));
		Thread.sleep(2000);
		sendKeys("Reflective pause question", By.id("questionBox"), question);
		clickElement("Save", By.xpath("//a[@title=\"Save\"]"));
	}
    
	private void createFITBQuestion(String question,String possibleans1, String possibleans2,String hint) throws InterruptedException {
		clickElement("FITB Question", By.cssSelector("[data-automation=\"btnFillInTheBlank\"]"));
		Thread.sleep(2000);
		sendKeys("FITB question", By.id("questionBox"), question);
		clickElement("Blank", By.xpath("//button[@class=\"addBlankBtn btn defaultcolor bbCode bbCodeVideoPoll\"]"));
		sendKeys("Possible ans 1", By.xpath("(//input[@placeholder=\"Enter potential answer here\"])[1]"), possibleans1);
		sendKeys("Possible ans 2", By.xpath("(//input[@placeholder=\"Enter potential answer here\"])[2]"), possibleans2);
		Thread.sleep(3000);
		clickHintbutton();
		Thread.sleep(3000);
		sendKeys("hint", By.id("questionHint"), hint);
		clickElement("Save", By.xpath("//a[@title=\"Save\"]"));
	}
	
	private void clickmultiplechoiceoption() {
		clickElement("Click Multiple Choice option",By.xpath("(//div[@id='pid'])[1]"),10);
	}
	
	private void clickSaveAns() {
		clickElement("Click Save Ans",By.xpath("//button[@id=\"submitQuestionBtn\"]"),10);
	}
	
	private void clickselectmultipleoption() {
		clickElement("Click Select Multiple  option",By.xpath("//div[contains(@class,'answerTile overlayItem')] "),10);
	}
	
	private void clicktrueoption() {
		clickElement("Click True  option",By.xpath("//div[contains(@class,'answerTile overlayItem')] "),10);
	}
	
	private void clickQuizSubmit() {
		clickElement("Click Submit Quiz",By.id("submit"), 10);
	}

	private String enterQuizName(String name) {
		sendKeys("Quiz Name", By.id("titleBox"), name);
		String quiznameObtained=Driver.getDriver().findElement(By.id("titleBox")).getAttribute("value");
		return quiznameObtained;
	}
	
	private void clickAddQuestion() {
		clickElement("Add question", By.id("addQuestionButton"));
	}
	
	protected void clickPlaybutton() {
		boolean check = verifyElementExist("play button",By.id("previewPlay"));
		if(check) {
			clickElement("Play button", By.id("previewPlay"));
             }
		else {
			boolean check1 = verifyElementExist("Play from beginning",By.id("previewPlayButton"));
			if(check1) {
				clickElement("Play from beginning", By.id("previewPlayButton"));
			}
			else {
				Report.reportStep(Driver.getDriver(), "both play buttons" + " element not exist method - thrown Exception", "FAIL", true);
			}
		}
       }
    
	private void clickHintbutton() {
		clickElement("Hint button", By.xpath("//img[@src=\"/Dashboard/icons/polls/hint.svg\"]"));
    }
    
	private void clickHintbuttonans() {
		clickElement("Hint button while answering", By.xpath("//button[@title='Hint']"));
    }
    
	private void clickQuizSavebutton() {
		clickElement("Quiz Save button", By.id("saveButton"));
	}
	
	private void clickQuizPostbutton() {
		clickElement("Quiz Post button", By.id("doneButton"));
	}
	
	private void clickQuizSelectcourse(String courseName) {
		List<WebElement> courseList= getElementList(By.xpath("//table//tbody//tr//td//div[@class=\"row postCourseName\"]"));
		String obtainedmemberFirstname=null;
		for(int i=0;i<courseList.size();i++) {
			WebElement element=courseList.get(i);
			String obtainedcourseName = element.getText();
			 System.out.println(obtainedcourseName);
			if(obtainedcourseName.contains(courseName)){
				int rowposition=i+1;
				clickElement("Select course Checkbox", By.xpath("(//input[@id=\"classCheck\"])["+rowposition+"]"));
				Report.reportStep(Driver.getDriver(), "The moodle user is enrolled to  course in yuja", "PASS", false);
				break;
				}
			}
		}
	
	private void clickQuizpostaftercourseSelection() {
		clickElement("Quiz post after course selection", By.xpath("(//button[@title=\"Post\"])[2]"));
	}
	 
	 protected String getQuizDirectLink() {
		Driver.getDriver().findElement(By.xpath("//input[@id='directLinkInputBox']")).click(); 
		String quizDirectLink=Driver.getDriver().findElement(By.xpath("//input[@id='directLinkInputBox']")).getAttribute("value");
		return quizDirectLink;
	 }
	
	 public void  checkGradebookTestafterLoginforMultiple(String mediaTitle,String quizName, String courseName,String studentName,String marks ) throws InterruptedException {
		 clickElement("Click Manage Media",By.xpath("//span[@id='topBarTabName3']"),10);
		 Thread.sleep(5000);
		 WebElement media = getMedia(mediaTitle);
		 hoverOverElement(media);
		 clickElement(media, "More menu button from the video hover", By.cssSelector("[data-automation=\"btnInVideoMenuMore\"]"), 30);
		 mediaDetailsModal.clickQuizzes();
		 sendKeys("Search for quizzes", By.id("quiz-search-input"), quizName);
	   	 mediaDetailsModal.clickGradebookButton();
		 Thread.sleep(3000);
		 gradebook.clickSelectCourse();
		 Thread.sleep(3000);
		 List <WebElement> element1= Driver.getDriver().findElements(By.xpath("//input[@class='select2-input']"));
		 WebElement element2=element1.get(element1.size()-1);
		 typeKeys(courseName);
		 keyboardEnter();
		 Thread.sleep(3000);
		 gradebook.clickSelectQuiz();
		 Thread.sleep(3000);
		 List <WebElement> element3= Driver.getDriver().findElements(By.xpath("//input[@class='select2-input']"));
		 WebElement element4=element3.get(element3.size()-1);
		 typeKeys(quizName);
		 keyboardEnter();
		 gradebook.clickGetGradebookReport();
		 int gradeBookUserIndex = gradebook.getUserIndexFromGradeBook(studentName);
		 String grade = gradebook.gradeShortAnswerforMultipleStudents(gradeBookUserIndex);
		 Thread.sleep(3000);
		 int d = gradeBookUserIndex + 1;
		 String expectedCorrectness="Used Hint : Yes, Status: Answered";
	
	        String question1correctness= Driver.getDriver().findElement(By.xpath("(//table/tbody/tr["+d+"]/td[@class='general-entry'])[1]")).getAttribute("title");
			String question2correctness= Driver.getDriver().findElement(By.xpath("(//table/tbody/tr["+d+"]/td[@class='general-entry'])[2]")).getAttribute("title");
			String question3correctness= Driver.getDriver().findElement(By.xpath("(//table/tbody/tr["+d+"]/td[@class='general-entry'])[3]")).getAttribute("title");
			String question4correctness= Driver.getDriver().findElement(By.xpath("(//table/tbody/tr["+d+"]/td[@class='general-entry'])[4]")).getAttribute("title");
			String question5correctness= Driver.getDriver().findElement(By.xpath("(//table/tbody/tr["+d+"]/td[@class='general-entry'])[5]")).getAttribute("title");
			String question6correctness= Driver.getDriver().findElement(By.xpath("(//table/tbody/tr["+d+"]/td[@class='general-entry'])[6]")).getAttribute("title");
			 
			if(grade.equals(marks)&&question1correctness.contains(expectedCorrectness)&&question2correctness.contains(expectedCorrectness)&&question3correctness.contains(expectedCorrectness)&&question4correctness.contains(expectedCorrectness)&&question5correctness.contains("Used Hint : N/A, Status: N/A")&&question6correctness.contains(expectedCorrectness) ){
				Report.reportStep(Driver.getDriver(), "The student grade is displayed correctly ", "PASS", false);
			} else {
				Report.reportStep(Driver.getDriver(), "The student grade is not displayed correctly", "FAIL", true);
			}
	 }
	 
	 public void checkGradebookforPlaybackQuiz(String mediaTitle, String courseName,String marks,String studentName, String quizName) throws InterruptedException {
		 clickElement("Click Manage Media",By.xpath("//span[@id='topBarTabName3']"),10);
		 mediaLibrary.accessMediaMoreMenu(mediaTitle);
		 mediaDetailsModal.clickQuizzes();
		 sendKeys("Search for quizzes", By.id("quiz-search-input"), quizName);
	   	 mediaDetailsModal.clickGradebookButton();
		 Thread.sleep(3000);
		 gradebook.clickSelectCourse();
		 Thread.sleep(3000);
		 List <WebElement> element1= Driver.getDriver().findElements(By.xpath("//input[@class='select2-input']"));
		 WebElement element2=element1.get(element1.size()-1);
		 typeKeys(courseName);
		 keyboardEnter();
		 Thread.sleep(3000);
		 gradebook.clickSelectQuiz();
		 Thread.sleep(3000);
		 List <WebElement> element3= Driver.getDriver().findElements(By.xpath("//input[@class='select2-input']"));
		 WebElement element4 = element3.get(element3.size()-1);
		 typeKeys(quizName);
		 keyboardEnter();
		 gradebook.clickGetGradebookReport();
		 int gradeBookUserIndex = gradebook.getUserIndexFromGradeBook(studentName);
		 String grade = gradebook.gradePlaybackQuiz(gradeBookUserIndex);
		 Thread.sleep(3000);
		 if(grade.equals(marks)) {
			 Report.reportStep(Driver.getDriver(), "The student grade is displayed correctly ", "PASS", false);
		 } else {
			 Report.reportStep(Driver.getDriver(), "The student grade is not displayed correctly", "FAIL", true);
		 }
	 }
		
}

	 


	


	
	


