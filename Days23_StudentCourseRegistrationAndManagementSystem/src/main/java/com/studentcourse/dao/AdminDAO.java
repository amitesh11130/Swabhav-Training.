package com.studentcourse.dao;

import java.sql.*;
import com.studentcourse.util.DBConnection;

public class AdminDAO {

	public boolean validateAdmin(String username, String password) {
		boolean status = false;
		String sql = "SELECT * FROM admin WHERE username=? AND password=?";

		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, username);
			ps.setString(2, password);
			try (ResultSet rs = ps.executeQuery()) {
				status = rs.next();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}
}