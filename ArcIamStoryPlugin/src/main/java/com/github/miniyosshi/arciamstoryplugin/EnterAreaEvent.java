package com.github.miniyosshi.arciamstoryplugin;

import com.github.miniyosshi.arciamstoryplugin.User;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;

public class EnterAreaEvent implements Listener {
	
	public EnterAreaEvent(Plugin plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	
	@EventHandler
	public void enterAreaEvent(PlayerMoveEvent e) {
		
		Location from = e.getFrom();
		Location to = e.getTo();
		
		if (from != to) {
			
			User u = User.getUser(e.getPlayer());
			AreaData presentarea = u.isInAreaOf();
			if (presentarea != u.getPastArea()) {
				if(presentarea != CSVReader.areadata.get(0)) {
					e.getPlayer().sendMessage("エリア"+presentarea.getName()+"にやって来ました");
					u.getPlayer().playSound(u.getPlayer().getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
					StoryProcessor.eventCheck(u, "enter", presentarea.getName());
				}
				u.setPastarea(presentarea);
			}
			
		}	
	}
	
	
}
