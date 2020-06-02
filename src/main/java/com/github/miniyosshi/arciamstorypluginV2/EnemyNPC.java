package com.github.miniyosshi.arciamstorypluginV2;

import java.util.Optional;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Mob;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class EnemyNPC extends NPC{

	public EnemyNPC(@JsonProperty("type")EntityType type, @JsonProperty("name")String name, 
			@JsonProperty("hasAI")boolean hasAI,
			@JsonProperty("target")Optional<LivingEntity> target) {
		super(type, name, false, hasAI, target);
	}
	
	public void changeTarget(LivingEntity target) {
		this.target = Optional.of(target);
	}

}
