package com.amitesh.dao;

import java.sql.*;
import com.amitesh.model.Student;
import com.amitesh.util.DBUtil;

public class StudentDAO {

	// Add Student
	public boolean addStudent(Student s) throws SQLException {
		String sql = "INSERT INTO student(id, name, age, branchid) VALUES (?, ?, ?, ?)";

		try (Connection con = DBUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, s.getId());
			ps.setString(2, s.getName());
			ps.setInt(3, s.getAge());
			ps.setInt(4, s.getBranchId());

			return ps.executeUpdate() > 0;
		}
	}

	// Student present or not
	public boolean exists(int id) throws SQLException {
		String sql = "SELECT id FROM student WHERE id=?";

		try (Connection con = DBUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, id);

			try (ResultSet rs = ps.executeQuery()) {
				return rs.next();
			}
		}
	}

	// Update Student
	public boolean updateStudent(String name, int branchId, int id) throws SQLException {
		String sql = "UPDATE student SET name=?, branchid=? WHERE id=?";

		try (Connection con = DBUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, name);
			ps.setInt(2, branchId);
			ps.setInt(3, id);

			return ps.executeUpdate() > 0;
		}
	}

	// Delete Student
	public boolean deleteStudent(Connection con, int id) throws SQLException {
		String sql = "DELETE FROM student WHERE id=?";

		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, id);
			return ps.executeUpdate() > 0;
		}
	}

	// View All Students
	public void viewAllStudents() throws SQLException {

		String sql = """
				    SELECT s.id, s.name, s.age, b.branchname,
				           c.coursename, r.feespaid
				    FROM student s
				    LEFT JOIN branch b ON s.branchid = b.branchid
				    LEFT JOIN registration r ON s.id = r.studentid
				    LEFT JOIN course c ON r.courseid = c.courseid
				    ORDER BY s.id
				""";

		try (Connection con = DBUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {

			int lastId = -1;

			while (rs.next()) {
				int id = rs.getInt("id");

				if (id != lastId) {
					System.out.println("\n==============================");
					System.out.println("ID     : " + id);
					System.out.println("Name   : " + rs.getString("name"));
					System.out.println("Age    : " + rs.getInt("age"));
					System.out.println("Branch : " + rs.getString("branchname"));
					System.out.println("Courses:");
					lastId = id;
				}

				String course = rs.getString("coursename");

				if (course != null) {
					System.out.println("  - " + course + " | Fees: " + rs.getDouble("feespaid"));
				} else {
					System.out.println("  - No courses registered");
				}
			}
		}
	}

	// Get single student
	public void getStudentById(int id) throws SQLException {

		String sql = """
				    SELECT s.id, s.name, s.age, b.branchname,
				           c.coursename, r.feespaid
				    FROM student s
				    JOIN branch b ON s.branchid = b.branchid
				    LEFT JOIN registration r ON s.id = r.studentid
				    LEFT JOIN course c ON r.courseid = c.courseid
				    WHERE s.id=?
				""";

		try (Connection con = DBUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, id);

			try (ResultSet rs = ps.executeQuery()) {

				boolean found = false;

				while (rs.next()) {
					if (!found) {
						System.out.println("\n STUDENT DETAILS");
						System.out.println("==============================");
						System.out.println("ID     : " + rs.getInt("id"));
						System.out.println("Name   : " + rs.getString("name"));
						System.out.println("Age    : " + rs.getInt("age"));
						System.out.println("Branch : " + rs.getString("branchname"));
						System.out.println("Courses:");
						found = true;
					}

					String course = rs.getString("coursename");
					if (course != null) {
						System.out.println("  - " + course + " | Fees: " + rs.getDouble("feespaid"));
					} else {
						System.out.println("  - No courses registered");
					}
				}

				if (!found) {
					System.out.println("Student not found!");
				}
			}
		}
	}
}