package com.github.QCEconomy.MoneyDB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MoneyDatabase {

	public static void addUserMoneyDB(String uuid)
	{
		try {
			
			Connection con = MoneyDBConnection.getConnection();
			Statement stmt = con.createStatement();
			
			stmt.executeUpdate("use economy");
			
			String insertSQL = "INSERT INTO money VALUES ('" + uuid + "', 0)";
			stmt.executeUpdate(insertSQL);
			
			con.close();
			stmt.close();
			
		} catch (SQLException e) {
			
		}
	}
	
	public static int getUserMoney(String uuid)
	{
		
		int money = 0;
		
		try {
			
			Connection con = MoneyDBConnection.getConnection();
			Statement stmt = con.createStatement();
			
			stmt.executeUpdate("use economy");
			
			String selectSQL = "SELECT money FROM money WHERE uuid = '" + uuid + "'";
			ResultSet rs = stmt.executeQuery(selectSQL);
			
			while (rs.next()) {
				money = rs.getInt("money");

				break;
			}
			
			con.close();
			stmt.close();
			
			return money;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return money;
	}
	
	
	public static void setUserMoney(String uuid, int money)
	{
		
		if (money < 0) { money = 0; } //음수 방지
		
		try {
			
			Connection con = MoneyDBConnection.getConnection();
			Statement stmt = con.createStatement();
			
			stmt.executeUpdate("use economy");
			
			String updateSQL = "UPDATE money SET money = " + money + " WHERE uuid = '" + uuid + "'";
			stmt.executeUpdate(updateSQL);
			
			con.close();
			stmt.close();
			
		} catch (SQLException e) {
			
		}
	}
}
