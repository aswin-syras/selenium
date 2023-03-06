package com.yuja.evp.pagehelpers;

import org.openqa.selenium.By;

import com.yuja.evp.utilities.Helpers;

public class MyAccountPageHelpers extends Helpers{
	
	 public void clickMyGrades() {
		clickElement("Click My Grades",By.xpath("//a[@title='My Grades']"),10);
	}

}
