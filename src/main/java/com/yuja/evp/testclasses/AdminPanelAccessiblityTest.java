package com.yuja.evp.testclasses;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.yuja.evp.pagehelpers.AccessiblityPageHelpers;
import com.yuja.evp.pagetestmethods.AccessiblityPageTestMethods;
import com.yuja.evp.pagetestmethods.QuizPageTestMethods;

public class AdminPanelAccessiblityTest extends BaseTest{
	
	String INSTRUCTOR_FULLNAME="Instructor Automation";
	String STUDENT_FULLNAME="Student Automation";
	String ITMANAGER_FULLNAME="Manager Automation2";
	String VIDEONAME="captionvideo";
	
	
	@Test(description="Check_humancaption_button_update")
	   @Parameters({"TestName"})
	public void Check_humancaption_button_update(String TestName) {
		AccessiblityPageTestMethods ap = new AccessiblityPageTestMethods();
		try {
			ap.checkHumanCaptionButtonUpdate( prop.getProperty("ManagerID"), prop.getProperty("Password"), "Role based", "All Instructor", "Caption Sync: Arjun Caption Captionsync", "Yes", VIDEONAME);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			reportStep("@Method "+Scenario_Name +" exception to be handled", "FAIL", true);
		}
	}

	@Test(description="Edit_humancaption_permissions_for_Specific_user_captionSync_test")
	   @Parameters({"TestName"})
	public void Edit_humancaption_permissions_for_Specific_user_captionSync_test(String TestName) {

		AccessiblityPageTestMethods ap = new AccessiblityPageTestMethods();
		try {
			ap.checkHumanCaptioningPermissionForUser(prop.getProperty("ManagerID"), prop.getProperty("Password"),"User based", "Manager Automation2" ,prop.getProperty("ManagerID2"), prop.getProperty("Password"),ITMANAGER_FULLNAME, "Caption Sync: Arjun Caption Captionsync","Yes",VIDEONAME);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			reportStep("@Method "+Scenario_Name +" exception to be handled", "FAIL", true);
		}
	}
	
	@Test(description="Edit_autocaption_permissions_for_all_instructors_test")
	   @Parameters({"TestName"})
	public void Edit_autocaption_permissions_for_all_instructors_test(String TestName) {
				AccessiblityPageTestMethods ap = new AccessiblityPageTestMethods();
				try {
					ap.checkAutoCaptioningPermissionForUser(prop.getProperty("ManagerID"), prop.getProperty("Password"),"Role based", "All Instructor", prop.getProperty("InstructorID"), prop.getProperty("Password"),INSTRUCTOR_FULLNAME,VIDEONAME);
				}
				catch (Exception e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
					reportStep("@Method "+Scenario_Name +" exception to be handled", "FAIL", true);
				}
			  }
	
	@Test(description="Edit_autocaption_permissions_for_all_students_test")
	  @Parameters({"TestName"})
	public void Edit_autocaption_permissions_for_all_students_test(String TestName) {
		AccessiblityPageTestMethods ap = new AccessiblityPageTestMethods();
		try {
			ap.checkAutoCaptioningPermissionForUser(prop.getProperty("ManagerID"), prop.getProperty("Password"),"Role based", "All Student",prop.getProperty("StudentID"), prop.getProperty("Password"),STUDENT_FULLNAME,VIDEONAME );
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			reportStep("@Method "+Scenario_Name +" exception to be handled", "FAIL", true);
		}
	  }
	
	@Test(description="Edit_autocaption_permissions_for_ITManager_test")
	  @Parameters({"TestName"})
	public void Edit_autocaption_permissions_for_ITManager_test(String TestName) {
		AccessiblityPageTestMethods ap = new AccessiblityPageTestMethods();
		try {
			ap.checkAutoCaptioningPermissionForUser(prop.getProperty("ManagerID"), prop.getProperty("Password"),"Role based", "IT Manager",prop.getProperty("ManagerID2"), prop.getProperty("Password"),ITMANAGER_FULLNAME,VIDEONAME );
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			reportStep("@Method "+Scenario_Name +" exception to be handled", "FAIL", true);
		}
	  }
	
	@Test(description="Edit_autocaption_permissions_for_Specific_user_test")
	  @Parameters({"TestName"})
	public void Edit_autocaption_permissions_for_Specific_user_test(String TestName) {
		AccessiblityPageTestMethods ap = new AccessiblityPageTestMethods();
		try {
			ap.checkAutoCaptioningPermissionForUser(prop.getProperty("ManagerID"), prop.getProperty("Password"),"User based", "Manager Automation2" ,prop.getProperty("ManagerID2"), prop.getProperty("Password"),ITMANAGER_FULLNAME,VIDEONAME );
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			reportStep("@Method "+Scenario_Name +" exception to be handled", "FAIL", true);
		}
	  }
	
