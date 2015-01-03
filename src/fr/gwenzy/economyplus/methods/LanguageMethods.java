package fr.gwenzy.economyplus.methods;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class LanguageMethods {

	public static void initDefaultLanguage()
	{
		try
		{
			File lang = new File("plugins/EconomyPlus/langs/en.yml");
			if(!lang.exists())
			{	
				lang.createNewFile();
			
				FileConfiguration en = YamlConfiguration.loadConfiguration(lang);
				en.set("basic.enabled", "Plugin enabled");
				en.set("basic.disabled", "Plugin disabled");
				en.set("basic.connectedSQLite", "Connected successfully to the SQLite Database");
				en.set("basic.connectedMySQL", "Connected successfully to the MySQL Database");
				en.set("basic.SQLSuccess", "SQL requests successfully executed");
				en.save(lang);
			}
		
		}
		catch(Exception e)
		{
			
		}
	}
}
