package fr.gwenzy.economyplus.listeners;

import java.io.UnsupportedEncodingException;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.Plugin;

import fr.gwenzy.economyplus.main.EconomyPlus;
import fr.gwenzy.economyplus.methods.CashMethods;

public class ChatListener implements Listener {

	private Plugin p;
	public ChatListener(EconomyPlus p)
	{
		this.p = p;
	}
	
	@EventHandler
	public void onChatEvent(AsyncPlayerChatEvent event) throws UnsupportedEncodingException
	{
		if(event.getPlayer().getMetadata("wait4put")!= null && event.getPlayer().getMetadata("wait4put")!= null )
		{
			
			if(event.getPlayer().getMetadata("wait4put").get(0).asBoolean())
			{
				int amount=0;
				boolean ok = false;
				try
				{
					amount = Integer.parseInt(event.getMessage());
					ok=true;
					
				}
				catch(Exception e)
				{
					event.getPlayer().sendMessage(ChatColor.RED + new String(("[" +EconomyPlus.bankName+"] "+ EconomyPlus.langFile.getString("error.mustBeInteger")).getBytes(), EconomyPlus.encoding));
				}
				event.getPlayer().setMetadata("wait4put", new FixedMetadataValue(this.p, false));
				if(ok)
					if(!CashMethods.putMoneyOnAccount(event.getPlayer().getName(), amount))
						event.getPlayer().sendMessage(ChatColor.RED + new String(("[" +EconomyPlus.bankName+"] "+ EconomyPlus.langFile.getString("commands.pay.errorNotEnough")).getBytes(), EconomyPlus.encoding));
					else
						event.getPlayer().sendMessage(ChatColor.GREEN + new String(("[" +EconomyPlus.bankName+"] "+ EconomyPlus.langFile.getString("account.success")).getBytes(), EconomyPlus.encoding));
						
				event.setCancelled(true);
			}
			if(event.getPlayer().getMetadata("wait4get").get(0).asBoolean())
			{
				int amount=0;
				boolean ok = false;
				try
				{
					amount = Integer.parseInt(event.getMessage());
					ok=true;
					
				}
				catch(Exception e)
				{
					event.getPlayer().sendMessage(ChatColor.RED + new String(("[" +EconomyPlus.bankName+"] "+ EconomyPlus.langFile.getString("error.mustBeInteger")).getBytes(), EconomyPlus.encoding));
				}
				event.getPlayer().setMetadata("wait4get", new FixedMetadataValue(this.p, false));
				if(ok)
					if(!CashMethods.getMoneyFromAccount(event.getPlayer().getName(), amount))
						event.getPlayer().sendMessage(ChatColor.RED + new String(("[" +EconomyPlus.bankName+"] "+ EconomyPlus.langFile.getString("error.notEnoughAccount")).getBytes(), EconomyPlus.encoding));
					else
						event.getPlayer().sendMessage(ChatColor.GREEN + new String(("[" +EconomyPlus.bankName+"] "+ EconomyPlus.langFile.getString("account.success")).getBytes(), EconomyPlus.encoding));
					
				event.setCancelled(true);
			}
		}
		else
			event.getPlayer().sendMessage(ChatColor.RED + new String(( "["+EconomyPlus.bankName+"] "+EconomyPlus.langFile.getString("error.metadata")).getBytes(), EconomyPlus.encoding));
		
			
	}
}