	@Test(description="Edit_humancaption_permissions_for_all_instructors_allcaptionproviders_test")
	   @Parameters({"TestName"})
	public void Edit_humancaption_permissions_for_all_instructors_allcaptionproviders_test(String TestName) {
				AccessiblityPageTestMethods ap = new AccessiblityPageTestMethods();
				try {
					ap.checkHumanCaptioningPermissionForUser(prop.getProperty("ManagerID"), prop.getProperty("Password"),"Role based", "All Instructor", prop.getProperty("InstructorID"), prop.getProperty("Password"),INSTRUCTOR_FULLNAME, "All Caption Providers","Yes",VIDEONAME);
				}
				catch (Exception e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
					reportStep("@Method "+Scenario_Name +" exception to be handled", "FAIL", true);
				}
			  }
	@Test(description="Edit_humancaption_permissions_for_all_instructors_rev_test")
	   @Parameters({"TestName"})
	public void Edit_humancaption_permissions_for_all_instructors_rev_test(String TestName) {
				AccessiblityPageTestMethods ap = new AccessiblityPageTestMethods();
				try {
					ap.checkHumanCaptioningPermissionForUser(prop.getProperty("ManagerID"), prop.getProperty("Password"),"Role based", "All Instructor", prop.getProperty("InstructorID"), prop.getProperty("Password"),INSTRUCTOR_FULLNAME, "Rev: Rev","Yes",VIDEONAME);
				}
				catch (Exception e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
					reportStep("@Method "+Scenario_Name +" exception to be handled", "FAIL", true);
				}
			  }
	@Test(description="Edit_humancaption_permissions_for_all_instructors_3playmedia_test")
	   @Parameters({"TestName"})
	public void Edit_humancaption_permissions_for_all_instructors_3playmedia_test(String TestName) {
				AccessiblityPageTestMethods ap = new AccessiblityPageTestMethods();
				try {
					ap.checkHumanCaptioningPermissionForUser(prop.getProperty("ManagerID"), prop.getProperty("Password"),"Role based", "All Instructor", prop.getProperty("InstructorID"), prop.getProperty("Password"),INSTRUCTOR_FULLNAME, "Three Play: Working","Yes",VIDEONAME);
				}
				catch (Exception e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
					reportStep("@Method "+Scenario_Name +" exception to be handled", "FAIL", true);
				}
			  }
	@Test(description="Edit_humancaption_permissions_for_all_instructors_Cielo24_test")
	   @Parameters({"TestName"})
	public void Edit_humancaption_permissions_for_all_instructors_Cielo24_test(String TestName) {
				AccessiblityPageTestMethods ap = new AccessiblityPageTestMethods();
				try {
					ap.checkHumanCaptioningPermissionForUser(prop.getProperty("ManagerID"), prop.getProperty("Password"),"Role based", "All Instructor", prop.getProperty("InstructorID"), prop.getProperty("Password"),INSTRUCTOR_FULLNAME, "Cielo24: yuja","Yes",VIDEONAME);
				}
				catch (Exception e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
					reportStep("@Method "+Scenario_Name +" exception to be handled", "FAIL", true);
				}
			  }
	
	@Test(description="Edit_humancaption_permissions_for_all_instructors_yujaprocaptioning_test")
	   @Parameters({"TestName"})
	public void Edit_humancaption_permissions_for_all_instructors_yujaprocaptioning_test(String TestName) {
				AccessiblityPageTestMethods ap = new AccessiblityPageTestMethods();
				try {
					ap.checkHumanCaptioningPermissionForUser(prop.getProperty("ManagerID"), prop.getProperty("Password"),"Role based", "All Instructor", prop.getProperty("InstructorID"), prop.getProperty("Password"),INSTRUCTOR_FULLNAME, "YuJa Pro Captioning","Yes",VIDEONAME);
				}
				catch (Exception e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
					reportStep("@Method "+Scenario_Name +" exception to be handled", "FAIL", true);
				}
			  }
	
