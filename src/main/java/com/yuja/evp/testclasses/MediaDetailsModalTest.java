package com.yuja.evp.testclasses;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.yuja.evp.modaltestmethods.MediaDetailsModalTestMethods;

public class MediaDetailsModalTest extends BaseTest {
	
	final String PASSWORD = "jamNOW123!@#123";
	final String MANAGER_USER = "automation_manager";
	final String INSTRUCTOR_USER = "automation_instructor";
	final String STUDENT_USER = "automation_student";

	@Test(description="Replace_media_test")
	  @Parameters({"TestName"})
	  public void Replace_media_test(String testName) {
		MediaDetailsModalTestMethods mediadetails = new MediaDetailsModalTestMethods();
		try {
			mediadetails.replaceMedia(prop.getProperty("ManagerID"), prop.getProperty("Password"), "ReplaceVideo", "ReplacedWith", INSTRUCTOR_USER, "automation instructor", PASSWORD, "Automation 1337");
		}catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	 }	
}
