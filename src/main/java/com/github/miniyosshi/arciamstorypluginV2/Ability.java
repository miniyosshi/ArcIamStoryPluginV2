package com.github.miniyosshi.arciamstorypluginV2;

import org.bukkit.entity.Player;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Ability {
	@JsonProperty
	private double walkspeed = 0.3;
	@JsonProperty
	private double handAttackRatio = 1;
	@JsonProperty
	private double defenceRatio = 1;
	@JsonProperty
	private double maxHealthRatio = 0.8; //0to1
	@JsonProperty
	private double jumping = 1;
	
	public Ability() {}
	
	@JsonCreator
	public Ability(@JsonProperty("walkspeed")double walkspeed, @JsonProperty("handAttackRatio")double handAttackRatio, 
					@JsonProperty("defenceRatio")double defenceRatio, @JsonProperty("maxHealthRatio")double maxHealthRatio, @JsonProperty("jumping")double jumping) {
		this.walkspeed = walkspeed;
		this.handAttackRatio = handAttackRatio;
		this.defenceRatio = defenceRatio;
		this.maxHealthRatio = maxHealthRatio;
		this.jumping = jumping;
	}
	
	//birthdayをもとに初期化
	/*
	public Ability(Birthday birthday) {
		this.walkspeed = 0.2;
		this.jumping = 1;
	}
	*/
	
	public void reflectWalkspeed(Player player) {
		player.setWalkSpeed((float) walkspeed);
	}
	
	
}
