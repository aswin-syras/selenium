package com.yuja.evp.testclasses;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.yuja.evp.pagetestmethods.MediaPlayerSideBarTestMethods;

//Sidebar
public class HTML5PlayerTest extends BaseTest {
	
	
//	String managerUserName = prop.getProperty("UserID");
//	String password = prop.getProperty("Password");

		// TODO Auto-generated method stub
		@Test(description="Sidebar_manager_test")
		  @Parameters({"TestName"})
		  public void Sidebar_manager_test(String testName) {
			MediaPlayerSideBarTestMethods sidebarObj = new MediaPlayerSideBarTestMethods();
			//System.out.println(managerUserName);
			try {
				sidebarObj.SidebarFunctionalityTest("automation_manager", "jamNOW123!@#123", "test");
				//sidebarObj.SidebarFunctionalityTest(managerUserName, password, "test");
				Thread.sleep(2000);
			}catch(Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		 }	
		
		@Test(description="Sidebar_student_test")
		  @Parameters({"TestName"})
		  public void Sidebar_student_test(String testName) {
			MediaPlayerSideBarTestMethods sidebarObj = new MediaPlayerSideBarTestMethods();
			try {
				sidebarObj.SidebarFunctionalityTest("automation_student", "jamNOW123!@#123", "test");
				Thread.sleep(2000);
			}catch(Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		 }	
		
		@Test(description="Sidebar_instructor_test")
		  @Parameters({"TestName"})
		  public void Sidebar_instructor_test(String testName) {
			MediaPlayerSideBarTestMethods sidebarObj = new MediaPlayerSideBarTestMethods();
			try {
				sidebarObj.SidebarFunctionalityTest("automation_instructor", "jamNOW123!@#123", "test");
				Thread.sleep(2000);
			}catch(Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		 }	
		
		@Test(description="Side_bar_gearicon_ui_test")
		  @Parameters({"TestName"})
		  public void Side_bar_gearicon_ui_test(String testName) {
			MediaPlayerSideBarTestMethods sidebarObj = new MediaPlayerSideBarTestMethods();
			try {
				sidebarObj.gear_icon("automation_manager", "jamNOW123!@#123", "test");
				Thread.sleep(2000);
			}catch(Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		 }	
		
		
	}

