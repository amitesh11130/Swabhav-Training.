package com.amitesh.model;

public class Course {

	private String courseName;
	private double fee;

	public Course(String courseName, double fee) {
		this.courseName = courseName;
		this.fee = fee;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public double getFee() {
		return fee;
	}

	public void setFee(double fee) {
		this.fee = fee;
	}

	@Override
	public String toString() {
		return "Course{" + " courseName='" + courseName + '\'' + ", fee=" + fee + '}';
	}
}
