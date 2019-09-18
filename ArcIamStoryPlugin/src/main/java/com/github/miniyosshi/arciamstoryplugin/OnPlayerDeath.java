package com.github.miniyosshi.arciamstoryplugin;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.Plugin;

public class OnPlayerDeath implements Listener {
	
	public OnPlayerDeath(Plugin plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	
	@EventHandler
	public void onPlayerDeath (PlayerDeathEvent e) {
		Player p = e.getEntity();
		User u = User.getUser(p);
		
		//ハードの人は病院へ、ノーマルの人はセーブ地点へ
		if (u.getHardmode()) {
			//
			p.getInventory().clear();
			u.setSkill(180, 180, 180);
			//べっどいるみたいp.setBedSpawnLocation(arg0);
			p.teleport(List.spawnpoints.get(1).getLocation());
			
		}
		else {
			p.teleport(u.getSavedLocation());
		}
		
	
		
	}
		

}
