package com.github.miniyosshi.arciamstorypluginV2;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Ability {
	@JsonProperty
	private double walkspeed;
	@JsonProperty
	private double jumping;
	
	public Ability() {}
	
	@JsonCreator
	public Ability(@JsonProperty("walkspeed")double walkspeed, @JsonProperty("jumping")double jumping) {
		this.walkspeed = walkspeed;
		this.jumping = jumping;
	}
	
	//birthdayをもとに初期化
	
	
}
