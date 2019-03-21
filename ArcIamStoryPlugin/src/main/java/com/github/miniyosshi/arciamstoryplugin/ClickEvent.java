package com.github.miniyosshi.arciamstoryplugin;

import java.util.Timer;
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
		User u = User.getUser(e.getPlayer());
		
		System.out.println(e.getRightClicked());
		System.out.println(e.getRightClicked().getName());
		System.out.println(e.getRightClicked().toString());
		
		
		//ストーリー進行
		StoryProcessor.eventCheck(u,"click", );
		
		//mob会話
		if(e.getRightClicked().getName().equals("村人A")) {
			e.getPlayer().sendMessage("こんにちは");
		}
		
		//待合所から転送
		
		if(e.getRightClicked().getName().equals("転送係")) {
			e.getPlayer().sendMessage("５秒後に転送します");
			
			
			FieldInEffect tc =new FieldInEffect(User.getUser(e.getPlayer()));
			
			Timer timer = new Timer();

			timer.schedule(tc, 5000);
			
						
		}	
		
		
	}
}
