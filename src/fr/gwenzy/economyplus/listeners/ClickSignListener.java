package fr.gwenzy.economyplus.listeners;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

import org.bukkit.ChatColor;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.Plugin;

import fr.gwenzy.economyplus.main.EconomyPlus;
import fr.gwenzy.economyplus.methods.CashMethods;

public class ClickSignListener implements Listener 
{
	
	private Plugin p;
	public ClickSignListener(EconomyPlus p)
	{
		this.p = p;
	}
	@EventHandler
	public void onClickSign(PlayerInteractEvent event) throws SQLException, UnsupportedEncodingException
	{
		if(event.getAction() == Action.RIGHT_CLICK_BLOCK)
		{
			if(event.getClickedBlock().getState() instanceof Sign)
			{
				Sign sign = (Sign) event.getClickedBlock().getState();
				if(sign.getLine(0).equalsIgnoreCase(EconomyPlus.config.getString("signs.line1color").replace("&", "§")+"["+EconomyPlus.bankName+"]"))
				{

					if(sign.getLine(1)!=null && sign.getLine(1).equals(EconomyPlus.config.getString("signs.put.line2")))
					{
						event.getPlayer().sendMessage(ChatColor.GOLD + new String(("["+EconomyPlus.bankName+"] ").getBytes(), EconomyPlus.encoding)+new String(EconomyPlus.langFile.getString("account.put").getBytes(),"UTF-8"));
						event.getPlayer().setMetadata("wait4put", new FixedMetadataValue(this.p, true));
					}
					else if(sign.getLine(1)!=null && sign.getLine(1).equals(EconomyPlus.config.getString("signs.take.line2")))
					{
						event.getPlayer().sendMessage(ChatColor.GOLD + new String(("["+EconomyPlus.bankName+"] "+EconomyPlus.langFile.getString("account.get")).getBytes(), EconomyPlus.encoding));
						
						event.getPlayer().setMetadata("wait4get", new FixedMetadataValue(this.p, true));
					}
					else if(sign.getLine(1)!=null && sign.getLine(1).equals(EconomyPlus.config.getString("signs.see.line2")))
					{
						
						String onAccount = String.valueOf(CashMethods.getMoneyAccount(event.getPlayer().getName()));
						event.getPlayer().sendMessage(ChatColor.GOLD + new String(("["+EconomyPlus.bankName+"] "+EconomyPlus.langFile.getString("money.onAccount").replaceAll("%g%", EconomyPlus.moneyName).replaceAll("%nb%", onAccount)).getBytes(), EconomyPlus.encoding));
					}
				}
			}
		}
	}
}
