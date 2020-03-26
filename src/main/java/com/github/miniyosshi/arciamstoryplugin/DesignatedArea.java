package com.github.miniyosshi.arciamstoryplugin;

import org.bukkit.Location;
import org.bukkit.World;

public abstract class DesignatedArea {
	
	private String name;
	private World world;
	
	DesignatedArea(String name, World world){
		this.name = name;
		this.world = world;
		DesignatedAreas.addDesignatedArea(this);
	}
	
	public boolean equals(String name) {
		return this.name.equals(name);
	}
	
	public boolean isIn(World world) {
		return this.world.equals(world);
		//これで良い？
	}
	
	public abstract boolean contains(Location location);

}
