package com.github.miniyosshi.arciamstoryplugin;

import java.util.TimerTask;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;

public class FieldLoginEffect extends TimerTask {
	
	User u;
	
	FieldLoginEffect(User u){
		this.u = u;
	}
	
	public void run(){
		//u.getPlayer().sendMessage("開始！");
		//u.getPlayer().sendTitle("第一章", "知らない天井");
		
		World w = Bukkit.getServer().getWorld("world");
		Location point = new Location(w,129,73,142,-90,0);
		u.getPlayer().teleport(point);
		
		u.getPlayer().sendTitle("You've logged in", "at " + u.isInAreaOf().getName() + ".", 10, 70, 20);
		u.getPlayer().playSound(u.getPlayer().getLocation(), Sound.BLOCK_END_PORTAL_SPAWN, 0.8f, 1);
		u.getPlayer().spawnParticle(Particle.PORTAL, u.getPlayer().getLocation(), 100);
		
	}
	
	
}
