package com.yuja.evp.testclasses;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.yuja.evp.pagehelpers.NavigationBarHelpers;
import com.yuja.evp.pagehelpers.QuizPageHelpers;
import com.yuja.evp.pagetestmethods.QuizPageTestMethods;

public class QuizTest extends BaseTest {
	
	final String PASSWORD = "jamNOW123!@#123";
	final String MANAGER_USER = "automation_manager";
	final String INSTRUCTOR_USER = "automation_instructor";
	final String STUDENT_USER = "automation_student";
	final String STUDENT_NAME = "Student Automation";
	final String VIDEO_NAME = "editAccessSharedVideoManager";
	final String QUIZ_NAME = "autoquiz";
	final String QUIZ_QUESTION = "color of apple";
	final String OPTION_1 = "red";
	final String OPTION_2 = "black";
	final String FITBPOSSIBLEANS_1 = "red";
	final String FITBPOSSIBLEANS_2 = "black";
	final String HINT = "hello";
	final String STUDENT_SHORTANSWER = "red";
	final String STUDENT_FITBANS = "red";
	final String QUIZ_CLOSEDATE = "1/5/23";
	final String QUIZ_PUBLISH_COURSE_NAME = "Automation 1337";
	
	NavigationBarHelpers navigationBar = new NavigationBarHelpers();
	
	@Test(description="Create_quiz_test")
	  @Parameters({"TestName"})
	public void Create_quiz_test(String TestName) {
		QuizPageTestMethods qp = new QuizPageTestMethods();
		try {
			qp.createPublishandAttendQuizTest(MANAGER_USER, PASSWORD, VIDEO_NAME, QUIZ_NAME,QUIZ_QUESTION,OPTION_1, OPTION_2,FITBPOSSIBLEANS_1,FITBPOSSIBLEANS_2,HINT,STUDENT_USER,PASSWORD,STUDENT_SHORTANSWER,STUDENT_FITBANS);
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			reportStep("@Method "+Scenario_Name +" exception to be handled", "FAIL", true);
		}
	  }

	@Test(description="Check_student_gradebook_test")
	  @Parameters({"TestName"})
	public void Check_student_gradebook_test(String TestName) {
		QuizPageTestMethods qp = new QuizPageTestMethods();
		try {
			qp.checkStudentgradeBookTest(MANAGER_USER, PASSWORD, VIDEO_NAME, QUIZ_CLOSEDATE,STUDENT_USER,PASSWORD,QUIZ_PUBLISH_COURSE_NAME, QUIZ_NAME);
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			reportStep("@Method "+Scenario_Name +" exception to be handled", "FAIL", true);
		}
	  }

	@Test(description="Check_instructor_gradebook_test")
	  @Parameters({"TestName"})
	public void Check_instructor_gradebook_test(String TestName) {
		QuizPageTestMethods qp = new QuizPageTestMethods();
		try {
			qp.checkGradebookTest(MANAGER_USER, PASSWORD, VIDEO_NAME, QUIZ_PUBLISH_COURSE_NAME, QUIZ_NAME,"100%",STUDENT_NAME);
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			reportStep("@Method "+Scenario_Name +" exception to be handled", "FAIL", true);
		}
	  }
	
	@Test(description="Check_unauthenticated_user_quiz_test")
	  @Parameters({"TestName"})
	public void Check_unauthenticated_user_quiz_test(String TestName) {
		QuizPageTestMethods qp = new QuizPageTestMethods();
		try {
			qp. unauthenticatedUserDirectQuizTest(MANAGER_USER, PASSWORD, VIDEO_NAME,STUDENT_SHORTANSWER,STUDENT_FITBANS,"Unauthenticated Users",QUIZ_NAME,"100%","Anonymous-1");
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			reportStep("@Method "+Scenario_Name +" exception to be handled", "FAIL", true);
		}
	  }
	
	@Test(description="Check_Unauthenticated_user_embedded_quiz_test")
	  @Parameters({"TestName"})
	public void Check_Unauthenticated_user_embedded_quiz_test(String TestName) {
		QuizPageTestMethods qp = new QuizPageTestMethods();
		try {
			qp. unauthenticatedUserEmbeddedQuizTest(MANAGER_USER, PASSWORD, VIDEO_NAME,STUDENT_SHORTANSWER,STUDENT_FITBANS, "Unauthenticated Users", QUIZ_NAME, "100%", "Anonymous-2");
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			reportStep("@Method "+Scenario_Name +" exception to be handled", "FAIL", true);
		}
	  }
	
	@Test(description="Check_delete_quiz_test")
	  @Parameters({"TestName"})
	public void Check_delete_quiz_test(String TestName) {
		QuizPageTestMethods qp = new QuizPageTestMethods();
		try {
			qp.deleteQuizTest(MANAGER_USER, PASSWORD, VIDEO_NAME);
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			reportStep("@Method "+Scenario_Name +" exception to be handled", "FAIL", true);
		}
	  }
	
	@Test(description="Check_playButton")
	  @Parameters({"TestName"})
	public void CheckPlaybutton(String TestName) {
		QuizPageTestMethods qp = new QuizPageTestMethods();
		try {
			qp.checkPlaybutton(MANAGER_USER, PASSWORD, VIDEO_NAME);
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			reportStep("@Method "+Scenario_Name +" exception to be handled", "FAIL", true);
		}
	  }
}

