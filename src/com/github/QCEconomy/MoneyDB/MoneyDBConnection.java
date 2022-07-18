package com.github.QCEconomy.MoneyDB;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MoneyDBConnection {

	private static Connection con;
	
	public static Connection getConnection()
	{
		String url = "jdbc:mysql://localhost/?characterEncoding=UTF-8&serverTimezone=UTC";
		String user = "root";
		String password = "zxcvv1324!";
		
		try {
			con = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			System.out.println("DB연결을 실패하거나, SQL문이 틀렸습니다.");
			System.out.print("사유 : " + e.getMessage());
		}
		
		return con;
	}
	
	public static void createDatabase()
	{
		Connection con = MoneyDBConnection.getConnection();
		Statement stmt;
		try {
			
			stmt = con.createStatement();
			
			stmt.executeUpdate("use economy");
			
			DatabaseMetaData dbm = con.getMetaData();
			ResultSet tables = dbm.getTables(null, null, "economy", null);
			if (!(tables.next()))
			{ 
				con.close();
				stmt.close();
				return;
			}
			
			String createTableSQL = "CREATE TABLE money " +
	                   "(uuid CHAR(36) not NULL, " +
	                   " money INT(255) not NULL, " + 
	                   " PRIMARY KEY ( uuid ))";
			
			stmt.executeUpdate(createTableSQL);
			
			con.close();
			stmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
