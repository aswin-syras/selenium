package com.yuja.lms.moodle;

public class RoleMappingObject {

	private int numberOfMainMenuOptions;
	private String moodleRole;
	private String courseRole;
	
	
	public RoleMappingObject(int numberOfMainMenuOptions, String moodleRole, String courseRole) {
		super();
		this.numberOfMainMenuOptions = numberOfMainMenuOptions;
		this.moodleRole = moodleRole;
		this.courseRole = courseRole;
	}
	public int getNumberOfMainMenuOptions() {
		return numberOfMainMenuOptions;
	}
	public void setNumberOfMainMenuOptions(int numberOfMainMenuOptions) {
		this.numberOfMainMenuOptions = numberOfMainMenuOptions;
	}
	public String getMoodleRole() {
		return moodleRole;
	}
	public void setMoodleRole(String moodleRole) {
		this.moodleRole = moodleRole;
	}
	public String getCourseRole() {
		return courseRole;
	}
	public void setCourseRole(String courseRole) {
		this.courseRole = courseRole;
	}
	
	
	
}
