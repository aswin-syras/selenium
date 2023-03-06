package com.yuja.evp.testclasses;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.yuja.evp.pagetestmethods.MediaPlayerSideBarTestMethods;

//Sidebar
public class HTML5PlayerTest extends BaseTest {

		// TODO Auto-generated method stub
		@Test(description="Sidebar_manager_test")
		  @Parameters({"TestName"})
		  public void Sidebar_manager_test(String testName) {
			MediaPlayerSideBarTestMethods sidebarObj = new MediaPlayerSideBarTestMethods();
			try {
				sidebarObj.SidebarFunctionalityTest("automation_manager", "jamNOW123!@#123", "test");
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
		
		
	}
