package com.github.QCEconomy.Database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Money {

	public void addUserToDB(String uuid)
	{
		try {
			
			EcoSQL ecoSql = new EcoSQL();
			
			String insertSQL = "INSERT INTO money VALUES ('" + uuid + "', 0)";
			ecoSql.stmt.executeUpdate(insertSQL);
			ecoSql.closeSQL();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int getUserMoney(String uuid)
	{
		
		int money = 0;
		
		try {
			
			EcoSQL ecoSql = new EcoSQL();
			
			String selectSQL = "SELECT money FROM money WHERE uuid = '" + uuid + "'";
			ResultSet rs = ecoSql.stmt.executeQuery(selectSQL);
			
			while (rs.next()) {
				money = rs.getInt("money");
				break;
			}
			
			ecoSql.closeSQL();
			return money;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return money;
	}
	
	public void setUserMoney(String uuid, int money)
	{
		
		if (money < 0) { money = 0; } //음수 방지
		
		try {
			
			EcoSQL ecoSql = new EcoSQL();
			
			String updateSQL = "UPDATE money SET money = " + money + " WHERE uuid = '" + uuid + "'";
			ecoSql.stmt.executeUpdate(updateSQL);
			ecoSql.closeSQL();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	public String getMoneyRank()
	{
		객체를 하나하나 만들어서 출력
	}
	**/
}
