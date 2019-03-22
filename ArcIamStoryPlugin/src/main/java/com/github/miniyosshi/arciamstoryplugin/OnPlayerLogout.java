package com.github.miniyosshi.arciamstoryplugin;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerQuitEvent;


public class OnPlayerLogout {
	
	public boolean horrorOn = false;
	
	@EventHandler
	public void onPlayerLogout (PlayerQuitEvent e) {
		//ストーリーイベント途中で落ちた場合
		
		
		//quit disconnect userがnullの可能性ある
		if(User.getUser(e.getPlayer()).getInStoryEvent()==true) {
			System.out.println("さんはストーリーイベント途中で切断されました。");
			//文章止める
			//chapter section 進めない
		}
		
		
		//save
		
		
		
		
		if (horrorOn == true) {
			//Horror.reviver(e.getPlayer());
		}
	}
	

}
