package com.github.miniyosshi.arciamstorypluginV2.listeners;

import java.io.File;
import java.util.Optional;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;

import com.github.miniyosshi.arciamstorypluginV2.*;

public class OnPlayerLogout implements Listener {
	
	public OnPlayerLogout(Plugin plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onPlayerLogout (PlayerQuitEvent e) {
		
		Player player = e.getPlayer();
		Optional<User> user = Users.getInstance().getElementBy(player);
		user.ifPresent(v ->{
			if(v.isIn().isPresent()) {
				//Area内でログアウト
				if(v instanceof NormalModeUser) {
					((NormalModeUser) v).saveCurrentLocation();
				}
			}else {
				//DesignatedArea外でログアウト
				//zombie
				v.generateCustomZombie();
				//ハードコア向けアイテムロスト
				if(v instanceof HardModeUser) {
					//一応記録
					System.out.println(player.getInventory().getStorageContents());				
					//p.getInventory().clear();
				}
			}
			
			// Teleport users except tutorial users and users who are in story event
			if(!(v instanceof TutorialUser || v.isInStoryEvent())) {
				v.teleportToLobby();
			}
			
			// Export User data to JSON
			//v.exportTo(new File(v.parentFolderString));
			
			//ストーリーイベント途中で落ちた場合
			if(v.isInStoryEvent()) {
				System.out.println(e.getPlayer().getName()+" has logged out half way through a story event...");
				v.setLogoutInStoryEvent(true);
				v.setIsInStoryEvent(false);
			}
			
		});		
		
	}
	

}
