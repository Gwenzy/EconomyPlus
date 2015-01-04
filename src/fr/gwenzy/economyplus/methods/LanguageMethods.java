package fr.gwenzy.economyplus.methods;

import java.io.File;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import fr.gwenzy.economyplus.main.EconomyPlus;

public class LanguageMethods {

	public static void initDefaultLanguage()
	{
		try
		{
			File lang = new File("plugins/EconomyPlus/langs/en.yml");
			if(!lang.exists())
			{	
				lang.createNewFile();
			
				FileConfiguration en = YamlConfiguration.loadConfiguration(lang);
				en.set("basic.enabled", "Plugin enabled");
				en.set("basic.disabled", "Plugin disabled");
				en.set("basic.connectedSQLite", "Connected successfully to the SQLite Database");
				en.set("basic.connectedMySQL", "Connected successfully to the MySQL Database");
				en.set("basic.SQLSuccess", "SQL requests successfully executed");
				en.set("account.created", "An account have been created for %p%");
				en.set("money.onPocket", "You have %nb% %g% in your pocket !");
				en.set("money.onAccount", "You have %nb% %g% in your pocket !");
				en.set("error.sqlGetMoney", "An error has occurred while trying to get money of player");
				en.set("error.sqlSetMoney", "An error has occurred while trying to set money of player");
				en.set("error.console", "You must be a player to do that");
				en.set("commands.pay.errorArgs", "Error in arguments : /pay <Player> <Amount>");
				en.set("commands.pay.errorSyntax", "Error in syntax : /pay <Player> <Amount>");
				en.set("commands.pay.errorNotEnough", "You don't have enough money !");
				en.set("commands.pay.successSended", "You have given %nb% to %p%");
				en.set("commands.pay.successReceived", "You receive %nb% from %p%");
				en.set("basic.error.notConnected", "The targeted player isn't online");
				en.set("basic.error.database", "Plugin is not connected to the database, please contact an administrator");
				
				en.save(lang);
			}
		
		}
		catch(Exception e)
		{
			
		}
	}
	public static void SQLERROR(String player)
	{
		EconomyPlus.s.getPlayer(player).sendMessage(ChatColor.RED + "["+EconomyPlus.bankName+"] "+EconomyPlus.langFile.getString("basic.error.database"));
		
	}
}
