package com.github.QCEconomy.Event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.github.QCEconomy.Main;
import com.github.QCEconomy.CashDB.CashDatabase;
import com.github.QCEconomy.MoneyDB.MoneyDatabase;

public class onFirstJoin implements Listener {

	public onFirstJoin(Main plugin)
	{
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
	  
		// if (event.getPlayer().hasPlayedBefore()) { return; }
		
		String uuid = event.getPlayer().getUniqueId().toString();
		
		MoneyDatabase.addUserMoneyDB(uuid);
		CashDatabase.addUserCashDB(uuid);
	}
}
