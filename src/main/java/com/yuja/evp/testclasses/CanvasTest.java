package com.yuja.evp.testclasses;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import io.opentelemetry.exporter.logging.SystemOutLogExporter;

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
     String userNameforProvisioning="moodlenewuser";
     String emailOfUserforProvisioning="moodleuser@yuja.com";
     String passwordOfUserforProvisioning="jamNOW123/";
     String courseforEnrollingUserforUserProvisioning="USER PROVISIONING";
     String[] courseArrayforEnrollingUserforUserProvisioning={"USER PROVISIONING","USER PROVISIONING2","USER PROVISIONING3"};
     String userRoleinMoodleCourse="Manager"; //can be Teacher corresponding yuja role is GroupOwner or Student corresponding yuja role is GroupMember
     String userRoleinYujaCourse="GroupOwner";
     String customToolName="TEST AUTOMATION STAGING 1.3";
     String moodleToolNameOnConfiguration="TEST AUTOMATION STAGING 1.3";
     String directoryPathForVideo="src\\fileResources\\lmsVideo";
     String directoryPathForAudio="src\\fileResources\\lmsAudio";
     String directoryPathForDoc="src\\fileResources\\lmsDocument";
    
	
	 @Test(description="Canvas_create_publish_attend_gradebook_administrator_test")
	  @Parameters({"TestName"})
	public void Canvas_create_publish_attend_gradebook_administrator_test(String TestName) {
		CanvasPage cp = new CanvasPage();
		try {
			cp.loginCreateandPublishEmbedQuiz(prop.getProperty("Canvas.adminusername"), prop.getProperty("Canvas.password"),LTILinkName,videoName,QuizTitle,question,option1, option2,fitbPossibleans1, fitbPossibleans2,hint,prop.getProperty("Canvas.studentusername"), prop.getProperty("Canvas.password"), studentAnsForShortAnsQuestion,studentAnsForFitbQuestion,courseNameForQuizPublish,marks,studentFullName,studentNameinActivityLog);
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			reportStep("@Method "+Scenario_Name +" exception to be handled", "FAIL", true);
		}
	 }
		
	
		  	  
			
			@Test(description="Canvas_create_publish_attend_gradebook_instructor_test")                                                                                                                                                                                                                                                                          
	      	@Parameters({"TestName"})                                                                                                                                                                                                                                                                                        
	    	public void Canvas_create_publish_attend_gradebook_instructor_test(String TestName) {                                                                                                                                                                                                                                                                
				CanvasPage cp = new CanvasPage();                                                                                                                                                                                                                                                                                 
	      		try {                                                                                                                                                                                                                                                                                                             
	      			cp.loginCreateandPublishEmbedQuiz(prop.getProperty("Canvas.instructorusername"), prop.getProperty("Canvas.password"),LTILinkName,videoNameinstructor,QuizTitle,question,option1, option2,fitbPossibleans1, fitbPossibleans2,hint,prop.getProperty("Canvas.studentusername"), prop.getProperty("Canvas.password"), studentAnsForShortAnsQuestion,studentAnsForFitbQuestion,courseNameForQuizPublish,marks,studentFullName,studentNameinActivityLog);         

	      		}                                                                                                                                                                                                                                                                                                                 
	      		catch (Exception e) {                                                                                                                                                                                                                                                                                             
	      			System.out.println(e.getMessage());                                                                                                                                                                                                                                                                              
	      			e.printStackTrace();                                                                                                                                                                                                                                                                                             
	      			reportStep("@Method "+Scenario_Name +" exception to be handled", "FAIL", true);                                                                                                                                                                                                                                  
	      		}                                                                                                                                                                                                                                                                                                                 
	     }
			
			
			 @Test(description="Canvas_create_playback_attend_gradebook_administrator_test")
			  @Parameters({"TestName"})
			public void Canvas_create_playback_attend_gradebook_administrator_test(String TestName) {
				CanvasPage cp = new CanvasPage();
				try {
					cp.loginCreateandEmbedPlaybackQuiz(prop.getProperty("Canvas.adminusername"), prop.getProperty("Canvas.password"),LTILinkName,videoNameforPlaybackquiz, playbackQuizTitle,prop.getProperty("Canvas.studentusername"), prop.getProperty("Canvas.password"), courseNameForQuizPublish ,marks,studentFullName,studentNameinActivityLog);
				} 
				catch (Exception e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
					reportStep("@Method "+Scenario_Name +" exception to be handled", "FAIL", true);
				}
		 }
			  	  
				
				@Test(description="Canvas_create_playback_attend_gradebook_instructor_test")                                                                                                                                                                                                                                                                          
		      	@Parameters({"TestName"})                                                                                                                                                                                                                                                                                        
		    	public void Canvas_create_playback_attend_gradebook_instructor_test(String TestName) {                                                                                                                                                                                                                                                                
					CanvasPage cp = new CanvasPage();                                                                                                                                                                                                                                                                               
		      		try {                                                                                                                                                                                                                                                                                                             
		      			cp.loginCreateandEmbedPlaybackQuiz(prop.getProperty("Canvas.instructorusername"), prop.getProperty("Canvas.password"),LTILinkName,videoNameforPlaybackquiz, playbackQuizTitle,prop.getProperty("Canvas.studentusername"), prop.getProperty("Canvas.password"), courseNameForQuizPublish ,marks,studentFullName,studentNameinActivityLog);         
		      		}                                                                                                                                                                                                                                                                                                                 
		      		catch (Exception e) {                                                                                                                                                                                                                                                                                             
		      			System.out.println(e.getMessage());                                                                                                                                                                                                                                                                              
		      			e.printStackTrace();                                                                                                                                                                                                                                                                                             
		      			reportStep("@Method "+Scenario_Name +" exception to be handled", "FAIL", true);                                                                                                                                                                                                                                  
		      		}                                                                                                                                                                                                                                                                                                                 
		  }
				
			
			@Test(description="Canvas_embed_video_as_instructor_check_accessible_by_student_test")                                                                                                                                                                                                                                                                          
	      	@Parameters({"TestName"})                                                                                                                                                                                                                                                                                        
	    	public void Canvas_embed_video_as_instructor_check_accessible_by_student_test(String TestName) {                                                                                                                                                                                                                                                                
				CanvasPage cp = new CanvasPage();                                                                                                                                                                                                                                                                                 
	      		try {                                                                                                                                                                                                                                                                                                             
	      			cp.loginEmbedMedia(prop.getProperty("Canvas.instructorusername"), prop.getProperty("Canvas.password"),videoNameinstructor,prop.getProperty("Canvas.studentusername"), prop.getProperty("Canvas.password"), embedVideoNameinstructor,LTILinkName);         
	      		}                                                                                                                                                                                                                                                                                                                 
	      		catch (Exception e) {                                                                                                                                                                                                                                                                                             
	      			System.out.println(e.getMessage());                                                                                                                                                                                                                                                                              
	      			e.printStackTrace();                                                                                                                                                                                                                                                                                             
	      			reportStep("@Method "+Scenario_Name +" exception to be handled", "FAIL", true);                                                                                                                                                                                                                                  
	      		}                                                                                                                                                                                                                                                                                                                 

	      	} 
			@Test(description="Canvas_embed_video_as_administrator_check_accessible_by_student_test")                                                                                                                                                                                                                                                                          
	      	@Parameters({"TestName"})                                                                                                                                                                                                                                                                                        
	    	public void Canvas_embed_video_as_administrator_check_accessible_by_student_test(String TestName) {                                                                                                                                                                                                                                                                
				CanvasPage cp = new CanvasPage();                                                                                                                                                                                                                                                                                
	      		try {                                                                                                                                                                                                                                                                                                             
	      			cp.loginEmbedMedia(prop.getProperty("Canvas.adminusername"), prop.getProperty("Canvas.password"),videoName,prop.getProperty("Canvas.studentusername"), prop.getProperty("Canvas.password"), embedVideoName,LTILinkName);         
	      		}                                                                                                                                                                                                                                                                                                                 
	      		catch (Exception e) {                                                                                                                                                                                                                                                                                             
	      			System.out.println(e.getMessage());                                                                                                                                                                                                                                                                              
	      			e.printStackTrace();                                                                                                                                                                                                                                                                                             
	      			reportStep("@Method "+Scenario_Name +" exception to be handled", "FAIL", true);                                                                                                                                                                                                                                  
	      		}                                                                                                                                                                                                                                                                                                                 
	     }
			
	          
			
			@Test(description="Canvas_embed_audio_as_instructor_check_accessible_by_student_test")                                                                                                                                                                                                                                                                          
	      	@Parameters({"TestName"})                                                                                                                                                                                                                                                                                        
	    	public void Canvas_embed_audio_as_instructor_check_accessible_by_student_test(String TestName) {                                                                                                                                                                                                                                                                
				CanvasPage cp = new CanvasPage();                                                                                                                                                                                                                                                                                 
	      		try {                                                                                                                                                                                                                                                                                                             
	      			cp.loginEmbedMedia(prop.getProperty("Canvas.instructorusername"), prop.getProperty("Canvas.password"),audioNameinstructor,prop.getProperty("Canvas.studentusername"), prop.getProperty("Canvas.password"), embedAudioNameinstructor,LTILinkName);         
	      		}                                                                                                                                                                                                                                                                                                                 
	      		catch (Exception e) {                                                                                                                                                                                                                                                                                             
	      			System.out.println(e.getMessage());                                                                                                                                                                                                                                                                              
	      			e.printStackTrace();                                                                                                                                                                                                                                                                                             
	      			reportStep("@Method "+Scenario_Name +" exception to be handled", "FAIL", true);                                                                                                                                                                                                                                  
	      		}                                                                                                                                                                                                                                                                                                                 

	      	  } 
			@Test(description="Canvas_embed_audio_as_administrator_check_accessible_by_student_test")                                                                                                                                                                                                                                                                          
	      	@Parameters({"TestName"})                                                                                                                                                                                                                                                                                        
	    	public void Canvas_embed_audio_as_administrator_check_accessible_by_student_test(String TestName) {                                                                                                                                                                                                                                                                
				CanvasPage cp = new CanvasPage();                                                                                                                                                                                                                                                                               
	      		try {                                                                                                                                                                                                                                                                                                             
	      			cp.loginEmbedMedia(prop.getProperty("Canvas.adminusername"), prop.getProperty("Canvas.password"),audioName,prop.getProperty("Canvas.studentusername"), prop.getProperty("Canvas.password"), embedAudioName,LTILinkName);         
	      		}                                                                                                                                                                                                                                                                                                                 
	      		catch (Exception e) {                                                                                                                                                                                                                                                                                             
	      			System.out.println(e.getMessage());                                                                                                                                                                                                                                                                              
	      			e.printStackTrace();                                                                                                                                                                                                                                                                                             
	      			reportStep("@Method "+Scenario_Name +" exception to be handled", "FAIL", true);                                                                                                                                                                                                                                  
	      		}                                                                                                                                                                                                                                                                                                                 
	     }   
			
			@Test(description="Canvas_embed_document_as_instructor_check_accessible_by_student_test")                                                                                                                                                                                                                                                                          
	      	@Parameters({"TestName"})                                                                                                                                                                                                                                                                                        
	    	public void Canvas_embed_document_as_instructor_check_accessible_by_student_test(String TestName) {                                                                                                                                                                                                                                                                
				CanvasPage cp = new CanvasPage();                                                                                                                                                                                                                                                                                
	      		try {                                                                                                                                                                                                                                                                                                             
	      			cp.loginEmbedMedia(prop.getProperty("Canvas.instructorusername"), prop.getProperty("Canvas.password"),documentNameinstructor,prop.getProperty("Canvas.studentusername"), prop.getProperty("Canvas.password"), embedDocumentNameinstructor,LTILinkName);         
	      		}                                                                                                                                                                                                                                                                                                                 
	      		catch (Exception e) {                                                                                                                                                                                                                                                                                             
	      			System.out.println(e.getMessage());                                                                                                                                                                                                                                                                              
	      			e.printStackTrace();                                                                                                                                                                                                                                                                                             
	      			reportStep("@Method "+Scenario_Name +" exception to be handled", "FAIL", true);                                                                                                                                                                                                                                  
	      		}                                                                                                                                                                                                                                                                                                                 
	      	  } 
	          
			@Test(description="Canvas_embed_document_as_administrator_check_accessible_by_student_test")                                                                                                                                                                                                                                                                          
	      	@Parameters({"TestName"})                                                                                                                                                                                                                                                                                        
	    	public void Canvas_embed_document_as_administrator_check_accessible_by_student_test(String TestName) {                                                                                                                                                                                                                                                                
				CanvasPage cp = new CanvasPage();                                                                                                                                                                                                                                                                                
	      		try {                                                                                                                                                                                                                                                                                                             
	      			cp.loginEmbedMedia(prop.getProperty("Canvas.adminusername"), prop.getProperty("Canvas.password"),documentName,prop.getProperty("Canvas.studentusername"), prop.getProperty("Canvas.password"), embedDocumentName,LTILinkName);         
	      		}                                                                                                                                                                                                                                                                                                                 
	      		catch (Exception e) {                                                                                                                                                                                                                                                                                             
	      			System.out.println(e.getMessage());                                                                                                                                                                                                                                                                              
	      			e.printStackTrace();                                                                                                                                                                                                                                                                                             
	      			reportStep("@Method "+Scenario_Name +" exception to be handled", "FAIL", true);                                                                                                                                                                                                                                  
	      		}                                                                                                                                                                                                                                                                                                                 
	     } 
			
			
			@Test(description="Canvas_upload_videos_as_administrator_check_in_media_library_test")                                                                                                                                                                                                                                                                          
	      	@Parameters({"TestName"})                                                                                                                                                                                                                                                                                        
	    	public void Canvas_upload_videos_as_administrator_check_in_media_library_test(String TestName) {                                                                                                                                                                                                                                                                
				CanvasPage cp = new CanvasPage();                                                                                                                                                                                                                                                                               
	      		try {                                                                                                                                                                                                                                                                                                             
	      			cp.CIMMediaChooserMediaUpload(prop.getProperty("Canvas.adminusername"), prop.getProperty("Canvas.password"), directoryPathForVideo,LTILinkName,embedVideoName);         
	      		}                                                                                                                                                                                                                                                                                                                 
	      		catch (Exception e) {                                                                                                                                                                                                                                                                                             
	      			System.out.println(e.getMessage());                                                                                                                                                                                                                                                                              
	      			e.printStackTrace();                                                                                                                                                                                                                                                                                             
	      			reportStep("@Method "+Scenario_Name +" exception to be handled", "FAIL", true);                                                                                                                                                                                                                                  
	      		}                                                                                                                                                                                                                                                                                                                 
	     } 
			
			
			@Test(description="Canvas_upload_videos_as_instuctor_check_in_media_library_test")                                                                                                                                                                                                                                                                          
	      	@Parameters({"TestName"})                                                                                                                                                                                                                                                                                        
	    	public void Canvas_upload_videos_as_instuctor_check_in_media_library_test(String TestName) {                                                                                                                                                                                                                                                                
				CanvasPage cp = new CanvasPage();                                                                                                                                                                                                                                                                                 
	      		try {                                                                                                                                                                                                                                                                                                             
	      			cp.CIMMediaChooserMediaUpload(prop.getProperty("Canvas.instructorusername"), prop.getProperty("Canvas.password"),directoryPathForVideo,LTILinkName,embedVideoName);         
	      		}                                                                                                                                                                                                                                                                                                                 
	      		catch (Exception e) {                                                                                                                                                                                                                                                                                             
	      			System.out.println(e.getMessage());                                                                                                                                                                                                                                                                              
	      			e.printStackTrace();                                                                                                                                                                                                                                                                                             
	      			reportStep("@Method "+Scenario_Name +" exception to be handled", "FAIL", true);                                                                                                                                                                                                                                  
	      		}                                                                                                                                                                                                                                                                                                                 
	      }  
			
			
			@Test(description="Canvas_upload_audio_as_administrator_check_in_media_library_test")                                                                                                                                                                                                                                                                          
	      	@Parameters({"TestName"})                                                                                                                                                                                                                                                                                        
	    	public void Canvas_upload_audio_as_administrator_check_in_media_library_test(String TestName) {                                                                                                                                                                                                                                                                
				CanvasPage cp = new CanvasPage();                                                                                                                                                                                                                                                                                 
	      		try {                                                                                                                                                                                                                                                                                                             
	      			cp.CIMMediaChooserMediaUpload(prop.getProperty("Canvas.adminusername"), prop.getProperty("Canvas.password"),directoryPathForAudio,LTILinkName,embedVideoName);         
	      		}                                                                                                                                                                                                                                                                                                                 
	      		catch (Exception e) {                                                                                                                                                                                                                                                                                             
	      			System.out.println(e.getMessage());                                                                                                                                                                                                                                                                              
	      			e.printStackTrace();                                                                                                                                                                                                                                                                                             
	      			reportStep("@Method "+Scenario_Name +" exception to be handled", "FAIL", true);                                                                                                                                                                                                                                  
	      		}                                                                                                                                                                                                                                                                                                                 
	      } 
			
			@Test(description="Canvas_upload_audio_as_instuctor_check_in_media_library_test")                                                                                                                                                                                                                                                                          
	      	@Parameters({"TestName"})                                                                                                                                                                                                                                                                                        
	    	public void Canvas_upload_audio_as_instuctor_check_in_media_library_test(String TestName) {                                                                                                                                                                                                                                                                
				CanvasPage cp = new CanvasPage();                                                                                                                                                                                                                                                                                
	      		try {                                                                                                                                                                                                                                                                                                             
	      			cp.CIMMediaChooserMediaUpload(prop.getProperty("Canvas.instructorusername"), prop.getProperty("Canvas.password"),directoryPathForAudio,LTILinkName,embedVideoName);         
	      		}                                                                                                                                                                                                                                                                                                                 
	      		catch (Exception e) {                                                                                                                                                                                                                                                                                             
	      			System.out.println(e.getMessage());                                                                                                                                                                                                                                                                              
	      			e.printStackTrace();                                                                                                                                                                                                                                                                                             
	      			reportStep("@Method "+Scenario_Name +" exception to be handled", "FAIL", true);                                                                                                                                                                                                                                  
	      		}                                                                                                                                                                                                                                                                                                                 

	      	 }
	          
			@Test(description="Canvas_upload_doc_as_administrator_check_in_media_library_test")                                                                                                                                                                                                                                                                          

	      	@Parameters({"TestName"})                                                                                                                                                                                                                                                                                        
	    	public void Canvas_upload_doc_as_administrator_check_in_media_library_test(String TestName) {                                                                                                                                                                                                                                                                
				CanvasPage cp = new CanvasPage();                                                                                                                                                                                                                                                                                
	      		try {                                                                                                                                                                                                                                                                                                             
	      			cp.CIMMediaChooserMediaUpload(prop.getProperty("Canvas.adminusername"), prop.getProperty("Canvas.password"),directoryPathForDoc,LTILinkName,embedVideoName);         
	      		}                                                                                                                                                                                                                                                                                                                 
	      		catch (Exception e) {                                                                                                                                                                                                                                                                                             
	      			System.out.println(e.getMessage());                                                                                                                                                                                                                                                                              
	      			e.printStackTrace();                                                                                                                                                                                                                                                                                             
	      			reportStep("@Method "+Scenario_Name +" exception to be handled", "FAIL", true);                                                                                                                                                                                                                                  
	      		}                                                                                                                                                                                                                                                                                                                 
	     } 
			
			
			@Test(description="Canvas_upload_doc_as_instuctor_check_in_media_library_test")                                                                                                                                                                                                                                                                          
	      	@Parameters({"TestName"})                                                                                                                                                                                                                                                                                        
	    	public void Canvas_upload_doc_as_instuctor_check_in_media_library_test(String TestName) {                                                                                                                                                                                                                                                                
				CanvasPage cp = new CanvasPage();                                                                                                                                                                                                                                                                                 
	      		try {                                                                                                                                                                                                                                                                                                             
	      			cp.CIMMediaChooserMediaUpload(prop.getProperty("Canvas.instructorusername"), prop.getProperty("Canvas.password"),directoryPathForDoc,LTILinkName,embedVideoName);         
	      		}                                                                                                                                                                                                                                                                                                                 
	      		catch (Exception e) {                                                                                                                                                                                                                                                                                             
	      			System.out.println(e.getMessage());                                                                                                                                                                                                                                                                              
	      			e.printStackTrace();                                                                                                                                                                                                                                                                                             
	      			reportStep("@Method "+Scenario_Name +" exception to be handled", "FAIL", true);                                                                                                                                                                                                                                  
	      		}                                                                                                                                                                                                                                                                                                                 
	      }  
	}


