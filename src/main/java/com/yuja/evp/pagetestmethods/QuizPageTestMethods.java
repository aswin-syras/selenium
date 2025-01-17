package com.yuja.evp.pagetestmethods;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.yuja.evp.modalhelpers.MediaDetailsModalHelperMethods;
import com.yuja.evp.pagehelpers.GradebookPageHelpers;
import com.yuja.evp.pagehelpers.MediaChannelPageHelpers;
import com.yuja.evp.pagehelpers.MyAccountPageHelpers;
import com.yuja.evp.pagehelpers.NavigationBarHelpers;
import com.yuja.evp.pagehelpers.QuizPageHelpers;
import com.yuja.evp.reports.Report;
import com.yuja.evp.utilities.Driver;

public class QuizPageTestMethods extends QuizPageHelpers{
	
	MediaChannelPageHelpers mediaChannel = new MediaChannelPageHelpers();
	NavigationBarHelpers navigationBar = new NavigationBarHelpers();
	MediaLibraryPageTestMethods mediaLibrary = new MediaLibraryPageTestMethods();
	MediaDetailsModalHelperMethods mediaDetailsModal= new MediaDetailsModalHelperMethods();
	GradebookPageHelpers gradebook=new GradebookPageHelpers();
	MyAccountPageHelpers myAccount= new MyAccountPageHelpers();
	
		public String createPublishandAttendQuizTest(String userName, String password, String mediaTitle, String name,String question,String option1, String option2,String possibleans1, String possibleans2,String hint, String studentUserName, String studentPassword,String sa, String fitbans, String courseName) throws Exception {
			loginAsCreator(userName, password);
			String newQuizName = createandPublishQuiz(mediaTitle, name, question,option1, option2, possibleans1, possibleans2,hint,courseName);
			navigationBar.userLogOut();
			mediaLibrary.navigateToMyMediaUserLogin(studentUserName, studentPassword);
			for(int i=0;i<5;i++) {
				Driver.getDriver().navigate().refresh();
				navigationBar.clickNotifications();
				Thread.sleep(5000);
				if( verifyElementExist("Quiz Notificaton",By.xpath("//div[@class='post']"))){
				clickElement("Quiz Notificaton",By.xpath("//div[@class='post']"),10);
				break;}
				}
				Thread.sleep(5000);
			switchToIframe("switch to video player frame", By.id("yujahtml5playerInVideoPoll"), 10);
			clickPlaybutton();
			Thread.sleep(5000);
			Driver.getDriver().switchTo().defaultContent();
			StudentattendallquestionsCorrectly(sa, fitbans);
			clickElement("go to media collection button",By.xpath("//button[@title='Exit Quiz']"),10);
			Thread.sleep(3000);
			navigationBar.userLogOut();
			return newQuizName;
		}
		
		public void checkStudentgradeBookTest(String userName,String password, String mediaTitle, String studentUserName, String studentPassword,String courseName,String quizName) throws InterruptedException{
			updatePostInfo(userName,password, mediaTitle,quizName,courseName);
			navigationBar.userLogOut();
			Thread.sleep(2000);
			mediaLibrary.navigateToMyMediaUserLogin(studentUserName, studentPassword);
			Thread.sleep(2000);
			navigationBar.clickMyAccountButton();
			navigationBar.clickMyAccountDropdownOption();
			myAccount.clickMyGrades();
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
			Thread.sleep(3000);
			
			String question1correctness = Driver.getDriver().findElement(By.xpath("//table/tbody/tr[1]/td[3]")).getText();
			String question2correctness = Driver.getDriver().findElement(By.xpath("//table/tbody/tr[2]/td[3]")).getText();
			String question3correctness = Driver.getDriver().findElement(By.xpath("//table/tbody/tr[3]/td[3]")).getText();
			String question4correctness = Driver.getDriver().findElement(By.xpath("//table/tbody/tr[4]/td[3]")).getText();
			String question5correctness = Driver.getDriver().findElement(By.xpath("//table/tbody/tr[5]/td[3]")).getText();
			String question6correctness = Driver.getDriver().findElement(By.xpath("//table/tbody/tr[6]/td[3]")).getText();
			Thread.sleep(3000);
			
			if( question1correctness.equals("Correct") && question2correctness.equals("Correct") && question3correctness.equals("Unmarked") && question4correctness.equals("Correct") && question5correctness.equals("Unmarked") && question6correctness.equals("Correct") ) {
				Report.reportStep(Driver.getDriver(), "The student grade is displayed correctly ", "PASS", false);
			} else {
				Report.reportStep(Driver.getDriver(), "The student grade is not displayed correctly", "FAIL", true);
			}
			navigationBar.userLogOut();
		}
	
		public void checkGradebookTest(String userName, String password, String mediaTitle,String quizName, String courseName, String studentName,String marks) throws InterruptedException {
			loginAsCreator(userName, password);
			checkGradebookTestafterLoginforMultiple(mediaTitle,quizName, courseName, studentName,marks);
		}
		
