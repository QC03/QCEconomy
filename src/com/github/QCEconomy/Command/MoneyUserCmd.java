package com.github.QCEconomy.Command;

import java.text.NumberFormat;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.QCEconomy.Database.EcoUser;
import com.github.QCEconomy.Database.Money;
import com.github.QCUtilLib.Info.InfoDatabase;

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
			p.sendMessage(getMoneyPrefix() + "     " + NumberFormat.getInstance().format(new Money().getUserMoney(uuid)) + " 원");

			p.sendMessage(getMoneyPrefix() + " /돈 도움말");
			return true;
		}
		
		if (args[0].equals("도움말"))
		{
			sender.sendMessage(" ");
			sender.sendMessage(getMoneyPrefix() + "/돈");
			sender.sendMessage(getMoneyPrefix() + "/돈 순위 <페이지>");
			sender.sendMessage(" ");
			
			return true;
		}
		
		if (args[0].equals("순위"))
		{
			int page = 1;
			if (args.length >= 2)
			{
				if (args[1].matches("[+-]?\\d*(\\.\\d+)?"))
				{
					page = Integer.parseInt(args[1]);
				}
			}
			
			
			int rank = 1;
			int preUserMoney = -1;
			int index = 0;
			for ( EcoUser user : new Money().getMoneyRank() )
			{
				index++;
				if (index < (((page)-1)*10)) { continue; }
				if (index > ((page)*10)) { continue; }
				
				if (preUserMoney != user.money) { rank++; }
				
				sender.sendMessage(rank + "등 [ " + InfoDatabase.getUserInfo("name", "uuid = '" + user.uuid + "'") + " ] [ " + NumberFormat.getInstance().format(user.money) + "원 ]");
				preUserMoney = user.money;
			}
			
			sender.sendMessage("        [ " + page + " / " + (index / 10) + " ]        ");
		}
		
		return true;

	}
}
