package fr.gwenzy.economyplus.listeners;

import java.sql.SQLException;

import org.bukkit.ChatColor;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import fr.gwenzy.economyplus.main.EconomyPlus;
import fr.gwenzy.economyplus.methods.CashMethods;

public class ClickSignListener implements Listener 
{
	@EventHandler
	public void onClickSign(PlayerInteractEvent event) throws SQLException
	{
		if(event.getAction() == Action.RIGHT_CLICK_BLOCK)
		{
			EconomyPlus.log.info("1");
			if(event.getClickedBlock().getState() instanceof Sign)
			{
				EconomyPlus.log.info("1");
				Sign sign = (Sign) event.getClickedBlock().getState();
				if(sign.getLine(0).equalsIgnoreCase(EconomyPlus.config.getString("signs.line1color").replace("&", "§")+"["+EconomyPlus.bankName+"]"))
				{
					EconomyPlus.log.info("1");

					if(sign.getLine(1)!=null && sign.getLine(1).equals(EconomyPlus.config.getString("signs.put.line2")))
					{
						
					}
					else if(sign.getLine(1)!=null && sign.getLine(1).equals(EconomyPlus.config.getString("signs.take.line2")))
					{
						
					}
					else if(sign.getLine(1)!=null && sign.getLine(1).equals(EconomyPlus.config.getString("signs.see.line2")))
					{
						
						String onAccount = String.valueOf(CashMethods.getMoneyAccount(event.getPlayer().getName()));
						event.getPlayer().sendMessage(ChatColor.GOLD+"["+EconomyPlus.bankName+"] "+EconomyPlus.langFile.getString("money.onAccount").replaceAll("%g%", EconomyPlus.moneyName).replaceAll("%nb%", onAccount));
					}
				}
			}
		}
	}
}
