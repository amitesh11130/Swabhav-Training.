package com.studentcourse.controller;

import java.io.IOException;

import com.studentcourse.dao.RegistrationDAO;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/registration/delete")
public class DeleteRegistrationServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private RegistrationDAO registrationDAO = new RegistrationDAO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("loggedInUser") == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		int id = Integer.parseInt(request.getParameter("id"));
		registrationDAO.deleteRegistration(id);
		response.sendRedirect(request.getContextPath() + "/registrations");
	}
}