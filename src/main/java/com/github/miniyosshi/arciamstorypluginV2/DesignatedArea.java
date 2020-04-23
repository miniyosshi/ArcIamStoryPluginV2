package com.github.miniyosshi.arciamstorypluginV2;

import org.bukkit.Location;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class DesignatedArea extends DesignatedPosition {
	
	public DesignatedArea(String name){
		this.name = name;
		DesignatedAreas das = DesignatedAreas.getInstance();
		das.add(this);
	}
	
	//jackson deserializeでデフォルトコンストラクタ必要?	
	
	@JsonIgnore
	public abstract Location getCenter();
	
	public abstract boolean contains(Location location);
	
	@JsonIgnore
	public abstract Location getRandomLocation();
}
