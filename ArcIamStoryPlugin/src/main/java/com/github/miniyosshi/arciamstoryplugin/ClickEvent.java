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
		
		System.out.println(e.getRightClicked().getName()+"getName");
		System.out.println(e.getRightClicked().toString()+"toString");
		
		//ストーリー進行中でなければ
		if(u.getInStoryEvent()==false) {
			//ストーリー進行
			StoryProcessor.eventCheck(u,"click", e.getRightClicked().getName());
			
			//mob会話
			if(e.getRightClicked().getName().equals("村人")) {
				e.getPlayer().sendMessage("こんにちは");
			}
			
			//待合所から転送
			
			if(e.getRightClicked().getName().equals("転送係")) {
				e.getPlayer().sendMessage("５秒後に転送します");
				
				
				FieldLoginEffect tc =new FieldLoginEffect(User.getUser(e.getPlayer()));
				
				Timer timer = new Timer();

				timer.schedule(tc, 5000);
				
							
			}	
		}
		
		
		
		
	}
}
