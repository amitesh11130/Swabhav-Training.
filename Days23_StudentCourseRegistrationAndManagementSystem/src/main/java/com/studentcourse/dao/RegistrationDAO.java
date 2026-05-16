package com.studentcourse.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.studentcourse.model.Course;
import com.studentcourse.model.Registration;
import com.studentcourse.model.Student;
import com.studentcourse.util.DBConnection;

public class RegistrationDAO {

	public boolean addRegistration(Registration r) {
		boolean status = false;
		String sql = "INSERT INTO registrations(student_id, course_id, registration_date, status) VALUES(?,?,?,?)";

		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, r.getStudentId());
			ps.setInt(2, r.getCourseId());
			ps.setString(3, r.getRegistrationDate());
			ps.setString(4, r.getStatus());

			status = ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	public boolean isDuplicateActiveRegistration(int studentId, int courseId) {
		boolean status = false;
		String sql = "SELECT * FROM registrations WHERE student_id=? AND course_id=?";

		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, studentId);
			ps.setInt(2, courseId);
			try (ResultSet rs = ps.executeQuery()) {
				status = rs.next();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	public List<Student> getStudents() {
		List<Student> list = new ArrayList<>();
		String sql = "SELECT student_id, student_name FROM students";

		try (Connection con = DBConnection.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				Student s = new Student();
				s.setStudentId(rs.getInt("student_id"));
				s.setStudentName(rs.getString("student_name"));
				list.add(s);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<Course> getCourses() {
		List<Course> list = new ArrayList<>();
		String sql = "SELECT course_id, course_name FROM courses";

		try (Connection con = DBConnection.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				Course c = new Course();
				c.setCourseId(rs.getInt("course_id"));
				c.setCourseName(rs.getString("course_name"));
				list.add(c);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<Registration> getAllRegistrations() {
		List<Registration> list = new ArrayList<>();
		
		String sql = "SELECT r.registration_id, r.student_id, r.course_id, "
				+ "s.student_name AS student_name, c.course_name AS course_name, r.registration_date, r.status "
				+ "FROM registrations r " + "JOIN students s ON r.student_id=s.student_id "
				+ "JOIN courses c ON r.course_id=c.course_id";

		try (Connection con = DBConnection.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				Registration r = new Registration();
				r.setRegistrationId(rs.getInt("registration_id"));
				r.setStudentId(rs.getInt("student_id"));
				r.setCourseId(rs.getInt("course_id"));
				r.setStudentName(rs.getString("student_name"));
				r.setCourseName(rs.getString("course_name"));
				r.setRegistrationDate(rs.getString("registration_date"));
				r.setStatus(rs.getString("status"));
				list.add(r);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public boolean updateStatus(int registrationId, String status) {
		boolean result = false;
		String sql = "UPDATE registrations SET status=? WHERE registration_id=?";

		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, status);
			ps.setInt(2, registrationId);

			result = ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean deleteRegistration(int id) {
		boolean status = false;
		String sql = "DELETE FROM registrations WHERE registration_id=?";

		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, id);
			status = ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}
}