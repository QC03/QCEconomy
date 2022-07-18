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
		
		if (args.length <= 0)
		{
			p.sendMessage(getMoneyPrefix() + p.getDisplayName() + " 님의 보유 돈");
			p.sendMessage(getMoneyPrefix() + "     " + NumberFormat.getInstance().format(MoneyDatabase.getUserMoney(uuid)) + " 원");

			p.sendMessage(getMoneyPrefix() + " /돈 도움말");
			return true;
		}
		
		if (args[0].equals("도움말"))
		{
			sender.sendMessage(" ");
			sender.sendMessage(getMoneyPrefix() + "/돈");
			sender.sendMessage(getMoneyPrefix() + "/돈 순위");
			sender.sendMessage(" ");
			
			return true;
		}
		
		return true;

	}
}
