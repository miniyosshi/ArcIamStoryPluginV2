package com.github.miniyosshi.arciamstorypluginV2;

import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;

public abstract class NPC extends MapElement {
	
	private Mob mob;
	
	public NPC(String name, Mob mob, boolean isInvulnerable, boolean hasAI, LivingEntity nullableTarget) {
		this.name = name;
		this.mob = mob;
		
		mob.setCustomName(name);
		mob.setInvulnerable(isInvulnerable);
		mob.setAI(hasAI);
		mob.setTarget(nullableTarget);
		
		NPCs npcs = NPCs.getInstance();
		npcs.add(this);
	}
	
	public void spawnAt(Location location) {
		location.getWorld().spawnEntity(location, mob.getType());
	}
	
	public void spawnIn(DesignatedArea da) {
		da.getRandomLocation().getWorld().spawnEntity(da.getRandomLocation(), mob.getType());
	}
	
	public void talk(Player player, String string) {
		player.sendMessage(string);
	}
	
	//walkaround
	
	//attack
	
	
	

}
