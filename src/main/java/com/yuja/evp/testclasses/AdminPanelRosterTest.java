package com.yuja.evp.testclasses;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.yuja.evp.pagehelpers.AdminPanelRosterPageHelpers;
import com.yuja.evp.pagetestmethods.MediaLibraryPageTestMethods;
import com.yuja.evp.pagetestmethods.RosterPageTestMethods;
import com.yuja.evp.modalhelpers.AddMediaModalHelperMethods;
import com.yuja.evp.pagehelpers.NavigationBarHelpers;

public class AdminPanelRosterTest extends BaseTest{
	final String MANAGER_USERNAME = "automation_manager";
	final String PASSWORD = "jamNOW123!@#123";
	final String INSTRUCTOR_USERNAME = "Automation Instructor";
	final String STUDENT_USERNAME = "Automation Student";
	final String SUBADMIN_USERNAME = "Automation Subadmin";
	
	@Test(description="Create_and_delete_student_User_test")
	@Parameters({"TestName"})
	public void Create_and_delete_student_User_test(String testName) {
		RosterPageTestMethods AdminPanelRoster = new RosterPageTestMethods();
		AdminPanelRoster.createAndDeleteUser(MANAGER_USERNAME, PASSWORD, "Student");
	}
	
	@Test(description="Create_and_delete_instructor_User_test")
	@Parameters({"TestName"})
	public void Create_and_delete_instructor_User_test(String testName) {
		RosterPageTestMethods AdminPanelRoster = new RosterPageTestMethods();
		AdminPanelRoster.createAndDeleteUser(MANAGER_USERNAME, PASSWORD, "Instructor");
	}
	
	@Test(description="Create_and_delete_manager_User_test")
	@Parameters({"TestName"})
	public void Create_and_delete_manager_User_test(String testName) {
		RosterPageTestMethods AdminPanelRoster = new RosterPageTestMethods();
		AdminPanelRoster.createAndDeleteUser(MANAGER_USERNAME, PASSWORD, "Manager");
	}
	
	@Test(description="Create_and_delete_subadmin_User_test")
	@Parameters({"TestName"})
	public void Create_and_delete_subadmin_User_test(String testName) {
		RosterPageTestMethods AdminPanelRoster = new RosterPageTestMethods();
		AdminPanelRoster.createAndDeleteUser(MANAGER_USERNAME, PASSWORD, "SubAdmin");
	}
	
	@Test(description="Act_as_student_test")
	@Parameters({"TestName"})
	public void Act_as_student_test(String testName) {
		RosterPageTestMethods AdminPanelRoster = new RosterPageTestMethods();
		AdminPanelRoster.actAsUser(STUDENT_USERNAME, MANAGER_USERNAME, PASSWORD);
	}
	
	@Test(description="Act_as_instructor_test")
	@Parameters({"TestName"})
	public void Act_as_instructor_test(String testName) {
		RosterPageTestMethods AdminPanelRoster = new RosterPageTestMethods();
		AdminPanelRoster.actAsUser(INSTRUCTOR_USERNAME, MANAGER_USERNAME, PASSWORD);
	}
	
	@Test(description="Act_as_subadmin_test")
	@Parameters({"TestName"})
	public void Act_as_subadmin_test(String testName) {
		RosterPageTestMethods AdminPanelRoster = new RosterPageTestMethods();
		AdminPanelRoster.actAsUser(SUBADMIN_USERNAME, MANAGER_USERNAME, PASSWORD);
	}
	
	@Test(description="Reset_password_for_student_test")
	@Parameters({"TestName"})
	public void Reset_password_for_student_test(String testName) {
		RosterPageTestMethods AdminPanelRoster = new RosterPageTestMethods();
		AdminPanelRoster.checkResetPasswordButton(MANAGER_USERNAME,PASSWORD,"Student");
		}
	
	@Test(description="Reset_password_for_instructor_test")
	@Parameters({"TestName"})
	public void Reset_password_for_instructor_test(String testName) {
		RosterPageTestMethods AdminPanelRoster = new RosterPageTestMethods();
		AdminPanelRoster.checkResetPasswordButton(MANAGER_USERNAME,PASSWORD,"Instructor");
		}
	
	@Test(description="Reset_password_for_subadmin_test")
	@Parameters({"TestName"})
	public void Reset_password_for_subadmin_test(String testName) {
		RosterPageTestMethods AdminPanelRoster = new RosterPageTestMethods();
		AdminPanelRoster.checkResetPasswordButton(MANAGER_USERNAME,PASSWORD,"SubAdmin");
		}
	
