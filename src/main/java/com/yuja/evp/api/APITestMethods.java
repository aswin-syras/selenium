package com.yuja.evp.api;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.yuja.evp.pagehelpers.AdminPanelPlatformPageHelpers;
import com.yuja.evp.reports.Report;
import com.yuja.evp.utilities.Driver;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class APITestMethods extends APIPageHelpers {
	
	private AdminPanelPlatformPageHelpers platform = new AdminPanelPlatformPageHelpers();
	
	public static void main(String[] args) {
	}
	
	public void getUsers(String userName, String password){
		try {
			platform.navigateToAdminPanelPlatformPageUserLogin(userName, password);
			platform.navigateToAPITab();
			generateToken(1);
			WebElement saveButton = waitForElement(By.id("api_saveButton"), 10);
			clickElement("API Save Button", saveButton);
			Thread.sleep(500);
			
			String authToken = getAuthToken(1);
			
			//specify the base URI
			RestAssured.baseURI = "https://test-automation.yuja.com/services/users";
			
			//request object
			RequestSpecification httpRequest = RestAssured.given().header("authToken", authToken);
			
			//response object
			Response response = httpRequest.request(Method.GET);
			
			//print response in console window
			String responseBody = response.getBody().asString();
			
			int statusCode = response.getStatusCode();
			String statusLine = response.getStatusLine();
			String contentType = response.contentType();
			
			if(statusCode == 200) {
				System.out.println("request was succesful");
				System.out.println("status line = " + statusLine);
				System.out.println("content type = " + contentType);
				System.out.println(responseBody);
				Report.reportStep(Driver.getDriver(), "GET request was succesful", "PASS", false);
			}else {
				System.out.println("request was unsuccesful with code = " + statusCode);
				Report.reportStep(Driver.getDriver(), "GET request was unsuccesful", "FAIL", false);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			Report.reportStep(Driver.getDriver(), "The test case did not complete all of its steps", "FAIL", false);
		}
	}
	
}
