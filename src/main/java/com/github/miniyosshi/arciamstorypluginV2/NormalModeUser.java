package com.github.miniyosshi.arciamstorypluginV2;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class NormalModeUser extends User {
	
	public NormalModeUser(Player player, UserInfo userInfo) {
		super(player, userInfo);
	}
	
	public void teleportToSavePoint() {
		Location location = userInfo.getSavedLocation();
		player.teleport(location);
	}
	/*
	public NormalModeUser(String name, Location savedLocation, int chapter, int section, int[] birthday) {
		super(name, savedLocation, chapter, section, birthday);
	}
	*/

}
