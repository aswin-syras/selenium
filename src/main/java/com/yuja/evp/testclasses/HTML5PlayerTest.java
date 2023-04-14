package com.yuja.evp.testclasses;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.yuja.evp.pagetestmethods.MediaPlayerSideBarTestMethods;
import com.yuja.evp.reports.Report;
import com.yuja.evp.utilities.Driver;

//Sidebar
public class HTML5PlayerTest extends BaseTest {
	
		// TODO Auto-generated method stub
		@Test(description="Sidebar_manager_test")
		  @Parameters({"TestName"})
		  public void Sidebar_manager_test(String testName) {
			MediaPlayerSideBarTestMethods sidebarObj = new MediaPlayerSideBarTestMethods();			
			try {
				sidebarObj.SidebarFunctionalityTest(prop.getProperty("ManagerID"), prop.getProperty("Password"), "test");
				Thread.sleep(2000);
			}catch(Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
				Report.reportStep(Driver.getDriver(), "The test case did not complete all of its steps", "FAIL", false);
			}
		 }	
		
		@Test(description="Sidebar_student_test")
		  @Parameters({"TestName"})
		  public void Sidebar_student_test(String testName) {
			MediaPlayerSideBarTestMethods sidebarObj = new MediaPlayerSideBarTestMethods();
			try {
				sidebarObj.SidebarFunctionalityTest(prop.getProperty("ManagerID"), prop.getProperty("Password"), "test");
				Thread.sleep(2000);
			}catch(Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
				Report.reportStep(Driver.getDriver(), "The test case did not complete all of its steps", "FAIL", false);
			}
		 }	
		
		@Test(description="Sidebar_instructor_test")
		  @Parameters({"TestName"})
		  public void Sidebar_instructor_test(String testName) {
			MediaPlayerSideBarTestMethods sidebarObj = new MediaPlayerSideBarTestMethods();
			try {
				sidebarObj.SidebarFunctionalityTest(prop.getProperty("StudentID"), prop.getProperty("Password"), "test");
				Thread.sleep(2000);
			}catch(Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
				Report.reportStep(Driver.getDriver(), "The test case did not complete all of its steps", "FAIL", false);
			}
		 }	
		
		@Test(description="Side_bar_gearicon_ui_test")
		  @Parameters({"TestName"})
		  public void Side_bar_gearicon_ui_test(String testName) {
			MediaPlayerSideBarTestMethods sidebarObj = new MediaPlayerSideBarTestMethods();
			try {
				//sidebarObj.gear_icon(prop.getProperty("UserID"), prop.getProperty("Password"), "test");
				Thread.sleep(2000);
			}catch(Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
				Report.reportStep(Driver.getDriver(), "The test case did not complete all of its steps", "FAIL", false);
			}
		 }	
		
		
	}

