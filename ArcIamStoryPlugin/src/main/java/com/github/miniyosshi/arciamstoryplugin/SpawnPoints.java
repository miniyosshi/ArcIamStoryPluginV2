package com.github.miniyosshi.arciamstoryplugin;

import org.bukkit.Location;

public class SpawnPoints {
	private String name;
	private Location loc;
	
	SpawnPoints (String name, Location loc){
		this.name = name;
		this.loc = loc;
	}
	
	public String getName() {
		return name;
	}
	
	public Location getLocation() {
		return loc;
	}

}