	@Test(description="Edit_humancaption_permissions_for_all_instructors_captionSync_test")
	   @Parameters({"TestName"})
	public void Edit_humancaption_permissions_for_all_instructors_captionSync_test(String TestName) {
				AccessiblityPageTestMethods ap = new AccessiblityPageTestMethods();
				try {
					ap.checkHumanCaptioningPermissionForUser(prop.getProperty("ManagerID"), prop.getProperty("Password"),"Role based", "All Instructor",prop.getProperty("InstructorID"), prop.getProperty("Password"),INSTRUCTOR_FULLNAME, "Caption Sync: Arjun Caption Captionsync","Yes",VIDEONAME);
				}
				catch (Exception e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
					reportStep("@Method "+Scenario_Name +" exception to be handled", "FAIL", true);
				}
			  }
	
	@Test(description="Edit_humancaption_permissions_for_ITManager_allcaptionproviders_test")
	   @Parameters({"TestName"})
	public void Edit_humancaption_permissions_for_ITManager_allcaptionproviders_test(String TestName) {
				AccessiblityPageTestMethods ap = new AccessiblityPageTestMethods();
				try {
					ap.checkHumanCaptioningPermissionForUser(prop.getProperty("ManagerID"), prop.getProperty("Password"),"Role based","IT Manager",prop.getProperty("ManagerID2"), prop.getProperty("Password"),ITMANAGER_FULLNAME, "All Caption Providers","Yes",VIDEONAME);
				}
				catch (Exception e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
					reportStep("@Method "+Scenario_Name +" exception to be handled", "FAIL", true);
				}
			  }
	@Test(description="Edit_humancaption_permissions_for_ITManager_rev_test")
	   @Parameters({"TestName"})
	public void Edit_humancaption_permissions_for_ITManager_rev_test(String TestName) {
				AccessiblityPageTestMethods ap = new AccessiblityPageTestMethods();
				try {
					ap.checkHumanCaptioningPermissionForUser(prop.getProperty("ManagerID"), prop.getProperty("Password"),"Role based","IT Manager",prop.getProperty("ManagerID2"), prop.getProperty("Password"),ITMANAGER_FULLNAME, "Rev: Rev","Yes",VIDEONAME);
				}
				catch (Exception e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
					reportStep("@Method "+Scenario_Name +" exception to be handled", "FAIL", true);
				}
			  }
	@Test(description="Edit_humancaption_permissions_for_ITManager_3playmedia_test")
	   @Parameters({"TestName"})
	public void Edit_humancaption_permissions_for_ITManager_3playmedia_test(String TestName) {
				AccessiblityPageTestMethods ap = new AccessiblityPageTestMethods();
				try {
					ap.checkHumanCaptioningPermissionForUser(prop.getProperty("ManagerID"), prop.getProperty("Password"),"Role based", "IT Manager",prop.getProperty("ManagerID2"), prop.getProperty("Password"),ITMANAGER_FULLNAME, "Three Play: Working","Yes",VIDEONAME);
				}
				catch (Exception e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
					reportStep("@Method "+Scenario_Name +" exception to be handled", "FAIL", true);
				}
			  }
	@Test(description="Edit_humancaption_permissions_for_ITManager_Cielo24_test")
	   @Parameters({"TestName"})
	public void Edit_humancaption_permissions_for_ITManager_Cielo24_test(String TestName) {
				AccessiblityPageTestMethods ap = new AccessiblityPageTestMethods();
				try {
					ap.checkHumanCaptioningPermissionForUser(prop.getProperty("ManagerID"), prop.getProperty("Password"),"Role based","IT Manager",prop.getProperty("ManagerID2"), prop.getProperty("Password"),ITMANAGER_FULLNAME, "Cielo24: yuja","Yes",VIDEONAME);
				}
				catch (Exception e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
					reportStep("@Method "+Scenario_Name +" exception to be handled", "FAIL", true);
				}
			  }
	
	@Test(description="Edit_humancaption_permissions_for_ITManager_yujaprocaptioning_test")
	   @Parameters({"TestName"})
	public void Edit_humancaption_permissions_for_ITManager_yujaprocaptioning_test(String TestName) {
				AccessiblityPageTestMethods ap = new AccessiblityPageTestMethods();
				try {
					ap.checkHumanCaptioningPermissionForUser(prop.getProperty("ManagerID"), prop.getProperty("Password"),"Role based","IT Manager",prop.getProperty("ManagerID2"), prop.getProperty("Password"),ITMANAGER_FULLNAME, "YuJa Pro Captioning","Yes",VIDEONAME);
				}
				catch (Exception e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
					reportStep("@Method "+Scenario_Name +" exception to be handled", "FAIL", true);
				}
			  }
	
