package fr.gwenzy.economyplus.listeners;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import fr.gwenzy.economyplus.main.EconomyPlus;
import fr.gwenzy.economyplus.methods.DatabaseMethods;

public class FirstJoinListener implements Listener
{
	@EventHandler
	public void onConnect(PlayerJoinEvent event) throws SQLException
	{
		Connection c = DatabaseMethods.getConnection(EconomyPlus.dbMethod);
		Statement state = c.createStatement();
		EconomyPlus.log.info("Connected");
		if(state.executeQuery("SELECT * FROM economyplus_players WHERE pseudo = '"+event.getPlayer().getName()+"'").next()==false)
		{
			state.executeUpdate("INSERT INTO economyplus_players(pseudo,onAccount,onPocket) VALUES('"+event.getPlayer().getName()+"', 0, "+EconomyPlus.config.getString("basic.moneyonfirstlogin")+")");
			EconomyPlus.log.info("[EconomyPlus] "+EconomyPlus.langFile.getString("account.created").replaceAll("%p%", event.getPlayer().getName()));
			
		}
		else
			EconomyPlus.log.info("Error");

	}
}
