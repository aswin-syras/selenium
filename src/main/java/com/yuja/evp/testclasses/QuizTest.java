package com.yuja.evp.testclasses;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.yuja.evp.pagehelpers.NavigationBarHelpers;
import com.yuja.evp.pagehelpers.QuizPageHelpers;
import com.yuja.evp.pagetestmethods.QuizPageTestMethods;
import com.yuja.evp.reports.Report;
import com.yuja.evp.utilities.Driver;

public class QuizTest extends BaseTest {
	
	
	final String STUDENT_NAME = "Student Automation";
	final String VIDEO_NAME = "quizvideo";
	final String QUIZ_NAME = "autoquiz";
	final String QUIZ_QUESTION = "color of apple";
	final String OPTION_1 = "red";
	final String OPTION_2 = "black";
	final String FITBPOSSIBLEANS_1 = "red";
	final String FITBPOSSIBLEANS_2 = "black";
	final String HINT = "hello";
	final String STUDENT_SHORTANSWER = "red";
	final String STUDENT_FITBANS = "red";
	final String QUIZ_PUBLISH_COURSE_NAME = "Automation 1337";
	
	NavigationBarHelpers navigationBar = new NavigationBarHelpers();
	
	@Test(description="Create_quiz_test")
	  @Parameters({"TestName"})
	public void Create_quiz_test(String TestName) {
		QuizPageTestMethods qp = new QuizPageTestMethods();
		try {
			qp.createPublishandAttendQuizTest(prop.getProperty("ManagerID"), prop.getProperty("Password"), VIDEO_NAME, QUIZ_NAME,QUIZ_QUESTION,OPTION_1, OPTION_2,FITBPOSSIBLEANS_1,FITBPOSSIBLEANS_2,HINT,prop.getProperty("StudentID"), prop.getProperty("Password"),STUDENT_SHORTANSWER,STUDENT_FITBANS,QUIZ_PUBLISH_COURSE_NAME);
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			Report.reportStep(Driver.getDriver(), "@Method "+Scenario_Name +" exception to be handled", "FAIL", true);
		}
	  }

	@Test(description="Check_student_gradebook_test")
	  @Parameters({"TestName"})
	public void Check_student_gradebook_test(String TestName) {
		QuizPageTestMethods qp = new QuizPageTestMethods();
		try {
			qp.checkStudentgradeBookTest(prop.getProperty("ManagerID"), prop.getProperty("Password"), VIDEO_NAME, prop.getProperty("StudentID"), prop.getProperty("Password"),QUIZ_PUBLISH_COURSE_NAME, QUIZ_NAME);
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			Report.reportStep(Driver.getDriver(), "@Method "+Scenario_Name +" exception to be handled", "FAIL", true);
		}
	  }

	@Test(description="Check_instructor_gradebook_test")
	  @Parameters({"TestName"})
	public void Check_instructor_gradebook_test(String TestName) {
		QuizPageTestMethods qp = new QuizPageTestMethods();
		try {
			qp.checkGradebookTest(prop.getProperty("ManagerID"), prop.getProperty("Password"), VIDEO_NAME, QUIZ_NAME, QUIZ_PUBLISH_COURSE_NAME, STUDENT_NAME, "100%");
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			Report.reportStep(Driver.getDriver(), "@Method "+Scenario_Name +" exception to be handled", "FAIL", true);
		}
	  }
	
	@Test(description="Check_unauthenticated_user_quiz_test")
	  @Parameters({"TestName"})
	public void Check_unauthenticated_user_quiz_test(String TestName) {
		QuizPageTestMethods qp = new QuizPageTestMethods();
		try {
			qp. unauthenticatedUserDirectQuizTest(prop.getProperty("ManagerID"), prop.getProperty("Password"), VIDEO_NAME,STUDENT_SHORTANSWER,STUDENT_FITBANS,"Unauthenticated Users","100%","Anonymous-1",QUIZ_NAME);
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			Report.reportStep(Driver.getDriver(), "@Method "+Scenario_Name +" exception to be handled", "FAIL", true);
		}
	  }
	
	@Test(description="Check_Unauthenticated_user_embedded_quiz_test")
	  @Parameters({"TestName"})
	public void Check_Unauthenticated_user_embedded_quiz_test(String TestName) {
		QuizPageTestMethods qp = new QuizPageTestMethods();
		try {
			qp. unauthenticatedUserEmbeddedQuizTest(prop.getProperty("ManagerID"), prop.getProperty("Password"), VIDEO_NAME,STUDENT_SHORTANSWER,STUDENT_FITBANS, "Unauthenticated Users", "100%", "Anonymous-2",QUIZ_NAME);
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			Report.reportStep(Driver.getDriver(), "@Method "+Scenario_Name +" exception to be handled", "FAIL", true);
		}
	  }
	
	@Test(description="Check_delete_quiz_test")
	  @Parameters({"TestName"})
	public void Check_delete_quiz_test(String TestName) {
		QuizPageTestMethods qp = new QuizPageTestMethods();
		try {
			qp.deleteQuizTest(prop.getProperty("ManagerID"), prop.getProperty("Password"), VIDEO_NAME,QUIZ_NAME);
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			Report.reportStep(Driver.getDriver(), "@Method "+Scenario_Name +" exception to be handled", "FAIL", true);
		}
	  }
	
	@Test(description="Check_")
	  @Parameters({"TestName"})
	public void CheckPlaybutton(String TestName) {
		QuizPageTestMethods qp = new QuizPageTestMethods();
		try {
			qp.checkPlaybutton(prop.getProperty("ManagerID"), prop.getProperty("Password"), VIDEO_NAME);
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			Report.reportStep(Driver.getDriver(), "@Method "+Scenario_Name +" exception to be handled", "FAIL", true);
		}
	  }
}

