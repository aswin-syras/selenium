package com.yuja.evp.testclasses;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.yuja.evp.pagehelpers.NavigationBarHelpers;
import com.yuja.evp.pagehelpers.QuizPageHelpers;
import com.yuja.evp.pagetestmethods.QuizPageTestMethods;

public class QuizTest extends BaseTest {
	NavigationBarHelpers navigationBar = new NavigationBarHelpers();
	
	@Test(description="Create_quiz_test")
	  @Parameters({"TestName"})
	public void Create_quiz_test(String TestName) {
		QuizPageTestMethods qp = new QuizPageTestMethods();
		try {
			qp.createPublishandAttendQuizTest("automation_manager", "jamNOW123!@#123", "editAccessSharedVideoManager", "autoquiz004","color of apple","red", "black","red","black","hello","automation_Student","jamNOW123!@#123","red","red");
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
			qp.checkStudentgradeBookTest("automation_manager", "jamNOW123!@#123", "editAccessSharedVideoManager", "1/5/23","automation_Student","jamNOW123!@#123","Automation 1337", "autoquiz004");
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
			qp.checkGradebookTest("automation_manager", "jamNOW123!@#123", "editAccessSharedVideoManager", "Automation 1337", "autoquiz004","100%","student automation");
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
			qp. unauthenticatedUserDirectQuizTest("automation_manager", "jamNOW123!@#123", "editAccessSharedVideoManager","red","red","Unauthenticated Users","autoquiz004","100%","Anonymous-1");
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
			qp. unauthenticatedUserEmbeddedQuizTest("automation_manager", "jamNOW123!@#123", "editAccessSharedVideoManager","red", "red", "Unauthenticated Users", "autoquiz004", "100%", "Anonymous-2");
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
			qp.deleteQuizTest("automation_manager", "jamNOW123!@#123", "editAccessSharedVideoManager");
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			reportStep("@Method "+Scenario_Name +" exception to be handled", "FAIL", true);
		}
	  }
}

