package com.yuja.evp.testclasses;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.yuja.evp.pagetestmethods.MediaLibraryPageTestMethods;
import com.yuja.evp.reports.Report;
import com.yuja.evp.utilities.Driver;

public class MediaLibraryTest extends BaseTest{
		
	 @Test(description="Video_upload_manager_test")
	  @Parameters({"TestName"})
	  public void Video_upload_manager_test(String testName) {
		MediaLibraryPageTestMethods mediaLibrary = new MediaLibraryPageTestMethods();
		try {
			mediaLibrary.uploadMedia(prop.getProperty("ManagerID"), prop.getProperty("Password"), "videoFolder", "src\\fileResources\\videoFiles\\videoType");
			mediaLibrary.deleteFolder("videoFolder");
		}catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			Report.reportStep(Driver.getDriver(), "The test case did not complete all of its steps", "FAIL", false);
		}
	 }
	 
	 @Test(description="Video_upload_instructor_test")
	  @Parameters({"TestName"})
	  public void Video_upload_instructor_test(String testName) {
		MediaLibraryPageTestMethods mediaLibrary = new MediaLibraryPageTestMethods();
		try {
			mediaLibrary.uploadMedia(prop.getProperty("InstructorID"), prop.getProperty("Password"), "videoFolder", "src\\fileResources\\videoFiles\\videoType");
			mediaLibrary.deleteFolder("videoFolder");
		}catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			Report.reportStep(Driver.getDriver(), "The test case did not complete all of its steps", "FAIL", false);
		}
	 }
	 
	 @Test(description="Video_upload_student_test")
	  @Parameters({"TestName"})
	  public void Video_upload_student_test(String testName) {
		MediaLibraryPageTestMethods mediaLibrary = new MediaLibraryPageTestMethods();
		try {
			mediaLibrary.uploadMedia(prop.getProperty("StudentID"), prop.getProperty("Password"), "videoFolder", "src\\fileResources\\videoFiles\\videoType");
			mediaLibrary.deleteFolder("videoFolder");
		}catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			Report.reportStep(Driver.getDriver(), "The test case did not complete all of its steps", "FAIL", false);
		}
	 }
	 
	 @Test(description="Video_quality_upload_manager_test")
	  @Parameters({"TestName"})
	  public void Video_quality_upload_manager_test(String testName) {
		MediaLibraryPageTestMethods mediaLibrary = new MediaLibraryPageTestMethods();
		try {
			mediaLibrary.uploadMedia(prop.getProperty("ManagerID"), prop.getProperty("Password"), "videoQualityFolder", "src\\fileResources\\videoFiles\\videoQuality");
			mediaLibrary.deleteFolder("videoQualityFolder");
		}catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			Report.reportStep(Driver.getDriver(), "The test case did not complete all of its steps", "FAIL", false);
		}
	 }
	 
	 @Test(description="Video_quality_upload_instructor_test")
	  @Parameters({"TestName"})
	  public void Video_quality_upload_instructor_test(String testName) {
		MediaLibraryPageTestMethods mediaLibrary = new MediaLibraryPageTestMethods();
		try {
			mediaLibrary.uploadMedia(prop.getProperty("InstructorID"), prop.getProperty("Password"), "videoQualityFolder", "src\\fileResources\\videoFiles\\videoQuality");
			mediaLibrary.deleteFolder("videoQualityFolder");
		}catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			Report.reportStep(Driver.getDriver(), "The test case did not complete all of its steps", "FAIL", false);
		}
	 }
	 
	 @Test(description="Video_quality_upload_student_test")
	  @Parameters({"TestName"})
	  public void Video_quality_upload_student_test(String testName) {
		MediaLibraryPageTestMethods mediaLibrary = new MediaLibraryPageTestMethods();
		try {
			mediaLibrary.uploadMedia(prop.getProperty("StudentID"), prop.getProperty("Password"), "videoQualityFolder", "src\\fileResources\\videoFiles\\videoQuality");
			mediaLibrary.deleteFolder("videoQualityFolder");
		}catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			Report.reportStep(Driver.getDriver(), "The test case did not complete all of its steps", "FAIL", false);
		}
	 }
	 
	 @Test(description="Audio_upload_manager_test")
	  @Parameters({"TestName"})
	  public void Audio_upload_manager_test(String testName) {
		MediaLibraryPageTestMethods mediaLibrary = new MediaLibraryPageTestMethods();
		try {
			mediaLibrary.uploadMedia(prop.getProperty("ManagerID"), prop.getProperty("Password"), "audioFolder", "src\\fileResources\\audioFiles");
			mediaLibrary.deleteFolder("audioFolder");
		}catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			Report.reportStep(Driver.getDriver(), "The test case did not complete all of its steps", "FAIL", false);
		}
	 }
	 
	 @Test(description="Audio_upload_instructor_test")
	  @Parameters({"TestName"})
	  public void Audio_upload_instructor_test(String testName) {
		MediaLibraryPageTestMethods mediaLibrary = new MediaLibraryPageTestMethods();
		try {
			mediaLibrary.uploadMedia(prop.getProperty("InstructorID"), prop.getProperty("Password"), "audioFolder", "src\\fileResources\\audioFiles");
			mediaLibrary.deleteFolder("audioFolder");
		}catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			Report.reportStep(Driver.getDriver(), "The test case did not complete all of its steps", "FAIL", false);
		}
	 }
	 
	 @Test(description="Audio_upload_student_test")
	  @Parameters({"TestName"})
	  public void Audio_upload_student_test(String testName) {
		MediaLibraryPageTestMethods mediaLibrary = new MediaLibraryPageTestMethods();
		try {
			mediaLibrary.uploadMedia(prop.getProperty("StudentID"), prop.getProperty("Password"), "audioFolder", "src\\fileResources\\audioFiles");
			mediaLibrary.deleteFolder("audioFolder");
		}catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			Report.reportStep(Driver.getDriver(), "The test case did not complete all of its steps", "FAIL", false);
		}
	 }
	 
	 @Test(description="Document_upload_manager_test")
	  @Parameters({"TestName"})
	  public void Document_upload_manager_test(String testName) {
		MediaLibraryPageTestMethods mediaLibrary = new MediaLibraryPageTestMethods();
		try {
			mediaLibrary.uploadMedia(prop.getProperty("ManagerID"), prop.getProperty("Password"), "documentFolder", "src\\fileResources\\documentFiles");
			mediaLibrary.deleteFolder("documentFolder");
		}catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			Report.reportStep(Driver.getDriver(), "The test case did not complete all of its steps", "FAIL", false);
		}
	 }
	 
	 @Test(description="Document_upload_instructor_test")
	  @Parameters({"TestName"})
	  public void Document_upload_instructor_test(String testName) {
		MediaLibraryPageTestMethods mediaLibrary = new MediaLibraryPageTestMethods();
		try {
			mediaLibrary.uploadMedia(prop.getProperty("InstructorID"), prop.getProperty("Password"), "documentFolder", "src\\fileResources\\documentFiles");
			mediaLibrary.deleteFolder("documentFolder");
		}catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			Report.reportStep(Driver.getDriver(), "The test case did not complete all of its steps", "FAIL", false);
		}
	 }
	 
	 @Test(description="Document_upload_student_test")
	  @Parameters({"TestName"})
	  public void Document_upload_student_test(String testName) {
		MediaLibraryPageTestMethods mediaLibrary = new MediaLibraryPageTestMethods();
		try {
			mediaLibrary.uploadMedia(prop.getProperty("StudentID"), prop.getProperty("Password"), "documentFolder", "src\\fileResources\\documentFiles");
			mediaLibrary.deleteFolder("documentFolder");
		}catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			Report.reportStep(Driver.getDriver(), "The test case did not complete all of its steps", "FAIL", false);
		}
	 }
	 
	 //////////////////////////////////////////////////////////////////////////
	 
	 @Test(description="Create_delete_folder_manager_test")
	  @Parameters({"TestName"})
	  public void Create_delete_folder_manager_test(String testName) {
		MediaLibraryPageTestMethods mediaLibrary = new MediaLibraryPageTestMethods();
		try {
			mediaLibrary.checkCreateAndDeleteFolder(prop.getProperty("ManagerID"), prop.getProperty("Password"),  "temp folder");
		}catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			Report.reportStep(Driver.getDriver(), "The test case did not complete all of its steps", "FAIL", false);
		}
	 }
	 
	 @Test(description="Create_delete_folder_instructor_test")
	  @Parameters({"TestName"})
	  public void Create_delete_folder_instructor_test(String testName) {
		MediaLibraryPageTestMethods mediaLibrary = new MediaLibraryPageTestMethods();
		try {
			mediaLibrary.checkCreateAndDeleteFolder(prop.getProperty("InstructorID"), prop.getProperty("Password"),  "temp folder");
		}catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			Report.reportStep(Driver.getDriver(), "The test case did not complete all of its steps", "FAIL", false);
		}
	 }
	 
	 @Test(description="Create_delete_folder_student_test")
	  @Parameters({"TestName"})
	  public void Create_delete_folder_student_test(String testName) {
		MediaLibraryPageTestMethods mediaLibrary = new MediaLibraryPageTestMethods();
		try {
			mediaLibrary.checkCreateAndDeleteFolder(prop.getProperty("StudentID"), prop.getProperty("Password"),  "temp folder");
		}catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			Report.reportStep(Driver.getDriver(), "The test case did not complete all of its steps", "FAIL", false);
		}
	 }
	 
	 @Test(description="Delete_folder_more_menu_test")
	  @Parameters({"TestName"})
	  public void delete_folder_more_menu_test(String testName) {
		MediaLibraryPageTestMethods mediaLibrary = new MediaLibraryPageTestMethods();
		try {
			mediaLibrary.navigateToMyMediaUserLogin(prop.getProperty("ManagerID"), prop.getProperty("Password"));
			mediaLibrary.deleteFolderViaMoreMenu("dope folder");
		}catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			Report.reportStep(Driver.getDriver(), "The test case did not complete all of its steps", "FAIL", false);
		}
	 }
	 /////////////////////////////////////////////////////////////////////////
	 
	 @Test(description="Share_video_full_access_manager_test")
	  @Parameters({"TestName"})
	  public void Share_video_full_access_manager_test(String testName) {
		MediaLibraryPageTestMethods mediaLibrary = new MediaLibraryPageTestMethods();
		try {
			mediaLibrary.shareMediaTest("fullAccess", "fullAccessSharedVideoManager", prop.getProperty("ManagerID"), prop.getProperty("Password"), prop.getProperty("InstructorID"), prop.getProperty("Password"), prop.getProperty("instructorName"));
			Thread.sleep(5000);
		}catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			Report.reportStep(Driver.getDriver(), "The test case did not complete all of its steps", "FAIL", false);
		}
	 }
	 
	 @Test(description="Share_video_edit_access_manager_test")
	  @Parameters({"TestName"})
	  public void Share_video_edit_access_manager_test(String testName) {
		MediaLibraryPageTestMethods mediaLibrary = new MediaLibraryPageTestMethods();
		try {
			mediaLibrary.shareMediaTest("editAccess", "editAccessSharedVideoManager", prop.getProperty("ManagerID"), prop.getProperty("Password"), prop.getProperty("InstructorID"), prop.getProperty("Password"), prop.getProperty("instructorName"));
			Thread.sleep(5000);
		}catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			Report.reportStep(Driver.getDriver(), "The test case did not complete all of its steps", "FAIL", false);
		}
	 }
	 
	 @Test(description="Share_video_read_only_manager_test")
	  @Parameters({"TestName"})
	  public void Share_video_read_only_manager_test(String testName) {
		MediaLibraryPageTestMethods mediaLibrary = new MediaLibraryPageTestMethods();
		try {
			mediaLibrary.shareMediaTest("readAccess", "readAccessSharedVideoManager", prop.getProperty("ManagerID"), prop.getProperty("Password"), prop.getProperty("InstructorID"), prop.getProperty("Password"), prop.getProperty("instructorName"));
			Thread.sleep(5000);
		}catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			Report.reportStep(Driver.getDriver(), "The test case did not complete all of its steps", "FAIL", false);
		}
	 }
	 
	 @Test(description="Share_video_full_access_instructor_test")
	  @Parameters({"TestName"})
	  public void Share_video_full_access_instructor_test(String testName) {
		MediaLibraryPageTestMethods mediaLibrary = new MediaLibraryPageTestMethods();
		try {
			mediaLibrary.shareMediaTest("fullAccess", "fullAccessSharedVideoInstructor", prop.getProperty("InstructorID"), prop.getProperty("Password"), prop.getProperty("ManagerID"), prop.getProperty("Password"), prop.getProperty("managerName"));
			Thread.sleep(5000);
		}catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			Report.reportStep(Driver.getDriver(), "The test case did not complete all of its steps", "FAIL", false);
		}
	 }
	 
	 @Test(description="Share_video_edit_access_instructor_test")
	  @Parameters({"TestName"})
	  public void Share_video_edit_access_instructor_test(String testName) {
		MediaLibraryPageTestMethods mediaLibrary = new MediaLibraryPageTestMethods();
		try {
			mediaLibrary.shareMediaTest("editAccess", "editAccessSharedVideoInstructor", prop.getProperty("InstructorID"), prop.getProperty("Password"), prop.getProperty("ManagerID"), prop.getProperty("Password"), prop.getProperty("managerName"));
			Thread.sleep(5000);
		}catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			Report.reportStep(Driver.getDriver(), "The test case did not complete all of its steps", "FAIL", false);
		}
	 }
	 
	 @Test(description="Share_video_read_only_instructor_test")
	  @Parameters({"TestName"})
	  public void Share_video_read_only_instructor_test(String testName) {
		MediaLibraryPageTestMethods mediaLibrary = new MediaLibraryPageTestMethods();
		try {
			mediaLibrary.shareMediaTest("readAccess", "readAccessSharedVideoInstructor", prop.getProperty("InstructorID"), prop.getProperty("Password"), prop.getProperty("ManagerID"), prop.getProperty("Password"), prop.getProperty("managerName"));
			Thread.sleep(5000);
		}catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			Report.reportStep(Driver.getDriver(), "The test case did not complete all of its steps", "FAIL", false);
		}
	 }
	 
	 @Test(description="Share_video_full_access_student_test")
	  @Parameters({"TestName"})
	  public void Share_video_full_access_student_test(String testName) {
		MediaLibraryPageTestMethods mediaLibrary = new MediaLibraryPageTestMethods();
		try {
			mediaLibrary.shareMediaTest("fullAccess", "fullAccessSharedVideoStudent", prop.getProperty("StudentID"), prop.getProperty("Password"), prop.getProperty("InstructorID"), prop.getProperty("Password"), prop.getProperty("instructorName"));
			Thread.sleep(5000);
		}catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			Report.reportStep(Driver.getDriver(), "The test case did not complete all of its steps", "FAIL", false);
		}
	 }
	 
	 @Test(description="Share_video_edit_access_student_test")
	  @Parameters({"TestName"})
	  public void Share_video_edit_access_student_test(String testName) {
		MediaLibraryPageTestMethods mediaLibrary = new MediaLibraryPageTestMethods();
		try {
			mediaLibrary.shareMediaTest("editAccess", "editAccessSharedVideoStudent", prop.getProperty("StudentID"), prop.getProperty("Password"), prop.getProperty("InstructorID"), prop.getProperty("Password"), prop.getProperty("instructorName"));
			Thread.sleep(5000);
		}catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			Report.reportStep(Driver.getDriver(), "The test case did not complete all of its steps", "FAIL", false);
		}
	 }
	 
	 @Test(description="Share_video_read_only_student_test")
	  @Parameters({"TestName"})
	  public void Share_video_read_only_student_test(String testName) {
		MediaLibraryPageTestMethods mediaLibrary = new MediaLibraryPageTestMethods();
		try {
			mediaLibrary.shareMediaTest("readAccess", "readAccessSharedVideoStudent", prop.getProperty("StudentID"), prop.getProperty("Password"), prop.getProperty("InstructorID"), prop.getProperty("Password"), prop.getProperty("instructorName"));
			Thread.sleep(5000);
		}catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			Report.reportStep(Driver.getDriver(), "The test case did not complete all of its steps", "FAIL", false);
		}
	 }
	 
	 @Test(description="Bulkfavorite_manager_Succesful_test")
	  @Parameters({"TestName"})
	 public void Bulkfavorite_manager_Succesful_test(String TestName) {
		MediaLibraryPageTestMethods ml = new MediaLibraryPageTestMethods();
		int randomInt = (int)Math.floor(Math.random() * 100 + 1);
		String folderName = "temp folder " + randomInt;
		try {
			ml.bulkFavoriteAndUnfavoriteTest(prop.getProperty("ManagerID"), prop.getProperty("Password"), folderName);
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			Report.reportStep(Driver.getDriver(), "@Method "+Scenario_Name +" exception to be handled", "FAIL", true);
		}
	  }
	 
	 @Test(description="Bulkfavorite_instructor_Succesful_test")
	  @Parameters({"TestName"})
	 public void Bulkfavorite_instructor_Succesful_test(String TestName) {
		MediaLibraryPageTestMethods ml = new MediaLibraryPageTestMethods();
		int randomInt = (int)Math.floor(Math.random() * 100 + 1);
		String folderName = "temp folder " + randomInt;
		try {
			ml.bulkFavoriteAndUnfavoriteTest(prop.getProperty("InstructorID"), prop.getProperty("Password"), folderName);
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			Report.reportStep(Driver.getDriver(), "@Method "+Scenario_Name +" exception to be handled", "FAIL", true);
		}
	  }
	 
	 @Test(description="Bulkfavorite_student_Succesful_test")
	  @Parameters({"TestName"})
	 public void Bulkfavorite_student_Succesful_test(String TestName) {
		MediaLibraryPageTestMethods ml = new MediaLibraryPageTestMethods();
		int randomInt = (int)Math.floor(Math.random() * 100 + 1);
		String folderName = "temp folder " + randomInt;
		try {
			ml.bulkFavoriteAndUnfavoriteTest(prop.getProperty("StudentID"), prop.getProperty("Password"), folderName);
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			Report.reportStep(Driver.getDriver(), "@Method "+Scenario_Name +" exception to be handled", "FAIL", true);
		}
	  }
	 
	 
	 @Test(description="Edit_folder_manager_test")
	  @Parameters({"TestName"})
	  public void Edit_folder_manager_test(String testName) {
		 MediaLibraryPageTestMethods mediaLibrary = new MediaLibraryPageTestMethods();
		try {
			mediaLibrary.editFolder(prop.getProperty("ManagerID"), prop.getProperty("Password"), "Testing", "newFolderName", "newDescription", "newTag");
		}catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			Report.reportStep(Driver.getDriver(), "The test case did not complete all of its steps", "FAIL", false);
		}
		
	 }	
	 
	 @Test(description="Delete_media_test")
	  @Parameters({"TestName"})
	  public void Delete_media_test(String testName) {
		 MediaLibraryPageTestMethods mediaLibrary = new MediaLibraryPageTestMethods();
		try {
			mediaLibrary.checkDeleteMedia(prop.getProperty("ManagerID"), prop.getProperty("Password"));
		}catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			Report.reportStep(Driver.getDriver(), "The test case did not complete all of its steps", "FAIL", false);
		}
	 }
	 
	 @Test(description="Publish_and_unpublish_media_Manager_test")
	  @Parameters({"TestName"})
	  public void Publish_and_unpublish_media_Manager_test(String testName) {
		 MediaLibraryPageTestMethods mediaLibrary = new MediaLibraryPageTestMethods();
		try {
			mediaLibrary.checkPublishandUnpublishMediaLibrary(prop.getProperty("ManagerID"), prop.getProperty("Password"),"publishVideo","Automation 1337");
		}catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			Report.reportStep(Driver.getDriver(), "The test case did not complete all of its steps", "FAIL", false);
		}
	 }
	 
	 @Test(description="Publish_and_unpublish_media_Instructor__test")
	  @Parameters({"TestName"})
	  public void Publish_and_unpublish_media_Instructor_test(String testName) {
		 MediaLibraryPageTestMethods mediaLibrary = new MediaLibraryPageTestMethods();
		try {
			mediaLibrary.checkPublishandUnpublishMediaLibrary(prop.getProperty("InstructorID"), prop.getProperty("Password"),"publishVideo","Automation 1337");
		}catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			Report.reportStep(Driver.getDriver(), "The test case did not complete all of its steps", "FAIL", false);
		}
	 }
	 
}
