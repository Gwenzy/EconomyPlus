package fr.gwenzy.economyplus.methods;

import java.sql.Connection;
import java.sql.DriverManager;
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
				EconomyPlus.log.info("[EconomyPlus] "+EconomyPlus.langFile.getString("basic.connectedSQLite"));
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
				String url = "jdbc:mysql://"+EconomyPlus.config.getString("mysql.host")+":"
						+ EconomyPlus.config.getString("mysql.port")+"/"
						+ EconomyPlus.config.getString("mysql.db_name");
				EconomyPlus.log.info(url);
				c = DriverManager.getConnection(url,EconomyPlus.config.getString("mysql.user"), EconomyPlus.config.getString("mysql.pass"));
				
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
			state.executeUpdate("CREATE TABLE IF NOT EXISTS `economyplus_players` (`id`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,`pseudo`	varchar(20) NOT NULL,`onAccount`	int(20) NOT NULL,`onPocket`	int(20) NOT NULL);");

			EconomyPlus.log.info("[EconomyPlus] "+EconomyPlus.langFile.getString("basic.SQLSuccess"));
		}
		catch(Exception e){e.printStackTrace();}
		
		return c;
	}
	
	
}
