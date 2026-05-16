package com.studentcourse.controller;

import java.io.IOException;

import com.studentcourse.dao.CourseDAO;
import com.studentcourse.model.Course;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/course/edit")
public class EditCourseServlet extends HttpServlet {

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
		request.setAttribute("course", courseDAO.getCourseById(id));
		request.getRequestDispatcher("/WEB-INF/views/course-edit.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("loggedInUser") == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}

		int id = Integer.parseInt(request.getParameter("courseId"));
		String name = request.getParameter("courseName");
		String duration = request.getParameter("duration");
		double fees = Double.parseDouble(request.getParameter("fees"));
		String trainer = request.getParameter("trainerName");

		Course c = new Course();
		c.setCourseId(id);
		c.setCourseName(name);
		c.setDuration(duration);
		c.setFees(fees);
		c.setTrainerName(trainer);

		if (fees <= 0) {
			request.setAttribute("error", "Fees must be greater than 0!");
			request.setAttribute("course", c);
			request.getRequestDispatcher("/WEB-INF/views/course-edit.jsp").forward(request, response);
			return;
		}

		courseDAO.updateCourse(c);
		response.sendRedirect(request.getContextPath() + "/courses");
	}
}