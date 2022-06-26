package com.github.QCEconomy.CashDB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CashDatabase {
	
	public static void addUserCashDB(String uuid)
	{
		try {
			
			Connection con = DBConnection.getConnection();
			Statement stmt = con.createStatement();
			
			stmt.executeUpdate("use economy");
			
			String insertSQL = "INSERT INTO cash VALUES ('" + uuid + "', 0)";
			stmt.executeUpdate(insertSQL);
			
			con.close();
			stmt.close();
			
		} catch (SQLException e) {
			
		}
	}
	
	public static int getUserCash(String uuid)
	{
		
		int cash = 0;
		
		try {
			
			Connection con = DBConnection.getConnection();
			Statement stmt = con.createStatement();
			
			stmt.executeUpdate("use economy");
			
			String selectSQL = "SELECT cash FROM cash WHERE uuid = '" + uuid + "'";
			ResultSet rs = stmt.executeQuery(selectSQL);
			
			while (rs.next()) {
				cash = rs.getInt("cash");

				break;
			}
			
			con.close();
			stmt.close();
			
			return cash;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cash;
	}
	
	
	public static void setUserCash(String uuid, int cash)
	{
		
		if (cash < 0) { cash = 0; } //음수 방지
		
		try {
			
			Connection con = DBConnection.getConnection();
			Statement stmt = con.createStatement();
			
			stmt.executeUpdate("use economy");
			
			String updateSQL = "UPDATE cash SET cash = " + cash + " WHERE uuid = '" + uuid + "'";
			stmt.executeUpdate(updateSQL);
			
			con.close();
			stmt.close();
			
		} catch (SQLException e) {
			
		}
	}
}
