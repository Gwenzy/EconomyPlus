package fr.gwenzy.economyplus.commands;

import java.io.UnsupportedEncodingException;

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
		try {
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
						p.sendMessage(ChatColor.RED + new String(("[" +EconomyPlus.bankName+"] "+ EconomyPlus.langFile.getString("commands.pay.errorSyntax")).getBytes(), EconomyPlus.encoding));
					}
					if(ok)
					{
						if(EconomyPlus.s.getPlayer(player)!=null)
						{
							if(CashMethods.tradeMoney(p.getName(), player, amount))
							{
								String msg = "["+EconomyPlus.bankName+"] "+EconomyPlus.langFile.getString("commands.pay.successSended").replaceAll("%nb%", String.valueOf(amount)).replaceAll("%p%", player).replaceAll("%g%", EconomyPlus.moneyName);
								
								
									p.sendMessage(ChatColor.GREEN + new String(msg.getBytes(), EconomyPlus.encoding));
								
								EconomyPlus.s.getPlayer(player).sendMessage(ChatColor.GREEN + new String(( "["+EconomyPlus.bankName+"] "+EconomyPlus.langFile.getString("commands.pay.successReceived").replaceAll("%nb%", String.valueOf(amount)).replaceAll("%g%", EconomyPlus.moneyName).replaceAll("%p%", p.getName())).getBytes(), EconomyPlus.encoding));
							}
							else
								p.sendMessage(ChatColor.RED + new String(("[" +EconomyPlus.bankName+"] "+ EconomyPlus.langFile.getString("commands.pay.errorNotEnough")).getBytes(), EconomyPlus.encoding));
							
						}
						else
							p.sendMessage(ChatColor.RED + new String(("[" +EconomyPlus.bankName+"] "+ EconomyPlus.langFile.getString("basic.error.notConnected")).getBytes(), EconomyPlus.encoding));

					}
					
				}
				else
					p.sendMessage(ChatColor.RED + new String(EconomyPlus.langFile.getString("commands.pay.errorArgs").getBytes(), EconomyPlus.encoding));
			}
			else
			{
				Command.broadcastCommandMessage(EconomyPlus.s.getConsoleSender(), ChatColor.RED + "[EconomyPlus] "+EconomyPlus.langFile.getString("error.console"));
			}
		return true;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}

}
