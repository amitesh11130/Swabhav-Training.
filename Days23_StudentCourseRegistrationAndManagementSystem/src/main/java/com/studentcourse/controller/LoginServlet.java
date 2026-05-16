package com.studentcourse.controller;

import java.io.IOException;

import com.studentcourse.dao.AdminDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login-action")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private AdminDAO adminDAO;

	@Override
	public void init() throws ServletException {
		adminDAO = new AdminDAO();
		System.out.println("LoginServlet initialized via init()");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String remember = request.getParameter("rememberMe");

		if (username == null || username.trim().isEmpty() || password == null || password.trim().isEmpty()) {
			request.setAttribute("error", "Username and Password are required!");
			request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
			return;
		}

		if (adminDAO.validateAdmin(username, password)) {
			HttpSession session = request.getSession(true);
			session.setAttribute("loggedInUser", username);
			session.setAttribute("loginTime", java.time.LocalDateTime.now().toString());

			Cookie cookie = new Cookie("rememberedAdmin", username);
			if (remember != null) {
				cookie.setMaxAge(24 * 60 * 60); // 1 Day Expiry
				response.addCookie(cookie);
			} else {
				cookie.setMaxAge(0); // Delete Cookie
				response.addCookie(cookie);
			}
			response.sendRedirect(request.getContextPath() + "/dashboard");
		} else {
			request.setAttribute("error", "Invalid username or password!");
			request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
		}
	}

	@Override
	public void destroy() {
		System.out.println("LoginServlet destroyed via destroy()");
	}
}