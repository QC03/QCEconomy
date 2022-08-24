package com.github.QCEconomy.Event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.github.QCEconomy.Main;
import com.github.QCEconomy.Database.Cash;
import com.github.QCEconomy.Database.Money;

public class onFirstJoin implements Listener {

	public onFirstJoin(Main plugin)
	{
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
	  
		if (event.getPlayer().hasPlayedBefore()) { return; }
		
		String uuid = event.getPlayer().getUniqueId().toString();
		
		new Cash().addUserToDB(uuid);
		new Money().addUserToDB(uuid);
	}
}
