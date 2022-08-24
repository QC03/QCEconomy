package com.github.QCEconomy.Database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EcoSQL {

	public Connection con;
	public Statement stmt;
	
	private final String url = "jdbc:mysql://localhost/?characterEncoding=UTF-8&serverTimezone=UTC";
	private final String user = "root";
	private final String password = "zxcvv1324!";
	
	public EcoSQL()
	{
		try {
			this.con = DriverManager.getConnection(this.url, this.user, this.password);
			this.stmt = this.con.createStatement();
			this.stmt.executeUpdate("use economy");
			
		} catch (SQLException e) {
			System.out.println("DB연결을 실패하거나, SQL문이 틀렸습니다.");
			System.out.print("사유 : " + e.getMessage());
		}
	}
	
	//Type = "cash" or "money"
	//return = if has exception -> false
	public void createDatabase(String type)
	{
		try {
			
			this.stmt = this.con.createStatement();
			this.stmt.executeUpdate("use economy");
			
			DatabaseMetaData dbm = con.getMetaData();
			ResultSet tables = dbm.getTables(null, null, type, null);
			if (!(tables.next()))
			{ 
				this.con.close();
				this.stmt.close();
				return;
			}
			
			String createTableSQL = "CREATE TABLE " + type +
	                   " (uuid CHAR(36) not NULL, " +
	                   " cash INT(255) not NULL, " + 
	                   " PRIMARY KEY ( uuid ))";
			
			this.stmt.executeUpdate(createTableSQL);
			
			this.con.close();
			this.stmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void closeSQL()
	{
		try {
			this.con.close();
			this.stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
