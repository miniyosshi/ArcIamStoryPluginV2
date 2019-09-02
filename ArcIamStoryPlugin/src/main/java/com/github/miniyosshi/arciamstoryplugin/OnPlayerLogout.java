package com.github.miniyosshi.arciamstoryplugin;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;


public class OnPlayerLogout implements Listener {
	
	public OnPlayerLogout(Plugin plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	
	public boolean horrorOn = false;
	
	@EventHandler
	public void onPlayerLogout (PlayerQuitEvent e) {
		//ストーリーイベント途中で落ちた場合
		
		if(User.getUser(e.getPlayer()).getInStoryEvent()==true) {
			System.out.println(e.getPlayer().getName()+"has logged out half way through a story event...");
			//文章止める
			//User.getUser(e.getPlayer()).subtractChapterSectionNumber();
		}
		
		if (horrorOn == true) {
			//Horror.reviver(e.getPlayer());
		}
	}
	

}
