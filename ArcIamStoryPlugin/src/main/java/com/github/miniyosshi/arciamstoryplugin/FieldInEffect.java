package com.github.miniyosshi.arciamstoryplugin;

import java.util.TimerTask;

import org.bukkit.Sound;

public class FieldInEffect extends TimerTask {
	
	User u;
	
	FieldInEffect(User u){
		this.u = u;
	}
	
	@SuppressWarnings("deprecation")
	public void run(){
		u.getPlayer().sendMessage("はろー");
		u.getPlayer().sendTitle("第一章", "知らない天井");
		//u.getPlayer().addPotionEffect(arg0);
		u.getPlayer().playSound(u.getPlayer().getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
		
	}
	
	
}
