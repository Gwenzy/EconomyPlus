package fr.gwenzy.economyplus.main;

import java.io.File;
import java.sql.SQLException;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import fr.gwenzy.economyplus.commands.BankCommand;
import fr.gwenzy.economyplus.commands.MoneyCommand;
import fr.gwenzy.economyplus.commands.PayCommand;
import fr.gwenzy.economyplus.listeners.ChatListener;
import fr.gwenzy.economyplus.listeners.ClickSignListener;
import fr.gwenzy.economyplus.listeners.FirstJoinListener;
import fr.gwenzy.economyplus.listeners.ModifySign;
import fr.gwenzy.economyplus.methods.DatabaseMethods;
import fr.gwenzy.economyplus.methods.LanguageMethods;

public class EconomyPlus extends JavaPlugin
{
	public static Server s;
	public static Logger log;
	public static String moneyName;
	public String lang;
	public static String bankName;
	public static String dbMethod;
	public static FileConfiguration langFile;
	public static FileConfiguration config;
	public boolean enabled = true;
	public static String encoding;
	@Override
	public void onEnable()
	{
		//From all the plugin
		s = Bukkit.getServer();
		log = s.getLogger();
		this.saveDefaultConfig();
		config = getConfig();
		moneyName = config.getString("basic.moneyname");
		lang = config.getString("basic.lang");
		bankName = config.getString("basic.bankname");
		dbMethod = config.getString("dbMethod");

		//Folders
		new File("plugins/EconomyPlus/").mkdir();
		new File("plugins/EconomyPlus/langs/").mkdir();
		new File("plugins/EconomyPlus/Databases/").mkdir();
		
		//Language
		LanguageMethods.initDefaultLanguage();
		File langF = new File("plugins/EconomyPlus/langs/"+lang+".yml");
		
		//File Check
		if(!langF.exists()){
			Command.broadcastCommandMessage(s.getConsoleSender(), ChatColor.RED + "[EconomyPlus] The language file selected doesn't exist. Plugin stopped.");
			enabled = false;
			s.getPluginManager().disablePlugin(this);
		}
		else
		{
			langFile = YamlConfiguration.loadConfiguration(langF);

		}
		encoding = config.getString("basic.encoding");

		if(bankName.length()>14)
		{
			Command.broadcastCommandMessage(s.getConsoleSender(), ChatColor.RED + "[EconomyPlus] Error : The bank name must contains maximum 14 characters. Plugin stopped.");
			enabled = false;
			s.getPluginManager().disablePlugin(this);
		}
		//Commands
		getCommand("money").setExecutor(new MoneyCommand());
		getCommand("pay").setExecutor(new PayCommand());
		getCommand("bank").setExecutor(new BankCommand(this));
		try {
			log.info(new String(("[EconomyPlus] "+langFile.getString("basic.enabled")).getBytes(), encoding));
		} catch (Exception e1) {
		}
		
		//Listeners
		s.getPluginManager().registerEvents(new FirstJoinListener(this), this);
		s.getPluginManager().registerEvents(new ModifySign(), this);
		s.getPluginManager().registerEvents(new ClickSignListener(this), this);
		s.getPluginManager().registerEvents(new ChatListener(this), this);
		
		if(DatabaseMethods.getConnection(dbMethod))
		{
			try {
				DatabaseMethods.executeMyUpdate("CREATE TABLE IF NOT EXISTS `economyplus_players` (`id`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,`pseudo`	varchar(20) NOT NULL,`onAccount`	int(20) NOT NULL,`onPocket`	int(20) NOT NULL);");
				DatabaseMethods.disconnect();
			} catch (SQLException e) {}
			

		}
		else
		{

			Command.broadcastCommandMessage(s.getConsoleSender(), ChatColor.RED + "[EconomyPlus] Error when trying to connect to database, please check your config.yml. Plugin stopped.");
			enabled = false;
			s.getPluginManager().disablePlugin(this);
		}
		
		try {
			log.info("[EconomyPlus] Test Encoding");
			new String("EncodingTest".getBytes(), encoding);
			log.info("[EconomtPlus] Encoding OK");
		} catch (Exception e) {
			Command.broadcastCommandMessage(s.getConsoleSender(), ChatColor.RED + "[EconomyPlus] Error with the encoding, please chech config.yml. Plugin stopped.");
			enabled = false;
			s.getPluginManager().disablePlugin(this);}
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
