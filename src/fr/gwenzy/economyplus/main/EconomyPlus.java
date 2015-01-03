package fr.gwenzy.economyplus.main;

import java.io.File;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import fr.gwenzy.economyplus.methods.DatabaseMethods;
import fr.gwenzy.economyplus.methods.LanguageMethods;

public class EconomyPlus extends JavaPlugin
{
	public static Server s;
	public static Logger log;
	public static String moneyName;
	public String lang;
	public static String bankName;
	public static FileConfiguration langFile;
	public static FileConfiguration config;
	public boolean enabled = true;
	@Override
	public void onEnable()
	{
		s = Bukkit.getServer();
		log = s.getLogger();
		this.saveDefaultConfig();
		config = getConfig();
		moneyName = config.getString("basic.moneyname");
		lang = config.getString("basic.lang");
		bankName = config.getString("basic.bankname");
		new File("plugins/EconomyPlus/").mkdir();
		new File("plugins/EconomyPlus/langs/").mkdir();
		new File("plugins/EconomyPlus/Databases/").mkdir();
		LanguageMethods.initDefaultLanguage();
		File langF = new File("plugins/EconomyPlus/langs/"+lang+".yml");
		if(!langF.exists()){
			Command.broadcastCommandMessage(s.getConsoleSender(), ChatColor.RED + "[EconomyPlus] The language file selected doesn't exist. Plugin stopped.");
			enabled = false;
			s.getPluginManager().disablePlugin(this);
		}
		else if(DatabaseMethods.getConnection(config.getString("dbMethod"))==null)
		{
			Command.broadcastCommandMessage(s.getConsoleSender(), ChatColor.RED + "[EconomyPlus] Error when trying to connect to database, please check your config.yml. Plugin stopped.");
			enabled = false;
			s.getPluginManager().disablePlugin(this);
		}
		else
		{
			langFile = YamlConfiguration.loadConfiguration(langF);
		log.info("[EconomyPlus] "+langFile.getString("basic.enabled"));
		}
	}
	
	@Override 
	public void onDisable()
	{
		if(enabled)
			log.info("[EconomyPlus] "+langFile.getString("basic.disabled"));
		else
			log.info("[EconomyPlus] Plugin disabled");
		
	}
}
