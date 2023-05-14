package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.User;

public class DAO {
	private Connection conn;

	public DAO() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

			String url = "jdbc:sqlserver://localhost:1433;database=FlabbyBird;encrypt=true;trustServerCertificate=true;";
			String user = "sa";
			String password = "123456";
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("Get a connection");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public boolean addUser(User s) {
		   if(s == null) {
		      return false;
		   }
		   String sql = "INSERT INTO [User] (Name, Score) VALUES (?, ?)";
		   try {
		      PreparedStatement ps = conn.prepareStatement(sql);
		      ps.setString(1, s.getName());
		      ps.setInt(2, s.getScore());

		      return ps.executeUpdate() > 0;
		   } catch (SQLException e) {
		      System.err.println("Error inserting user: " + e.getMessage());
		      return false;
		   }
		}

	public static void main(String[] args) {
		new DAO();
	}
}

