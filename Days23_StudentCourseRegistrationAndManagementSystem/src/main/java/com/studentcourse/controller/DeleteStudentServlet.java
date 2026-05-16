package com.studentcourse.controller;

import java.io.IOException;

import com.studentcourse.dao.StudentDAO;
import com.studentcourse.exception.DAOException;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/student/delete")
public class DeleteStudentServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private StudentDAO studentDAO = new StudentDAO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("loggedInUser") == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}

		String idStr = request.getParameter("id");
		if (idStr == null || idStr.isEmpty()) {
			response.sendRedirect(request.getContextPath() + "/students");
			return;
		}

		int id = Integer.parseInt(idStr);

		try {
			studentDAO.deleteStudent(id);
			response.sendRedirect(request.getContextPath() + "/students");
		} catch (DAOException e) {

			request.setAttribute("errorMessage", e.getMessage());
			request.setAttribute("studentList", studentDAO.getAllStudents());
			request.getRequestDispatcher("/WEB-INF/views/student-list.jsp").forward(request, response);
		}
	}
}
