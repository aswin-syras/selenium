package com.yuja.evp.testclasses;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.yuja.evp.modaltestmethods.MediaDetailsModalTestMethods;
import com.yuja.evp.reports.Report;
import com.yuja.evp.utilities.Driver;

public class MediaDetailsModalTest extends BaseTest {

	@Test(description="Replace_media_test")
	  @Parameters({"TestName"})
	  public void Replace_media_test(String testName) {
		MediaDetailsModalTestMethods mediadetails = new MediaDetailsModalTestMethods();
		try {
			mediadetails.replaceMedia(prop.getProperty("ManagerID"), prop.getProperty("Password"), "ReplaceVideo", "ReplacedWith", prop.getProperty("InstructorID"), "automation instructor",  prop.getProperty("Password"), "Automation 1337");
		}catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			Report.reportStep(Driver.getDriver(), "The test case did not complete all of its steps", "FAIL", false);
		}
	 }	
}
