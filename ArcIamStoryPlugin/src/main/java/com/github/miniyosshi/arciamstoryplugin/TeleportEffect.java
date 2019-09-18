package com.github.miniyosshi.arciamstoryplugin;

import java.util.TimerTask;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;

public class TeleportEffect extends TimerTask {
	
	User u;
	
	TeleportEffect(User u){
		this.u = u;
	}
	
	public void run(){
		
		World w = Bukkit.getServer().getWorld("world");
		Location point = new Location(w,129,73,142,-90,0);
		u.getPlayer().teleport(point);
		
		u.getPlayer().sendTitle("You've teleported", "at " + u.isInAreaOf().getName() + ".", 10, 70, 20);
		u.getPlayer().playSound(u.getPlayer().getLocation(), Sound.BLOCK_END_PORTAL_SPAWN, 0.8f, 1);
		u.getPlayer().spawnParticle(Particle.PORTAL, u.getPlayer().getLocation(), 100);
		
	}
	
	
}
