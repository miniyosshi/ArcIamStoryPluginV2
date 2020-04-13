package com.github.miniyosshi.arciamstorypluginV2;

import org.bukkit.entity.Player;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class HardModeUser extends User {

	public HardModeUser(String name, Player player, UserInfo userInfo) {
		super(name, player, userInfo);
	}
	
	@JsonCreator
	public HardModeUser(@JsonProperty("name")String name, @JsonProperty("userInfo")UserInfo userInfo) {
		super(name, userInfo);
	}
	

}
