package com.yuja.evp.api;

import java.io.File;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.yuja.evp.exceptions.EndTestException;
import com.yuja.evp.pagehelpers.AdminPanelPlatformPageHelpers;
import com.yuja.evp.pagehelpers.AdminPanelRosterPageHelpers;
import com.yuja.evp.reports.Report;
import com.yuja.evp.utilities.Driver;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class APITestMethods extends APIPageHelpers {
	
	private AdminPanelPlatformPageHelpers platformPage = new AdminPanelPlatformPageHelpers();
	private AdminPanelRosterPageHelpers rosterPage = new AdminPanelRosterPageHelpers();
	
	public static void main(String[] args) {
	}
	
	public void getUsers(String userName, String password){
		try {
			platformPage.navigateToAdminPanelPlatformPageUserLogin(userName, password);
			platformPage.navigateToAPITab();
			
			String authToken = generateAuthToken(1);
			
			////////////////////////////////////
			//specify the base URI
			RestAssured.baseURI = prop.getProperty("URL") + "/services/users";
			//request object
			RequestSpecification httpRequest = RestAssured.given().header("authToken", authToken);
			//response object
			Response response = httpRequest.request(Method.GET);
			///////////////////////////////////////
		
			//print response in console window
			String responseBody = response.getBody().asString();
			int statusCode = response.getStatusCode();
			String statusLine = response.getStatusLine();
			String contentType = response.contentType();
			
			if(statusCode == 200) {
				System.out.println(responseBody);
				Report.reportStep(Driver.getDriver(), "Status : " + statusLine + ". GET request succesful", "PASS", false);
			}else {
				Report.reportStep(Driver.getDriver(), "Status : " + statusLine + ". GET request unsuccesful", "FAIL", false);
			}
		} catch(Exception e) {
			e.printStackTrace();
			Report.reportStep(Driver.getDriver(), "The test case did not complete all of its steps", "FAIL", false);
		}
	}
	
	public void createUser(String userName, String password) {
		try {
			platformPage.navigateToAdminPanelPlatformPageUserLogin(userName, password);
			platformPage.navigateToAPITab();
			
			String authToken = generateAuthToken(1);
			//specify the base URI
			RestAssured.baseURI = prop.getProperty("URL") + "/services/users";
			//request object
			RequestSpecification httpRequest = RestAssured.given().header("authToken", authToken);
			httpRequest.header("Content-Type", "application/json;charset=UTF-8");
			
			String userId = "testUserAPI"+getRandomInteger(10000);
			JSONObject requestBody = generateUserBody("leonardo.hernandez@yuja.com", "Dummy", "UserAPI", userId, "Instructor");
			//httpRequest.header("Content-Type", "application/json");
			httpRequest.body(requestBody.toJSONString());
			
			//response object
			Response response = httpRequest.request(Method.POST);
			
			String statusLine = response.getStatusLine();
			String message = response.asPrettyString();
			
			if(response.getStatusCode() == 200) {
				Report.reportStep(Driver.getDriver(), "Status : " + statusLine + ". " + message, "PASS", false);
			}else {
				Report.reportStep(Driver.getDriver(), "Status : " + statusLine + ". " + message, "FAIL", false);
			}
			rosterPage.navigateToAdminPanelRosterPage();
			Thread.sleep(1000);
			if(rosterPage.userExists(userId)) {
				Report.reportStep(Driver.getDriver(), "The user exists", "PASS", false);
			} else {
				Report.reportStep(Driver.getDriver(), "The user does not exist", "FAIL", true);
			}
		} catch(Exception e) {
			e.printStackTrace();
			Report.reportStep(Driver.getDriver(), "The test case did not complete all of its steps", "FAIL", false);
		}
	}
	
	public void uploadMediaAPI(String userName, String password) {
		try {
			platformPage.navigateToAdminPanelPlatformPageUserLogin(userName, password);
			platformPage.navigateToAPITab();

			String authToken = generateAuthToken(1);
			String uploadLink = generateUploadLinkAndKey(authToken).get("url");
			
			if(uploadLink != null) {
				Report.reportStep(Driver.getDriver(), "Upload link was succesfully generated", "PASS", false);
			}else {
				Report.reportStep(Driver.getDriver(), "Upload link was not succesfully generated", "FAIL", false);
				throw new EndTestException();
			}
			
			RestAssured.baseURI = uploadLink;
			RequestSpecification httpRequest = RestAssured.given();
			httpRequest.multiPart("file", new File(""), "text/html");
			
			
		} catch(Exception e) {
			e.printStackTrace();
			Report.reportStep(Driver.getDriver(), e.getMessage(), "FAIL", false);
		}
		
	}
	
}
