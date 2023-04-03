package com.yuja.evp.testclasses;

import com.relevantcodes.extentreports.ExtentTest;
import com.yuja.evp.reports.Report;
import com.yuja.evp.utilities.Driver;
import com.yuja.evp.utilities.Helpers;
import com.yuja.evp.utilities.Wrapper;

import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;

import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class BaseTest extends Wrapper {
	
	public ITestContext testContext;
	public String testCaseName, testDescription, author, category;

	@BeforeMethod(alwaysRun = true)
	@Parameters({ "TestName" })
	public void beforeMethod(String refTestSheetName, Method M, ITestContext context) {
		
		System.out.println("@BeforeMethod; Current thread: " + Thread.currentThread().getId());

		// Sheet reference for test data
		refTestDataName = refTestSheetName;

		// Scenario_Name = context.getCurrentXmlTest().getName().toString();
		Scenario_Name = M.getName();
		Test testRunning = M.getAnnotation(Test.class);
		testDescription = testRunning.description();

		// Creation of Report Steps
		ExtentTest test = Report.startTestCase(Scenario_Name, testDescription);
		test.assignAuthor("EVP_Automated_Test");
		test.assignCategory("Regression_Testing");
		
		//launchBrowser(Browser);
		Driver.setDriver(config);
	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod() {
		System.out.println("@AfterMethod");
		Driver.closeBrowser();
		Report.endTestcase();
	}

	@BeforeTest
	public void beforeTest() {
		System.out.println("@BeforeTest");
	}

	@AfterTest
	public void afterTest() {
		System.out.println("@AfterTest");
	}

	@BeforeSuite
	public void beforeSuite() {
		System.out.println("@BeforeSuite");
		System.out.println("Initiating the automation test suite");
		
		try {
			Runtime.getRuntime().exec("taskkill /F /IM ChromeDriver.exe");
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		// Creating the Test Report
		Report.startResult();
		
		// Loading the Objects (Page Objects) and variables
		loadConfig();
		loadObject();
		suiteVariables();
	}

	@AfterSuite
	public void afterSuite() {
		System.out.println("@AfterSuite");
		System.out.println("Clearing variables and object memory");

		Report.endResult();
		System.out.println("Exiting the automation test suite");
		
		// Displaying the Executed Test Result
		Report.testDisplayResult();
		clearingMemory();
	}

}
