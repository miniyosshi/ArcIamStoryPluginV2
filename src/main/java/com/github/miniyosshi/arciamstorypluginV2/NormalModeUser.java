package com.github.miniyosshi.arciamstorypluginV2;

import java.util.Optional;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class NormalModeUser extends User {
	
	public NormalModeUser(String name, Player player, UserInfo userInfo) {
		super(name, player, userInfo);
	}
	
	@JsonCreator
	public NormalModeUser(@JsonProperty("name")String name, @JsonProperty("userInfo")UserInfo userInfo) {
		super(name, userInfo);
	}
	
	public void saveCurrentLocation() {
		player.ifPresent(v->{
			userInfo.setSavedLocation(v.getLocation());
		});
		//save
	}
	
	public void teleportToSavePoint() {
		player.ifPresent(v->{
			userInfo.getSavedLocation().ifPresent(w->{
				v.teleport(w);
			});
		});
	}

}
