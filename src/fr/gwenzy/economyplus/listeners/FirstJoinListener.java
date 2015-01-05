package fr.gwenzy.economyplus.listeners;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.Plugin;

import fr.gwenzy.economyplus.main.EconomyPlus;
import fr.gwenzy.economyplus.methods.DatabaseMethods;

public class FirstJoinListener implements Listener
{
	private Plugin p;
	public FirstJoinListener(EconomyPlus p)
	{
		this.p = p;
	}
	@EventHandler
	public void onConnect(PlayerJoinEvent event) throws SQLException, UnsupportedEncodingException
	{
		DatabaseMethods.getConnection(EconomyPlus.dbMethod);
		if(DatabaseMethods.executeQuery("SELECT * FROM economyplus_players WHERE pseudo = '"+event.getPlayer().getName()+"'").next()==false)
		{
			DatabaseMethods.executeMyUpdate("INSERT INTO economyplus_players(pseudo,onAccount,onPocket) VALUES('"+event.getPlayer().getName()+"', 0, "+EconomyPlus.config.getString("basic.moneyonfirstlogin")+")");
			EconomyPlus.log.info(new String(("[EconomyPlus] "+EconomyPlus.langFile.getString("account.created").replaceAll("%p%", event.getPlayer().getName())).getBytes(), EconomyPlus.encoding));
			
		}
		else
			EconomyPlus.log.info("Error");

		
		DatabaseMethods.disconnect();
		event.getPlayer().setMetadata("wait4put", new FixedMetadataValue(this.p, false));
		event.getPlayer().setMetadata("wait4get", new FixedMetadataValue(this.p, false));
	}
}
