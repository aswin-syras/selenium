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

public class Report {

	private static final ThreadLocal<ExtentTest> extent_tests = new ThreadLocal<ExtentTest>();
	public static ExtentReports extent;
	public static String reportFileName;
	
	private Report() {};

	public static ExtentReports startResult() {
		try {
			Date dtTimeStamp = new Date();
			reportFileName = "EVP_Execution_Report_" + dtTimeStamp.toString().replaceAll(":", "_").replaceAll(" ", "_");
			extent = new ExtentReports("./reports/" + reportFileName + ".html", true);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return extent;
	}

	public static ExtentTest startTestCase(String testCaseName, String testDescription) {
		//test = extent.startTest(testCaseName, testDescription);
		extent_tests.set(extent.startTest(testCaseName, testDescription));
		//test = null;
		return extent_tests.get();
	}

	public static void endResult() {
		extent.flush();
	}

	public static void endTestcase() {
		extent.endTest(extent_tests.get());
		extent_tests.remove();
	}

	public static void testDisplayResult() {
		try {
			String browser = "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe ";
			Runtime runtime = Runtime.getRuntime();
			File resFile = new File("./reports/" + reportFileName + ".html");
			runtime.exec(browser + (resFile.getAbsolutePath()));
		} catch (Exception e) {
			System.err.println("Result file opening failed: " + reportFileName);
		}
	}

	public static void reportStep(WebDriver driver, String desc, String status, boolean screenShot) {
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

				snapNumber = takeSnap(driver);
				File imgPath = new File("./reports/images/" + snapNumber + ".png");
				strImagePath = extent_tests.get().addScreenCapture(imgPath.getAbsolutePath());
			}
			switch (status.toUpperCase()) {
			case "PASS":
				extent_tests.get().log(LogStatus.PASS, desc + strImagePath);
				break;
			case "FAIL":
				extent_tests.get().log(LogStatus.FAIL, desc + strImagePath);
				// throw new RuntimeException("FAILED");
				break;
			case "INFO":
				extent_tests.get().log(LogStatus.INFO, desc + strImagePath);
				break;
			case "WARN":
			case "WARNING":
				extent_tests.get().log(LogStatus.WARNING, desc + strImagePath);
				break;
			case "ERROR":
				extent_tests.get().log(LogStatus.ERROR, desc + strImagePath);
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static long takeSnap(WebDriver driver) {
		long number = (long) Math.floor(Math.random() * 900000000L) + 10000000L;
		try {
			File screenShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenShot, new File("./reports/images/" + number + ".png"));
		} catch (WebDriverException e) {
			reportStep(driver, "The browser has been closed.", "FAIL", false);
		} catch (IOException e) {
			reportStep(driver, "The snapshot could not be taken", "WARN", false);
		}
		return number;
	}

}
