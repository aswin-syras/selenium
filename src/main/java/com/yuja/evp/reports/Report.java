package com.yuja.evp.reports;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.yuja.evp.utilities.Wrapper;

public class Report extends Wrapper{

	public static ExtentTest test = null;
	public static ExtentReports extent;
	public String testCaseName, testDescription, author, category;
	public static String reportFileName;

	public ExtentReports startResult() {
		try {
			Date dtTimeStamp = new Date();
			reportFileName = "EVP_Execution_Report_" + dtTimeStamp.toString().replaceAll(":", "_").replaceAll(" ", "_");
			extent = new ExtentReports("./reports/" + reportFileName + ".html", true);
			// extent.loadConfig(new File("./extent-config.xml"));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return extent;
	}

	public ExtentTest startTestCase(String testCaseName, String testDescription) {
		test = extent.startTest(testCaseName, testDescription);
		return test;
	}

	public void endResult() {
		extent.flush();
	}

	public void endTestcase() {
		extent.endTest(test);
		test = null;
	}

	public void testDisplayResult() {
		try {

			String browser = "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe ";
			Runtime runtime = Runtime.getRuntime();
			File resFile = new File("./reports/" + reportFileName + ".html");
			/*
			 * boolean mail=true; if (mail==true) { Mail.sendmail(); }
			 */
			runtime.exec(browser + (resFile.getAbsolutePath()));

		}

		catch (Exception e) {
			System.err.println("Result file opening failed: " + reportFileName);
		}
	}

	public void reportStep(String desc, String status, boolean screenShot) {
		String strImagePath = "";

		if (desc.contains("#B")) {
			desc = desc.replace("#B", "<B><Font size=\"+1\">");
			if (desc.contains("#C")) {
				desc = desc.replace("#C", "</Font></B>");
			} else {
				desc = desc + "</Font></B>";
			}
		}

		long snapNumber = 100000l;
		try {
			if (screenShot == true) {

				snapNumber = takeSnap();
				File imgPath = new File("./reports/images/" + snapNumber + ".png");
				strImagePath = test.addScreenCapture(imgPath.getAbsolutePath());
			}

			switch (status.toUpperCase()) {
			case "PASS":
				test.log(LogStatus.PASS, desc + strImagePath);
				break;

			case "FAIL":
				test.log(LogStatus.FAIL, desc + strImagePath);
				// throw new RuntimeException("FAILED");
				break;

			case "INFO":
				test.log(LogStatus.INFO, desc + strImagePath);
				break;

			case "WARN":
			case "WARNING":
				test.log(LogStatus.WARNING, desc + strImagePath);
				break;

			case "ERROR":
				test.log(LogStatus.ERROR, desc + strImagePath);
				break;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public long takeSnap() {
		long number = (long) Math.floor(Math.random() * 900000000L) + 10000000L;
		try {

			File screenShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

			FileUtils.copyFile(screenShot, new File("./reports/images/" + number + ".png"));

		} catch (WebDriverException e) {
			reportStep("The browser has been closed.", "FAIL", false);
		} catch (IOException e) {
			reportStep("The snapshot could not be taken", "WARN", false);
		}
		return number;
	}

}
