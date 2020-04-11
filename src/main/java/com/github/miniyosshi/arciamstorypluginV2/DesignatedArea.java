package com.github.miniyosshi.arciamstorypluginV2;

import org.bukkit.Location;

public abstract class DesignatedArea extends DesignatedPosition {
	
	public DesignatedArea(String name){
		this.name = name;
		DesignatedAreas das = DesignatedAreas.getInstance();
		das.add(this);
	}
	
	//jackson deserializeでデフォルトコンストラクタ必要?	
	
	public abstract Location getCenter();
	
	public abstract boolean contains(Location location);
	
	public abstract Location getRandomLocation();
}
