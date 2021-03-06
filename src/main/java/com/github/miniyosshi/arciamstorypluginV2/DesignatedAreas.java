package com.github.miniyosshi.arciamstorypluginV2;

import java.util.Set;
import java.util.stream.Collectors;

import org.bukkit.Location;


public class DesignatedAreas extends MapDataRepository<DesignatedArea> {
	//Singleton because there are extensions although it has an instance field
	private static final DesignatedAreas INSTANCE = new DesignatedAreas();
	
	private DesignatedAreas() {}
	
	public static DesignatedAreas getInstance() {
		return INSTANCE;
	}
	
	public Set<DesignatedArea> getAllElementsBy(Location location) {
		return map.entrySet().stream().filter(entry -> entry.getValue().contains(location))
		.map(entry -> entry.getValue()).collect(Collectors.toSet());
		/*
		for (Element element : map.values()) {
			DesignatedArea da = (DesignatedArea) element;
			if(da.contains(location)) {
				return Optional.of(da);
			}
		}
		return Optional.empty();
		*/
	}
	
}
