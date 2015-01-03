package fr.gwenzy.economyplus.methods;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import fr.gwenzy.economyplus.main.EconomyPlus;

public class DatabaseMethods {

	public static Connection getConnection(String method)
	{
		
		Connection c = null;
		if(method.equalsIgnoreCase("sqlite"))
		{
			try
			{
				Class.forName("org.sqlite.JDBC");
				c = DriverManager.getConnection("jdbc:sqlite:plugins/EconomyPlus/Databases/"+EconomyPlus.config.getString("sqlite.db_name"));
				//EconomyPlus.log.info("[EconomyPlus] "+EconomyPlus.langFile.getString("basic.connectedSQLite"));
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		else if(method.equalsIgnoreCase("mysql"))
		{
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				c = DriverManager.getConnection("jdbc:mysql://"+EconomyPlus.config.getString("mysql.host")+":"
				+ EconomyPlus.config.getString("mysql.port")+"/"
				+ EconomyPlus.config.getString("mysql.db_name"), 
				EconomyPlus.config.getString("mysql.user"),
				EconomyPlus.config.getString("mysql.pass"));
				EconomyPlus.log.info("[EconomyPlus] "+EconomyPlus.langFile.getString("basic.connectedMySQL"));
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		try
		{
			Statement state = c.createStatement();
			state.executeUpdate("CREATE TABLE IF NOT EXISTS `economyplus_players` (  `id` int(10) NOT NULL,  `pseudo` varchar(20) NOT NULL,  `onAccount` int(20) NOT NULL,  `onPocket` int(20) NOT NULL)");
			state.executeUpdate("ALTER TABLE `economyplus_players` ADD UNIQUE KEY `Id` (`id`);");

			state.executeUpdate("ALTER TABLE `economyplus_players` MODIFY `id` int(10) NOT NULL AUTO_INCREMENT;");

			c.close();
			EconomyPlus.log.info("[EconomyPlus] "+EconomyPlus.langFile.getString("basic.SQLSuccess"));
		}
		catch(Exception e){e.printStackTrace();}
		
		return c;
	}
}
