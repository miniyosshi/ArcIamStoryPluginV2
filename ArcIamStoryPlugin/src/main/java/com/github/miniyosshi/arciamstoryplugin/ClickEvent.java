package com.github.miniyosshi.arciamstoryplugin;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.plugin.Plugin;


public class ClickEvent implements Listener {
	
	public ClickEvent(Plugin plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void clickEvent(PlayerInteractEntityEvent e) {
		
		StoryProcessor.eventCheck("click");
		
		
		
		//System.out.println(e.getRightClicked().getName());
		if(e.getRightClicked().getName().equals("村人A")) {
			e.getPlayer().sendMessage("こんにちは");
		}
		
	}
}