	@Test(description="Edit_humancaption_permissions_for_ITManager_captionSync_test")
	   @Parameters({"TestName"})
	public void Edit_humancaption_permissions_for_ITManager_captionSync_test(String TestName) {
				AccessiblityPageTestMethods ap = new AccessiblityPageTestMethods();
				try {
					ap.checkHumanCaptioningPermissionForUser(prop.getProperty("ManagerID"), prop.getProperty("Password"),"Role based", "IT Manager",prop.getProperty("ManagerID2"), prop.getProperty("Password"),ITMANAGER_FULLNAME, "Caption Sync: Arjun Caption Captionsync","Yes",VIDEONAME);
				}
				catch (Exception e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
					reportStep("@Method "+Scenario_Name +" exception to be handled", "FAIL", true);
				}
			  }
	
	@Test(description="Edit_humancaption_permissions_for_all_students_allcaptionproviders_test")
	   @Parameters({"TestName"})
	public void Edit_humancaption_permissions_for_all_students_allcaptionproviders_test(String TestName) {
				AccessiblityPageTestMethods ap = new AccessiblityPageTestMethods();
				try {
					ap.checkHumanCaptioningPermissionForUser(prop.getProperty("ManagerID"), prop.getProperty("Password"),"Role based","All Student",prop.getProperty("StudentID"), prop.getProperty("Password"),STUDENT_FULLNAME, "All Caption Providers","Yes",VIDEONAME);
				}
				catch (Exception e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
					reportStep("@Method "+Scenario_Name +" exception to be handled", "FAIL", true);
				}
			  }
	@Test(description="Edit_humancaption_permissions_for_all_students_rev_test")
	   @Parameters({"TestName"})
	public void Edit_humancaption_permissions_for_all_students_rev_test(String TestName) {
				AccessiblityPageTestMethods ap = new AccessiblityPageTestMethods();
				try {
					ap.checkHumanCaptioningPermissionForUser(prop.getProperty("ManagerID"), prop.getProperty("Password"),"Role based","All Student",prop.getProperty("StudentID"), prop.getProperty("Password"),STUDENT_FULLNAME, "Rev: Rev","Yes",VIDEONAME);
				}
				catch (Exception e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
					reportStep("@Method "+Scenario_Name +" exception to be handled", "FAIL", true);
				}
			  }
	@Test(description="Edit_humancaption_permissions_for_all_students_3playmedia_test")
	   @Parameters({"TestName"})
	public void Edit_humancaption_permissions_for_all_students_3playmedia_test(String TestName) {
				AccessiblityPageTestMethods ap = new AccessiblityPageTestMethods();
				try {
					ap.checkHumanCaptioningPermissionForUser(prop.getProperty("ManagerID"), prop.getProperty("Password"),"Role based", "All Student",prop.getProperty("StudentID"), prop.getProperty("Password"),STUDENT_FULLNAME, "Three Play: Working","Yes",VIDEONAME);
				}
				catch (Exception e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
					reportStep("@Method "+Scenario_Name +" exception to be handled", "FAIL", true);
				}
			  }
	@Test(description="Edit_humancaption_permissions_for_all_students_Cielo24_test")
	   @Parameters({"TestName"})
	public void Edit_humancaption_permissions_for_all_students_Cielo24_test(String TestName) {
				AccessiblityPageTestMethods ap = new AccessiblityPageTestMethods();
				try {
					ap.checkHumanCaptioningPermissionForUser(prop.getProperty("ManagerID"), prop.getProperty("Password"),"Role based","All Student",prop.getProperty("StudentID"), prop.getProperty("Password"),STUDENT_FULLNAME, "Cielo24: yuja","Yes",VIDEONAME);
				}
				catch (Exception e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
					reportStep("@Method "+Scenario_Name +" exception to be handled", "FAIL", true);
				}
			  }
	
	@Test(description="Edit_humancaption_permissions_for_all_students_yujaprocaptioning_test")
	   @Parameters({"TestName"})
	public void Edit_humancaption_permissions_for_all_students_yujaprocaptioning_test(String TestName) {
				AccessiblityPageTestMethods ap = new AccessiblityPageTestMethods();
				try {
					ap.checkHumanCaptioningPermissionForUser(prop.getProperty("ManagerID"), prop.getProperty("Password"),"Role based","All Student",prop.getProperty("StudentID"), prop.getProperty("Password"),STUDENT_FULLNAME, "YuJa Pro Captioning","Yes",VIDEONAME);
				}
				catch (Exception e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
					reportStep("@Method "+Scenario_Name +" exception to be handled", "FAIL", true);
				}
			  }
	
