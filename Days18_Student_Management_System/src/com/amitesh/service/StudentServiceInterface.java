package com.amitesh.service;

import com.amitesh.model.Branch;
import com.amitesh.model.Course;
import com.amitesh.model.Registration;
import com.amitesh.model.Student;

public interface StudentServiceInterface {

	void registerCourse(Registration registration);

	void addStudent(Student student);

	void updateStudent(String name, int branchid, int id);

	void viewAllStudents();

	void getStudent(int id);

	void deleteStudent(int studentId);

	void updateFee(int studentId, int courseid, double fee);

	void showHighPaying(double fee);

	void showCourseWiseCount();

	void cancelRegistration(int studentId);

	public void showBranches();

	public void showCourses();

	public boolean isValidBranch(int branchId);

	public boolean isValidCourse(int courseId);

	public boolean isStudentExists(int id);

	public void addCourse(Course course);

	public void addBranch(Branch branch);
}