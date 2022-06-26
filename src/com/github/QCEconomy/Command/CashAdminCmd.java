package com.github.QCEconomy.Command;

import java.text.NumberFormat;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.QCEconomy.CashDB.CashDatabase;
import com.github.QCUtilLib.Info.InfoDatabase;
import com.github.QCUtilLib.Message.ErrorMessage;

public class CashAdminCmd implements CommandExecutor {
	
	private String getCashPrefix()
	{
		return ChatColor.YELLOW + "[Cash] " + ChatColor.RESET;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
			
		if (args.length <= 1)
		{
			sender.sendMessage(" ");
			sender.sendMessage(getCashPrefix() + "/캐시관리 확인 [닉네임]");
			sender.sendMessage(getCashPrefix() + "/캐시관리 설정 [닉네임] [값]");
			sender.sendMessage(getCashPrefix() + "/캐시관리 지급 [닉네임] [값]");
			sender.sendMessage(getCashPrefix() + "/캐시관리 차감 [닉네임] [값]");
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
		int cash;
		
		
		if (args[0].equals("확인"))
		{
			cash = CashDatabase.getUserCash(uuid);
			sender.sendMessage(getCashPrefix() + name + " 님의 보유 캐시 : " + NumberFormat.getInstance().format(cash) + " 원");
			return true;
			
		}
		
		
		if (args.length <= 2)
		{
			ErrorMessage.sendError((Player) sender, "값을 모두 적어주세요.");
			return true;
		}
		
		
		cash = Integer.parseInt(args[2]);
		
		if (args[0].equals("설정"))
		{
			CashDatabase.setUserCash(uuid, cash);
			sender.sendMessage(getCashPrefix() + name + " 님의 보유 캐시을 " + NumberFormat.getInstance().format(cash) + " 원 (으)로 설정하였습니다.");
			return true;
		}
		
		
		int userCash = CashDatabase.getUserCash(uuid);
		
		if (args[0].equals("지급"))
		{
			
			CashDatabase.setUserCash(uuid, (userCash + cash));
			sender.sendMessage(getCashPrefix() + name + " 님의 보유 캐시에 " + NumberFormat.getInstance().format(cash) + " 원을 지급하였습니다.");
			return true;
		}
		
		if (args[0].equals("차감"))
		{
			
			CashDatabase.setUserCash(uuid, (userCash - cash));
			sender.sendMessage(getCashPrefix() + name + " 님의 보유 캐시에서 " + NumberFormat.getInstance().format(cash) + " 원을 차감하였습니다.");
			return true;
		}
		
		return false;
	}
}
