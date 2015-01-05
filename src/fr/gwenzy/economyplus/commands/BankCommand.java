package fr.gwenzy.economyplus.commands;

import java.io.File;
import java.io.UnsupportedEncodingException;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import fr.gwenzy.economyplus.main.EconomyPlus;
import fr.gwenzy.economyplus.methods.LanguageMethods;

public class BankCommand implements CommandExecutor {

	private Plugin p;
	
	public BankCommand(EconomyPlus p) {
		this.p = p;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String CommandLabel,
			String[] args) {
			if(sender.hasPermission("EconomyPlus.reload"))
			{
				p.saveDefaultConfig();
				FileConfiguration config = p.getConfig();
				
				EconomyPlus.config = config;
				LanguageMethods.initDefaultLanguage();
				File langF = new File("plugins/EconomyPlus/langs/"+config.getString("basic.lang")+".yml");
				if(langF.exists())
				{
					EconomyPlus.langFile = YamlConfiguration.loadConfiguration(langF);
					try {
						sender.sendMessage(ChatColor.GREEN+"[EconomyPlus] "+new String(EconomyPlus.langFile.getString("basic.reloadSuccess").getBytes(), EconomyPlus.encoding));
					} catch (UnsupportedEncodingException e) {}
					
				}
				else
				{
					EconomyPlus.langFile = YamlConfiguration.loadConfiguration(new File("plugins/EconomyPlus/langs/en.yml"));
					try {
						sender.sendMessage(ChatColor.RED+"[EconomyPlus] "+new String(EconomyPlus.langFile.getString("basic.error.reload").getBytes(), EconomyPlus.encoding));
					} catch (UnsupportedEncodingException e) {}
					
				}
				
			}
		return true;
	}

}
