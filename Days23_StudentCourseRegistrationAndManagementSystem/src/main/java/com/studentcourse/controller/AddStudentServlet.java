package com.studentcourse.controller;

import java.io.IOException;

import com.studentcourse.dao.StudentDAO;
import com.studentcourse.model.Student;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/student/add")
public class AddStudentServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private StudentDAO studentDAO;

	@Override
	public void init() throws ServletException {
		studentDAO = new StudentDAO();
		System.out.println("AddStudentServlet initialized via init()");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("loggedInUser") == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		request.getRequestDispatcher("/WEB-INF/views/student-form.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("loggedInUser") == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}

		String name = request.getParameter("studentName");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String ageStr = request.getParameter("age");
		String city = request.getParameter("city");

		if (name == null || name.trim().isEmpty() || email == null || email.trim().isEmpty() || phone == null
				|| phone.trim().isEmpty() || ageStr == null || city == null || city.trim().isEmpty()) {
			request.setAttribute("error", "All fields are required!");
			request.getRequestDispatcher("/WEB-INF/views/student-form.jsp").forward(request, response);
			return;
		}

		int age;
		try {
			age = Integer.parseInt(ageStr);
			if (age < 18) {
				request.setAttribute("error", "Age must be 18 or above!");
				request.getRequestDispatcher("/WEB-INF/views/student-form.jsp").forward(request, response);
				return;
			}
		} catch (NumberFormatException e) {
			request.setAttribute("error", "Invalid Age structural format!");
			request.getRequestDispatcher("/WEB-INF/views/student-form.jsp").forward(request, response);
			return;
		}

		Student student = new Student();
		student.setStudentName(name);
		student.setEmail(email);
		student.setPhone(phone);
		student.setAge(age);
		student.setCity(city);

		if (studentDAO.addStudent(student)) {
			response.sendRedirect(request.getContextPath() + "/students");
		} else {
			request.setAttribute("error", "Database Insertion Failure occurred.");
			request.getRequestDispatcher("/WEB-INF/views/student-form.jsp").forward(request, response);
		}
	}

	@Override
	public void destroy() {
		System.out.println("AddStudentServlet destroyed via destroy()");
	}
}