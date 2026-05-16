package com.studentcourse.controller;

import java.io.IOException;

import com.studentcourse.dao.CourseDAO;
import com.studentcourse.dao.RegistrationDAO;
import com.studentcourse.dao.StudentDAO;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private StudentDAO studentDAO;
	private CourseDAO courseDAO;
	private RegistrationDAO registrationDAO;

	@Override
	public void init() throws ServletException {
		studentDAO = new StudentDAO();
		courseDAO = new CourseDAO();
		registrationDAO = new RegistrationDAO();
		System.out.println("DashboardServlet initialized via init()");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("loggedInUser") == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}

		int totalStudents = studentDAO.getAllStudents().size();
		int totalCourses = courseDAO.getAllCourses().size();
		int totalRegistrations = registrationDAO.getAllRegistrations().size();

		request.setAttribute("totalStudents", totalStudents);
		request.setAttribute("totalCourses", totalCourses);
		request.setAttribute("totalRegistrations", totalRegistrations);

		request.getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(request, response);
	}

	@Override
	public void destroy() {
		System.out.println("DashboardServlet destroyed via destroy()");
	}
}