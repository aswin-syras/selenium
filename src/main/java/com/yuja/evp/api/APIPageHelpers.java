package com.yuja.evp.api;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.yuja.evp.exceptions.EndTestException;
import com.yuja.evp.reports.Report;
import com.yuja.evp.utilities.Driver;
import com.yuja.evp.utilities.Helpers;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class APIPageHelpers extends Helpers {
	
	private void generateToken(int tokenNumber) throws InterruptedException {
		WebElement generateButton = waitForElement(By.cssSelector("[onclick=\"institutionCommon.generateAPIToken('APIToken"+tokenNumber+"')\"][title=\"Generate API Token\"]"), 10);
		clickElement("Generate button Token #"+tokenNumber, generateButton);
		WebElement saveButton = waitForElement(By.id("api_saveButton"), 10);
		clickElement("API Save Button", saveButton);
		Thread.sleep(500);
	}
	
	private String getAuthToken(int tokenNumber) throws UnsupportedFlavorException, IOException, InterruptedException {
		WebElement showTokenIcon = waitForElement(By.cssSelector("[id=\"APIToken"+tokenNumber+"_toggle\"]"), 10);
		clickElement("Show icon Token #"+tokenNumber, showTokenIcon);
		Thread.sleep(500);
		
		WebElement copyButton = waitForElement(By.cssSelector("[id=\"copyToken"+tokenNumber+"\"][title=\"Copy Token\"]"), 10);
		clickElement("Copy button Token #"+tokenNumber, copyButton);
		Thread.sleep(500);
		
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		Transferable contents = clipboard.getContents(null);
		String authToken = (String) contents.getTransferData(DataFlavor.stringFlavor);
		
		return authToken;
	}
	
	public String generateAuthToken(int tokenNumber) 
			throws UnsupportedFlavorException, IOException, InterruptedException {
		generateToken(tokenNumber);
		return getAuthToken(tokenNumber);
	}
	
	public void revokeToken(int tokenNumber) {
		WebElement revokeButton = waitForElement(By.cssSelector("[onclick=\"institutionCommon.revokeToken('APIToken"+tokenNumber+"')\"][title=\"Revoke\"]"), 10);
		clickElement("Revoke button Token #"+tokenNumber, revokeButton);
	}
	
	public JSONObject generateUserBody(String email, String firstName, String lastName, String loginId, String userType) {
		JSONObject body = new JSONObject();
		body.put("email_address", email);
		body.put("first_name", firstName);
		body.put("last_name", lastName);
		body.put("login_id", loginId);
		body.put("login_password", "jamNOW123!@#1231");
		body.put("user_type", userType);
		body.put("phone_number", "4167301337");
		return body;
	}
	
	public Map<String, String> generateUploadLinkAndKey(String authToken) throws ParseException {
		RestAssured.baseURI = prop.getProperty("URL") + "/services/media/createuploadlink";
		RequestSpecification httpRequest = RestAssured.given();
		httpRequest.header("authToken", authToken);
		httpRequest.header("Content-Type", "application/json");
		Response response =  httpRequest.request(Method.GET);
		String responseBody = response.getBody().asString();
		if(response.getStatusCode() == 200) {
			JSONParser parser = new JSONParser();
			JSONObject jsonObject = (JSONObject) parser.parse(responseBody);
			Map<String, String> values = new HashMap<String, String>();
			values.put("url", jsonObject.get("url").toString());
			values.put("key", jsonObject.get("key").toString());
			return values;
		}else {
			return null;
		}
	}

}
