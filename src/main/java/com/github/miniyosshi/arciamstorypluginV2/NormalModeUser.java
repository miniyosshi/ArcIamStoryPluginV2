package com.github.miniyosshi.arciamstorypluginV2;

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
		userInfo.setSavedLocation(player.getLocation());
		//CSVExporter.exportCSV("UserData");
	}
	
	public void teleportToSavePoint() {
		Location location = userInfo.getSavedLocation();
		player.teleport(location);
	}

}
