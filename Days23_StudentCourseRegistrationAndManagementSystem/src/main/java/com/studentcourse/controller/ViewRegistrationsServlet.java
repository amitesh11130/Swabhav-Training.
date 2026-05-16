package com.studentcourse.controller;

import java.io.IOException;

import com.studentcourse.dao.RegistrationDAO;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/registrations")
public class ViewRegistrationsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private RegistrationDAO registrationDAO = new RegistrationDAO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("loggedInUser") == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		request.setAttribute("registrationList", registrationDAO.getAllRegistrations());
		request.getRequestDispatcher("/WEB-INF/views/registration-list.jsp").forward(request, response);
	}
}