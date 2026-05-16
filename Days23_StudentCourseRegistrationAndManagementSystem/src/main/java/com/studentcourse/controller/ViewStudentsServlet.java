package com.studentcourse.controller;

import java.io.IOException;

import com.studentcourse.dao.StudentDAO;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/students")
public class ViewStudentsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private StudentDAO studentDAO = new StudentDAO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("loggedInUser") == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		request.setAttribute("studentList", studentDAO.getAllStudents());
		request.getRequestDispatcher("/WEB-INF/views/student-list.jsp").forward(request, response);
	}
}
