package com.github.QCEconomy;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.QCEconomy.Command.CashAdminCmd;
import com.github.QCEconomy.Command.CashUserCmd;
import com.github.QCEconomy.Command.MoneyAdminCmd;
import com.github.QCEconomy.Command.MoneyUserCmd;
import com.github.QCEconomy.Event.onFirstJoin;

public class Main extends JavaPlugin implements Listener {
	
	ConsoleCommandSender console = Bukkit.getConsoleSender();
	
	@Override
	public void onEnable() {
		
		console.sendMessage( ChatColor.AQUA + "[QCEconomy] Enable Plugin.");
		
		getServer().getPluginManager().registerEvents(this, this);
		getServer().getPluginManager().registerEvents(new onFirstJoin(this), this);
		
		getCommand("돈").setExecutor(new MoneyUserCmd());
		getCommand("돈관리").setExecutor(new MoneyAdminCmd());
		
		getCommand("캐시").setExecutor(new CashUserCmd());
		getCommand("캐시관리").setExecutor(new CashAdminCmd());

	}
	
	@Override
	public void onDisable() {
		
		console.sendMessage( ChatColor.AQUA + "[QCEconomy] Disable Plugin.");
		
	}
	
}