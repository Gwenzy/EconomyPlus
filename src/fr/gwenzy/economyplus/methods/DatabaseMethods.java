package fr.gwenzy.economyplus.methods;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import fr.gwenzy.economyplus.main.EconomyPlus;

public class DatabaseMethods {

	private static Connection c;
	private static Statement state;
	public static boolean getConnection(String method)
	{
		
			
		if(method.equalsIgnoreCase("sqlite"))
		{
			try
			{
				Class.forName("org.sqlite.JDBC");
				c = DriverManager.getConnection("jdbc:sqlite:plugins/EconomyPlus/Databases/"+EconomyPlus.config.getString("sqlite.db_name"));
				EconomyPlus.log.info("[EconomyPlus] "+EconomyPlus.langFile.getString("basic.connectedSQLite"));
				return true;
			}
			catch(Exception e)
			{
				e.printStackTrace();
				return false;
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
				return true;
			}
			catch(Exception e)
			{
				e.printStackTrace();
				return false;
			}
		}
		/*try
		{
			state = c.createStatement();
			state.executeUpdate("CREATE TABLE IF NOT EXISTS `economyplus_players` (`id`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,`pseudo`	varchar(20) NOT NULL,`onAccount`	int(20) NOT NULL,`onPocket`	int(20) NOT NULL);");

			EconomyPlus.log.info("[EconomyPlus] "+EconomyPlus.langFile.getString("basic.SQLSuccess"));
		}
		catch(Exception e){e.printStackTrace();}*/
		return true;
		
	
	
	
}
	public static void disconnect(){
		 try {
			 state.close();
			 c.close();
			 EconomyPlus.log.info("Disconnected");
		 } catch(Exception e){		 }
	 }
	 public static ResultSet executeQuery(String query) throws SQLException{
		 
			state = c.createStatement();
			return state.executeQuery(query);
		
		
	 }
	 public static void executeMyUpdate(String query) throws SQLException{
		 
				state = c.createStatement();
				state.executeUpdate(query);
			  
 
	 }
}
