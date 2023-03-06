package com.yuja.evp.testclasses;

import com.relevantcodes.extentreports.ExtentTest;
import com.yuja.evp.reports.Report;
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

public class BaseTest extends Report {

	public ITestContext testContext;

	@BeforeMethod(alwaysRun = true)
	@Parameters({ "TestName" })
	public void beforeMethod(String refTestSheetName, Method M, ITestContext context) {

		System.out.println("@BeforeMethod");

		// Sheet reference for test data
		refTestDataName = refTestSheetName;

		// Scenario_Name = context.getCurrentXmlTest().getName().toString();
		Scenario_Name = M.getName();
		Test testRunning = M.getAnnotation(Test.class);
		testDescription = testRunning.description();

		// Creation of Report Steps
		ExtentTest test = startTestCase(Scenario_Name, testDescription);
		test.assignAuthor("EVP_Automated_Test");
		test.assignCategory("Regression_Testing");
		
		launchBrowser(Browser);

	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod() {
		// extentreport.reportend
		System.out.println("@AfterMethod");
		// Complete the test case
		if (driver != null) {
			driver.quit();
		}
		endTestcase();
	}

	@BeforeTest
	public void beforeTest() {
		System.out.println("@BeforeTest");

		// XmlSuite suite = testContext.getSuite().getXmlSuite();
		// System.out.println(testContext.getCurrentXmlTest().getName());
		// Scenario_Name = testContext.getSuite().getName().toString();
		// Get thread count
		// int threads = suite.getThreadCount();
		// System.out.println(suite.getCurrentXmlTest().getSuite().getFileName().length());

	}

	@AfterTest
	public void afterTest() {
		System.out.println("@AfterTest");
		endTestcase();
	}

	@BeforeSuite
	public void beforeSuite() {

		System.out.println("Initiating Automation Suite...");
		// Creating the Test Report
		startResult();
		// Loading the Objects (Page Objects) and variables
		loadObject();
		suiteVariables();
		// Launching the Browser
		//launchBrowser(Browser);
	}

	@AfterSuite
	public void afterSuite() {
		System.out.println("Clearing variables and object memory");
//		if (driver != null) {
//			driver.quit();
//		}
		endResult();
		System.out.println("...Exiting Automation Suite");
		// Displaying the Executed Test Result
		testDisplayResult();
		clearingMemory();
	}

}
