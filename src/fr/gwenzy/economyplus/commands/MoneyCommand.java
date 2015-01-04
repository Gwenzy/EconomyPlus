package fr.gwenzy.economyplus.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.gwenzy.economyplus.main.EconomyPlus;
import fr.gwenzy.economyplus.methods.CashMethods;

public class MoneyCommand implements CommandExecutor
{

	public MoneyCommand() {}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String CommansLabel,
			String[] args) {
		if(sender instanceof Player)
		{
			Player p = (Player) sender;
				int money = CashMethods.getMoneyPocket(p.getName());
				p.sendMessage(ChatColor.GOLD+"["+EconomyPlus.bankName+"] "+EconomyPlus.langFile.getString("money.onPocket").replaceAll("%nb%", String.valueOf(money)).replaceAll("%g%", EconomyPlus.moneyName));
				
			
		}
		else
		{
			Command.broadcastCommandMessage(EconomyPlus.s.getConsoleSender(), ChatColor.RED + "[EconomyPlus] "+EconomyPlus.langFile.getString("error.console"));
		}
		
		
		return true;
	}

}