	@Test(description="Reset_password_for_manager_test")
	@Parameters({"TestName"})
	public void Reset_password_for_manager_test(String testName) {
		RosterPageTestMethods AdminPanelRoster = new RosterPageTestMethods();
		AdminPanelRoster.checkResetPasswordButton(MANAGER_USERNAME,PASSWORD,"Manager");
		}
	
	@Test(description="Add_student_to_course_test")
	@Parameters({"TestName"})
	public void Add_student_to_course_test(String testName) {
		RosterPageTestMethods AdminPanelRoster = new RosterPageTestMethods();
		AdminPanelRoster.checkAddToCourseButton(MANAGER_USERNAME,PASSWORD,"Student");
		}
	
	@Test(description="Add_instructor_to_course_test")
	@Parameters({"TestName"})
	public void Add_instructor_to_course_test(String testName) {
		RosterPageTestMethods AdminPanelRoster = new RosterPageTestMethods();
		AdminPanelRoster.checkAddToCourseButton(MANAGER_USERNAME,PASSWORD,"Instructor");
		}
	
	@Test(description="Add_subadmin_to_course_test")
	@Parameters({"TestName"})
	public void Add_subadmin_to_course_test(String testName) {
		RosterPageTestMethods AdminPanelRoster = new RosterPageTestMethods();
		AdminPanelRoster.checkAddToCourseButton(MANAGER_USERNAME,PASSWORD,"SubAdmin");
		}
	
	@Test(description="Add_manager_to_course_test")
	@Parameters({"TestName"})
	public void Add_manager_to_course_test(String testName) {
		RosterPageTestMethods AdminPanelRoster = new RosterPageTestMethods();
		AdminPanelRoster.checkAddToCourseButton(MANAGER_USERNAME,PASSWORD,"Manager");
		}
	
	@Test(description="Media_library_button_for_student_test")
	@Parameters({"TestName"})
	public void Media_library_button_for_student_test(String testName) {
		RosterPageTestMethods AdminPanelRoster = new RosterPageTestMethods();
		AdminPanelRoster.checkMediaLibraryButton(MANAGER_USERNAME,PASSWORD,"Student");
		}
	
	@Test(description="Media_library_button_for_instructor_test")
	@Parameters({"TestName"})
	public void Media_library_button_for_instructor_test(String testName) {
		RosterPageTestMethods AdminPanelRoster = new RosterPageTestMethods();
		AdminPanelRoster.checkMediaLibraryButton(MANAGER_USERNAME,PASSWORD,"Instructor");
		}
	
	@Test(description="Media_library_button_for_subadmin_test")
	@Parameters({"TestName"})
	public void Media_library_button_for_subadmin_test(String testName) {
		RosterPageTestMethods AdminPanelRoster = new RosterPageTestMethods();
		AdminPanelRoster.checkMediaLibraryButton(MANAGER_USERNAME,PASSWORD,"SubAdmin");
		}
	
	@Test(description="Media_library_button_for_manager_test")
	@Parameters({"TestName"})
	public void Media_library_button_for_manager_test(String testName) {
		RosterPageTestMethods AdminPanelRoster = new RosterPageTestMethods();
		AdminPanelRoster.checkMediaLibraryButton(MANAGER_USERNAME,PASSWORD,"Manager");
		}
		
	@Test(description="Delete_button_for_student_test")
	@Parameters({"TestName"})
	public void Delete_button_for_student_test(String testName) {
		RosterPageTestMethods AdminPanelRoster = new RosterPageTestMethods();
		AdminPanelRoster.checkDeleteUserButton(MANAGER_USERNAME,PASSWORD,"Student");
		}
	
	@Test(description="Delete_button_for_instructor_test")
	@Parameters({"TestName"})
	public void Delete_button_for_instructor_test(String testName) {
		RosterPageTestMethods AdminPanelRoster = new RosterPageTestMethods();
		AdminPanelRoster.checkDeleteUserButton(MANAGER_USERNAME,PASSWORD,"Instructor");
		}
	
	@Test(description="Delete_button_for_subadmin_test")
	@Parameters({"TestName"})
	public void Delete_button_for_subadmin_test(String testName) {
		RosterPageTestMethods AdminPanelRoster = new RosterPageTestMethods();
		AdminPanelRoster.checkDeleteUserButton(MANAGER_USERNAME,PASSWORD,"SubAdmin");
		}
	
	@Test(description="Delete_button_for_manager_test")
	@Parameters({"TestName"})
	public void Delete_button_for_manager_test(String testName) {
		RosterPageTestMethods AdminPanelRoster = new RosterPageTestMethods();
		AdminPanelRoster.checkDeleteUserButton(MANAGER_USERNAME,PASSWORD,"Manager");
		}
	
}
	
 


