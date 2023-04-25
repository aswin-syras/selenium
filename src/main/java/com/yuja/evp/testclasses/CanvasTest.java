package com.yuja.evp.testclasses;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import io.opentelemetry.exporter.logging.SystemOutLogExporter;

import com.yuja.evp.reports.Report;
import com.yuja.evp.utilities.Driver;
import com.yuja.lms.canvas.CanvasPage;
import com.yuja.lms.moodle.MoodlePage;



public class CanvasTest extends BaseTest {
	
	 String LTILinkName="'TEST AUTOMATION STAGING 1.3'";        
     String videoName="videoforautomation";
     String videoNameinstructor="videoforautomationinstructor";
     String videoNameforPlaybackquiz="video for playback";
     String audioName="audioforautomation";
     String audioNameinstructor="audioforautomationinstructor";
     String documentName="automationdocument";
     String documentNameinstructor="automationdocumentinstructor";
     String QuizTitle= "canvasvideoquiz";
     String playbackQuizTitle= "canvasplaybackquiz";
     String question = "Fav lms";
     String option1 = "moodle";
     String option2 = "blackboard";
     String fitbPossibleans1="moodle";
     String fitbPossibleans2="blackboard";
     String hint="new";
     String studentFullName="NA automationstudent ";
     String studentNameinActivityLog="automationstudent NA";
     String embedVideoName= "'videoforautomation'";
     String embedAudioName="'audioforautomation'";
     String embedDocumentName="'automationdocument'";
     String embedVideoNameinstructor= "'videoforautomationinstructor'";
     String embedAudioNameinstructor="'audioforautomationinstructor'";
     String embedDocumentNameinstructor="'automationdocumentinstructor'";
     String studentAnsForShortAnsQuestion="moodle";
     String studentAnsForFitbQuestion="moodle";
     String courseNameForQuizPublish = "AUTOMATION CANVAS COURSE";
     String marks="100%";
     String courseNameForAutoProvisioning="AUTOPROVISION";
     String courseCodeForAutoProvisioning="AP";
     String courseNameForManualnewProvisioning="MANUALPROVISIONNEW";
     String courseCodeForManualnewProvisioning="MPN";
     String courseNameForManualexistingProvisioning="MANUALPROVISIONEXISTING";
     String courseCodeForManualexistingProvisioning="MPE";
     String userNameforProvisioning="canvasnewuser";
     String emailOfUserforProvisioning="canvasuser@yuja.com";
     String passwordOfUserforProvisioning="jamNOW123/";
     String courseforEnrollingUserforUserProvisioning="USER PROVISIONING CANVAS";
     String[] courseArrayforEnrollingUserforUserProvisioning={"USER PROVISIONING CANVAS1","USER PROVISIONING CANVAS2","USER PROVISIONING CANVAS3"};
     String userRoleinCanvasCourse="Manager"; //can be Teacher corresponding yuja role is GroupOwner or Student corresponding yuja role is GroupMember
     String userRoleinYujaCourse="GroupOwner";
     String rolemappingUsername="rolemappingusercanvas";
     String directoryPathForVideo="src\\fileResources\\lmsVideo";
     String directoryPathForAudio="src\\fileResources\\lmsAudio";
     String directoryPathForDoc="src\\fileResources\\lmsDocument";
    
	
	 @Test(description="Canvas_create_publish_attend_gradebook_administrator_test")
	 @Parameters({"TestName"})
	 public void Canvas_create_publish_attend_gradebook_administrator_test(String TestName) throws Exception {
	 CanvasPage cp = new CanvasPage();
	 cp.loginCreateandPublishEmbedQuiz(prop.getProperty("Canvas.adminusername"), prop.getProperty("Canvas.password"),LTILinkName,videoName,QuizTitle,question,option1, option2,fitbPossibleans1, fitbPossibleans2,hint,prop.getProperty("Canvas.studentusername"), prop.getProperty("Canvas.password"), studentAnsForShortAnsQuestion,studentAnsForFitbQuestion,courseNameForQuizPublish,marks,studentFullName,studentNameinActivityLog);
	 }
		
	 @Test(description="Canvas_create_publish_attend_gradebook_instructor_test")                                                                                                                                                                                                                                                                          
	 @Parameters({"TestName"})                                                                                                                                                                                                                                                                                        
	 public void Canvas_create_publish_attend_gradebook_instructor_test(String TestName) throws Exception {                                                                                                                                                                                                                                                                
     CanvasPage cp = new CanvasPage();                                                                                                                                                                                                                                                                                 
     cp.loginCreateandPublishEmbedQuiz(prop.getProperty("Canvas.instructorusername"), prop.getProperty("Canvas.password"),LTILinkName,videoNameinstructor,QuizTitle,question,option1, option2,fitbPossibleans1, fitbPossibleans2,hint,prop.getProperty("Canvas.studentusername"), prop.getProperty("Canvas.password"), studentAnsForShortAnsQuestion,studentAnsForFitbQuestion,courseNameForQuizPublish,marks,studentFullName,studentNameinActivityLog);         
     }
			
