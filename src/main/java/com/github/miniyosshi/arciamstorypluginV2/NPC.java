package com.github.miniyosshi.arciamstorypluginV2;

import java.util.Optional;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


public class NPC extends Element {
	
	//playerに対応するのがmob,具体的な形ゾンビとかはmob.getType()
	//private Mob mob;
	@JsonProperty
	private EntityType type;
	@JsonProperty
	private boolean isInvulnerable;
	@JsonProperty
	private boolean hasAI;
	@JsonProperty
	protected Optional<LivingEntity> target;
	//private Optional<Location> location;
	
	@JsonCreator
	public NPC(@JsonProperty("type")EntityType type, @JsonProperty("name")String name, @JsonProperty("isInvulnerable")boolean isInvulnerable, 
				@JsonProperty("hasAI")boolean hasAI, @JsonProperty("target")LivingEntity target) {
		this.type =type;
		this.name = name;
		this.isInvulnerable = isInvulnerable;
		this.hasAI = hasAI;
		this.target = Optional.ofNullable(target);	
		NPCs.getInstance().add(this);
	}
	
	public void spawnAt(Location location) {
		Mob mob = (Mob) location.getWorld().spawnEntity(location, type);
		mob.setCustomName(name);
		mob.setCustomNameVisible(true);
		mob.setInvulnerable(isInvulnerable);
		mob.setAI(hasAI);
		target.ifPresent(v-> mob.setTarget(v));
	}
	
	public void spawnIn(DesignatedArea da) {
		spawnAt(da.getRandomLocation());
	}
	
	public void talk(Player player, String string) {
		player.sendMessage(string);
	}
	
	//walkaround	
	

}
