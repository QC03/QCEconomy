package com.github.QCEconomy.Database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Cash {

	public void addUserToDB(String uuid)
	{
		try {
			
			EcoSQL ecoSql = new EcoSQL();
			
			String insertSQL = "INSERT INTO cash VALUES ('" + uuid + "', 0)";
			ecoSql.stmt.executeUpdate(insertSQL);
			ecoSql.closeSQL();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int getUserCash(String uuid)
	{
		
		int cash = 0;
		
		try {
			
			EcoSQL ecoSql = new EcoSQL();
			
			String selectSQL = "SELECT cash FROM cash WHERE uuid = '" + uuid + "'";
			ResultSet rs = ecoSql.stmt.executeQuery(selectSQL);
			
			while (rs.next()) {
				cash = rs.getInt("cash");
				break;
			}
			
			ecoSql.closeSQL();
			return cash;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cash;
	}
	
	public void setUserCash(String uuid, int cash)
	{
		
		if (cash < 0) { cash = 0; } //음수 방지
		
		try {
			
			EcoSQL ecoSql = new EcoSQL();
			
			String updateSQL = "UPDATE cash SET cash = " + cash + " WHERE uuid = '" + uuid + "'";
			ecoSql.stmt.executeUpdate(updateSQL);
			ecoSql.closeSQL();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
