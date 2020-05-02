package com.github.miniyosshi.arciamstorypluginV2;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Villager;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SaverNPC extends NPC {
	
	@JsonCreator
	public SaverNPC(@JsonProperty("name")String name) {
		super(EntityType.VILLAGER, name, true, false, null);
	}

}
