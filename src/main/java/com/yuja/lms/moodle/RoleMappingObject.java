package com.yuja.lms.moodle;

public class RoleMappingObject {

	private int numberOfMainMenuOptions;
	private String lmsRole;
	private String courseRole;
	
	
	public RoleMappingObject(int numberOfMainMenuOptions, String lmsRole, String courseRole) {
		super();
		this.numberOfMainMenuOptions = numberOfMainMenuOptions;
		this.lmsRole = lmsRole;
		this.courseRole = courseRole;
	}
	public int getNumberOfMainMenuOptions() {
		return numberOfMainMenuOptions;
	}
	public void setNumberOfMainMenuOptions(int numberOfMainMenuOptions) {
		this.numberOfMainMenuOptions = numberOfMainMenuOptions;
	}
	public String getlmsRole() {
		return lmsRole;
	}
	public void setlmsRole(String lmsRole) {
		this.lmsRole = lmsRole;
	}
	public String getCourseRole() {
		return courseRole;
	}
	public void setCourseRole(String courseRole) {
		this.courseRole = courseRole;
	}
	
	
	
}