	 @Test(description="Canvas_create_playback_attend_gradebook_administrator_test")
     @Parameters({"TestName"})
	 public void Canvas_create_playback_attend_gradebook_administrator_test(String TestName) throws Exception {
	 CanvasPage cp = new CanvasPage();
     cp.loginCreateandEmbedPlaybackQuiz(prop.getProperty("Canvas.adminusername"), prop.getProperty("Canvas.password"),LTILinkName,videoNameforPlaybackquiz, playbackQuizTitle,prop.getProperty("Canvas.studentusername"), prop.getProperty("Canvas.password"), courseNameForQuizPublish ,marks,studentFullName,studentNameinActivityLog);
	 }
			  	  
	 @Test(description="Canvas_create_playback_attend_gradebook_instructor_test")                                                                                                                                                                                                                                                                          
	 @Parameters({"TestName"})                                                                                                                                                                                                                                                                                        
	 public void Canvas_create_playback_attend_gradebook_instructor_test(String TestName) throws Exception {                                                                                                                                                                                                                                                                
	 CanvasPage cp = new CanvasPage();                                                                                                                                                                                                                                                                               
	 cp.loginCreateandEmbedPlaybackQuiz(prop.getProperty("Canvas.instructorusername"), prop.getProperty("Canvas.password"),LTILinkName,videoNameforPlaybackquiz, playbackQuizTitle,prop.getProperty("Canvas.studentusername"), prop.getProperty("Canvas.password"), courseNameForQuizPublish ,marks,studentFullName,studentNameinActivityLog);         
	 }
				
	 @Test(description="Canvas_embed_video_as_instructor_check_accessible_by_student_test")                                                                                                                                                                                                                                                                          
	 @Parameters({"TestName"})                                                                                                                                                                                                                                                                                        
	 public void Canvas_embed_video_as_instructor_check_accessible_by_student_test(String TestName) throws InterruptedException {                                                                                                                                                                                                                                                                
     CanvasPage cp = new CanvasPage();                                                                                                                                                                                                                                                                                 
	 cp.loginEmbedMedia(prop.getProperty("Canvas.instructorusername"), prop.getProperty("Canvas.password"),videoNameinstructor,prop.getProperty("Canvas.studentusername"), prop.getProperty("Canvas.password"), embedVideoNameinstructor,LTILinkName);         
	 } 
			
	 @Test(description="Canvas_embed_video_as_administrator_check_accessible_by_student_test")                                                                                                                                                                                                                                                                          
	 @Parameters({"TestName"})                                                                                                                                                                                                                                                                                        
	 public void Canvas_embed_video_as_administrator_check_accessible_by_student_test(String TestName) throws InterruptedException {                                                                                                                                                                                                                                                                
     CanvasPage cp = new CanvasPage();                                                                                                                                                                                                                                                                                
	 cp.loginEmbedMedia(prop.getProperty("Canvas.adminusername"), prop.getProperty("Canvas.password"),videoName,prop.getProperty("Canvas.studentusername"), prop.getProperty("Canvas.password"), embedVideoName,LTILinkName);         
	 }
			
	 @Test(description="Canvas_embed_audio_as_instructor_check_accessible_by_student_test")                                                                                                                                                                                                                                                                          
	 @Parameters({"TestName"})                                                                                                                                                                                                                                                                                        
	 public void Canvas_embed_audio_as_instructor_check_accessible_by_student_test(String TestName) throws InterruptedException {                                                                                                                                                                                                                                                                
	 CanvasPage cp = new CanvasPage();                                                                                                                                                                                                                                                                                 
	 cp.loginEmbedMedia(prop.getProperty("Canvas.instructorusername"), prop.getProperty("Canvas.password"),audioNameinstructor,prop.getProperty("Canvas.studentusername"), prop.getProperty("Canvas.password"), embedAudioNameinstructor,LTILinkName);         
	 } 
			
