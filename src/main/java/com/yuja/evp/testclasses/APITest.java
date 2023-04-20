package com.yuja.evp.testclasses;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.yuja.evp.api.APITestMethods;

public class APITest extends BaseTest{
	
	@Test(description="Get_users_api_test")
	  @Parameters({"TestName"})
	  public void Get_users_api_test(String testName) {
		APITestMethods api = new APITestMethods();
		api.getUsers(prop.getProperty("ManagerID"), prop.getProperty("Password"));
	 }
	
	@Test(description="Create_user_api_test")
	  @Parameters({"TestName"})
	  public void Create_user_api_test(String testName) {
		APITestMethods api = new APITestMethods();
		api.createUser(prop.getProperty("ManagerID"), prop.getProperty("Password"));
	 }
	
	@Test(description="Upload_media_api_test")
	  @Parameters({"TestName"})
	  public void Upload_media_api_test(String testName) {
		APITestMethods api = new APITestMethods();
		api.uploadMediaAPI(prop.getProperty("ManagerID"), prop.getProperty("Password"));
	 }
}
