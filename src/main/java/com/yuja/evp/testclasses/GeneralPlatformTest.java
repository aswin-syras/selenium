package com.yuja.evp.testclasses;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.yuja.evp.pagehelpers.HomePageHelpers;
import com.yuja.evp.pagehelpers.MediaChannelPageHelpers;
import com.yuja.evp.pagehelpers.NavigationBarHelpers;
import com.yuja.evp.pagehelpers.SignInPageHelpers;

public class GeneralPlatformTest extends BaseTest{
	
	 @Test(description="Login_Succesful")
	  @Parameters({"TestName"})
	  public void Login_Succesful(String TestName) {
		SignInPageHelpers sp = new SignInPageHelpers();
		MediaChannelPageHelpers mc = new MediaChannelPageHelpers();
			
		try {
			sp.navigateToLoginPage();
			sp.loginFast("manager_leo", "jamNOW123!@#123");
			
			if(mc.fetchedPageTitle()) System.out.println("Login_Succesful test success");
			else throw new Exception("Login_Succesful test fail");
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			reportStep("@Method "+Scenario_Name +" exception to be handled", "Fail", true);
		}
	  }
	 
	 @Test(description="Login_Fail_Message_Check")
	  @Parameters({"TestName"})
	  public void Login_Fail_Message_Check(String TestName) {
		try {
			SignInPageHelpers sp = new SignInPageHelpers();
			
			sp.navigateToLoginPage();
			sp.loginFast("manager_leo", "12345");
			if(sp.checkInvalidCredentialsMessage()) System.out.println("Login_Fail_Message_Check test success");
			else throw new Exception("Login_Fail_Message_Check test fail");
			
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			reportStep("@Method "+Scenario_Name +" exception to be handled", "Fail", true);
		}
	  }
	 
	 @Test(description="Logout_Succesful")
	 @Parameters({"TestName"})
	 public void Logout_Succesful(String TestName){
		 HomePageHelpers hp = new HomePageHelpers();
		 MediaChannelPageHelpers mc = new MediaChannelPageHelpers();
		 NavigationBarHelpers navBar = new NavigationBarHelpers();
		 try {
			 mc.navigateToMediaChannel();
			 navBar.userLogOut();
			 if(hp.fetchedPageTitle()) System.out.println("Logout_Succesful test success");
			 else throw new Exception("Logout_Succesful test fail");
			 
		 } catch(Exception e) {
			 	System.out.println(e.getMessage());
				e.printStackTrace();
				reportStep("@Method "+Scenario_Name +" exception to be handled", "Fail", true);
		 }
	 }
	 
	 @Test(description="Login_Using_SSO_HomePage")
	 @Parameters({"TestName"})
	 public void Login_Using_SSO_HomePage() {
		 HomePageHelpers hp = new HomePageHelpers();
		 try {
			 hp.navigateToHomePage();
			 hp.loginViaSSO();
			 
			 if(hp.fetchedSSOPageTitle()) System.out.println("Login_Using_SSO_HomePage test success");
			 else throw new Exception("Login_Using_SSO_HomePage test fail");
			 
		 } catch(Exception e) {
			 System.out.println(e.getMessage());
				e.printStackTrace();
				reportStep("@Method "+Scenario_Name +" exception to be handled", "Fail", true);
		 }
	 }
	 
	 @Test(description="Login_Using_SSO_LoginPage")
	 @Parameters({"TestName"})
	 public void Login_Using_SSO_LoginPage() {
		 SignInPageHelpers sp = new SignInPageHelpers();
		 try {
			 sp.navigateToLoginPage();
			 sp.loginViaSSO();
			 
			 if(sp.fetchedSSOPageTitle()) System.out.println("Login_Using_SSO_LoginPage test success");
			 else throw new Exception("Login_Using_SSO_LoginPage test fail");
			 
		 } catch(Exception e) {
			 System.out.println(e.getMessage());
				e.printStackTrace();
				reportStep("@Method "+Scenario_Name +" exception to be handled", "Fail", true);
		 }
	 }

}
