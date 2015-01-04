package fr.gwenzy.economyplus.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.gwenzy.economyplus.main.EconomyPlus;
import fr.gwenzy.economyplus.methods.CashMethods;

public class PayCommand implements CommandExecutor {
	public PayCommand() {}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String CommandLabel,
			String[] args) {

			if(sender instanceof Player)
			{
				Player p = (Player) sender;
				if(args.length == 2)
				{
					int amount = 0;
					boolean ok = false;
					String player = args [0];
					try
					{
						amount = Integer.parseInt(args[1]);
						ok=true;
						
					}
					catch(Exception e)
					{
						p.sendMessage(ChatColor.RED +"[" +EconomyPlus.bankName+"] "+ EconomyPlus.langFile.getString("commands.pay.errorSyntax"));
					}
					if(ok)
					{
						if(EconomyPlus.s.getPlayer(player)!=null)
						{
							if(CashMethods.tradeMoney(p.getName(), player, amount))
							{
								p.sendMessage(ChatColor.GREEN + "["+EconomyPlus.bankName+"] "+EconomyPlus.langFile.getString("commands.pay.successSended").replaceAll("%nb%", String.valueOf(amount)).replaceAll("%p%", player).replaceAll("%g%", EconomyPlus.moneyName));
								EconomyPlus.s.getPlayer(player).sendMessage(ChatColor.GREEN + "["+EconomyPlus.bankName+"] "+EconomyPlus.langFile.getString("commands.pay.successReceived").replaceAll("%nb%", String.valueOf(amount)).replaceAll("%g%", EconomyPlus.moneyName).replaceAll("%p%", p.getName()));
							}
							else
								p.sendMessage(ChatColor.RED +"[" +EconomyPlus.bankName+"] "+ EconomyPlus.langFile.getString("commands.pay.errorNotEnough"));
							
						}
						else
							p.sendMessage(ChatColor.RED +"[" +EconomyPlus.bankName+"] "+ EconomyPlus.langFile.getString("basic.error.notConnected"));

					}
					
				}
				else
					p.sendMessage(ChatColor.RED + EconomyPlus.langFile.getString("commands.pay.errorArgs"));
			}
			else
			{
				Command.broadcastCommandMessage(EconomyPlus.s.getConsoleSender(), ChatColor.RED + "[EconomyPlus] "+EconomyPlus.langFile.getString("error.console"));
			}
		return true;
	}

}
