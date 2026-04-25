package com.amitesh.service;

import java.sql.Connection;

import com.amitesh.dao.BranchDAO;
import com.amitesh.dao.CourseDAO;
import com.amitesh.dao.RegistrationDAO;
import com.amitesh.dao.StudentDAO;
import com.amitesh.model.Branch;
import com.amitesh.model.Course;
import com.amitesh.model.Registration;
import com.amitesh.model.Student;
import com.amitesh.util.DBUtil;

public class StudentService implements StudentServiceInterface {

	private StudentDAO studentDAO = new StudentDAO();
	private RegistrationDAO registrationDAO = new RegistrationDAO();
	private BranchDAO branchDAO = new BranchDAO();
	private CourseDAO courseDAO = new CourseDAO();

	// 1. Add Student
	@Override
	public void addStudent(Student student) {
		try {

			boolean result = studentDAO.addStudent(student);

			if (result) {
				System.out.println("SUCCESS: Student added successfully!");
			} else {
				System.out.println("ERROR: Failed to add student!");
			}

		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
		}
	}

	// 2. Register Course
	@Override
	public void registerCourse(Registration registration) {
		try (Connection con = DBUtil.getConnection()) {

			con.setAutoCommit(false);

			// check duplicate
			if (registrationDAO.isAlreadyRegistered(con, registration.getStudentId(), registration.getCourseId())) {
				System.out.println("WARNING: Already registered for this course!");
				return;
			}

			boolean success = registrationDAO.register(con, registration.getStudentId(), registration.getCourseId(),
					registration.getFeesPaid());

			if (success) {
				con.commit();
				System.out.println("SUCCESS: Course registered successfully!");
			} else {
				con.rollback();
				System.out.println("ERROR: Registration failed!");
			}

		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
		}
	}

	// 3. View All Students
	@Override
	public void viewAllStudents() {
		try {
			studentDAO.viewAllStudents();
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
		}
	}

	// 4. Search Student
	@Override
	public void getStudent(int id) {
		try {
			studentDAO.getStudentById(id);
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
		}
	}

	// 5. Update Student
	@Override
	public void updateStudent(String name, int branchId, int id) {
		try {
			boolean result = studentDAO.updateStudent(name, branchId, id);

			if (result) {
				System.out.println("SUCCESS: Student updated successfully!");
			} else {
				System.out.println("ERROR: Student not found!");
			}

		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
		}
	}

	// 6. Update Fee
	@Override
	public void updateFee(int studentId, int courseId, double fee) {
		try {
			boolean updated = registrationDAO.updateFee(studentId, courseId, fee);

			if (updated) {
				System.out.println("SUCCESS: Fee updated successfully!");
			} else {
				System.out.println("ERROR: Record not found!");
			}

		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
		}
	}

	// 7. Cancel Registration
	@Override
	public void cancelRegistration(int studentId) {
		try (Connection con = DBUtil.getConnection()) {

			boolean deleted = registrationDAO.deleteRegistration(con, studentId);

			if (deleted) {
				System.out.println("SUCCESS: Registration cancelled!");
			} else {
				System.out.println("ERROR: No registration found!");
			}

		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
		}
	}

	// 8. Delete Student
	@Override
	public void deleteStudent(int studentId) {
		try (Connection con = DBUtil.getConnection()) {

			con.setAutoCommit(false);

			// delete registrations first
			registrationDAO.deleteRegistration(con, studentId);

			boolean deleted = studentDAO.deleteStudent(con, studentId);

			if (deleted) {
				con.commit();
				System.out.println("SUCCESS: Student deleted successfully!");
			} else {
				con.rollback();
				System.out.println("ERROR: Student not found!");
			}

		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
		}
	}

	// 9. High Paying Students
	@Override
	public void showHighPaying(double fee) {
		try {
			registrationDAO.highPaying(fee);
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
		}
	}

	// 10. Course Wise Count
	@Override
	public void showCourseWiseCount() {
		try {
			registrationDAO.courseWiseStudentCount();
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
		}
	}

	// Show all branch
	public void showBranches() {
		try {
			branchDAO.showAllBranches();
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
		}
	}

	// Show all course
	public void showCourses() {
		try {
			courseDAO.showAllCourses();
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
		}
	}

	// branch exist or not
	public boolean isValidBranch(int branchId) {
		try {
			return branchDAO.branchExists(branchId);
		} catch (Exception e) {
			return false;
		}
	}

	// course exist or not
	public boolean isValidCourse(int courseId) {
		try {
			return courseDAO.courseExists(courseId);
		} catch (Exception e) {
			return false;
		}
	}

	//// Student exist or not
	public boolean isStudentExists(int id) {
		try {
			return studentDAO.exists(id);
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public void addCourse(Course course) {
		try {

			boolean result = courseDAO.addCourse(course);

			if (result) {
				System.out.println("SUCCESS: Course added successfully!");
			} else {
				System.out.println("ERROR: Failed to add course!");
			}

		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
		}
	}

	@Override
	public void addBranch(Branch branch) {
		try {

			boolean result = branchDAO.addBranch(branch);

			if (result) {
				System.out.println("SUCCESS: Branch added successfully!");
			} else {
				System.out.println("ERROR: Failed to add branch!");
			}

		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
		}
	}
}