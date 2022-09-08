package com.mohammad.base.JdbcBrushUp.TestingConnection;

import java.sql.Connection;
import java.sql.DriverManager;
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
		// Here we are creating our query for inserting the value inside the table in the form of String.
		String q2="insert into employee( empId,empName) values ('E2K19206468','Mohammad Kaif Altaf Sayyed')";
		try {
			/*
			 * Step 3:Here we are creating the object of statement object. This will be
			 * required to fire the queries. Also we this line of code can throw an
			 * Exception saying 'SQLException' thus surrounding it with try catch over here.
			 */			
			Statement statement = connection.createStatement();
			//Now we are executing our create table query and this is how we do it using Statement object.
			statement.executeUpdate(q1);
			System.out.println("Table created");
			//Executing the insert query.
			statement.executeUpdate(q2);
			System.out.println("Inserted the data sucessfully");

		} catch (Exception e) {
			System.out.println("A problem was encountered during creation of statement");
			e.printStackTrace();
		}
		return;
	}
}
