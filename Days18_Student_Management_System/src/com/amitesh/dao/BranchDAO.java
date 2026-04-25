package com.amitesh.dao;

import java.sql.*;

import com.amitesh.model.Branch;
import com.amitesh.util.DBUtil;

public class BranchDAO {

	public boolean addBranch(Branch b) throws SQLException {
		String sql = "INSERT INTO branch(branchname) VALUES (?)";

		try (Connection con = DBUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setString(1, b.getBranchName());

			return ps.executeUpdate() > 0;
		}
	}

	// show all branch
	public void showAllBranches() throws SQLException {
		String query = "SELECT branchid, branchname FROM branch";

		try (Connection con = DBUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(query);
				ResultSet rs = ps.executeQuery()) {

			System.out.println("\n AVAILABLE BRANCHES");
			System.out.println("========================");

			while (rs.next()) {
				System.out.println("ID: " + rs.getInt("branchid") + " | Name: " + rs.getString("branchname"));
			}
		}
	}

	// branch exit or not
	public boolean branchExists(int branchId) throws SQLException {
		String query = "SELECT branchid FROM branch WHERE branchid = ?";

		try (Connection con = DBUtil.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {

			ps.setInt(1, branchId);

			try (ResultSet rs = ps.executeQuery()) {
				return rs.next();
			}
		}
	}

}