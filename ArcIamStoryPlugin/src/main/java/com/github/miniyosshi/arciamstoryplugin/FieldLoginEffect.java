package com.github.miniyosshi.arciamstoryplugin;

import java.util.TimerTask;

import org.bukkit.Sound;

public class FieldLoginEffect extends TimerTask {
	
	User u;
	
	FieldLoginEffect(User u){
		this.u = u;
	}
	
	@SuppressWarnings("deprecation")
	public void run(){
		//u.getPlayer().sendMessage("開始！");
		//u.getPlayer().sendTitle("第一章", "知らない天井");
		//u.getPlayer().addPotionEffect(arg0);
		u.getPlayer().sendTitle("You've logged in", "at " + u.getSavedLocation());
		u.getPlayer().playSound(u.getPlayer().getLocation(), Sound.BLOCK_END_PORTAL_SPAWN, 1, 1);
		
	}
	
	
}