	@Test(description="Edit_humancaption_permissions_for_all_students_captionSync_test")
	   @Parameters({"TestName"})
	public void Edit_humancaption_permissions_for_all_students_captionSync_test(String TestName) {
				AccessiblityPageTestMethods ap = new AccessiblityPageTestMethods();
				try {
					ap.checkHumanCaptioningPermissionForUser(prop.getProperty("ManagerID"), prop.getProperty("Password"),"Role based", "All Student",prop.getProperty("StudentID"), prop.getProperty("Password"),STUDENT_FULLNAME, "Caption Sync: Arjun Caption Captionsync","Yes",VIDEONAME);
				}
				catch (Exception e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
					reportStep("@Method "+Scenario_Name +" exception to be handled", "FAIL", true);
				}
			  }
	
	@Test(description="Edit_humancaption_permissions_for_Specific_users_allcaptionproviders_test")
	   @Parameters({"TestName"})
	public void Edit_humancaption_permissions_for_Specific_users_allcaptionproviders_test(String TestName) {
				AccessiblityPageTestMethods ap = new AccessiblityPageTestMethods();
				try {
					ap.checkHumanCaptioningPermissionForUser(prop.getProperty("ManagerID"), prop.getProperty("Password"),"User based", "Manager Automation2" ,prop.getProperty("ManagerID2"), prop.getProperty("Password"),ITMANAGER_FULLNAME, "All Caption Providers","Yes",VIDEONAME);
				}
				catch (Exception e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
					reportStep("@Method "+Scenario_Name +" exception to be handled", "FAIL", true);
				}
			  }
	@Test(description="Edit_humancaption_permissions_for_Specific_user_rev_test")
	   @Parameters({"TestName"})
	public void Edit_humancaption_permissions_for_Specific_user_rev_test(String TestName) {
				AccessiblityPageTestMethods ap = new AccessiblityPageTestMethods();
				try {
					ap.checkHumanCaptioningPermissionForUser(prop.getProperty("ManagerID"), prop.getProperty("Password"),"User based", "Manager Automation2" ,prop.getProperty("ManagerID2"), prop.getProperty("Password"),ITMANAGER_FULLNAME, "Rev: Rev","Yes",VIDEONAME);
				}
				catch (Exception e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
					reportStep("@Method "+Scenario_Name +" exception to be handled", "FAIL", true);
				}
			  }
	@Test(description="Edit_humancaption_permissions_for_Specific_user_3playmedia_test")
	   @Parameters({"TestName"})
	public void Edit_humancaption_permissions_for_Specific_user_3playmedia_test(String TestName) {
				AccessiblityPageTestMethods ap = new AccessiblityPageTestMethods();
				try {
					ap.checkHumanCaptioningPermissionForUser(prop.getProperty("ManagerID"), prop.getProperty("Password"),"User based", "Manager Automation2" ,prop.getProperty("ManagerID2"), prop.getProperty("Password"),ITMANAGER_FULLNAME, "Three Play: Working","Yes",VIDEONAME);
				}
				catch (Exception e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
					reportStep("@Method "+Scenario_Name +" exception to be handled", "FAIL", true);
				}
			  }
	@Test(description="Edit_humancaption_permissions_for_Specific_user_Cielo24_test")
	   @Parameters({"TestName"})
	public void Edit_humancaption_permissions_for_Specific_user_Cielo24_test(String TestName) {
				AccessiblityPageTestMethods ap = new AccessiblityPageTestMethods();
				try {
					ap.checkHumanCaptioningPermissionForUser(prop.getProperty("ManagerID"), prop.getProperty("Password"),"User based", "Manager Automation2" ,prop.getProperty("ManagerID2"), prop.getProperty("Password"),ITMANAGER_FULLNAME, "Cielo24: yuja","Yes",VIDEONAME);
				}
				catch (Exception e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
					reportStep("@Method "+Scenario_Name +" exception to be handled", "FAIL", true);
				}
			  }
	
	@Test(description="Edit_humancaption_permissions_for_Specific_user_yujaprocaptioning_test")
	   @Parameters({"TestName"})
	public void Edit_humancaption_permissions_for_Specific_user_yujaprocaptioning_test(String TestName) {
				AccessiblityPageTestMethods ap = new AccessiblityPageTestMethods();
				try {
					ap.checkHumanCaptioningPermissionForUser(prop.getProperty("ManagerID"), prop.getProperty("Password"),"User based", "Manager Automation2" ,prop.getProperty("ManagerID2"), prop.getProperty("Password"),ITMANAGER_FULLNAME, "YuJa Pro Captioning","Yes",VIDEONAME);
				}
				catch (Exception e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
					reportStep("@Method "+Scenario_Name +" exception to be handled", "FAIL", true);
				}
			  }
	

	
}
