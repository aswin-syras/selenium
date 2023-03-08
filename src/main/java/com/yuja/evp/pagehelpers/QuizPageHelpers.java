package com.yuja.evp.pagehelpers;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.yuja.evp.modalhelpers.MediaDetailsModalHelperMethods;
import com.yuja.evp.pagetestmethods.MediaLibraryPageTestMethods;
import com.yuja.evp.utilities.Helpers;

public class QuizPageHelpers extends Helpers {
	
	private SignInPageHelpers signInPage = new SignInPageHelpers();
	MediaChannelPageHelpers mediaChannel = new MediaChannelPageHelpers();
	NavigationBarHelpers navigationBar = new NavigationBarHelpers();
	MediaLibraryPageTestMethods mediaLibrary = new MediaLibraryPageTestMethods();
	protected MediaDetailsModalHelperMethods mediaDetailsModal= new MediaDetailsModalHelperMethods();
	GradebookPageHelpers gradebook=new GradebookPageHelpers();
	MyAccountPageHelpers myAccount= new MyAccountPageHelpers();
	
	public void createDraftQuiz(String mediaTitle, String name,String question,String option1, String option2,String possibleans1, String possibleans2,String hint) throws Exception{
		clickElement("Click Manage Media",By.xpath("//span[@id='topBarTabName3']"),10);
		Thread.sleep(2000);
		mediaLibrary.accessMediaMoreMenu(mediaTitle);
		mediaDetailsModal.clickQuizzes();
		clickElement("Create Video Quiz", By.id("createVideoQuizBtn"));
		Thread.sleep(2000);
		enterQuizName(name+getRandomInteger(1000));
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
	}
	
