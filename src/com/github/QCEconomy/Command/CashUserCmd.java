package com.github.QCEconomy.Command;

import java.text.NumberFormat;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.QCEconomy.CashDB.CashDatabase;

public class CashUserCmd implements CommandExecutor {
	
	private String getCashPrefix()
	{
		return ChatColor.GREEN + "[Cash] " + ChatColor.RESET;
	}
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
			
		if (!(sender instanceof Player)) { return false; }
		
		Player p = (Player) sender;
		String uuid = p.getUniqueId().toString();
		
		p.sendMessage(getCashPrefix() + p.getDisplayName() + " 님의 보유 캐시");
		p.sendMessage(getCashPrefix() + "     " + NumberFormat.getInstance().format(CashDatabase.getUserCash(uuid)) + " 원");
		return true;

	}
}
