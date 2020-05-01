package com.github.miniyosshi.arciamstorypluginV2;

import java.util.Optional;

import org.bukkit.Location;


public class DesignatedAreas extends MapDataRepository<DesignatedArea> {
	//Singleton because there are extensions although it has an instance field
	private static final DesignatedAreas INSTANCE = new DesignatedAreas();
	
	private DesignatedAreas() {}
	
	public static DesignatedAreas getInstance() {
		return INSTANCE;
	}
	
	public Optional<DesignatedArea> getElementBy(Location location) {
		for (MapElement element : map.values()) {
			DesignatedArea da = (DesignatedArea) element;
			if(da.contains(location)) {
				return Optional.of(da);
			}
		}
		return Optional.empty();
	}
}
