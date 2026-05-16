package com.studentcourse.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.studentcourse.exception.DAOException;
import com.studentcourse.model.Course;
import com.studentcourse.util.DBConnection;

public class CourseDAO {

	public boolean addCourse(Course course) throws DAOException {
	
		if (isCourseNameExists(course.getCourseName())) {
			throw new DAOException("A course with the name '" + course.getCourseName() + "' already exists!");
		}

		String sql = "insert into courses(course_name,duration,fees,trainer_name) values(?,?,?,?)";
		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, course.getCourseName());
			ps.setString(2, course.getDuration());
			ps.setDouble(3, course.getFees());
			ps.setString(4, course.getTrainerName());

			return ps.executeUpdate() > 0;

		} catch (SQLException e) {

			if (e.getMessage().contains("Duplicate entry")) {
				throw new DAOException("Database Constraint Violation: Course name already exists!", e);
			}
			throw new DAOException("Critical system error tracking failed course registration storage", e);
		}
	}

	public boolean isCourseNameExists(String courseName) throws DAOException {
		String sql = "SELECT * FROM courses WHERE course_name = ?";
		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, courseName);
			try (ResultSet rs = ps.executeQuery()) {
				return rs.next();
			}
		} catch (SQLException e) {
			throw new DAOException("Error verifying course duplicate name constraint", e);
		}
	}

	public List<Course> getAllCourses() {
		List<Course> list = new ArrayList<>();
		String sql = "SELECT * FROM courses";

		try (Connection con = DBConnection.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				Course c = new Course();
				c.setCourseId(rs.getInt("course_id"));
				c.setCourseName(rs.getString("course_name"));
				c.setDuration(rs.getString("duration"));
				c.setFees(rs.getDouble("fees"));
				c.setTrainerName(rs.getString("trainer_name"));
				list.add(c);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public Course getCourseById(int id) {
		Course c = null;
		String sql = "SELECT * FROM courses WHERE course_id=?";

		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					c = new Course();
					c.setCourseId(rs.getInt("course_id"));
					c.setCourseName(rs.getString("course_name"));
					c.setDuration(rs.getString("duration"));
					c.setFees(rs.getDouble("fees"));
					c.setTrainerName(rs.getString("trainer_name"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return c;
	}

	public boolean updateCourse(Course course) {
		boolean status = false;
		String sql = "UPDATE courses SET course_name=?, duration=?, fees=?, trainer_name=? WHERE course_id=?";

		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, course.getCourseName());
			ps.setString(2, course.getDuration());
			ps.setDouble(3, course.getFees());
			ps.setString(4, course.getTrainerName());
			ps.setInt(5, course.getCourseId());

			status = ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	public boolean hasActiveRegistration(int courseId) {
		boolean status = false;
		String sql = "SELECT * FROM registrations WHERE course_id=? AND status IN ('Active', 'Cancelled', 'Completed')";

		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, courseId);
			try (ResultSet rs = ps.executeQuery()) {
				status = rs.next();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	public boolean deleteCourse(int courseId) {
		boolean status = false;
		String sql = "DELETE FROM courses WHERE course_id=?";

		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, courseId);
			status = ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}
}