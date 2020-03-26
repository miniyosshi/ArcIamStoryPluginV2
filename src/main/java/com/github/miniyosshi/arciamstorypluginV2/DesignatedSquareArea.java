package com.github.miniyosshi.arciamstorypluginV2;

import org.bukkit.Location;
import org.bukkit.World;

public class DesignatedSquareArea extends DesignatedArea {
	
	private Location cornerA;
	private Location cornerB;
	
	DesignatedSquareArea(String name, World world, Location cornerA, Location cornerB) {
		super(name, world);
		this.cornerA = cornerA;
		this.cornerB = cornerB;
	}

	@Override
	public boolean contains(Location location) {
		boolean bX = ArcIamMath.rIsBetweenPandQ(location.getX(), cornerA.getX(), cornerB.getX());
		boolean bZ = ArcIamMath.rIsBetweenPandQ(location.getZ(), cornerA.getZ(), cornerB.getZ());
		
		return bX&&bZ;
	}
	
	

}
