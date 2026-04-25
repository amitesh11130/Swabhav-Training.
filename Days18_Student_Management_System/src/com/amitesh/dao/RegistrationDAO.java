package com.amitesh.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.amitesh.util.DBUtil;

public class RegistrationDAO {

	// Register Course
	public boolean register(Connection con, int studentId, int courseid, double fee) throws SQLException {
		String query = "INSERT INTO registration(studentid, courseid, feespaid) VALUES (?, ?, ?)";

		try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
			preparedStatement.setInt(1, studentId);
			preparedStatement.setInt(2, courseid);
			preparedStatement.setDouble(3, fee);

			return preparedStatement.executeUpdate() > 0;
		}
	}

	// Duplicate check
	public boolean isAlreadyRegistered(Connection con, int id, int courseid) throws SQLException {
		String query = "select * from registration where studentid = ? and courseid = ?";

		try (PreparedStatement ps = con.prepareStatement(query)) {

			ps.setInt(1, id);
			ps.setInt(2, courseid);
			try (ResultSet executeQuery = ps.executeQuery()) {
				return executeQuery.next();
			}
		}
	}

	// Update fee
	public boolean updateFee(int studentid, int courseid, double fee) throws SQLException {
		String query = "update registration set feespaid = ? where studentid = ? and courseid = ?";

		try (Connection con = DBUtil.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
			ps.setDouble(1, fee);
			ps.setInt(2, studentid);
			ps.setInt(3, courseid);

			return ps.executeUpdate() > 0;
		}
	}

	// Cancel registration
	public boolean deleteRegistration(Connection con, int studentid) throws SQLException {
		String query = "delete from registration where studentid = ?";
		try (PreparedStatement ps = con.prepareStatement(query)) {

			ps.setInt(1, studentid);
			return ps.executeUpdate() > 0;
		}
	}

	// High paying students
	public void highPaying(double fee) throws SQLException {
		String query = """
				    SELECT s.id, s.name, SUM(r.feespaid) AS total
				    FROM student s
				    JOIN registration r ON s.id = r.studentid
				    GROUP BY s.id, s.name
				    HAVING total > ?
				""";

		try (Connection con = DBUtil.getConnection(); PreparedStatement ps = con.prepareStatement(query);) {
			ps.setDouble(1, fee);

			try (ResultSet rs = ps.executeQuery()) {
				boolean found = false;
				System.out.println("\n HIGH PAYING STUDENTS");

				while (rs.next()) {
					found = true;
					System.out.println("ID: " + rs.getInt("id") + " | Name: " + rs.getString("name") + " | Total: "
							+ rs.getDouble("total"));

				}
				if (!found) {
					System.out.println("No high paying students found.");
				}
			}
		}
	}

	// Course wise count
	public void courseWiseStudentCount() throws SQLException {
		String query = "SELECT c.coursename, COUNT(r.studentid) AS total FROM course c "
				+ "LEFT JOIN registration r ON c.courseid = r.courseid GROUP BY c.coursename";

		try (Connection con = DBUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(query);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				System.out.println("Course Name: " + rs.getString(1) + "| Student Count: " + rs.getInt(2));
			}
		}
	}
}
