package com.github.miniyosshi.arciamstorypluginV2;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Mob;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class EnemyNPC extends NPC{
	
	@JsonCreator
	public EnemyNPC(@JsonProperty("name")String name, @JsonProperty("mob")Mob mob, @JsonProperty("isInvulnerable")boolean isInvulnerable, 
			@JsonProperty("hasAI")boolean hasAI, @JsonProperty("target")LivingEntity nullableTarget,
			@JsonProperty("health")double health) {
		super(name, mob, isInvulnerable, hasAI, nullableTarget);
		mob.setHealth(health);		
	}

}
