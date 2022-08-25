package com.github.QCEconomy.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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
	
	
	public List<EcoUser> getMoneyRank()
	{
		
		List<Integer> moneyList = new ArrayList<Integer>();
		List<EcoUser> userList = new ArrayList<EcoUser>();
		
		try {
			
			EcoSQL ecoSql = new EcoSQL();
			
			String selectSQL = "SELECT uuid, money FROM money";
			ResultSet rs = ecoSql.stmt.executeQuery(selectSQL);
			
			int money;
			String uuid;
			while (rs.next()) {
				money = rs.getInt("money");
				uuid = rs.getString("uuid");
				
				moneyList.add(money);
				userList.add(new EcoUser(uuid, money));
			}
			
			ecoSql.closeSQL();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (moneyList == null || userList == null) { return null; }
		
		List<EcoUser> ranking = new ArrayList<EcoUser>();
		moneyList.sort(Comparator.naturalOrder());
		for ( int rankMoney : moneyList)
		{
			for ( EcoUser user : userList )
			{
				if (rankMoney != user.money) { continue; }
				
				ranking.add(user);
			}
		}
		
		return ranking;
	}
}
