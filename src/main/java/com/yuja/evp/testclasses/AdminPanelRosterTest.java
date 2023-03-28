package com.yuja.evp.testclasses;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.yuja.evp.pagetestmethods.RosterPageTestMethods;

public class AdminPanelRosterTest extends BaseTest{
	final String INSTRUCTOR_USERNAME = "Automation Instructor";
	final String STUDENT_USERNAME = "Automation Student";
	final String SUBADMIN_USERNAME = "Automation Subadmin";
	
	@Test(description="Create_and_delete_student_User_test")
	@Parameters({"TestName"})
	public void Create_and_delete_student_User_test(String testName) {
		RosterPageTestMethods AdminPanelRoster = new RosterPageTestMethods();
		AdminPanelRoster.createAndDeleteUser(prop.getProperty("ManagerID"), prop.getProperty("Password"), "Student");
	}
	
	@Test(description="Create_and_delete_instructor_User_test")
	@Parameters({"TestName"})
	public void Create_and_delete_instructor_User_test(String testName) {
		RosterPageTestMethods AdminPanelRoster = new RosterPageTestMethods();
		AdminPanelRoster.createAndDeleteUser(prop.getProperty("ManagerID"), prop.getProperty("Password"), "Instructor");
	}
	
	@Test(description="Create_and_delete_manager_User_test")
	@Parameters({"TestName"})
	public void Create_and_delete_manager_User_test(String testName) {
		RosterPageTestMethods AdminPanelRoster = new RosterPageTestMethods();
		AdminPanelRoster.createAndDeleteUser(prop.getProperty("ManagerID"), prop.getProperty("Password"), "Manager");
	}
	
	@Test(description="Create_and_delete_subadmin_User_test")
	@Parameters({"TestName"})
	public void Create_and_delete_subadmin_User_test(String testName) {
		RosterPageTestMethods AdminPanelRoster = new RosterPageTestMethods();
		AdminPanelRoster.createAndDeleteUser(prop.getProperty("ManagerID"), prop.getProperty("Password"), "SubAdmin");
	}
	
	@Test(description="Act_as_student_test")
	@Parameters({"TestName"})
	public void Act_as_student_test(String testName) {
		RosterPageTestMethods AdminPanelRoster = new RosterPageTestMethods();
		AdminPanelRoster.actAsUser(STUDENT_USERNAME, prop.getProperty("ManagerID"), prop.getProperty("Password"));
	}
	
	@Test(description="Act_as_instructor_test")
	@Parameters({"TestName"})
	public void Act_as_instructor_test(String testName) {
		RosterPageTestMethods AdminPanelRoster = new RosterPageTestMethods();
		AdminPanelRoster.actAsUser(INSTRUCTOR_USERNAME, prop.getProperty("ManagerID"), prop.getProperty("Password"));
	}
	
	@Test(description="Act_as_subadmin_test")
	@Parameters({"TestName"})
	public void Act_as_subadmin_test(String testName) {
		RosterPageTestMethods AdminPanelRoster = new RosterPageTestMethods();
		AdminPanelRoster.actAsUser(SUBADMIN_USERNAME, prop.getProperty("ManagerID"), prop.getProperty("Password"));
	}
	
	@Test(description="Reset_password_for_student_test")
	@Parameters({"TestName"})
	public void Reset_password_for_student_test(String testName) {
		RosterPageTestMethods AdminPanelRoster = new RosterPageTestMethods();
		AdminPanelRoster.checkResetPasswordButton(prop.getProperty("ManagerID"),prop.getProperty("Password"),"Student");
		}
	
	@Test(description="Reset_password_for_instructor_test")
	@Parameters({"TestName"})
	public void Reset_password_for_instructor_test(String testName) {
		RosterPageTestMethods AdminPanelRoster = new RosterPageTestMethods();
		AdminPanelRoster.checkResetPasswordButton(prop.getProperty("ManagerID"),prop.getProperty("Password"),"Instructor");
		}
	
	@Test(description="Reset_password_for_subadmin_test")
	@Parameters({"TestName"})
	public void Reset_password_for_subadmin_test(String testName) {
		RosterPageTestMethods AdminPanelRoster = new RosterPageTestMethods();
		AdminPanelRoster.checkResetPasswordButton(prop.getProperty("ManagerID"),prop.getProperty("Password"),"SubAdmin");
		}
	
	@Test(description="Reset_password_for_manager_test")
	@Parameters({"TestName"})
	public void Reset_password_for_manager_test(String testName) {
		RosterPageTestMethods AdminPanelRoster = new RosterPageTestMethods();
		AdminPanelRoster.checkResetPasswordButton(prop.getProperty("ManagerID"),prop.getProperty("Password"),"Manager");
		}
	
