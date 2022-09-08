package com.mohammad.base.JdbcBrushUp.TestingConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class JdbcOperation {
	public void provideConnection() {
		// 
		// not found exception
		/*
		 * Step 1: To load the oracle driver. We are using forName()method for this purpose.
		 * We are putting this in try catch block as this line of code throws a Exception saying "Class not found exception"*/
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("Encountered a problem while loading the driver");
			e.printStackTrace();
		}
		/*Step 2: Here we are getting the connection
		 * Again handling the Exception as it throws "SQLException"*/
		try {
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr", "hr");
		     if(con.isClosed()) {
		    	 System.out.println("Connection is closed");
		    	 return;
		     }
		     else
		    	 System.out.println("Connection is created");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
