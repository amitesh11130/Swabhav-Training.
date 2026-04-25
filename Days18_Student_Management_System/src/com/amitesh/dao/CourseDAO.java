package com.amitesh.dao;

import java.sql.*;

import com.amitesh.model.Course;
import com.amitesh.util.DBUtil;

public class CourseDAO {

	// 1. Add course
	public boolean addCourse(Course c) throws SQLException {
	    String sql = "INSERT INTO course(coursename, fee) VALUES (?, ?)";

	    try (Connection con = DBUtil.getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {

	        ps.setString(1, c.getCourseName());
	        ps.setDouble(2, c.getFee());

	        return ps.executeUpdate() > 0;
	    }
	}

	// show all course
	public void showAllCourses() throws SQLException {
		String query = "SELECT courseid, coursename, fee FROM course";

		try (Connection con = DBUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(query);
				ResultSet rs = ps.executeQuery()) {

			System.out.println("\n AVAILABLE COURSES");
			System.out.println("========================");

			while (rs.next()) {
				System.out.println("ID: " + rs.getInt("courseid") + " | Name: " + rs.getString("coursename")
						+ " | Fee: " + rs.getDouble("fee"));
			}
		}
	}

	// course present or not
	public boolean courseExists(int courseId) throws SQLException {
		String query = "SELECT courseid FROM course WHERE courseid = ?";

		try (Connection con = DBUtil.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {

			ps.setInt(1, courseId);

			try (ResultSet rs = ps.executeQuery()) {
				return rs.next();
			}
		}
	}

}