	 @Test(description="Canvas_embed_audio_as_administrator_check_accessible_by_student_test")                                                                                                                                                                                                                                                                          
	 @Parameters({"TestName"})                                                                                                                                                                                                                                                                                        
	 public void Canvas_embed_audio_as_administrator_check_accessible_by_student_test(String TestName) throws InterruptedException {                                                                                                                                                                                                                                                                
     CanvasPage cp = new CanvasPage();                                                                                                                                                                                                                                                                               
	 cp.loginEmbedMedia(prop.getProperty("Canvas.adminusername"), prop.getProperty("Canvas.password"),audioName,prop.getProperty("Canvas.studentusername"), prop.getProperty("Canvas.password"), embedAudioName,LTILinkName);         
	 }   
			
	 @Test(description="Canvas_embed_document_as_instructor_check_accessible_by_student_test")                                                                                                                                                                                                                                                                          
	 @Parameters({"TestName"})                                                                                                                                                                                                                                                                                        
	 public void Canvas_embed_document_as_instructor_check_accessible_by_student_test(String TestName) throws InterruptedException {                                                                                                                                                                                                                                                                
	 CanvasPage cp = new CanvasPage();                                                                                                                                                                                                                                                                                
	 cp.loginEmbedMedia(prop.getProperty("Canvas.instructorusername"), prop.getProperty("Canvas.password"),documentNameinstructor,prop.getProperty("Canvas.studentusername"), prop.getProperty("Canvas.password"), embedDocumentNameinstructor,LTILinkName);         
	  } 
	          
	 @Test(description="Canvas_embed_document_as_administrator_check_accessible_by_student_test")                                                                                                                                                                                                                                                                          
	 @Parameters({"TestName"})                                                                                                                                                                                                                                                                                        
	 public void Canvas_embed_document_as_administrator_check_accessible_by_student_test(String TestName) throws InterruptedException {                                                                                                                                                                                                                                                                
	 CanvasPage cp = new CanvasPage();                                                                                                                                                                                                                                                                                
	 cp.loginEmbedMedia(prop.getProperty("Canvas.adminusername"), prop.getProperty("Canvas.password"),documentName,prop.getProperty("Canvas.studentusername"), prop.getProperty("Canvas.password"), embedDocumentName,LTILinkName);         
	 } 
			
	 @Test(description="Canvas_upload_videos_as_administrator_check_in_media_library_test")                                                                                                                                                                                                                                                                          
	 @Parameters({"TestName"})                                                                                                                                                                                                                                                                                        
	 public void Canvas_upload_videos_as_administrator_check_in_media_library_test(String TestName) throws InterruptedException {                                                                                                                                                                                                                                                                
     CanvasPage cp = new CanvasPage();                                                                                                                                                                                                                                                                               
	 cp.CIMMediaChooserMediaUpload(prop.getProperty("Canvas.adminusername"), prop.getProperty("Canvas.password"), directoryPathForVideo,LTILinkName,embedVideoName);         
	 } 
			
     @Test(description="Canvas_upload_videos_as_instuctor_check_in_media_library_test")                                                                                                                                                                                                                                                                          
	 @Parameters({"TestName"})                                                                                                                                                                                                                                                                                        
	 public void Canvas_upload_videos_as_instuctor_check_in_media_library_test(String TestName) throws InterruptedException {                                                                                                                                                                                                                                                                
	 CanvasPage cp = new CanvasPage();                                                                                                                                                                                                                                                                                 
	 cp.CIMMediaChooserMediaUpload(prop.getProperty("Canvas.instructorusername"), prop.getProperty("Canvas.password"),directoryPathForVideo,LTILinkName,embedVideoName);         
	 }  
			
	 @Test(description="Canvas_upload_audio_as_administrator_check_in_media_library_test")                                                                                                                                                                                                                                                                          
	 @Parameters({"TestName"})                                                                                                                                                                                                                                                                                        
	 public void Canvas_upload_audio_as_administrator_check_in_media_library_test(String TestName) throws InterruptedException {                                                                                                                                                                                                                                                                
	 CanvasPage cp = new CanvasPage();                                                                                                                                                                                                                                                                                 
	 cp.CIMMediaChooserMediaUpload(prop.getProperty("Canvas.adminusername"), prop.getProperty("Canvas.password"),directoryPathForAudio,LTILinkName,embedVideoName);         
	 } 
			
     @Test(description="Canvas_upload_audio_as_instuctor_check_in_media_library_test")                                                                                                                                                                                                                                                                          
	 @Parameters({"TestName"})                                                                                                                                                                                                                                                                                        
	 public void Canvas_upload_audio_as_instuctor_check_in_media_library_test(String TestName) throws InterruptedException {                                                                                                                                                                                                                                                                
	 CanvasPage cp = new CanvasPage();                                                                                                                                                                                                                                                                                
	 cp.CIMMediaChooserMediaUpload(prop.getProperty("Canvas.instructorusername"), prop.getProperty("Canvas.password"),directoryPathForAudio,LTILinkName,embedVideoName);         
	 }
	          
