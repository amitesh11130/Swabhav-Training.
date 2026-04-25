package com.amitesh.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

	static final String url = "jdbc:mysql://localhost:3306/student_management_system";
	private static final String username = "root";
	private static final String password = "AmitMono@897879";

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, username, password);
	}
}