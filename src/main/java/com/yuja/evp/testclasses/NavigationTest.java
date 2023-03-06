// To Do : add all cases in 1 test run  

package com.yuja.evp.testclasses;

import org.openqa.selenium.By;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.yuja.evp.pagehelpers.HomePageHelpers;
import com.yuja.evp.pagehelpers.MediaChannelPageHelpers;
import com.yuja.evp.pagehelpers.NavigationBarHelpers;
import com.yuja.evp.pagehelpers.SignInPageHelpers;
import com.yuja.evp.pagetestmethods.MediaLibraryPageTestMethods;

public class NavigationTest extends BaseTest {
	
	@Test(description="GotoAdminPanel")
	@Parameters({"TestName"})
	
	public void GotoAdminPanel(String TestName) {
		
		MediaChannelPageHelpers mc = new MediaChannelPageHelpers();
		MediaLibraryPageTestMethods ml = new MediaLibraryPageTestMethods();
		NavigationBarHelpers navBar = new NavigationBarHelpers();
		
		try {
			ml.navigateToMyMediaUserLogin("automation_mananger", "JameNOW123!@#123");
			navBar.clickAdminPanel();
			
			if(mc.fetchedPageTitle()) System.out.println("GotoAdminPanel test success but this test is not completed");
			else throw new Exception("GotoAdminPanel test fail");
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			reportStep("@Method "+Scenario_Name +" exception to be handled", "Fail", true);
		}	
		
	}
	
	@Test(description="GotoCoursesandGroups")
	@Parameters({"TestName"})
	
	public void GotoCoursesandGroups(String TestName) {

		MediaChannelPageHelpers mc = new MediaChannelPageHelpers();
		NavigationBarHelpers navBar = new NavigationBarHelpers();
		MediaLibraryPageTestMethods ml = new MediaLibraryPageTestMethods();
		
		try {
			ml.navigateToMyMediaUserLogin("automation_mananger", "JameNOW123!@#123");
			mc.waitForElement(By.id("mainSearchBox"),5000) ;
			navBar.clickCoursesAndGroups();
			
			if(mc.fetchedPageTitle()) System.out.println("GotoCoursesandGroups test success but this test is not completed");
			else throw new Exception("GotoCoursesandGroups test fail");
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			reportStep("@Method "+Scenario_Name +" exception to be handled", "Fail", true);
		}	
		
	}
	
	@Test(description="GotoHimalayas")
	@Parameters({"TestName"})
	
	public void GotoHimalayas(String TestName) {
		
		MediaChannelPageHelpers mc = new MediaChannelPageHelpers();
		NavigationBarHelpers navBar = new NavigationBarHelpers();
		MediaLibraryPageTestMethods ml = new MediaLibraryPageTestMethods();
		
		try {
			ml.navigateToMyMediaUserLogin("automation_mananger", "JameNOW123!@#123");
			mc.waitForElement(By.id("mainSearchBox"),5000) ;
			navBar.clickHimalayas();
			
			if(mc.fetchedPageTitle()) System.out.println("GotoHimalayas test success but this test is not completed");
			else throw new Exception("GotoHimalayas test fail");
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			reportStep("@Method "+Scenario_Name +" exception to be handled", "Fail", true);
		}		
		
	}
	
		
	@Test(description="GotoUnA")
	@Parameters({"TestName"})
	
	public void GotoUnA(String TestName) {
		
		MediaChannelPageHelpers mc = new MediaChannelPageHelpers();
		NavigationBarHelpers navBar = new NavigationBarHelpers();
		MediaLibraryPageTestMethods ml = new MediaLibraryPageTestMethods();

		
		try {
			ml.navigateToMyMediaUserLogin("automation_mananger", "JameNOW123!@#123");
			mc.waitForElement(By.id("mainSearchBox"),5000) ;
			navBar.clickUsageAndAnalytics();
			
			if(mc.fetchedPageTitle()) System.out.println("GotoUnA test success but this test is not completed");
			else throw new Exception("GotoUnA test fail");
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			reportStep("@Method "+Scenario_Name +" exception to be handled", "Fail", true);
		}		
		
	}
		

	@Test(description="GotoDiscussFeed")
	@Parameters({"TestName"})
	
	public void GotoDiscussFeed(String TestName) {
		
		MediaChannelPageHelpers mc = new MediaChannelPageHelpers();
		NavigationBarHelpers navBar = new NavigationBarHelpers();
		MediaLibraryPageTestMethods ml = new MediaLibraryPageTestMethods();
		
		try {
			ml.navigateToMyMediaUserLogin("automation_mananger", "JameNOW123!@#123");
			mc.waitForElement(By.id("mainSearchBox"),5000) ;
			navBar.clickDiscussionFeed();
			
			if(mc.fetchedPageTitle()) System.out.println("GotoDiscussFeed test success but this test is not completed");
			else throw new Exception("GotoDiscussFeed test fail");
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			reportStep("@Method "+Scenario_Name +" exception to be handled", "Fail", true);
		}		
		
	}	
	
	
}
