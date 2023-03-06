package com.yuja.evp.testclasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.yuja.evp.pagetestmethods.AdminPanelPlatformTestMethods;

public class AdminPanelPlatformTest extends BaseTest{
	final String TYPESTRING = "String";
	final String TYPELIST = "List";
	final String TYPEDATE = "Date";
	final String TYPETIME = "Time";
	final Boolean REQUIRED = true;
	final Boolean NOTREQUIRED = false;
	
	@Test(description="Add_and_delete_string_metadata_test")
	@Parameters({"TestName"})
	public void Add_and_delete_string_metadata_test(String testName) {
		AdminPanelPlatformTestMethods AdminPanelPlatform = new AdminPanelPlatformTestMethods();
		try {
		AdminPanelPlatform.addandDeleteMetadata(TYPESTRING, NOTREQUIRED);
		} catch (InterruptedException e) {
		e.printStackTrace();
		}
		}
	
	@Test(description="Add_and_delete_list_metadata_test")
	@Parameters({"TestName"})
	public void Add_and_delete_list_metadata_test(String testName) {
		AdminPanelPlatformTestMethods AdminPanelPlatform = new AdminPanelPlatformTestMethods();
		try {
		AdminPanelPlatform.addandDeleteMetadata(TYPELIST, NOTREQUIRED);
		} catch (InterruptedException e) {
		e.printStackTrace();
		}
		}
	
	@Test(description="Add_and_delete_date_metadata_test")
	@Parameters({"TestName"})
	public void Add_and_delete_date_metadata_test(String testName) {
		AdminPanelPlatformTestMethods AdminPanelPlatform = new AdminPanelPlatformTestMethods();
		try {
		AdminPanelPlatform.addandDeleteMetadata(TYPEDATE,NOTREQUIRED);
		} catch (InterruptedException e) {
		e.printStackTrace();
		}
		}
	
	@Test(description="Add_and_delete_time_metadata_test")
	@Parameters({"TestName"})
	public void Add_and_delete_time_metadata_test(String testName) {
		AdminPanelPlatformTestMethods AdminPanelPlatform = new AdminPanelPlatformTestMethods();
		try {
		AdminPanelPlatform.addandDeleteMetadata(TYPETIME,NOTREQUIRED);
		} catch (InterruptedException e) {
		e.printStackTrace();
		}
		}
	
	@Test(description="Add_and_delete_required_string_metadata_test")
	@Parameters({"TestName"})
	public void Add_and_delete_required_string_metadata_test(String testName) {
		AdminPanelPlatformTestMethods AdminPanelPlatform = new AdminPanelPlatformTestMethods();
		try {
		AdminPanelPlatform.addandDeleteMetadata(TYPESTRING, REQUIRED);
		} catch (InterruptedException e) {
		e.printStackTrace();
		}
		}
	
	@Test(description="Add_and_delete_required_list_metadata_test")
	@Parameters({"TestName"})
	public void Add_and_delete_required_list_metadata_test(String testName) {
		AdminPanelPlatformTestMethods AdminPanelPlatform = new AdminPanelPlatformTestMethods();
		try {
		AdminPanelPlatform.addandDeleteMetadata(TYPELIST, REQUIRED);
		} catch (InterruptedException e) {
		e.printStackTrace();
		}
		}
	
	@Test(description="Add_and_delete_required_date_metadata_test")
	@Parameters({"TestName"})
	public void Add_and_delete_required_date_metadata_test(String testName) {
		AdminPanelPlatformTestMethods AdminPanelPlatform = new AdminPanelPlatformTestMethods();
		try {
		AdminPanelPlatform.addandDeleteMetadata(TYPEDATE,NOTREQUIRED);
		} catch (InterruptedException e) {
		e.printStackTrace();
		}
		}
	
	@Test(description="Add_and_delete_required_time_metadata_test")
	@Parameters({"TestName"})
	public void Add_and_delete_required_time_metadata_test(String testName) {
		AdminPanelPlatformTestMethods AdminPanelPlatform = new AdminPanelPlatformTestMethods();
		try {
		AdminPanelPlatform.addandDeleteMetadata(TYPETIME,NOTREQUIRED);
		} catch (InterruptedException e) {
		e.printStackTrace();
		}
		}
}