	public String createPlaybackquiz(String playbackQuizTitle,String mediaTitle) throws InterruptedException {
		clickElement("Create Playback Quiz", By.xpath("(//button[@class=\"btn btn-inline leftAlignIcon\"])[2]"));
		sendKeys("Playback Quiz Title", By.id("createPlaybackTitle"), playbackQuizTitle+getRandomInteger(1000));
		clickElement("Click Create Button", By.xpath("//button[@title='Create']"));
		mediaDetailsModal.clickCloseMoreMenu();
		mediaLibrary.accessMediaMoreMenu(mediaTitle);
		mediaDetailsModal.clickQuizzes();
		Thread.sleep(5000);
		String quiznewName=driver.findElement(By.xpath("//div[@class='quiz-panel-contents']/h4")).getText();
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ESCAPE).build().perform();
		System.out.println(quiznewName);
		return quiznewName;
	}
	
	
	public String createandPublishQuiz(String mediaTitle, String name,String question,String option1, String option2,String possibleans1, String possibleans2,String hint) throws Exception {
		createDraftQuiz(mediaTitle, name, question,option1, option2, possibleans1, possibleans2,hint);
		clickQuizPostbutton();
		clickQuizSelectcourse();
		Thread.sleep(2000);
		clickQuizpostaftercourseSelection();
		Thread.sleep(5000);
		mediaLibrary.accessMediaMoreMenu(mediaTitle);
		mediaDetailsModal.clickQuizzes();
		Thread.sleep(1000);
		String quiznewName=driver.findElement(By.xpath("//div[@class='quiz-panel-contents']/h4")).getText();
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ESCAPE).build().perform();
		System.out.println(quiznewName);
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
		mediaDetailsModal.clickActivityLogButton();
		String logtext=mediaDetailsModal.getActivityLogforQuizSync().getText();
		System.out.println(logtext);
		String expectedLogText="LMS User "+studentFullName+"'s grade of "+marks+ " successfully synced to LMS Gradebook for quiz titled '"+quizName+"'";
        System.out.println(expectedLogText);
        
		if(logtext.contains(expectedLogText)){
			reportStep("The student grade is sucessfully synced to LMS ", "PASS", false);
		} else {
			reportStep("The student grade is not sucessfully synced to LMS", "FAIL", true);
		}	
	}
	
	public void updatePostInfo(String userName,String password, String mediaTitle, String quizclosedate) throws InterruptedException {
		mediaLibrary.navigateToMyMediaUserLogin(userName, password);;
		mediaLibrary.accessMediaMoreMenu(mediaTitle);
		mediaDetailsModal.clickQuizzes();
		mediaDetailsModal.clickManageQuizButton();
		clickElement("Click Update post date Dropdown",By.xpath("//div[@class='postInfoIcon']"),10);
		clickElement("Click Uncheck no close date checkbox",By.xpath("(//input[@type='checkbox'])[2]"),10);
		sendKeys("Quiz close date", By.xpath("//input[@id='publishPollEndDate_145314']"), quizclosedate);
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

	private void enterQuizName(String name) {
		sendKeys("Quiz Name", By.id("titleBox"), name);
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
				reportStep("both play buttons" + " element not exist method - thrown Exception", "FAIL", true);
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
	
	private void clickQuizSelectcourse() {
		clickElement("Select course Checkbox", By.id("classCheck"));
	}
	
	private void clickQuizpostaftercourseSelection() {
		clickElement("Quiz post after course selection", By.xpath("(//button[@title=\"Post\"])[2]"));
	}
	 
	 protected String getQuizDirectLink() {
		driver.findElement(By.xpath("//input[@id='directLinkInputBox']")).click(); 
		String quizDirectLink=driver.findElement(By.xpath("//input[@id='directLinkInputBox']")).getAttribute("value");
		return quizDirectLink;
	 }
	
	 public void  checkGradebookTestafterLoginforMultiple(String mediaTitle, String courseName,String name,String marks,String studentName) throws InterruptedException {
		 clickElement("Click Manage Media",By.xpath("//span[@id='topBarTabName3']"),10);
		 mediaLibrary.accessMediaMoreMenu(mediaTitle);
		 mediaDetailsModal.clickQuizzes();
	   	 mediaDetailsModal.clickGradebookButton();
		 Thread.sleep(3000);
		 gradebook.clickSelectCourse();
		 Thread.sleep(3000);
		 List <WebElement> element1= driver.findElements(By.xpath("//input[@class='select2-input']"));
		 WebElement element2=element1.get(element1.size()-1);
		 typeKeys(courseName);
		 keyboardEnter();
		 Thread.sleep(3000);
		 gradebook.clickSelectQuiz();
		 Thread.sleep(3000);
		 List <WebElement> element3= driver.findElements(By.xpath("//input[@class='select2-input']"));
		 WebElement element4=element3.get(element3.size()-1);
		 typeKeys(name);
		 keyboardEnter();
		 gradebook.clickGetGradebookReport();
		 int gradeBookUserIndex = gradebook.getUserIndexFromGradeBook(studentName);
		 String grade = gradebook.gradeShortAnswerforMultipleStudents(gradeBookUserIndex);
		 Thread.sleep(3000);
		 int d = gradeBookUserIndex + 1;
		 String expectedCorrectness="Used Hint : Yes, Status: Answered";
	
	        String question1correctness= driver.findElement(By.xpath("(//table/tbody/tr["+d+"]/td[@class='general-entry'])[1]")).getAttribute("title");
			String question2correctness= driver.findElement(By.xpath("(//table/tbody/tr["+d+"]/td[@class='general-entry'])[2]")).getAttribute("title");
			String question3correctness= driver.findElement(By.xpath("(//table/tbody/tr["+d+"]/td[@class='general-entry'])[3]")).getAttribute("title");
			String question4correctness= driver.findElement(By.xpath("(//table/tbody/tr["+d+"]/td[@class='general-entry'])[4]")).getAttribute("title");
			String question5correctness= driver.findElement(By.xpath("(//table/tbody/tr["+d+"]/td[@class='general-entry'])[5]")).getAttribute("title");
			String question6correctness= driver.findElement(By.xpath("(//table/tbody/tr["+d+"]/td[@class='general-entry'])[6]")).getAttribute("title");
			 
			if(grade.equals(marks)&&question1correctness.contains(expectedCorrectness)&&question2correctness.contains(expectedCorrectness)&&question3correctness.contains(expectedCorrectness)&&question4correctness.contains(expectedCorrectness)&&question5correctness.contains("Used Hint : N/A, Status: N/A")&&question6correctness.contains(expectedCorrectness) ){
				reportStep("The student grade is displayed correctly ", "PASS", false);
			} else {
				reportStep("The student grade is not displayed correctly", "FAIL", true);
			}
	 }
	 
	 public void checkGradebookforPlaybackQuiz(String mediaTitle, String courseName,String name,String marks,String studentName) throws InterruptedException {
		 clickElement("Click Manage Media",By.xpath("//span[@id='topBarTabName3']"),10);
		 mediaLibrary.accessMediaMoreMenu(mediaTitle);
		 mediaDetailsModal.clickQuizzes();
	   	 mediaDetailsModal.clickGradebookButton();
		 Thread.sleep(3000);
		 gradebook.clickSelectCourse();
		 Thread.sleep(3000);
		 List <WebElement> element1= driver.findElements(By.xpath("//input[@class='select2-input']"));
		 WebElement element2=element1.get(element1.size()-1);
		 typeKeys(courseName);
		 keyboardEnter();
		 Thread.sleep(3000);
		 gradebook.clickSelectQuiz();
		 Thread.sleep(3000);
		 List <WebElement> element3= driver.findElements(By.xpath("//input[@class='select2-input']"));
		 WebElement element4 = element3.get(element3.size()-1);
		 typeKeys(name);
		 keyboardEnter();
		 gradebook.clickGetGradebookReport();
		 int gradeBookUserIndex = gradebook.getUserIndexFromGradeBook(studentName);
		 String grade = gradebook.gradePlaybackQuiz(gradeBookUserIndex);
		 Thread.sleep(3000);
		 if(grade.equals(marks)) {
			 reportStep("The student grade is displayed correctly ", "PASS", false);
		 } else {
			 reportStep("The student grade is not displayed correctly", "FAIL", true);
		 }
	 }
		
}

	 


	


	
	


