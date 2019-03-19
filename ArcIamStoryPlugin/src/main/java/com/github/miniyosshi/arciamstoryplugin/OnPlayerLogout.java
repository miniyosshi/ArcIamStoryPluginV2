package com.github.miniyosshi.arciamstoryplugin;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerQuitEvent;


public class OnPlayerLogout {
	
	public boolean horrorOn = false;
	
	@EventHandler
	public void onPlayerLogout (PlayerQuitEvent e) {
		
		//save
		
		
		
		
		if (horrorOn == true) {
			//Horror.reviver(e.getPlayer());
		}
	}
	

}
