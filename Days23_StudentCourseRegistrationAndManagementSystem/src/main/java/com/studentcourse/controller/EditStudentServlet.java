package com.studentcourse.controller;

import java.io.IOException;
import com.studentcourse.dao.StudentDAO;
import com.studentcourse.model.Student;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/student/edit")
public class EditStudentServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private StudentDAO studentDAO = new StudentDAO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("loggedInUser") == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		int id = Integer.parseInt(request.getParameter("id"));
		Student s = studentDAO.getStudentById(id);
		request.setAttribute("student", s);
		request.getRequestDispatcher("/WEB-INF/views/student-edit.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("loggedInUser") == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}

		int id = Integer.parseInt(request.getParameter("studentId"));
		String name = request.getParameter("studentName");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		int age = Integer.parseInt(request.getParameter("age"));
		String city = request.getParameter("city");

		Student s = new Student();
		s.setStudentId(id);
		s.setStudentName(name);
		s.setEmail(email);
		s.setPhone(phone);
		s.setAge(age);
		s.setCity(city);

		if (age < 18) {
			request.setAttribute("error", "Age must be 18 or above!");
			request.setAttribute("student", s);
			request.getRequestDispatcher("/WEB-INF/views/student-edit.jsp").forward(request, response);
			return;
		}

		studentDAO.updateStudent(s);
		response.sendRedirect(request.getContextPath() + "/students");
	}
}