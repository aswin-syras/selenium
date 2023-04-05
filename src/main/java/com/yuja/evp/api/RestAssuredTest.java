package com.yuja.evp.api;

import com.yuja.evp.pagehelpers.AdminPanelPlatformPageHelpers;
import com.yuja.evp.utilities.Helpers;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestAssuredTest{
	
	AdminPanelPlatformPageHelpers platform = new AdminPanelPlatformPageHelpers();
	
	public static void main(String[] args) {
		
		getRequestTest();
	}
	
	public static void getRequestTest() {
		
		//specify the base URI
		RestAssured.baseURI = "https://staging-demo.yuja.com/services/users";
		
		//request object
		RequestSpecification httpRequest = RestAssured.given().header("authToken", "d2a5b864815dfd99c6910ef8adf50164");
		
		//response object
		Response response = httpRequest.request(Method.GET);
		
		//print response in console window
		String responseBody = response.getBody().asString();
		
		int statusCode = response.getStatusCode();
		
		String statusLine = response.getStatusLine();
		
		String contentType = response.contentType();
		
		if(statusCode == 200) {
			
			System.out.println("status line = " + statusLine);
			
			System.out.println("content type = " + contentType);
		}else {
			System.out.println("request was unsuccesful with code = " + statusCode);
		}
		
		//httpRequest.header("authToken", "d2a5b864815dfd99c6910ef8adf50164").get("https://staging-demo.yuja.com/services/users").then().log().body();
		
	}

}
