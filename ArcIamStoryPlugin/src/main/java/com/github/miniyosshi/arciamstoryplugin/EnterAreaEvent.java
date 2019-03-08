package com.github.miniyosshi.arciamstoryplugin;

import com.github.miniyosshi.arciamstoryplugin.User;

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
		
		if (e.getTo().getX()!=e.getFrom().getX() || e.getTo().getY()!=e.getFrom().getY() || e.getTo().getZ()!=e.getFrom().getZ()) {
			
			for (User u : CSVReader.userdata) {
				
				if (u.getName().equals(e.getPlayer().getName())) {
					AreaData presentarea = u.isInAreaOf();
					//System.out.println(user.pastarea+"から"+presentarea);
					
					if (presentarea != u.pastarea) {
						if(presentarea != CSVReader.areadata.get(0)) {
							e.getPlayer().sendMessage("エリア"+presentarea.getName()+"に来ました");
							StoryProcessor.eventCheck("enter");
							
						}
						u.pastarea = presentarea;
					}
					
					break;
				}
			}
		}	
	}
	
	
}
