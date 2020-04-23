package com.github.miniyosshi.arciamstorypluginV2;

import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class NPC extends MapElement {
	
	private Mob mob;
	
	@JsonCreator
	public NPC(@JsonProperty("name")String name, @JsonProperty("mob")Mob mob, @JsonProperty("isInvulnerable")boolean isInvulnerable, 
				@JsonProperty("hasAI")boolean hasAI, @JsonProperty("target")LivingEntity nullableTarget) {
		this.name = name;
		this.mob = mob;
		
		mob.setCustomName(name);
		mob.setCustomNameVisible(true);
		mob.setInvulnerable(isInvulnerable);
		mob.setAI(hasAI);
		mob.setTarget(nullableTarget);
		
		NPCs.getInstance().add(this);
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
