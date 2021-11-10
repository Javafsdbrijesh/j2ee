package com.javafsd.registration.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.javafsd.registration.model.Employee;

public class EmployeeDao {

	private final static String dbURL = "jdbc:mysql://localhost:3306/employee?useSSL=false";
	private final static String dbUserName = "root";
	private final static String dbPassword = "root";
	private final static String dbDriver = "com.mysql.jdbc.Driver";
	private String INSERT_EMPLOYEE_DETAILS = " INSERT INTO employee"
			+ "(id, first_name, last_name, username, password, "
			+ "address, contact) VALUES"
			+ "(?,?,?,?,?,?,?);";		

	public int registerEmployee(Employee employee) throws ClassNotFoundException {


		int result = 0;
		loadDriver(dbDriver);
		Connection con = getConnection();
		PreparedStatement preparedStatement;		
		try {	
			preparedStatement = con.prepareStatement(INSERT_EMPLOYEE_DETAILS);
			preparedStatement.setInt(1, 3);
			preparedStatement.setString(2, employee.getFirstName());
			preparedStatement.setString(3, employee.getLastName());
			preparedStatement.setString(4, employee.getUserName());
			preparedStatement.setString(5, employee.getPassword());
			preparedStatement.setString(6, employee.getAddress());
			preparedStatement.setString(7, employee.getContact());

			System.out.println(preparedStatement);
			result = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
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
