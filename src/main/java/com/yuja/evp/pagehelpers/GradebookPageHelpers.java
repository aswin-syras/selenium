package com.yuja.evp.pagehelpers;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.yuja.evp.utilities.Helpers;

public class GradebookPageHelpers extends Helpers{
	
	public void clickSelectCourse() {
		clickElement("Click Select Course from Gradebook",By.xpath("//span[@class='select2-chosen']"), 10);
	}
	
	public void clickSelectQuiz() {
		clickElement("Click Select quiz from Gradebook",By.xpath("(//span[@class='select2-chosen'])[2]"), 10);
	}
	
	public void clickGetGradebookReport() {
		clickElement("Click Get report",By.id("getReportBtn"), 10);
	}
	
	
	public String gradeShortAnswerforMultipleStudents(int x) throws InterruptedException {
		x++;
		int z=2*x-1;
		int y= x+1;
		clickElement("Click short answer tab",By.xpath("//table//tbody//tr["+x+"]//td[6]"),10);
		clickElement("Click short answer tab to open window",By.xpath("//table//tbody//tr["+y+"]//td[5]"),10);
		Thread.sleep(3000);
		clickElement("Click short answer correct radio button",By.xpath("(//input[@type='radio'])["+x+"]"),10);
		clickElement("Click short answer window save button",By.xpath("//button[@title='Save']"),10);
		clickGetGradebookReport();
		Thread.sleep(3000);
		String grade= driver.findElement(By.xpath("//table//tbody//tr["+x+"]//td[3]")).getText();
		System.out.println(grade);
		return grade;
	}
	
	public String gradePlaybackQuiz(int x) throws InterruptedException {
		x++;
		clickElement("Click on student checkbox",By.xpath("//table[@id='fullStudentDataTable']//tbody//tr["+x+"]//td[1]//input[@name=\"student-selector\"]"),10);
		clickElement("Click sync gradebook button",By.xpath("//button[@id='syncButtonOptions']"),10);
		clickElement("Click on demand LMS student gradebook sync",By.xpath("//div[@id='dropdownSyncContent']//a"),10);
		clickElement("Click ok dialog",By.xpath("//span[@id='buttonSection']//button[2]"),10);
		Thread.sleep(5000);
		clickGetGradebookReport();
		Thread.sleep(3000);
		String grade= driver.findElement(By.xpath("//table[@id='fullStudentDataTable']//tbody//tr["+x+"]//td[3]")).getText();
		System.out.println(grade);
		return grade;
		
	}
	
	public int getUserIndexFromGradeBook(String studentName) {
		List<WebElement> studentRows = getElementList(By.cssSelector("table#fullStudentDataTable > tbody > tr"));
		
		int numberOfRows = studentRows.size();
		System.out.println("numberOfRows = " + numberOfRows);
		WebElement studentNameElement = null;
		String studentNameElementText = "";
		reportStep("Fetching the element with the name \"" + studentName + "\"", "PASS", false);
		
		int i = 0;
		boolean studentFound = false;
		int studentIndex = 0;
		
		while(!studentFound && i < numberOfRows ) {
			studentNameElement = studentRows.get(i);
			WebElement element = studentNameElement.findElement(By.xpath("//table//tbody//tr["+(i+1)+"]//td[2]"));
			if(element != null) {
				studentNameElementText = element.getText();
				System.out.print("studentNameElementText["+i+"] = " + studentNameElementText);
				if(studentNameElementText.equals(studentName)) {
					studentFound = true;
					studentIndex = i;
				}
			}
			i++;
	    }
		return studentIndex;
	}
	
}
