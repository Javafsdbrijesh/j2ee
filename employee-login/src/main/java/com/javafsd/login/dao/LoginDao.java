package com.javafsd.login.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.javafsd.login.model.Login;

public class LoginDao {

	private final static String dbURL = "jdbc:mysql://localhost:3306/employee?useSSL=false";
	private final static String dbUserName = "root";
	private final static String dbPassword = "root";
	private final static String dbDriver = "com.mysql.jdbc.Driver";		
	private String validateUserQuery = "select * from login where username =? and password =? ";



	public boolean validateLogin(Login login) {

		boolean status = false;
		loadDriver(dbDriver);
		Connection con = getConnection();
		PreparedStatement ps;		
		try {
			ps = con.prepareStatement(validateUserQuery);
			ps.setString(1, login.getUsername());
			ps.setString(2, login.getPassword());

			ResultSet rs = ps.executeQuery();
			status = rs.next();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return status;

	}

	// method to load driver
	public void loadDriver(String driver) {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	// method to create connection
	private Connection getConnection() {
		Connection con = null;
		try {
			con = DriverManager.getConnection(dbURL, dbUserName, dbPassword);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

}


