package fr.gwenzy.economyplus.listeners;

import java.io.UnsupportedEncodingException;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

import fr.gwenzy.economyplus.main.EconomyPlus;

public class ModifySign implements Listener {

	@EventHandler
	public void modifySign(SignChangeEvent event) throws IndexOutOfBoundsException, UnsupportedEncodingException
	{
		
		if(event.getLine(0).equalsIgnoreCase(EconomyPlus.bankName))
		{		
			if(event.getPlayer().hasPermission("EconomyPlus.signs"))
			{		
				if(event.getLine(1)!=null)
				{		
					event.setLine(0, EconomyPlus.config.getString("signs.line1color").replaceAll("&", "§")+"["+EconomyPlus.bankName+"]");
					if(event.getLine(1).equalsIgnoreCase(EconomyPlus.config.getString("signs.put.detector")))
					{
						if (EconomyPlus.config.getString("signs.put.line2")!=null)
							event.setLine(1, new String(EconomyPlus.config.getString("signs.put.line2").replaceAll("&", "§").getBytes(), EconomyPlus.encoding));
						if (EconomyPlus.config.getString("signs.put.line3")!=null)
							event.setLine(1, new String(EconomyPlus.config.getString("signs.put.line3").replaceAll("&", "§").getBytes(), EconomyPlus.encoding));
						if (EconomyPlus.config.getString("signs.put.line4")!=null)
							event.setLine(1, new String(EconomyPlus.config.getString("signs.put.line4").replaceAll("&", "§").getBytes(), EconomyPlus.encoding));
					}
					else if(event.getLine(1).equalsIgnoreCase(EconomyPlus.config.getString("signs.take.detector")))
					{
						if (EconomyPlus.config.getString("signs.take.line2")!=null)
							event.setLine(1, new String(EconomyPlus.config.getString("signs.take.line2").replaceAll("&", "§").getBytes(), EconomyPlus.encoding));
						if (EconomyPlus.config.getString("signs.take.line3")!=null)
							event.setLine(2, new String(EconomyPlus.config.getString("signs.take.line3").replaceAll("&", "§").getBytes(), EconomyPlus.encoding));
						if (EconomyPlus.config.getString("signs.take.line4")!=null)
							event.setLine(3, new String(EconomyPlus.config.getString("signs.take.line4").replaceAll("&", "§").getBytes(), EconomyPlus.encoding));
					}
					else if(event.getLine(1).equalsIgnoreCase(EconomyPlus.config.getString("signs.see.detector")))
					{
						if (EconomyPlus.config.getString("signs.see.line2")!=null)
							event.setLine(1, new String(EconomyPlus.config.getString("signs.see.line2").replaceAll("&", "§").getBytes(), EconomyPlus.encoding));
						if (EconomyPlus.config.getString("signs.see.line3")!=null)
							event.setLine(2, new String(EconomyPlus.config.getString("signs.see.line3").replaceAll("&", "§").getBytes(), EconomyPlus.encoding));
						if (EconomyPlus.config.getString("signs.see.line4")!=null)
							event.setLine(3, new String(EconomyPlus.config.getString("signs.see.line4").replaceAll("&", "§").getBytes(), EconomyPlus.encoding));
					}
				}
			}
		}
	}
}
