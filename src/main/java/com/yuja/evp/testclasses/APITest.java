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
}