		public void unauthenticatedUserDirectQuizTest(String userName,String password, String mediaTitle,String sa, String fitbans,String courseName,String marks,String studentName, String quizName) throws InterruptedException {
			mediaLibrary.navigateToMyMediaUserLogin(userName, password);
			mediaLibrary.accessMediaMoreMenu(mediaTitle);
			mediaDetailsModal.clickQuizzes();
			sendKeys("Search for quizzes", By.id("quiz-search-input"), quizName);
			mediaDetailsModal.clickQuizLinksdrop();
			String directLink=getQuizDirectLink();
			mediaDetailsModal.clickCloseMoreMenu();
			navigationBar.userLogOut();
			waitForElement(By.id("loginBtn"), 20);
			Driver.getDriver().navigate().to(directLink);
			switchToIframe("switch to video player frame", By.id("yujahtml5playerInVideoPoll"), 10);
			clickPlaybutton();
			Driver.getDriver().switchTo().defaultContent();
			StudentattendallquestionsCorrectly(sa, fitbans);
			Thread.sleep(2000);
			loginAsCreator(userName, password);
			checkGradebookTestafterLoginforMultiple(mediaTitle,quizName, courseName,studentName, marks);
			navigationBar.userLogOut();
		}

		public void unauthenticatedUserEmbeddedQuizTest(String userName,String password, String mediaTitle,String sa, String fitbans,String courseName,String marks,String studentName, String quizName) throws InterruptedException {
			mediaLibrary.navigateToMyMediaUserLogin(userName, password);
			mediaLibrary.accessMediaMoreMenu(mediaTitle);
			mediaDetailsModal.clickQuizzes();
			sendKeys("Search for quizzes", By.id("quiz-search-input"), quizName);
			mediaDetailsModal.clickQuizLinksdrop();
			clickElement("click on embed code",By.xpath("//input[@id='embedCodeInputBox']"),10);
			//Driver.getDriver().findElement(By.xpath("//input[@id='embedCodeInputBox']")).click(); 
			Actions action=new Actions(Driver.getDriver());
			action.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).build().perform();
			action.keyDown(Keys.CONTROL).sendKeys("c").keyUp(Keys.CONTROL).build().perform();
			mediaDetailsModal.clickCloseMoreMenu();
			navigationBar.userLogOut();
			waitForElement(By.id("loginBtn"), 20);
			Driver.getDriver().navigate().to("https://www.w3schools.com/html/tryit.asp?filename=tryhtml_intro");
			Thread.sleep(6000);
			WebElement text= Driver.getDriver().findElement(By.xpath("//div[@class='CodeMirror-code']//child::pre[13]"));
			//text.click();
			clickElement("click on text field",text);
			action.keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL).build().perform();
			Thread.sleep(4000);
			clickElement("run button",By.xpath("//button[@id='runbtn']"),10);
			Thread.sleep(4000);
			switchToIframe("switch to iframe result", By.id("iframeResult"), 10);
			//Driver.getDriver().switchTo().frame("iframeResult");
			Thread.sleep(4000);
			WebElement frame = Driver.getDriver().findElement(By.xpath("//iframe[contains(@src,'"+prop.getProperty("URL")+"/')]"));
			Driver.getDriver().switchTo().frame(frame);
			Thread.sleep(4000);
			switchToIframe("switch to video player frame", By.id("yujahtml5playerInVideoPoll"), 10);
			clickPlaybutton();
			Driver.getDriver().switchTo().parentFrame();
			StudentattendallquestionsCorrectly(sa, fitbans);
			Thread.sleep(2000);
			loginAsCreator(userName, password);
			checkGradebookTestafterLoginforMultiple(mediaTitle,quizName, courseName, studentName,marks);
			navigationBar.userLogOut();
		}
		
		public void deleteQuizTest(String userName, String password, String mediaTitle, String quizName) throws InterruptedException {
			mediaLibrary.navigateToMyMediaUserLogin(userName, password);
			mediaLibrary.accessMediaMoreMenu(mediaTitle);
			mediaDetailsModal.clickQuizzes();
			sendKeys("Search for quizzes", By.id("quiz-search-input"), quizName);
			mediaDetailsModal.clickDeleteQuizButton();
			clickElement("yes",By.xpath("//button[@title='Yes']"),10);
			String x=Driver.getDriver().findElement(By.xpath("//div[@class='toast-message']")).getAttribute("innerHTML");
			
			if( x.equals("Your quiz has been deleted.") ){
				Report.reportStep(Driver.getDriver(), "The quiz is  deleted sucessfully ", "PASS", false);
			} else {
				Report.reportStep(Driver.getDriver(), "TThe quiz is not deleted sucessfully", "FAIL", true);
			}
		}
		
		public void checkPlaybutton(String userName, String password, String mediaTitle) throws InterruptedException {
			mediaLibrary.navigateToMyMediaUserLogin(userName, password);
			WebElement media=getMedia(mediaTitle);
			clickElement("media",media);
			 switchToIframe("switch to video player frame", By.id("ifi_videoPlayerContainer"), 10);
			clickPlaybutton();
			Thread.sleep(12000);
			
			
			
		}

}