	 @Test(description="Canvas_upload_doc_as_administrator_check_in_media_library_test")                                                                                                                                                                                                                                                                          
     @Parameters({"TestName"})                                                                                                                                                                                                                                                                                        
	 public void Canvas_upload_doc_as_administrator_check_in_media_library_test(String TestName) throws InterruptedException {                                                                                                                                                                                                                                                                
     CanvasPage cp = new CanvasPage();                                                                                                                                                                                                                                                                                
	 cp.CIMMediaChooserMediaUpload(prop.getProperty("Canvas.adminusername"), prop.getProperty("Canvas.password"),directoryPathForDoc,LTILinkName,embedVideoName);         
	 } 
			
	 @Test(description="Canvas_upload_doc_as_instuctor_check_in_media_library_test")                                                                                                                                                                                                                                                                          
	 @Parameters({"TestName"})                                                                                                                                                                                                                                                                                        
	 public void Canvas_upload_doc_as_instuctor_check_in_media_library_test(String TestName) throws InterruptedException {                                                                                                                                                                                                                                                                
     CanvasPage cp = new CanvasPage();                                                                                                                                                                                                                                                                                 
	 cp.CIMMediaChooserMediaUpload(prop.getProperty("Canvas.instructorusername"), prop.getProperty("Canvas.password"),directoryPathForDoc,LTILinkName,embedVideoName);         
	 }  
			
	 @Test(description="Canvas_check_autoprovisionofcourse")                                                                                                                                                                                                                                                                          
	 @Parameters({"TestName"})                                                                                                                                                                                                                                                                                        
	 public void Canvas_check_autoprovisionofcourse(String TestName) throws InterruptedException {                                                                                                                                                                                                                                                                
	 CanvasPage cp = new CanvasPage();                                                                                                                                                                                                                                                                                 
	 cp.checkAutoprovisionOfCourse(prop.getProperty("Canvas.adminusername"), prop.getProperty("Canvas.password"), courseNameForAutoProvisioning, courseCodeForAutoProvisioning,prop.getProperty("ManagerID"), prop.getProperty("Password"),LTILinkName);         
	 }  
			
	 @Test(description="Canvas_check_manualprovisionofNewcourse")                                                                                                                                                                                                                                                                          
	 @Parameters({"TestName"})                                                                                                                                                                                                                                                                                        
	 public void Canvas_check_manualprovisionofNewcourse(String TestName) throws InterruptedException {                                                                                                                                                                                                                                                                
	 CanvasPage cp = new CanvasPage();                                                                                                                                                                                                                                                                                 
	 cp.checkManualprovisionOfNewCourse(prop.getProperty("Canvas.adminusername"), prop.getProperty("Canvas.password"), courseNameForAutoProvisioning, courseCodeForAutoProvisioning,prop.getProperty("ManagerID"), prop.getProperty("Password"),LTILinkName);         
	 }  
			
	 @Test(description="Canvas_check_manualprovisionto_existingcourse")                                                                                                                                                                                                                                                                          
	 @Parameters({"TestName"})                                                                                                                                                                                                                                                                                        
	 public void Canvas_check_manualprovisionto_existingcourse(String TestName) throws InterruptedException {                                                                                                                                                                                                                                                                
	 CanvasPage cp = new CanvasPage();                                                                                                                                                                                                                                                                                 
	 cp.checkManualprovisionToExistingCourse(prop.getProperty("Canvas.adminusername"), prop.getProperty("Canvas.password"), courseNameForAutoProvisioning, courseCodeForAutoProvisioning,prop.getProperty("ManagerID"), prop.getProperty("Password"),LTILinkName);         
	 } 
	 
	 @Test(description="Canvas_check_autoprovisioning_user")                                                                                                                                                                                                                                                                          
	 @Parameters({"TestName"})                                                                                                                                                                                                                                                                                        
	 public void Canvas_check_autoprovisioning_user(String TestName) throws InterruptedException {                                                                                                                                                                                                                                                                
     CanvasPage cp = new CanvasPage();                                                                                                                                                                                                                                                                                 
	 cp.checkAutoprovisionOfUser(prop.getProperty("Canvas.adminusername"), prop.getProperty("Canvas.password"),prop.getProperty("ManagerID"), prop.getProperty("Password"),emailOfUserforProvisioning,userNameforProvisioning,passwordOfUserforProvisioning,courseforEnrollingUserforUserProvisioning,LTILinkName);
	 }  
			
