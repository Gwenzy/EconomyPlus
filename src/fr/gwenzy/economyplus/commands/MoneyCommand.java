package fr.gwenzy.economyplus.commands;

import java.sql.ResultSet;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.gwenzy.economyplus.main.EconomyPlus;
import fr.gwenzy.economyplus.methods.DatabaseMethods;

public class MoneyCommand implements CommandExecutor
{

	public MoneyCommand() {}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String CommansLabel,
			String[] args) {
		if(sender instanceof Player)
		{
			Player p = (Player) sender;
			try
			{
				java.sql.Statement state = DatabaseMethods.getConnection(EconomyPlus.dbMethod).createStatement();
				ResultSet result = state.executeQuery("SELECT * FROM economyplus_players WHERE pseudo = '"+p.getName()+"'");
				if(result.next())
				{
					p.sendMessage(ChatColor.GOLD+EconomyPlus.langFile.getString("money.onPocket").replaceAll("%nb%", String.valueOf(result.getInt("onPocket"))).replaceAll("%g%", EconomyPlus.moneyName));
					
				}
			}
			catch(Exception e){
				Command.broadcastCommandMessage(EconomyPlus.s.getConsoleSender(), ChatColor.RED + "[EconomyPlus] "+EconomyPlus.langFile.getString("error.sqlMoneyPocket"));
				
			}
		}
		
		
		return true;
	}

}
