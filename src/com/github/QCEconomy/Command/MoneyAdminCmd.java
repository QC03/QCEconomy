package com.github.QCEconomy.Command;

import java.text.NumberFormat;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.QCEconomy.Database.Money;
import com.github.QCUtilLib.Info.InfoDatabase;
import com.github.QCUtilLib.Message.ErrorMessage;

public class MoneyAdminCmd implements CommandExecutor {

	
	private String getMoneyPrefix()
	{
		return ChatColor.GREEN + "[Money] " + ChatColor.RESET;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
			
		if (args.length <= 1)
		{
			sender.sendMessage(" ");
			sender.sendMessage(getMoneyPrefix() + "/돈관리 확인 [닉네임]");
			sender.sendMessage(getMoneyPrefix() + "/돈관리 설정 [닉네임] [값]");
			sender.sendMessage(getMoneyPrefix() + "/돈관리 지급 [닉네임] [값]");
			sender.sendMessage(getMoneyPrefix() + "/돈관리 차감 [닉네임] [값]");
			sender.sendMessage(" ");
			
			return true;
		}
		
		String name = args[1];
		String uuid;
		
		if (!(InfoDatabase.isUser("name", name)))
		{
			ErrorMessage.sendError((Player) sender, "존재하지 않는 유저입니다.");
			return true;
		}
		
		
		uuid = InfoDatabase.getUserInfo("uuid", "name = '" + name + "'");
		int money;
		Money Money = new Money();
		
		if (args[0].equals("확인"))
		{
			money = Money.getUserMoney(uuid);
			sender.sendMessage(getMoneyPrefix() + name + " 님의 보유 돈 : " + NumberFormat.getInstance().format(money) + " 원");
			return true;
			
		}
		
		
		if (args.length <= 2)
		{
			ErrorMessage.sendError((Player) sender, "값을 모두 적어주세요.");
			return true;
		}
		
		
		money = Integer.parseInt(args[2]);
		
		if (args[0].equals("설정"))
		{
			Money.setUserMoney(uuid, money);
			sender.sendMessage(getMoneyPrefix() + name + " 님의 보유 돈을 " + NumberFormat.getInstance().format(money) + " 원 (으)로 설정하였습니다.");
			return true;
		}
		
		
		int userMoney = Money.getUserMoney(uuid);
		
		if (args[0].equals("지급"))
		{
			
			Money.setUserMoney(uuid, (userMoney + money));
			sender.sendMessage(getMoneyPrefix() + name + " 님의 보유 돈에 " + NumberFormat.getInstance().format(money) + " 원을 지급하였습니다.");
			return true;
		}
		
		if (args[0].equals("차감"))
		{
			
			Money.setUserMoney(uuid, (userMoney - money));
			sender.sendMessage(getMoneyPrefix() + name + " 님의 보유 돈에서 " + NumberFormat.getInstance().format(money) + " 원을 차감하였습니다.");
			return true;
		}
		
		return false;
	}
}
