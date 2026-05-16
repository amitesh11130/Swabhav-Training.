package com.studentcourse.controller;

import java.io.IOException;

import com.studentcourse.dao.CourseDAO;
import com.studentcourse.exception.DAOException;
import com.studentcourse.model.Course;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/course/add")
public class AddCourseServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		new CourseDAO();
		System.out.println("AddCourseServlet initialized via init()");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("loggedInUser") == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		request.getRequestDispatcher("/WEB-INF/views/course-form.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String courseName = request.getParameter("courseName");
		String duration = request.getParameter("duration");
		String feesStr = request.getParameter("fees");
		String trainerName = request.getParameter("trainerName");

		
		if (courseName.isEmpty() || duration.isEmpty() || feesStr.isEmpty() || trainerName.isEmpty()) {
			request.setAttribute("errorMessage", "All fields are required");
			request.getRequestDispatcher("/WEB-INF/views/course-form.jsp").forward(request, response);
			return;
		}

		double fees;
		try {
			fees = Double.parseDouble(feesStr);
			if (fees <= 0) {
				request.setAttribute("errorMessage", "Fees must be greater than 0");
				request.getRequestDispatcher("/WEB-INF/views/course-form.jsp").forward(request, response);
				return;
			}
		} catch (NumberFormatException e) {
			request.setAttribute("errorMessage", "Invalid format: Fees must be a valid number");
			request.getRequestDispatcher("/WEB-INF/views/course-form.jsp").forward(request, response);
			return;
		}

		Course c = new Course();
		c.setCourseName(courseName);
		c.setDuration(duration);
		c.setFees(fees);
		c.setTrainerName(trainerName);

		CourseDAO dao = new CourseDAO();

		try {

			boolean status = dao.addCourse(c);
			if (status) {
				response.sendRedirect(request.getContextPath() + "/courses");
			} else {
				request.setAttribute("errorMessage", "Unable to add course due to internal processing error.");
				request.getRequestDispatcher("/WEB-INF/views/course-form.jsp").forward(request, response);
			}
		} catch (DAOException e) {
			request.setAttribute("errorMessage", e.getMessage());
			request.getRequestDispatcher("/WEB-INF/views/course-form.jsp").forward(request, response);
		}
	}

	@Override
	public void destroy() {
		System.out.println("AddCourseServlet destroyed via destroy()");
	}
}