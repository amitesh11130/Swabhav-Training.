package com.studentcourse.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.studentcourse.exception.DAOException;
import com.studentcourse.model.Student;
import com.studentcourse.util.DBConnection;

public class StudentDAO {

	public boolean addStudent(Student student) {
		boolean status = false;
		String sql = "INSERT INTO students(student_name, email, phone, age, city) VALUES(?,?,?,?,?)";

		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, student.getStudentName());
			ps.setString(2, student.getEmail());
			ps.setString(3, student.getPhone());
			ps.setInt(4, student.getAge());
			ps.setString(5, student.getCity());

			status = ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	public List<Student> getAllStudents() {
		List<Student> list = new ArrayList<>();
		String sql = "SELECT * FROM students";

		try (Connection con = DBConnection.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				Student s = new Student();
				s.setStudentId(rs.getInt("student_id"));
				s.setStudentName(rs.getString("student_name"));
				s.setEmail(rs.getString("email"));
				s.setPhone(rs.getString("phone"));
				s.setAge(rs.getInt("age"));
				s.setCity(rs.getString("city"));
				list.add(s);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public Student getStudentById(int id) {
		Student s = null;
		String sql = "SELECT * FROM students WHERE student_id=?";

		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					s = new Student();
					s.setStudentId(rs.getInt("student_id"));
					s.setStudentName(rs.getString("student_name"));
					s.setEmail(rs.getString("email"));
					s.setPhone(rs.getString("phone"));
					s.setAge(rs.getInt("age"));
					s.setCity(rs.getString("city"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}

	public boolean updateStudent(Student student) {
		boolean status = false;
		String sql = "UPDATE students SET student_name=?, email=?, phone=?, age=?, city=? WHERE student_id=?";

		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, student.getStudentName());
			ps.setString(2, student.getEmail());
			ps.setString(3, student.getPhone());
			ps.setInt(4, student.getAge());
			ps.setString(5, student.getCity());
			ps.setInt(6, student.getStudentId());

			status = ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	public boolean hasRegistration(int studentId) {
		boolean status = false;
		String sql = "SELECT * FROM registrations WHERE student_id=?";

		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, studentId);
			try (ResultSet rs = ps.executeQuery()) {
				status = rs.next();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	public boolean deleteStudent(int studentId) throws DAOException {

		if (hasRegistration(studentId)) {
			throw new DAOException(
					"Cannot delete student! This student is currently assigned to active course records.");
		}

		String sql = "delete from students where student_id=?";
		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, studentId);
			return ps.executeUpdate() > 0;

		} catch (SQLException e) {
			throw new DAOException("Database communication exception dropped student profile removal task.", e);
		}
	}
}