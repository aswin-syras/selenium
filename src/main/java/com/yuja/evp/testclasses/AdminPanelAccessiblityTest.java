package com.yuja.evp.testclasses;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.yuja.evp.pagehelpers.AccessiblityPageHelpers;
import com.yuja.evp.pagetestmethods.AccessiblityPageTestMethods;
import com.yuja.evp.pagetestmethods.QuizPageTestMethods;

public class AdminPanelAccessiblityTest extends BaseTest{
	String ADMIN_USERNAME="automation_manager";
	String ADMIN_PASSWORD="jamNOW123!@#123";
	String INSTRUCTOR_USERNAME="automation_Instructor";
	String INSTRUCTOR_PASSWORD="jamNOW123!@#123";
	String INSTRUCTOR_FULLNAME="Automation Instructor";
	String STUDENT_USERNAME="automation_Student";
	String STUDENT_PASSWORD="jamNOW123!@#123";
	String STUDENT_FULLNAME="Automation Student";
	String ITMANAGER_USERNAME="manager_leo";
	String ITMANAGER_PASSWORD="jamNOW123!@#123";
	String ITMANAGER_FULLNAME="Leo Hernandez";
	String VIDEONAME="humancaption";
	
	
	@Test(description="Check_humancaption_button_update")
	   @Parameters({"TestName"})
	public void Check_humancaption_button_update(String TestName) {
		AccessiblityPageTestMethods ap = new AccessiblityPageTestMethods();
		try {
			ap.checkHumanCaptionButtonUpdate( ADMIN_USERNAME,ADMIN_PASSWORD, "Role based", "All Instructor", "Caption Sync: Arjun Caption Captionsync", "Yes", VIDEONAME);
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
			ap.checkHumanCaptioningPermissionForUser(ADMIN_USERNAME,ADMIN_PASSWORD,"User based", "manager_leo" ,ITMANAGER_USERNAME, ITMANAGER_PASSWORD,ITMANAGER_FULLNAME, "Caption Sync: Arjun Caption Captionsync","Yes",VIDEONAME);
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
					ap.checkAutoCaptioningPermissionForUser(ADMIN_USERNAME,ADMIN_PASSWORD,"Role based", "All Instructor", INSTRUCTOR_USERNAME, INSTRUCTOR_PASSWORD,INSTRUCTOR_FULLNAME,VIDEONAME);
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
			ap.checkAutoCaptioningPermissionForUser(ADMIN_USERNAME,ADMIN_PASSWORD,"Role based", "All Student",STUDENT_USERNAME, STUDENT_PASSWORD,STUDENT_FULLNAME,VIDEONAME );
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
			ap.checkAutoCaptioningPermissionForUser(ADMIN_USERNAME,ADMIN_PASSWORD,"Role based", "IT Manager",ITMANAGER_USERNAME, ITMANAGER_PASSWORD,ITMANAGER_FULLNAME,VIDEONAME );
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
			ap.checkAutoCaptioningPermissionForUser(ADMIN_USERNAME,ADMIN_PASSWORD,"User based", "manager_leo" ,ITMANAGER_USERNAME, ITMANAGER_PASSWORD,ITMANAGER_FULLNAME,VIDEONAME );
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
					ap.checkHumanCaptioningPermissionForUser(ADMIN_USERNAME,ADMIN_PASSWORD,"Role based", "All Instructor", INSTRUCTOR_USERNAME, INSTRUCTOR_PASSWORD,INSTRUCTOR_FULLNAME, "All Caption Providers","Yes",VIDEONAME);
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
					ap.checkHumanCaptioningPermissionForUser(ADMIN_USERNAME,ADMIN_PASSWORD,"Role based", "All Instructor", INSTRUCTOR_USERNAME, INSTRUCTOR_PASSWORD,INSTRUCTOR_FULLNAME, "Rev: Rev","Yes",VIDEONAME);
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
					ap.checkHumanCaptioningPermissionForUser(ADMIN_USERNAME,ADMIN_PASSWORD,"Role based", "All Instructor", INSTRUCTOR_USERNAME, INSTRUCTOR_PASSWORD,INSTRUCTOR_FULLNAME, "Three Play: Working","Yes",VIDEONAME);
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
					ap.checkHumanCaptioningPermissionForUser(ADMIN_USERNAME,ADMIN_PASSWORD,"Role based", "All Instructor", INSTRUCTOR_USERNAME, INSTRUCTOR_PASSWORD,INSTRUCTOR_FULLNAME, "Cielo24: yuja","Yes",VIDEONAME);
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
					ap.checkHumanCaptioningPermissionForUser(ADMIN_USERNAME,ADMIN_PASSWORD,"Role based", "All Instructor", INSTRUCTOR_USERNAME, INSTRUCTOR_PASSWORD,INSTRUCTOR_FULLNAME, "YuJa Pro Captioning","Yes",VIDEONAME);
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
					ap.checkHumanCaptioningPermissionForUser(ADMIN_USERNAME,ADMIN_PASSWORD,"Role based", "All Instructor", INSTRUCTOR_USERNAME, INSTRUCTOR_PASSWORD,INSTRUCTOR_FULLNAME, "Caption Sync: Arjun Caption Captionsync","Yes",VIDEONAME);
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
					ap.checkHumanCaptioningPermissionForUser(ADMIN_USERNAME,ADMIN_PASSWORD,"Role based","IT Manager",ITMANAGER_USERNAME, ITMANAGER_PASSWORD,ITMANAGER_FULLNAME, "All Caption Providers","Yes",VIDEONAME);
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
					ap.checkHumanCaptioningPermissionForUser(ADMIN_USERNAME,ADMIN_PASSWORD,"Role based","IT Manager",ITMANAGER_USERNAME, ITMANAGER_PASSWORD,ITMANAGER_FULLNAME, "Rev: Rev","Yes",VIDEONAME);
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
					ap.checkHumanCaptioningPermissionForUser(ADMIN_USERNAME,ADMIN_PASSWORD,"Role based", "IT Manager",ITMANAGER_USERNAME, ITMANAGER_PASSWORD,ITMANAGER_FULLNAME, "Three Play: Working","Yes",VIDEONAME);
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
					ap.checkHumanCaptioningPermissionForUser(ADMIN_USERNAME,ADMIN_PASSWORD,"Role based","IT Manager",ITMANAGER_USERNAME, ITMANAGER_PASSWORD,ITMANAGER_FULLNAME, "Cielo24: yuja","Yes",VIDEONAME);
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
					ap.checkHumanCaptioningPermissionForUser(ADMIN_USERNAME,ADMIN_PASSWORD,"Role based","IT Manager",ITMANAGER_USERNAME, ITMANAGER_PASSWORD,ITMANAGER_FULLNAME, "YuJa Pro Captioning","Yes",VIDEONAME);
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
					ap.checkHumanCaptioningPermissionForUser(ADMIN_USERNAME,ADMIN_PASSWORD,"Role based", "IT Manager",ITMANAGER_USERNAME, ITMANAGER_PASSWORD,ITMANAGER_FULLNAME, "Caption Sync: Arjun Caption Captionsync","Yes",VIDEONAME);
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
					ap.checkHumanCaptioningPermissionForUser(ADMIN_USERNAME,ADMIN_PASSWORD,"Role based","All Student",STUDENT_USERNAME, STUDENT_PASSWORD,STUDENT_FULLNAME, "All Caption Providers","Yes",VIDEONAME);
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
					ap.checkHumanCaptioningPermissionForUser(ADMIN_USERNAME,ADMIN_PASSWORD,"Role based","All Student",STUDENT_USERNAME, STUDENT_PASSWORD,STUDENT_FULLNAME, "Rev: Rev","Yes",VIDEONAME);
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
					ap.checkHumanCaptioningPermissionForUser(ADMIN_USERNAME,ADMIN_PASSWORD,"Role based", "All Student",STUDENT_USERNAME, STUDENT_PASSWORD,STUDENT_FULLNAME, "Three Play: Working","Yes",VIDEONAME);
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
					ap.checkHumanCaptioningPermissionForUser(ADMIN_USERNAME,ADMIN_PASSWORD,"Role based","All Student",STUDENT_USERNAME, STUDENT_PASSWORD,STUDENT_FULLNAME, "Cielo24: yuja","Yes",VIDEONAME);
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
					ap.checkHumanCaptioningPermissionForUser(ADMIN_USERNAME,ADMIN_PASSWORD,"Role based","All Student",STUDENT_USERNAME, STUDENT_PASSWORD,STUDENT_FULLNAME, "YuJa Pro Captioning","Yes",VIDEONAME);
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
					ap.checkHumanCaptioningPermissionForUser(ADMIN_USERNAME,ADMIN_PASSWORD,"Role based", "All Student",STUDENT_USERNAME, STUDENT_PASSWORD,STUDENT_FULLNAME, "Caption Sync: Arjun Caption Captionsync","Yes",VIDEONAME);
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
					ap.checkHumanCaptioningPermissionForUser(ADMIN_USERNAME,ADMIN_PASSWORD,"User based", "manager_leo" ,ITMANAGER_USERNAME, ITMANAGER_PASSWORD,ITMANAGER_FULLNAME, "All Caption Providers","Yes",VIDEONAME);
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
					ap.checkHumanCaptioningPermissionForUser(ADMIN_USERNAME,ADMIN_PASSWORD,"User based", "manager_leo" ,ITMANAGER_USERNAME, ITMANAGER_PASSWORD,ITMANAGER_FULLNAME, "Rev: Rev","Yes",VIDEONAME);
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
					ap.checkHumanCaptioningPermissionForUser(ADMIN_USERNAME,ADMIN_PASSWORD,"User based", "manager_leo" ,ITMANAGER_USERNAME, ITMANAGER_PASSWORD,ITMANAGER_FULLNAME, "Three Play: Working","Yes",VIDEONAME);
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
					ap.checkHumanCaptioningPermissionForUser(ADMIN_USERNAME,ADMIN_PASSWORD,"User based", "manager_leo" ,ITMANAGER_USERNAME, ITMANAGER_PASSWORD,ITMANAGER_FULLNAME, "Cielo24: yuja","Yes",VIDEONAME);
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
					ap.checkHumanCaptioningPermissionForUser(ADMIN_USERNAME,ADMIN_PASSWORD,"User based", "manager_leo" ,ITMANAGER_USERNAME, ITMANAGER_PASSWORD,ITMANAGER_FULLNAME, "YuJa Pro Captioning","Yes",VIDEONAME);
				}
				catch (Exception e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
					reportStep("@Method "+Scenario_Name +" exception to be handled", "FAIL", true);
				}
			  }
	

	
}