	 @Test(description="Canvas_check_manualprovisioning_newuser")                                                                                                                                                                                                                                                                          
	 @Parameters({"TestName"})                                                                                                                                                                                                                                                                                        
	 public void Canvas_check_manualprovisioning_newuser(String TestName) throws InterruptedException {                                                                                                                                                                                                                                                                
	 CanvasPage cp = new CanvasPage();                                                                                                                                                                                                                                                                                 
	 cp.checkManualprovisionOfUser(prop.getProperty("Canvas.adminusername"), prop.getProperty("Canvas.password"),prop.getProperty("ManagerID"), prop.getProperty("Password"),emailOfUserforProvisioning,userNameforProvisioning,passwordOfUserforProvisioning,courseforEnrollingUserforUserProvisioning,"new","automation_student",LTILinkName);
	 } 
			
	 @Test(description="Canvas_check_manualprovisioning_existinguser")                                                                                                                                                                                                                                                                          
	 @Parameters({"TestName"})                                                                                                                                                                                                                                                                                        
	 public void Canvas_check_manualprovisioning_existinguser(String TestName) throws InterruptedException {                                                                                                                                                                                                                                                                
     CanvasPage cp = new CanvasPage();                                                                                                                                                                                                                                                                                 
	 cp.checkManualprovisionOfUser(prop.getProperty("Canvas.adminusername"), prop.getProperty("Canvas.password"),prop.getProperty("ManagerID"), prop.getProperty("Password"),emailOfUserforProvisioning,userNameforProvisioning,passwordOfUserforProvisioning,courseforEnrollingUserforUserProvisioning,"existing","automation_student",LTILinkName);
	 }  
			
	 @Test(description="Canvas_check_autoprovisioning_user_multipleCourses")                                                                                                                                                                                                                                                                          
	 @Parameters({"TestName"})                                                                                                                                                                                                                                                                                        
	 public void Canvas_check_autoprovisioning_user_multipleCourses(String TestName) throws InterruptedException {                                                                                                                                                                                                                                                                
	 CanvasPage cp = new CanvasPage();                                                                                                                                                                                                                                                                                 
	 cp.checkAutoprovisionOfUserinMultipleCourses(prop.getProperty("Canvas.adminusername"), prop.getProperty("Canvas.password"),prop.getProperty("ManagerID"), prop.getProperty("Password"),emailOfUserforProvisioning,userNameforProvisioning,passwordOfUserforProvisioning,courseArrayforEnrollingUserforUserProvisioning,LTILinkName);
	 } 
			
	 @Test(description="Check_role_mapping_unlocked")                                                                                                                                                                                                                                                                          
	 @Parameters({"TestName"})                                                                                                                                                                                                                                                                                        
	 public void Check_role_mapping_unlocked(String TestName) throws InterruptedException {                                                                                                                                                                                                                                                                
	 CanvasPage cp = new CanvasPage();                                                                                                                                                                                                                                                                                 
	 cp.roleMappingUserTypeUnlocked(prop.getProperty("ManagerID"), prop.getProperty("Password"),prop.getProperty("Canvas.adminusername"), prop.getProperty("Canvas.password"),"Student",rolemappingUsername,LTILinkName);
	 } 
			
	 @Test(description="Check_role_mapping_locked_as_student")                                                                                                                                                                                                                                                                          
	 @Parameters({"TestName"})                                                                                                                                                                                                                                                                                        
	 public void Check_role_mapping_locked_as_student(String TestName) throws InterruptedException {                                                                                                                                                                                                                                                                
	 CanvasPage cp = new CanvasPage();                                                                                                                                                                                                                                                                                 
	 cp.roleMappingUserTypeLocked(prop.getProperty("ManagerID"), prop.getProperty("Password"),prop.getProperty("Canvas.adminusername"), prop.getProperty("Canvas.password"),"Student",2,rolemappingUsername,LTILinkName);
	 }  
			
	 @Test(description="Check_role_mapping_locked_as_instructor")                                                                                                                                                                                                                                                                          
	 @Parameters({"TestName"})                                                                                                                                                                                                                                                                                        
	 public void Check_role_mapping_locked_as_instructor(String TestName) throws InterruptedException {                                                                                                                                                                                                                                                                
	 CanvasPage cp = new CanvasPage();                                                                                                                                                                                                                                                                                 
	 cp.roleMappingUserTypeLocked(prop.getProperty("ManagerID"), prop.getProperty("Password"),prop.getProperty("Canvas.adminusername"), prop.getProperty("Canvas.password"),"Instructor",3,rolemappingUsername,LTILinkName);
	 }
}
	


