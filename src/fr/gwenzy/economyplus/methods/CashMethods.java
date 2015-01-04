package fr.gwenzy.economyplus.methods;

import java.sql.ResultSet;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;

import fr.gwenzy.economyplus.main.EconomyPlus;

public class CashMethods {

	public static int getMoneyPocket(String player)
	{
		int money = 0;
		try
		{
			if(DatabaseMethods.getConnection(EconomyPlus.dbMethod))
			{
				ResultSet result = DatabaseMethods.executeQuery("SELECT * FROM economyplus_players WHERE pseudo = '"+player+"'");
				result.next();
				money = result.getInt("onPocket");
				DatabaseMethods.disconnect();
			}
			else
			{
				LanguageMethods.SQLERROR(player);
			}
		}
		catch(Exception e){
			Command.broadcastCommandMessage(EconomyPlus.s.getConsoleSender(), ChatColor.RED + "[EconomyPlus] "+EconomyPlus.langFile.getString("error.sqlGetMoney"));
		}
		return money;
	}
	public static int getMoneyAccount(String player){
		int money = 0;
		try
		{
			if(DatabaseMethods.getConnection(EconomyPlus.dbMethod))
			{
			ResultSet result = DatabaseMethods.executeQuery("SELECT * FROM economyplus_players WHERE pseudo = '"+player+"'");
			result.next();
			money = result.getInt("onAccount");
			DatabaseMethods.disconnect();
			}
			else
				LanguageMethods.SQLERROR(player);
			}
		catch(Exception e){
			Command.broadcastCommandMessage(EconomyPlus.s.getConsoleSender(), ChatColor.RED + "[EconomyPlus] "+EconomyPlus.langFile.getString("error.sqlGetMoney"));
		}
		return money;
	}
	
	public static void addMoney(String player, int amount){
		int money1 = getMoneyPocket(player);
		int money2 = money1+amount;
		setMoneyPocket(player, money2);
	}
	public static boolean removeMoney(String player, int amount){
		boolean success =false;
		int money1 = getMoneyPocket(player);
		int money2 = money1-amount;
		if(money2>=0)
		{
			setMoneyPocket(player, money2);
			success = true;
		}
		return success;
	}
	public static void setMoneyPocket(String player, int amount)
	{
		try
		{
			String sql = "UPDATE economyplus_players SET onPocket = "+amount+" WHERE pseudo ='"+player+"'";
			if(DatabaseMethods.getConnection(EconomyPlus.dbMethod))
			{
				DatabaseMethods.executeMyUpdate(sql);
				DatabaseMethods.disconnect();
			}
			else
				LanguageMethods.SQLERROR(player);
		}
		catch(Exception e){			
			Command.broadcastCommandMessage(EconomyPlus.s.getConsoleSender(), ChatColor.RED + "[EconomyPlus] "+EconomyPlus.langFile.getString("error.sqlSetMoney"));
			e.printStackTrace();
		}
	}
	public static boolean tradeMoney(String giver, String receiver, int amount){
		boolean success = false;
		if(removeMoney(giver, amount))
		{
			addMoney(receiver, amount);
			success = true;
		}
		return success;
	}
}
