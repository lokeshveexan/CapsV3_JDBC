package com.caps.jsp;


import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

import com.mysql.jdbc.Driver; //required for jdbc to connect to mySql database


public class MyFirstJDBCProgram {


	public static void main(String[] args) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			/*
			 * 1. Load the Driver
			 */
			java.sql.Driver driverRef = new Driver();
			DriverManager.registerDriver(driverRef);
			System.out.println("Driver Loaded...");
			
			/*
			 * 2. Get the DB Connection via Driver
			 */
//						String dbUrl="jdbc:mysql://localhost:3306/capsV3_db"
//								+ "?user=root&password=root";
			String dbUrl="jdbc:mysql://localhost:3306/capsV3_db";
//			con = DriverManager.getConnection(dbUrl); //1st version of getConnection

			//2nd Version of getConnection
//			Scanner in = new Scanner(System.in);
//			String user = in.nextLine();
//			String password = in.nextLine();
//			con = DriverManager.getConnection(dbUrl, user, password);
			
			//3rd Way to get a DB Connection
			String filePath = "F:/Files/db.properties";
			FileReader reader = new FileReader(filePath);
			Properties prop = new Properties();
			prop.load(reader);
			
			con = DriverManager.getConnection(dbUrl, prop);
			
			
			System.out.println("Connected...");
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			/*
			 * 3. Issue the SQL query via connection
			 */
			String sql = "select * from students_info";

			int count = 0;
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);

			/*
			 * 4. Process the results
			 */

			while(rs.next()){
				int regno = rs.getInt(1);
				String firstname = rs.getString(2);
				String lastname = rs.getString(3);
				String isAdmin = rs.getString(4);
				String passwd = rs.getString(5);

				System.out.println(regno);
				System.out.println(firstname);
				System.out.println(lastname);
				System.out.println(isAdmin);
				System.out.println(passwd);
				System.out.println("*********************");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			/*
			 * 5. Close all the JDBC Objects
			 */

			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(stmt != null){
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(rs != null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
	}//end of main

}//End of Class