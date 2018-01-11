package com.teacher.pojo;

public class Course {
	private int courseId;
	private String cName;
	private String advancedPlacement;
	private String credit;
	private String elective;
	private String category;
	private int schoolHours;
	
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public String getcName() {
		return cName;
	}
	public void setcName(String cName) {
		this.cName = cName;
	}
	public String getAdvancedPlacement() {
		return advancedPlacement;
	}
	public void setAdvancedPlacement(String advancedPlacement) {
		this.advancedPlacement = advancedPlacement;
	}
	public String getCredit() {
		return credit;
	}
	public void setCredit(String credit) {
		this.credit = credit;
	}
	public String getElective() {
		return elective;
	}
	public void setElective(String elective) {
		this.elective = elective;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getSchoolHours() {
		return schoolHours;
	}
	public void setSchoolHours(int schoolHours) {
		this.schoolHours = schoolHours;
	}

}
