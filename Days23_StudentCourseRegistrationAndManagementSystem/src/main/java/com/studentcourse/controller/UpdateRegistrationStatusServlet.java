package com.studentcourse.controller;

import java.io.IOException;

import com.studentcourse.dao.RegistrationDAO;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/registration/status")
public class UpdateRegistrationStatusServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private RegistrationDAO registrationDAO = new RegistrationDAO();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("loggedInUser") == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		int id = Integer.parseInt(request.getParameter("registrationId"));
		String status = request.getParameter("status");

		registrationDAO.updateStatus(id, status);
		response.sendRedirect(request.getContextPath() + "/registrations");
	}
}