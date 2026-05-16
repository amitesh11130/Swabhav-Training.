package com.studentcourse.controller;

import java.io.IOException;

import com.studentcourse.dao.CourseDAO;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/course/delete")
public class DeleteCourseServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private CourseDAO courseDAO = new CourseDAO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("loggedInUser") == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		int id = Integer.parseInt(request.getParameter("id"));

		if (courseDAO.hasActiveRegistration(id)) {
			request.setAttribute("error", "Cannot delete course! Active registration linkages present.");
			request.setAttribute("courseList", courseDAO.getAllCourses());
			request.getRequestDispatcher("/WEB-INF/views/course-list.jsp").forward(request, response);
		} else {
			courseDAO.deleteCourse(id);
			response.sendRedirect(request.getContextPath() + "/courses");
		}
	}
}