package com.yuja.evp.testclasses;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.yuja.evp.pagetestmethods.QuizPageTestMethods;
import com.yuja.lms.moodle.MoodlePage;

public class MoodleTest extends BaseTest {
	     String adminUserName="automationadmin";
	     String adminPassword="jamNOW123/"; 
	     String instuctorUserName="automationinstructor"; 
	     String instuctorPassword="jamNOW123/";
	     String LTILinkName="'Xavier LTI 1.3'";        
	     String videoName="videoforautomation";
	     String videoNameinstructor="videoforautomationinstructor";
	     String videoNameforPlaybackquiz="video for playback";
	     String audioName="audioforautomation";
	     String audioNameinstructor="audioforautomationinstrctor";
	     String documentName="automationdocument";
	     String documentNameinstructor="automationdocumentinstructor";
	     String QuizTitle= "moodlevideoquiz";
	     String playbackQuizTitle="moodleplaybackquiz";
	     String question="Fav lms";
	     String option1="moodle";
	     String option2="blackboard";
	     String fitbPossibleans1="moodle";
	     String fitbPossibleans2="blackboard";
	     String hint="new";
	     String studentUserName="automationstudent";
	     String studentFullName="student automation";
	     String studentNameinActivityLog="automation student";
	     String studentPassword="jamNOW123/";
	     String embedVideoName= "'videoforautomation'";
	     String embedAudioName="'audioforautomation'";
	     String embedDocumentName="'automationdocument'";
	     String embedVideoNameinstructor= "'videoforautomationinstructor'";
	     String embedAudioNameinstructor="'audioforautomationinstructor'";
	     String embedDocumentNameinstructor="'automationdocumentinstructor'";
	     String studentAnsForShortAnsQuestion="moodle";
	     String studentAnsForFitbQuestion="moodle";
	     String courseName="AUTOMATION MOODLE COURSE";
	     String marks="100%"; 
	     String directoryPathForVideo="src\\fileResources\\lmsVideo";
         String directoryPathForAudio="src\\fileResources\\lmsAudio";
	     String directoryPathForDoc="src\\fileResources\\lmsDocument";

	
	  @Test(description="Moodle_create_publish_attend_gradebook_administrator_test")
	  @Parameters({"TestName"})
	public void Moodle_create_publish_attend_gradebook_administrator_test(String TestName) {
		MoodlePage mp = new MoodlePage();
		try {
			mp.loginCreateandPublishEmbedQuiz(adminUserName, adminPassword,LTILinkName,videoName,QuizTitle,question,option1, option2,fitbPossibleans1, fitbPossibleans2,hint,studentUserName, studentPassword, studentAnsForShortAnsQuestion,studentAnsForFitbQuestion,courseName,marks,studentFullName,studentNameinActivityLog);
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			reportStep("@Method "+Scenario_Name +" exception to be handled", "FAIL", true);
		}
	}
	  	  
		
		@Test(description="Moodle_create_publish_attend_gradebook_instructor_test")                                                                                                                                                                                                                                                                          
      	@Parameters({"TestName"})                                                                                                                                                                                                                                                                                        
    	public void Moodle_create_publish_attend_gradebook_instructor_test(String TestName) {                                                                                                                                                                                                                                                                
      		MoodlePage mp = new MoodlePage();                                                                                                                                                                                                                                                                                 
      		try {                                                                                                                                                                                                                                                                                                             
      			mp.loginCreateandPublishEmbedQuiz(instuctorUserName, instuctorPassword,LTILinkName,videoNameinstructor,QuizTitle,question,option1, option2,fitbPossibleans1, fitbPossibleans2,hint,studentUserName, studentPassword, studentAnsForShortAnsQuestion,studentAnsForFitbQuestion,courseName,marks,studentFullName,studentNameinActivityLog);         
      		}                                                                                                                                                                                                                                                                                                                 
      		catch (Exception e) {                                                                                                                                                                                                                                                                                             
      			System.out.println(e.getMessage());                                                                                                                                                                                                                                                                              
      			e.printStackTrace();                                                                                                                                                                                                                                                                                             
      			reportStep("@Method "+Scenario_Name +" exception to be handled", "FAIL", true);                                                                                                                                                                                                                                  
      		}                                                                                                                                                                                                                                                                                                                 
     }
		
		
		 @Test(description="Moodle_create_playback_attend_gradebook_administrator_test")
		  @Parameters({"TestName"})
		public void Moodle_create_playback_attend_gradebook_administrator_test(String TestName) {
			MoodlePage mp = new MoodlePage();
			try {
				mp.loginCreateandEmbedPlaybackQuiz(adminUserName,adminPassword,LTILinkName,videoNameforPlaybackquiz, playbackQuizTitle,studentUserName, studentPassword, courseName,marks,studentFullName,studentNameinActivityLog);
			} 
			catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
				reportStep("@Method "+Scenario_Name +" exception to be handled", "FAIL", true);
			}
	 }
		  	  
			
			@Test(description="Moodle_create_playback_attend_gradebook_instructor_test")                                                                                                                                                                                                                                                                          
	      	@Parameters({"TestName"})                                                                                                                                                                                                                                                                                        
	    	public void Moodle_create_playback_attend_gradebook_instructor_test(String TestName) {                                                                                                                                                                                                                                                                
	      		MoodlePage mp = new MoodlePage();                                                                                                                                                                                                                                                                                 
	      		try {                                                                                                                                                                                                                                                                                                             
	      			mp.loginCreateandEmbedPlaybackQuiz(instuctorUserName,instuctorPassword,LTILinkName,videoNameforPlaybackquiz, playbackQuizTitle,studentUserName, studentPassword,courseName,marks,studentFullName,studentNameinActivityLog);         
	      		}                                                                                                                                                                                                                                                                                                                 
	      		catch (Exception e) {                                                                                                                                                                                                                                                                                             
	      			System.out.println(e.getMessage());                                                                                                                                                                                                                                                                              
	      			e.printStackTrace();                                                                                                                                                                                                                                                                                             
	      			reportStep("@Method "+Scenario_Name +" exception to be handled", "FAIL", true);                                                                                                                                                                                                                                  
	      		}                                                                                                                                                                                                                                                                                                                 
	  }
			
		
		@Test(description="Moodle_embed_video_as_instructor_check_accessible_by_student_test")                                                                                                                                                                                                                                                                          
      	@Parameters({"TestName"})                                                                                                                                                                                                                                                                                        
    	public void Moodle_embed_video_as_instructor_check_accessible_by_student_test(String TestName) {                                                                                                                                                                                                                                                                
      		MoodlePage mp = new MoodlePage();                                                                                                                                                                                                                                                                                 
      		try {                                                                                                                                                                                                                                                                                                             
      			mp.loginEmbedMedia(instuctorUserName, instuctorPassword,videoNameinstructor,studentUserName, studentPassword, embedVideoNameinstructor);         
      		}                                                                                                                                                                                                                                                                                                                 
      		catch (Exception e) {                                                                                                                                                                                                                                                                                             
      			System.out.println(e.getMessage());                                                                                                                                                                                                                                                                              
      			e.printStackTrace();                                                                                                                                                                                                                                                                                             
      			reportStep("@Method "+Scenario_Name +" exception to be handled", "FAIL", true);                                                                                                                                                                                                                                  
      		}                                                                                                                                                                                                                                                                                                                 

      	} 
          
		@Test(description="Moodle_embed_video_as_administrator_check_accessible_by_student_test")                                                                                                                                                                                                                                                                          
      	@Parameters({"TestName"})                                                                                                                                                                                                                                                                                        
    	public void Moodle_embed_video_as_administrator_check_accessible_by_student_test(String TestName) {                                                                                                                                                                                                                                                                
      		MoodlePage mp = new MoodlePage();                                                                                                                                                                                                                                                                                 
      		try {                                                                                                                                                                                                                                                                                                             
      			mp.loginEmbedMedia(adminUserName, adminPassword,videoName,studentUserName, studentPassword, embedVideoName);         
      		}                                                                                                                                                                                                                                                                                                                 
      		catch (Exception e) {                                                                                                                                                                                                                                                                                             
      			System.out.println(e.getMessage());                                                                                                                                                                                                                                                                              
      			e.printStackTrace();                                                                                                                                                                                                                                                                                             
      			reportStep("@Method "+Scenario_Name +" exception to be handled", "FAIL", true);                                                                                                                                                                                                                                  
      		}                                                                                                                                                                                                                                                                                                                 
     }   
		
		@Test(description="Moodle_embed_audio_as_instructor_check_accessible_by_student_test")                                                                                                                                                                                                                                                                          
      	@Parameters({"TestName"})                                                                                                                                                                                                                                                                                        
    	public void Moodle_embed_audio_as_instructor_check_accessible_by_student_test(String TestName) {                                                                                                                                                                                                                                                                
      		MoodlePage mp = new MoodlePage();                                                                                                                                                                                                                                                                                 
      		try {                                                                                                                                                                                                                                                                                                             
      			mp.loginEmbedMedia(instuctorUserName, instuctorPassword,audioNameinstructor,studentUserName, studentPassword, embedAudioNameinstructor);         
      		}                                                                                                                                                                                                                                                                                                                 
      		catch (Exception e) {                                                                                                                                                                                                                                                                                             
      			System.out.println(e.getMessage());                                                                                                                                                                                                                                                                              
      			e.printStackTrace();                                                                                                                                                                                                                                                                                             
      			reportStep("@Method "+Scenario_Name +" exception to be handled", "FAIL", true);                                                                                                                                                                                                                                  
      		}                                                                                                                                                                                                                                                                                                                 

      	  } 
		@Test(description="Moodle_embed_audio_as_administrator_check_accessible_by_student_test")                                                                                                                                                                                                                                                                          
      	@Parameters({"TestName"})                                                                                                                                                                                                                                                                                        
    	public void Moodle_embed_audio_as_administrator_check_accessible_by_student_test(String TestName) {                                                                                                                                                                                                                                                                
      		MoodlePage mp = new MoodlePage();                                                                                                                                                                                                                                                                                 
      		try {                                                                                                                                                                                                                                                                                                             
      			mp.loginEmbedMedia(adminUserName, adminPassword,audioName,studentUserName, studentPassword, embedAudioName);         
      		}                                                                                                                                                                                                                                                                                                                 
      		catch (Exception e) {                                                                                                                                                                                                                                                                                             
      			System.out.println(e.getMessage());                                                                                                                                                                                                                                                                              
      			e.printStackTrace();                                                                                                                                                                                                                                                                                             
      			reportStep("@Method "+Scenario_Name +" exception to be handled", "FAIL", true);                                                                                                                                                                                                                                  
      		}                                                                                                                                                                                                                                                                                                                 
     }   
		
		@Test(description="Moodle_embed_document_as_instructor_check_accessible_by_student_test")                                                                                                                                                                                                                                                                          
      	@Parameters({"TestName"})                                                                                                                                                                                                                                                                                        
    	public void Moodle_embed_document_as_instructor_check_accessible_by_student_test(String TestName) {                                                                                                                                                                                                                                                                
      		MoodlePage mp = new MoodlePage();                                                                                                                                                                                                                                                                                 
      		try {                                                                                                                                                                                                                                                                                                             
      			mp.loginEmbedMedia(instuctorUserName, instuctorPassword,documentNameinstructor,studentUserName, studentPassword, embedDocumentNameinstructor);         
      		}                                                                                                                                                                                                                                                                                                                 
      		catch (Exception e) {                                                                                                                                                                                                                                                                                             
      			System.out.println(e.getMessage());                                                                                                                                                                                                                                                                              
      			e.printStackTrace();                                                                                                                                                                                                                                                                                             
      			reportStep("@Method "+Scenario_Name +" exception to be handled", "FAIL", true);                                                                                                                                                                                                                                  
      		}                                                                                                                                                                                                                                                                                                                 
      	  } 
          
		@Test(description="Moodle_embed_document_as_administrator_check_accessible_by_student_test")                                                                                                                                                                                                                                                                          
      	@Parameters({"TestName"})                                                                                                                                                                                                                                                                                        
    	public void Moodle_embed_document_as_administrator_check_accessible_by_student_test(String TestName) {                                                                                                                                                                                                                                                                
      		MoodlePage mp = new MoodlePage();                                                                                                                                                                                                                                                                                 
      		try {                                                                                                                                                                                                                                                                                                             
      			mp.loginEmbedMedia(adminUserName, adminPassword,documentName,studentUserName, studentPassword, embedDocumentName);         
      		}                                                                                                                                                                                                                                                                                                                 
      		catch (Exception e) {                                                                                                                                                                                                                                                                                             
      			System.out.println(e.getMessage());                                                                                                                                                                                                                                                                              
      			e.printStackTrace();                                                                                                                                                                                                                                                                                             
      			reportStep("@Method "+Scenario_Name +" exception to be handled", "FAIL", true);                                                                                                                                                                                                                                  
      		}                                                                                                                                                                                                                                                                                                                 
     } 
		
		
		@Test(description="Moodle_upload_videos_as_administrator_check_in_media_library_test")                                                                                                                                                                                                                                                                          
      	@Parameters({"TestName"})                                                                                                                                                                                                                                                                                        
    	public void Moodle_upload_videos_as_administrator_check_in_media_library_test(String TestName) {                                                                                                                                                                                                                                                                
      		MoodlePage mp = new MoodlePage();                                                                                                                                                                                                                                                                                 
      		try {                                                                                                                                                                                                                                                                                                             
      			mp.CIMMediaChooserMediaUpload(adminUserName, adminPassword, directoryPathForVideo,LTILinkName);         
      		}                                                                                                                                                                                                                                                                                                                 
      		catch (Exception e) {                                                                                                                                                                                                                                                                                             
      			System.out.println(e.getMessage());                                                                                                                                                                                                                                                                              
      			e.printStackTrace();                                                                                                                                                                                                                                                                                             
      			reportStep("@Method "+Scenario_Name +" exception to be handled", "FAIL", true);                                                                                                                                                                                                                                  
      		}                                                                                                                                                                                                                                                                                                                 
     } 
		
		
		@Test(description="Moodle_upload_video_as_instuctor_check_in_media_library_test")                                                                                                                                                                                                                                                                          
      	@Parameters({"TestName"})                                                                                                                                                                                                                                                                                        
    	public void Moodle_upload_videos_as_instuctor_check_in_media_library_test(String TestName) {                                                                                                                                                                                                                                                                
      		MoodlePage mp = new MoodlePage();                                                                                                                                                                                                                                                                                 
      		try {                                                                                                                                                                                                                                                                                                             
      			mp.CIMMediaChooserMediaUpload(instuctorUserName, instuctorPassword,directoryPathForVideo,LTILinkName);         
      		}                                                                                                                                                                                                                                                                                                                 
      		catch (Exception e) {                                                                                                                                                                                                                                                                                             
      			System.out.println(e.getMessage());                                                                                                                                                                                                                                                                              
      			e.printStackTrace();                                                                                                                                                                                                                                                                                             
      			reportStep("@Method "+Scenario_Name +" exception to be handled", "FAIL", true);                                                                                                                                                                                                                                  
      		}                                                                                                                                                                                                                                                                                                                 
      }  
		
		
		@Test(description="Moodle_upload_audio_as_administrator_check_in_media_library_test")                                                                                                                                                                                                                                                                          
      	@Parameters({"TestName"})                                                                                                                                                                                                                                                                                        
    	public void Moodle_upload_audio_as_administrator_check_in_media_library_test(String TestName) {                                                                                                                                                                                                                                                                
      		MoodlePage mp = new MoodlePage();                                                                                                                                                                                                                                                                                 
      		try {                                                                                                                                                                                                                                                                                                             
      			mp.CIMMediaChooserMediaUpload(adminUserName, adminPassword,directoryPathForAudio,LTILinkName);         
      		}                                                                                                                                                                                                                                                                                                                 
      		catch (Exception e) {                                                                                                                                                                                                                                                                                             
      			System.out.println(e.getMessage());                                                                                                                                                                                                                                                                              
      			e.printStackTrace();                                                                                                                                                                                                                                                                                             
      			reportStep("@Method "+Scenario_Name +" exception to be handled", "FAIL", true);                                                                                                                                                                                                                                  
      		}                                                                                                                                                                                                                                                                                                                 
      } 
		
		@Test(description="Moodle_upload_audio_as_instuctor_check_in_media_library_test")                                                                                                                                                                                                                                                                          
      	@Parameters({"TestName"})                                                                                                                                                                                                                                                                                        
    	public void Moodle_upload_audio_as_instuctor_check_in_media_library_test(String TestName) {                                                                                                                                                                                                                                                                
      		MoodlePage mp = new MoodlePage();                                                                                                                                                                                                                                                                                 
      		try {                                                                                                                                                                                                                                                                                                             
      			mp.CIMMediaChooserMediaUpload(instuctorUserName, instuctorPassword,directoryPathForAudio,LTILinkName);         
      		}                                                                                                                                                                                                                                                                                                                 
      		catch (Exception e) {                                                                                                                                                                                                                                                                                             
      			System.out.println(e.getMessage());                                                                                                                                                                                                                                                                              
      			e.printStackTrace();                                                                                                                                                                                                                                                                                             
      			reportStep("@Method "+Scenario_Name +" exception to be handled", "FAIL", true);                                                                                                                                                                                                                                  
      		}                                                                                                                                                                                                                                                                                                                 

      	 }
          
		@Test(description="Moodle_upload_doc_as_administrator_check_in_media_library_test")                                                                                                                                                                                                                                                                          

      	@Parameters({"TestName"})                                                                                                                                                                                                                                                                                        
    	public void Moodle_upload_doc_as_administrator_check_in_media_library_test(String TestName) {                                                                                                                                                                                                                                                                
      		MoodlePage mp = new MoodlePage();                                                                                                                                                                                                                                                                                 
      		try {                                                                                                                                                                                                                                                                                                             
      			mp.CIMMediaChooserMediaUpload(adminUserName, adminPassword,directoryPathForDoc,LTILinkName);         
      		}                                                                                                                                                                                                                                                                                                                 
      		catch (Exception e) {                                                                                                                                                                                                                                                                                             
      			System.out.println(e.getMessage());                                                                                                                                                                                                                                                                              
      			e.printStackTrace();                                                                                                                                                                                                                                                                                             
      			reportStep("@Method "+Scenario_Name +" exception to be handled", "FAIL", true);                                                                                                                                                                                                                                  
      		}                                                                                                                                                                                                                                                                                                                 
     } 
		
		
		@Test(description="Moodle_upload_doc_as_instuctor_check_in_media_library_test")                                                                                                                                                                                                                                                                          
      	@Parameters({"TestName"})                                                                                                                                                                                                                                                                                        
    	public void Moodle_upload_doc_as_instuctor_check_in_media_library_test(String TestName) {                                                                                                                                                                                                                                                                
      		MoodlePage mp = new MoodlePage();                                                                                                                                                                                                                                                                                 
      		try {                                                                                                                                                                                                                                                                                                             
      			mp.CIMMediaChooserMediaUpload(instuctorUserName, instuctorPassword,directoryPathForDoc,LTILinkName);         
      		}                                                                                                                                                                                                                                                                                                                 
      		catch (Exception e) {                                                                                                                                                                                                                                                                                             
      			System.out.println(e.getMessage());                                                                                                                                                                                                                                                                              
      			e.printStackTrace();                                                                                                                                                                                                                                                                                             
      			reportStep("@Method "+Scenario_Name +" exception to be handled", "FAIL", true);                                                                                                                                                                                                                                  
      		}                                                                                                                                                                                                                                                                                                                 
      }  
		
                
                                                                                                                                                                                                                                                                                                                          }                                                                                                                                                                                                                                                                                                                 