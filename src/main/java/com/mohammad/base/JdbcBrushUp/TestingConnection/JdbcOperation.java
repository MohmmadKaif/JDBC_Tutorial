package com.mohammad.base.JdbcBrushUp.TestingConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcOperation {
	public Connection connection;

	public void provideConnection() {

		/*
		 * Step 1: To load the oracle driver. We are using forName()method for this
		 * purpose. We are putting this in try catch block as this line of code throws a
		 * Exception saying "Class not found exception"
		 */
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("Encountered a problem while loading the driver");
			e.printStackTrace();
		}
		/*
		 * Step 2: Here we are getting the connection Again handling the Exception as it
		 * throws "SQLException"
		 */
		try {
			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr", "hr");
			if (connection.isClosed()) {
				System.out.println("Connection is closed");
				return;
			} else
				System.out.println("Connection is created");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void createEmployeeTable() {
		// Here we are creating our query for creating the table in the form of String.
		String q1 = "create table employee(empId VARCHAR(100) PRIMARY KEY,empName VARCHAR(100))";

		try {
			/*
			 * Step 3:Here we are creating the object of statement object. This will be
			 * required to fire the queries. Also we this line of code can throw an
			 * Exception saying 'SQLException' thus surrounding it with try catch over here.
			 */
			Statement statement = connection.createStatement();
			// Now we are executing our create table query and this is how we do it using
			// Statement object.
			statement.executeUpdate(q1);
			System.out.println("Table created");
		} catch (Exception e) {
			System.out.println("A problem was encountered during creation of statement");
			e.printStackTrace();
		}

		return;
	}

	// This is method is created to add the employee inside the database.
	public void addEmployee(String empId, String empName) {
		// Here you can see that there are two question marks. This is because we are
		// inputting this value dynamically.
		String q = "insert into employee (empId,empName) values(?,?)";
		/*
		 * Now here we are getting the PreparedStatement object using Connection
		 * object.Please see the spellings carefully Also this line of code can throw an
		 * error saying that 'SQLException'
		 */
		try {
			PreparedStatement pstatement = connection.prepareStatement(q);
			/*
			 * Now setting the values inside the PreparedStatement object. This is supposed
			 * to be done when entering the values dynamically inside the query. The first
			 * argument refers to the number of question mark and second argument refers to the actual value which we want to insert.
			 */
			pstatement.setString(1, empId);
			pstatement.setString(2, empName);
			pstatement.executeUpdate();
			System.out.println("Employee added succesfully");
		} catch (SQLException e) {
			System.out.println("Problem was encountered while creating the PreparedStatement object\n");
			e.printStackTrace();
		}
	}
}
