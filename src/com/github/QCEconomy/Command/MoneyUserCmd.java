package com.github.QCEconomy.Command;

import java.text.NumberFormat;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.QCEconomy.MoneyDB.MoneyDatabase;

public class MoneyUserCmd implements CommandExecutor {

	
	private String getMoneyPrefix()
	{
		return ChatColor.GREEN + "[Money] " + ChatColor.RESET;
	}
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
			
		if (!(sender instanceof Player)) { return false; }
		
		Player p = (Player) sender;
		String uuid = p.getUniqueId().toString();
		
		int money = MoneyDatabase.getUserMoney(uuid);
		
		p.sendMessage(getMoneyPrefix() + p.getDisplayName() + " 님의 보유 돈");
		p.sendMessage(getMoneyPrefix() + "     " + NumberFormat.getInstance().format(money) + " 원");
		return true;

	}
}
