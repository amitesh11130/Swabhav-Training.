package com.amitesh.model;

public class Registration {

	private int studentId;
	private int courseid;
	private double feesPaid;

	public Registration(int studentId, int courseid, double feesPaid) {
		this.studentId = studentId;
		this.courseid = courseid;
		this.feesPaid = feesPaid;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public int getCourseId() {
		return courseid;
	}

	public void setCourseId(int courseid) {
		this.courseid = courseid;
	}

	public double getFeesPaid() {
		return feesPaid;
	}

	public void setFeesPaid(double feesPaid) {
		this.feesPaid = feesPaid;
	}

}
