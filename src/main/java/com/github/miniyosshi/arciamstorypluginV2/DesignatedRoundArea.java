package com.github.miniyosshi.arciamstorypluginV2;

import org.bukkit.Location;
import org.bukkit.World;

public class DesignatedRoundArea extends DesignatedArea {
	
	private Location center;
	private double radius;
	
	DesignatedRoundArea(String name, World world, Location center, double radius) {
		super(name, world);
		this.center = center;
		this.radius = radius;
	}

	@Override
	public boolean contains(Location location) {
		double distance = location.distance(center);
		return distance<=radius;
	}
	

}