	@Test(description="Add_student_to_course_test")
	@Parameters({"TestName"})
	public void Add_student_to_course_test(String testName) {
		RosterPageTestMethods AdminPanelRoster = new RosterPageTestMethods();
		AdminPanelRoster.checkAddToCourseButton(prop.getProperty("ManagerID"),prop.getProperty("Password"),"Student");
		}
	
	@Test(description="Add_instructor_to_course_test")
	@Parameters({"TestName"})
	public void Add_instructor_to_course_test(String testName) {
		RosterPageTestMethods AdminPanelRoster = new RosterPageTestMethods();
		AdminPanelRoster.checkAddToCourseButton(prop.getProperty("ManagerID"),prop.getProperty("Password"),"Instructor");
		}
	
	@Test(description="Add_subadmin_to_course_test")
	@Parameters({"TestName"})
	public void Add_subadmin_to_course_test(String testName) {
		RosterPageTestMethods AdminPanelRoster = new RosterPageTestMethods();
		AdminPanelRoster.checkAddToCourseButton(prop.getProperty("ManagerID"),prop.getProperty("Password"),"SubAdmin");
		}
	
	@Test(description="Add_manager_to_course_test")
	@Parameters({"TestName"})
	public void Add_manager_to_course_test(String testName) {
		RosterPageTestMethods AdminPanelRoster = new RosterPageTestMethods();
		AdminPanelRoster.checkAddToCourseButton(prop.getProperty("ManagerID"),prop.getProperty("Password"),"Manager");
		}
	
	@Test(description="Media_library_button_for_student_test")
	@Parameters({"TestName"})
	public void Media_library_button_for_student_test(String testName) {
		RosterPageTestMethods AdminPanelRoster = new RosterPageTestMethods();
		AdminPanelRoster.checkMediaLibraryButton(prop.getProperty("ManagerID"),prop.getProperty("Password"),"Student");
		}
	
	@Test(description="Media_library_button_for_instructor_test")
	@Parameters({"TestName"})
	public void Media_library_button_for_instructor_test(String testName) {
		RosterPageTestMethods AdminPanelRoster = new RosterPageTestMethods();
		AdminPanelRoster.checkMediaLibraryButton(prop.getProperty("ManagerID"),prop.getProperty("Password"),"Instructor");
		}
	
	@Test(description="Media_library_button_for_subadmin_test")
	@Parameters({"TestName"})
	public void Media_library_button_for_subadmin_test(String testName) {
		RosterPageTestMethods AdminPanelRoster = new RosterPageTestMethods();
		AdminPanelRoster.checkMediaLibraryButton(prop.getProperty("ManagerID"),prop.getProperty("Password"),"SubAdmin");
		}
	
	@Test(description="Media_library_button_for_manager_test")
	@Parameters({"TestName"})
	public void Media_library_button_for_manager_test(String testName) {
		RosterPageTestMethods AdminPanelRoster = new RosterPageTestMethods();
		AdminPanelRoster.checkMediaLibraryButton(prop.getProperty("ManagerID"),prop.getProperty("Password"),"Manager");
		}
		
	@Test(description="Delete_button_for_student_test")
	@Parameters({"TestName"})
	public void Delete_button_for_student_test(String testName) {
		RosterPageTestMethods AdminPanelRoster = new RosterPageTestMethods();
		AdminPanelRoster.checkDeleteUserButton(prop.getProperty("ManagerID"),prop.getProperty("Password"),"Student");
		}
	
	@Test(description="Delete_button_for_instructor_test")
	@Parameters({"TestName"})
	public void Delete_button_for_instructor_test(String testName) {
		RosterPageTestMethods AdminPanelRoster = new RosterPageTestMethods();
		AdminPanelRoster.checkDeleteUserButton(prop.getProperty("ManagerID"),prop.getProperty("Password"),"Instructor");
		}
	
	@Test(description="Delete_button_for_subadmin_test")
	@Parameters({"TestName"})
	public void Delete_button_for_subadmin_test(String testName) {
		RosterPageTestMethods AdminPanelRoster = new RosterPageTestMethods();
		AdminPanelRoster.checkDeleteUserButton(prop.getProperty("ManagerID"),prop.getProperty("Password"),"SubAdmin");
		}
	
	@Test(description="Delete_button_for_manager_test")
	@Parameters({"TestName"})
	public void Delete_button_for_manager_test(String testName) {
		RosterPageTestMethods AdminPanelRoster = new RosterPageTestMethods();
		AdminPanelRoster.checkDeleteUserButton(prop.getProperty("ManagerID"),prop.getProperty("Password"),"Manager");
		}
	
}
	
 


