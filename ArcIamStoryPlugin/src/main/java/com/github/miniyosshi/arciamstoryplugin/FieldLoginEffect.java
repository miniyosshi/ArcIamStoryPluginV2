package com.github.miniyosshi.arciamstoryplugin;

import java.util.TimerTask;

import org.bukkit.Particle;
import org.bukkit.Sound;

public class FieldLoginEffect extends TimerTask {
	
	User u;
	
	FieldLoginEffect(User u){
		this.u = u;
	}
	
	public void run(){
		//u.getPlayer().sendMessage("開始！");
		//u.getPlayer().sendTitle("第一章", "知らない天井");
		u.getPlayer().sendTitle("You've logged in", "at " + u.isInAreaOf().getName() + ".", 10, 70, 20);
		u.getPlayer().playSound(u.getPlayer().getLocation(), Sound.BLOCK_END_PORTAL_SPAWN, 0.8f, 1);
		u.getPlayer().spawnParticle(Particle.PORTAL, u.getPlayer().getLocation(), 100);
		
	}
	
	
}
