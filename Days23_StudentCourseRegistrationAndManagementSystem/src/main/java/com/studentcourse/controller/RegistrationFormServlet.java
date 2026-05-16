package com.studentcourse.controller;

import java.io.IOException;
import com.studentcourse.dao.RegistrationDAO;
import com.studentcourse.model.Registration;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/registration/add")
public class RegistrationFormServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private RegistrationDAO registrationDAO = new RegistrationDAO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("loggedInUser") == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		request.setAttribute("students", registrationDAO.getStudents());
		request.setAttribute("courses", registrationDAO.getCourses());
		request.getRequestDispatcher("/WEB-INF/views/registration-form.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("loggedInUser") == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}

		int studentId = Integer.parseInt(request.getParameter("studentId"));
		int courseId = Integer.parseInt(request.getParameter("courseId"));
		String date = request.getParameter("registrationDate");
		String status = request.getParameter("status");

		if (registrationDAO.isDuplicateActiveRegistration(studentId, courseId)) {
			request.setAttribute("error",
					"Duplicate active registration found! The student is already actively enrolled in this course.");
			request.setAttribute("students", registrationDAO.getStudents());
			request.setAttribute("courses", registrationDAO.getCourses());
			request.getRequestDispatcher("/WEB-INF/views/registration-form.jsp").forward(request, response);
			return;
		}

		Registration r = new Registration();
		r.setStudentId(studentId);
		r.setCourseId(courseId);
		r.setRegistrationDate(date);
		r.setStatus(status);

		registrationDAO.addRegistration(r);
		response.sendRedirect(request.getContextPath() + "/registrations");
	}
}